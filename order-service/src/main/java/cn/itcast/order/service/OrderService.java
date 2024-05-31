package cn.itcast.order.service;


import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.gem.clients.UserClient;
import com.gem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.从fegin远程调用
        User user = userClient.findById(order.getUserId());
        //封装user到order
        order.setUser(user);
        //4.返回
        return order;
    }

//    @Autowired
//    private RestTemplate restTemplate;

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //2.利用resttemplate发送http请求查询用户
//        //url路径
//        String url="http://userService/user/"+order.getId();
//        //3.发送http请求，实现远程调用
//        User user = restTemplate.getForObject(url, User.class);
//        //封装user到order
//        order.setUser(user);
        // 4.返回
//        return null;
//    }
}
