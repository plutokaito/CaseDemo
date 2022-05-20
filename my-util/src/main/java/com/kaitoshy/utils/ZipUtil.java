package com.kaitoshy.utils;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    /**
     * 将需要目录中的文件压缩成 zip
     *
     * @param filePath 需要压缩的路径
     * @param zipFile  压缩后的路径
     * @return
     */
    public static void zip(String filePath, String zipFile) throws IOException {
        File file = new File(filePath);

        // 如果路径不存在或者不是路径直接返回
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("文件不存在或者不是目录");
            return;
        }

        List<File> allFiles = new ArrayList<>();
        getAllFile(file, allFiles);

        try (
                // 文件输出流
                FileOutputStream outputStream = new FileOutputStream(zipFile);
                // zip 包输出流
                ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream)) {
            for (File f : allFiles) {
                byte[] buffer = new byte[2048];
                String name = f.getAbsolutePath().substring(file.getAbsolutePath().length() + 1);
                System.out.println(f.getAbsolutePath() + "=====" + name);
                // 设置 entry 节点
                ZipEntry zipEntry = new ZipEntry(name);
                zipOutputStream.putNextEntry(zipEntry);

                // 写入数据流
                try (FileInputStream fileInputStream = new FileInputStream(f)) {
                    int read;
                    while ((read = fileInputStream.read(buffer, 0, buffer.length)) > 0) {
                        zipOutputStream.write(buffer, 0, read);
                    }
                }

                zipOutputStream.closeEntry();
            }
        }
    }


    public static void unzip(String zipFile, String decompressPath) throws IOException {
        // 这里可以优化，目前只支持一层的外部路径
        Path path = Paths.get(decompressPath);
        File decompressPathFile = path.toFile();
        if (!decompressPathFile.exists()) {
            decompressPathFile.mkdirs();
        }

        try (
        FileInputStream fileInputStream = new FileInputStream(zipFile);
        ZipInputStream zipInputStream = new ZipInputStream(fileInputStream)) {

            ZipEntry zipEntry;
            byte[] buffer = new byte[2048];
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                Path formatPath = path.resolve(zipEntry.getName());
                if (zipEntry.getName().contains("/")) {
                    mkdirAllDir(zipEntry.getName(), decompressPath);
                }
                try (
                        // 文件输出流
                        FileOutputStream fos = new FileOutputStream(formatPath.toFile());
                        // 内容输出流
                        BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length)) {
                    int read;
                    if ((read = zipInputStream.read(buffer, 0, buffer.length)) > 0) {
                        bos.write(buffer, 0, read);
                    }
                }
            }
        }

    }

    private static void mkdirAllDir(String name, String decompressPath) {
        String[] paths = name.split("/");
        StringBuilder allPath = new StringBuilder(decompressPath + "/");
        for (int i = 0; i < paths.length - 1; i++) {
            allPath.append(paths[0]);
            if (!new File(allPath.toString()).mkdirs()) {
                System.out.println("创建文件失败！！");
                return;
            }
        }
    }

    /**
     * 递归获取对应的文件
     *
     * @param file
     * @return
     */
    private static void getAllFile(File file, List<File> af) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (Objects.nonNull(files)) {
                for (File f : files) {
                    getAllFile(f, af);
                }
            }
        }

        if (file.isFile()) {
            af.add(file);
        }
    }


    public static void main(String[] args) {
        try {
            zip("tmp/dir222", "tmp/b.zip");
            unzip("tmp/b.zip", "tmp/dddd444");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }


}
