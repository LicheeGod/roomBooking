package com.demo.roombooking.service;


import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.entity.Order;
import com.demo.roombooking.entity.dto.OrderQueryDTO;
import org.springframework.data.domain.Page;


public interface OrderService {

    /**
     * 添加订单
     *
     * @param order 订单信息
     * @return JsonResponse
     */
    JsonResponse insertOrder(Order order);

    /**
     * 修改订单信息
     *
     * @param order 订单信息
     * @return Order
     */
    Order updateOrder(Order order);

    /**
     * 分页查询订单
     *
     * @param orderQueryDTO 订单查询分页构造器
     * @return Page<Order>
     */
    Page<Order> getOrder(OrderQueryDTO orderQueryDTO);
}
