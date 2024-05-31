package com.gem.clients;

import com.gem.config.FrignConfiguraTion;
import com.gem.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author：dengwenxin-wb
 * @Project：cloud-demo
 * @name：UserClient
 * @Date：2024/4/7 17:21
 */
@FeignClient(value = "userService"
//        ,configuration = FrignConfiguraTion.class
)
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable Long id);

}
