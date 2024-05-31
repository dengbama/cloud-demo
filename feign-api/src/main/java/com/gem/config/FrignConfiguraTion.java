package com.gem.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Author：dengwenxin-wb
 * @Project：cloud-demo
 * @name：FrignConfiguraTion
 * @Date：2024/4/8 10:39
 */
public class FrignConfiguraTion {

    @Bean
    public Logger.Level feignLogLavel(){
        return Logger.Level.BASIC;
    }

}
