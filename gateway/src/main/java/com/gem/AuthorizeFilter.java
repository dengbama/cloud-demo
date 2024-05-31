package com.gem;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author：dengwenxin-wb
 * @Project：cloud-demo
 * @name：AuthorizeFilter
 * @Date：2024/5/8 9:19
 */
//全局过滤器
@Component
@Order(-1)//设置过滤器执行顺序，值越小越高
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求
        ServerHttpRequest request = exchange.getRequest();
        //获取请求参数
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        //获取具体某一个
        String authorization = queryParams.getFirst("authorization");//取出第一个匹配的
        //判断条件
        if ("admin".equals(authorization)){
            //放行
            return chain.filter(exchange);
        }
        //拦截
        //设置状态码
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);//未登录
        return response.setComplete();
    }
}
