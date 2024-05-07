package com.parch.combine.core.common.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public final class IOUtil {
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static byte[] SKIP_BYTE_BUFFER;
    private static int BYTE_ARRAY_MAX_OVERRIDE = -1;

    private IOUtil() {
    }

    public static void setByteArrayMaxOverride(int maxOverride) {
        BYTE_ARRAY_MAX_OVERRIDE = maxOverride;
    }


    private static void checkByteSizeLimit(int length) throws IOException {
        if (BYTE_ARRAY_MAX_OVERRIDE != -1 && length > BYTE_ARRAY_MAX_OVERRIDE) {
            throwRFE((long)length, BYTE_ARRAY_MAX_OVERRIDE);
        }

    }

    public static byte[] toByteArray(InputStream stream) throws IOException {
        return toByteArray(stream, 2147483647);
    }

    public static byte[] toByteArray(InputStream stream, int length) throws IOException {
        return toByteArray(stream, length, 2147483647);
    }

    public static byte[] toByteArray(InputStream stream, long length, int maxLength) throws IOException {
        if (length >= 0L && (long)maxLength >= 0L) {
            if (length > 2147483647L) {
                throw new IOException("Can't allocate an array > 2147483647");
            } else {
                if (length != 2147483647L || maxLength != 2147483647) {
                    checkLength(length, maxLength);
                }

                int len = Math.min((int)length, maxLength);
                ByteArrayOutputStream baos = new ByteArrayOutputStream(len == 2147483647 ? 4096 : len);
                byte[] buffer = new byte[4096];
                int totalBytes = 0;

                int readBytes;
                do {
                    readBytes = stream.read(buffer, 0, Math.min(buffer.length, len - totalBytes));
                    totalBytes += Math.max(readBytes, 0);
                    if (readBytes > 0) {
                        baos.write(buffer, 0, readBytes);
                    }

                    checkByteSizeLimit(readBytes);
                } while(totalBytes < len && readBytes > -1);

                if (maxLength != 2147483647 && totalBytes == maxLength) {
                    throw new IOException("MaxLength (" + maxLength + ") reached - stream seems to be invalid.");
                } else if (len != 2147483647 && totalBytes < len) {
                    throw new EOFException("unexpected EOF - expected len: " + len + " - actual len: " + totalBytes);
                } else {
                    return baos.toByteArray();
                }
            }
        } else {
            throw new IOException("Can't allocate an array of length < 0");
        }
    }

    private static void checkLength(long length, int maxLength) throws IOException {
        if (BYTE_ARRAY_MAX_OVERRIDE > 0) {
            if (length > (long)BYTE_ARRAY_MAX_OVERRIDE) {
                throwRFE(length, BYTE_ARRAY_MAX_OVERRIDE);
            }
        } else if (length > (long)maxLength) {
            throwRFE(length, maxLength);
        }

    }

    public static byte[] toByteArray(ByteBuffer buffer, int length) throws IOException {
        if (buffer.hasArray() && buffer.arrayOffset() == 0) {
            return buffer.array();
        } else {
            checkByteSizeLimit(length);
            byte[] data = new byte[length];
            buffer.get(data);
            return data;
        }
    }

    public static int readFully(InputStream in, byte[] b) throws IOException {
        return readFully(in, b, 0, b.length);
    }

    public static int readFully(InputStream in, byte[] b, int off, int len) throws IOException {
        int total = 0;

        do {
            int got = in.read(b, off + total, len - total);
            if (got < 0) {
                return total == 0 ? -1 : total;
            }

            total += got;
        } while(total != len);

        return total;
    }

    public static int readFully(ReadableByteChannel channel, ByteBuffer b) throws IOException {
        int total = 0;

        do {
            int got = channel.read(b);
            if (got < 0) {
                return total == 0 ? -1 : total;
            }

            total += got;
        } while(total != b.capacity() && b.position() != b.capacity());

        return total;
    }

    public static long copy(InputStream inp, OutputStream out) throws IOException {
        return copy(inp, out, -1L);
    }

    public static long copy(InputStream inp, OutputStream out, long limit) throws IOException {
        byte[] buff = new byte[4096];
        long totalCount = 0L;
        int readBytes = -1;

        do {
            int todoBytes = (int)(limit < 0L ? (long)buff.length : Math.min(limit - totalCount, (long)buff.length));
            if (todoBytes > 0) {
                readBytes = inp.read(buff, 0, todoBytes);
                if (readBytes > 0) {
                    out.write(buff, 0, readBytes);
                    totalCount += (long)readBytes;
                }
            }
        } while(readBytes >= 0 && (limit == -1L || totalCount < limit));

        return totalCount;
    }

    public static long copy(InputStream srcStream, File destFile) throws IOException {
        File destDirectory = destFile.getParentFile();
        if (!destDirectory.exists() && !destDirectory.mkdirs()) {
            throw new RuntimeException("Can't create destination directory: " + destDirectory);
        } else {
            OutputStream destStream = new FileOutputStream(destFile);
            Throwable var4 = null;

            long var5;
            try {
                var5 = copy(srcStream, (OutputStream)destStream);
            } catch (Throwable var15) {
                var4 = var15;
                throw var15;
            } finally {
                if (destStream != null) {
                    if (var4 != null) {
                        try {
                            destStream.close();
                        } catch (Throwable var14) {
                            var4.addSuppressed(var14);
                        }
                    } else {
                        destStream.close();
                    }
                }

            }

            return var5;
        }
    }

    public static long calculateChecksum(byte[] data) {
        Checksum sum = new CRC32();
        sum.update(data, 0, data.length);
        return sum.getValue();
    }

    public static long calculateChecksum(InputStream stream) throws IOException {
        Checksum sum = new CRC32();
        byte[] buf = new byte[4096];

        int count;
        while((count = stream.read(buf)) != -1) {
            if (count > 0) {
                sum.update(buf, 0, count);
            }
        }

        return sum.getValue();
    }

    public static void closeQuietly(Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

    public static long skipFully(InputStream input, long toSkip) throws IOException {
        if (toSkip < 0L) {
            throw new IllegalArgumentException("Skip count must be non-negative, actual: " + toSkip);
        } else if (toSkip == 0L) {
            return 0L;
        } else {
            if (SKIP_BYTE_BUFFER == null) {
                SKIP_BYTE_BUFFER = new byte[2048];
            }

            long remain;
            long n;
            for(remain = toSkip; remain > 0L; remain -= n) {
                n = (long)input.read(SKIP_BYTE_BUFFER, 0, (int)Math.min(remain, 2048L));
                if (n < 0L) {
                    break;
                }
            }

            return toSkip == remain ? -1L : toSkip - remain;
        }
    }

    public static byte[] safelyAllocate(long length, int maxLength) throws IOException {
        safelyAllocateCheck(length, maxLength);
        checkByteSizeLimit((int)length);
        return new byte[(int)length];
    }

    public static void safelyAllocateCheck(long length, int maxLength) throws IOException {
        if (length < 0L) {
            throw new IOException("Can't allocate an array of length < 0, but had " + length + " and " + maxLength);
        } else if (length > 2147483647L) {
            throw new IOException("Can't allocate an array > 2147483647");
        } else {
            checkLength(length, maxLength);
        }
    }

    public static int readByte(InputStream is) throws IOException {
        int b = is.read();
        if (b == -1) {
            throw new EOFException();
        } else {
            return b;
        }
    }

    private static void throwRFE(long length, int maxLength) throws IOException {
        throw new IOException("Tried to allocate an array of length " + length + ", but " + maxLength + " is the maximum for this record type.\nIf the file is not corrupt, please open an issue on bugzilla to request \nincreasing the maximum allowable size for this record type.\nAs a temporary workaround, consider setting a higher override value with IOUtils.setByteArrayMaxOverride()");
    }
}
