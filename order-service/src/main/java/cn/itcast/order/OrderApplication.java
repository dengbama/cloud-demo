package cn.itcast.order;

import com.gem.clients.UserClient;
import com.gem.config.FrignConfiguraTion;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
@EnableFeignClients(defaultConfiguration = FrignConfiguraTion.class,clients = {UserClient.class},basePackages = {})
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

//    @Bean
////    @LoadBalanced  //负载均衡
//    public RestTemplate restTemplate(){
//        RestTemplate restTemplate=new RestTemplate();
//        return  restTemplate;
//    }

}