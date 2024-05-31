package cn.itcast.user.web;

import cn.itcast.user.Config;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Slf4j
@RestController
@RequestMapping("/user")
//@RefreshScope
public class UserController {

    public static void main(String[] args) throws IOException {
        File directory = new File("G:\\zk\\gw\\prpall\\component\\com\\sinosoft"); // 替换为你的目录路径
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

    @Autowired
    private UserService userService;

//    @Value("${}")//获取配置文件的值
//    private String value;

    @Autowired
    private Config config;
    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,@RequestHeader(value = "Truth",required = false) String truth) {
        System.out.println("truth--------------------"+truth);
        return userService.queryById(id);
    }

    @RequestMapping("/getvalue")
    @ResponseBody
    public String getValue(){
        return JSONObject.toJSONString(config);
    }

    @RequestMapping("/getvalue1")
    public String getValue1(){
        return JSONObject.toJSONString(config);
    }
}
