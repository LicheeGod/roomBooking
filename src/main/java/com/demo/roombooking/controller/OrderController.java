package com.demo.roombooking.controller;


import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.entity.Order;
import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.User;
import com.demo.roombooking.entity.dto.OrderDTO;
import com.demo.roombooking.entity.dto.OrderQueryDTO;
import com.demo.roombooking.entity.enums.OrderStatus;
import com.demo.roombooking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@Transactional
public class OrderController {

    private OrderService orderService;

    @Autowired
    private void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 添加订单
     *
     * @param orderDTO 订单基本信息
     * @return JsonResponse
     */
    @RequestMapping("/order/insert")
    public JsonResponse insertOrder(@RequestBody OrderDTO orderDTO) throws ParseException {

        return new JsonResponse(JsonResponse.SUCCESS, orderService.insertOrder(orderDTO));

    }

    /**
     * 订单星级等回复
     * @param code
     * @param rate
     * @param remark
     * @return
     */
    @RequestMapping("/order/updateInfo")
    public JsonResponse updateOrder(@RequestParam("code") String code,
                                    @RequestParam("rate") Integer rate,
                                    @RequestParam("remark") String remark) {
        return new JsonResponse(JsonResponse.SUCCESS, orderService.operateRate(code, rate, remark));
    }

    /**
     * 修改订单状态
     * @param code
     * @param status
     * @return
     */
    @RequestMapping("/order/operate")
    public JsonResponse operateOrder(@RequestParam("code") String code,
                                     @RequestParam("status") String status) {

        orderService.operateOrder(code, OrderStatus.valueOf(status));

        return new JsonResponse(JsonResponse.SUCCESS);
    }

    /**
     * 分页查询订单
     *
     * @param orderQueryDTO 订单分页查询构造器
     * @return JsonResponse
     */
    @RequestMapping("/order/get")
    public JsonResponse getOrder(@RequestBody OrderQueryDTO orderQueryDTO) {
        return new JsonResponse(JsonResponse.SUCCESS, orderService.getOrder(orderQueryDTO));
    }
}
