//package com.parch.combine.components.file.opt.compress.hanlder;
//
//
//import com.github.junrar.Archive;
//import net.sf.sevenzipjbinding.IInArchive;
//import net.sf.sevenzipjbinding.SevenZip;
//import net.sf.sevenzipjbinding.impl.RandomAccessFileInStream;
//import org.apache.commons.compress.archivers.ArchiveOutputStream;
//import org.apache.commons.compress.archivers.ArchiveStreamFactory;
//
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
///**
// * rar处理器
// */
//public class RarHandler {
//
//    public static boolean rar(String source, String target) {
//        File sourceFile = new File(sourcePath);
//        if (!sourceFile.exists()) {
//            throw new FileNotFoundException("Source file/directory does not exist.");
//        }
//
//        try (OutputStream destStream = new FileOutputStream(destPath)) {
//            try (ArchiveOutputStream archiveStream = new ArchiveStreamFactory()
//                    .createArchiveOutputStream(ArchiveStreamFactory.RAR, destStream)) {
//
//                archiveStream.setComment("Created by RarCompressor");
//
//                if (sourceFile.isDirectory()) {
//                    compressDirectory(sourceFile, archiveStream, "");
//                } else {
//                    compressFile(sourceFile, archiveStream, "");
//                }
//            }
//        }
//
//        return true;
//    }
//
//    private static void compressDirectory(File sourceDir, Archive archive, String parentPath) throws IOException, RarException {
//        File[] files = sourceDir.listFiles();
//        if (files != null) {
//            for (File file : files) {
//                if (file.isDirectory()) {
//                    String subDirPath = parentPath + file.getName() + "/";
//                    archive.addFolder(subDirPath);
//                    compressDirectory(file, archive, subDirPath);
//                } else {
//                    compressFile(file, archive, parentPath);
//                }
//            }
//        }
//    }
//
//    private static void compressFile(File sourceFile, Archive archive, String parentPath) throws IOException, RarException {
//        FileHeader fileHeader = new FileHeader();
//        fileHeader.setFileName(parentPath + sourceFile.getName());
//        fileHeader.setUnpSize(sourceFile.length());
//        fileHeader.setTime(System.currentTimeMillis());
//        CRC32 crc32 = new CRC32();
//
//        try (InputStream is = new FileInputStream(sourceFile)) {
//            byte[] buffer = new byte[8096];
//            int bytesRead;
//            while ((bytesRead = is.read(buffer)) != -1) {
//                crc32.update(buffer, 0, bytesRead);
//            }
//
//            fileHeader.setCrc(crc32.getValue());
//
//            archive.addFile(fileHeader, new InputStreamDataSource(is, sourceFile.length()));
//        }
//    }
//
//    public static void main(String[] args) {
//        try {
//            compressToRar("/path/to/source", "/path/to/dest/archive.rar");
//        } catch (IOException | RarException e) {
//            e.printStackTrace();
//        }
//    }
//
//    static class InputStreamDataSource implements com.github.junrar.io.DataSource {
//        private final InputStream inputStream;
//        private final long size;
//
//        public InputStreamDataSource(InputStream inputStream, long size) {
//            this.inputStream = inputStream;
//            this.size = size;
//        }
//
//        @Override
//        public long getSize() {
//            return size;
//        }
//
//        @Override
//        public int read(byte[] buffer) throws IOException {
//            return inputStream.read(buffer);
//        }
//
//        @Override
//        public void close() throws IOException {
//            inputStream.close();
//        }
//    }
//
//    public static boolean unrar(String source, String target) {
//        Path srcPath = Paths.get(source);
//        if (!Files.exists(srcPath) || Files.isDirectory(srcPath)) {
//            throw new IllegalArgumentException(source + " is not a valid file");
//        }
//
//        File destFile = new File(target);
//        if (!destFile.exists() || !destFile.isDirectory()) {
//            if (!destFile.mkdirs()) {
//                throw new IOException("Failed to create destination directory: " + target);
//            }
//        }
//
//        try (RandomAccessFile randomAccessFile = new RandomAccessFile(source, "r");
//             IInArchive inArchive = SevenZip.openInArchive(null, new RandomAccessFileInStream(randomAccessFile))) {
//
//            inArchive.extract(null, false, new IInArchive.ExtractCallback() {
//                @Override
//                public IInArchive.AskMode onExtractAsk(IArchiveEntry archiveEntry) throws IOException {
//                    return IInArchive.AskMode.EXTRACT;
//                }
//
//                @Override
//                public void setOperationResult(IInArchive inArchive, IInArchive.ExtractOperationResult extractOperationResult) throws IOException {
//                    // do nothing
//                }
//
//                @Override
//                public void prepareOperation(IInArchive inArchive, IArchiveEntry archiveEntry) throws IOException {
//                    // do nothing
//                }
//
//                @Override
//                public void setCurrentFile(IInArchive inArchive, IArchiveEntry archiveEntry) throws IOException {
//                    Path outputPath = Paths.get(destDir, archiveEntry.getUnicodeName());
//                    File outputFile = outputPath.toFile();
//
//                    if (archiveEntry.isDirectory()) {
//                        if (!outputFile.exists()) {
//                            if (!outputFile.mkdirs()) {
//                                throw new IOException("Failed to create directory: " + outputFile.getAbsolutePath());
//                            }
//                        }
//                    } else {
//                        if (!outputFile.getParentFile().exists()) {
//                            if (!outputFile.getParentFile().mkdirs()) {
//                                throw new IOException("Failed to create directory: " + outputFile.getParentFile().getAbsolutePath());
//                            }
//                        }
//
//                        try (OutputStream outputStream = Files.newOutputStream(outputPath)) {
//                            byte[] buffer = new byte[1024];
//                            int bytesRead = 0;
//                            try (InputStream inputStream = inArchive.getInputStream(archiveEntry)) {
//                                while ((bytesRead = inputStream.read(buffer)) != -1) {
//                                    outputStream.write(buffer, 0, bytesRead);
//                                }
//                            }
//                        }
//                    }
//                }
//            });
//        } catch (Exception e) {
//
//        }
//
//        return true;
//    }
//}
