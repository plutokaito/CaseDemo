package com.kaitoshy.utils;

import sun.misc.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipUtil {

    /**
     * 将需要目录中的文件压缩成 zip
     * @param filePath 需要压缩的路径
     * @param zipFile 压缩后的路径
     * @return
     */
    public static void zip(String filePath, String zipFile) throws IOException {
        Path path = Paths.get(filePath);
        File file = path.toFile();

        // 如果路径不存在或者不是路径直接返回
        if (!file.exists() || !file.isDirectory()) {
            System.out.println("文件不存在或者不是目录");
            return;
        }

        List<File> allFiles = new LinkedList<>();
        getAllFile(file, allFiles);
        FileOutputStream outputStream = new FileOutputStream(zipFile);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        for (File f: allFiles) {
            byte[] buffer = new byte[2048];
            String name = f.getAbsolutePath().substring(file.getAbsolutePath().length() + 1);
            System.out.println(f.getAbsolutePath() + "=====" + name);
            // 设置 entry 节点
            ZipEntry zipEntry = new ZipEntry(name);
            zipOutputStream.putNextEntry(zipEntry);

            // 写入数据流
            FileInputStream fileInputStream = new FileInputStream(f);
            int read;
            while ((read = fileInputStream.read(buffer, 0, buffer.length)) > 0) {
                zipOutputStream.write(buffer, 0, read);
            }

            zipOutputStream.closeEntry();
            fileInputStream.close();
        }
    }

    /**
     * 递归获取对应的文件
     * @param file
     * @return
     */
    private static void getAllFile(File file, List<File> af) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (Objects.nonNull(files)) {
                for (File f: files) {
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
            zip("E:\\workspace\\tmp\\dir222", "E:\\workspace\\tmp\\a.zip");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }


}
