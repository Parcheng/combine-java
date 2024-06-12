package com.parch.combine.core.common.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class PackageScanUtil {

    private PackageScanUtil() {}

    public static Set<Class<?>> scan(String packageName) {
        Set<Class<?>> classes = new HashSet<>();
        try {
            String paths = packageName.replaceAll("\\.", "/");
            Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(paths);
            while (resources.hasMoreElements()){
                URL resource = resources.nextElement();
                String filePath = URLDecoder.decode(resource.getFile(), String.valueOf(StandardCharsets.UTF_8));
                if (filePath.contains(".jar!/")) {
                    findClassesInPackageByJar(packageName, filePath, classes);
                } else {
                    findClassesInPackageByFile(packageName, filePath, classes);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return classes;
    }

    private static void findClassesInPackageByJar(String packageName, String filePath, Set<Class<?>> classes) throws ClassNotFoundException, IOException {
        String[] jarPathArr = filePath.split("!/");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL(jarPathArr[0])});

        String jarFilePath;
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            jarFilePath = jarPathArr[0].replace("file:/", "");
        } else {
            jarFilePath = jarPathArr[0].replace("file:", "");
        }

        try (JarFile jarFile = new JarFile(jarFilePath)) {
            List<String> classNames = jarFile.stream().filter(entry -> entry.getName().startsWith(jarPathArr[1]) && entry.getName().endsWith(".class"))
                    .map(entry -> entry.getName().replace("/", ".").replace(".class", "")).collect(Collectors.toList());
            for (String className : classNames) {
                Class<?> clazz = urlClassLoader.loadClass(className);
                classes.add(clazz);
            }
        }
    }

    private static void findClassesInPackageByFile(String packageName, String filePath, Set<Class<?>> classes) throws ClassNotFoundException {
        File dir = new File(filePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }

        for (File file : files) {
            if (file.isDirectory()) {
                findClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), classes);
            } else if (file.getName().endsWith(".class")) {
                String className = file.getName().substring(0, file.getName().length() - 6);
                Class<?> clazz = Class.forName(packageName +  '.' + className);
                classes.add(clazz);
            }
        }
    }
}
