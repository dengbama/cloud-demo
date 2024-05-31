package cn.itcast.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class FileUtil {
    public static void main(String[] args) throws IOException {
        File directory = new File("G:\\zk\\gw\\prpall\\webapps\\prpall\\commonship"); // 替换为你的目录路径
        LocalDateTime dateTime = LocalDateTime.of(2024, 5, 27, 9, 0, 0);
        listFilesRecursively(directory,dateTime);

    }

    public static void listFilesRecursively(File dir,LocalDateTime dateTime) throws IOException {
        File[] files = dir.listFiles();
        // 转换为 Instant 以方便与文件的修改时间比较
        Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // 如果是目录，递归文件
                    listFilesRecursively(file,dateTime);
                } else {
                    Path path = file.toPath();
                    BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
                    // 获取文件的最后修改时间并转换为 Instant
                    Instant fileLastModified = attrs.lastModifiedTime().toInstant();
                    if (fileLastModified.isAfter(instant)) {
//                        // 如果是文件，打印文件路径
                        System.out.println(file.getAbsolutePath());
                    }
                }
            }
        }
    }
}
