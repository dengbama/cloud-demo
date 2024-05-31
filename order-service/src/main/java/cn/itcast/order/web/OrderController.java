package cn.itcast.order.web;

import cn.itcast.order.pojo.Order;
import cn.itcast.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

    @GetMapping("{orderId}")
    @ResponseBody
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId,@RequestHeader(value = "Truth",required = false)String head) {
        System.out.println("head--------------"+head);
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }
}
