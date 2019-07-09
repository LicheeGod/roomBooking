package com.demo.roombooking.controller;


import com.demo.roombooking.common.resp.JsonResponse;
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
     * @param orderDTO
     * @return
     */
    @RequestMapping("/order/operateRate")
    public JsonResponse updateOrder(@RequestBody OrderDTO orderDTO) {

        return new JsonResponse(JsonResponse.SUCCESS, orderService.operateRate(orderDTO.getCode(), orderDTO.getRate(), orderDTO.getRemark()));
    }

    /**
     * 修改订单状态
     * @param orderDTO
     * @return
     */
    @RequestMapping("/order/operateStatus")
    public JsonResponse operateOrder(@RequestBody OrderDTO orderDTO) {

        orderService.operateOrder(orderDTO.getCode(), OrderStatus.valueOf(orderDTO.getStatus()));

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
