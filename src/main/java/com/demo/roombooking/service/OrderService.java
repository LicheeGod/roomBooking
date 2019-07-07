package com.demo.roombooking.service;


import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.entity.Order;
import com.demo.roombooking.entity.dto.OrderDTO;
import com.demo.roombooking.entity.dto.OrderQueryDTO;
import com.demo.roombooking.entity.enums.OrderStatus;
import org.springframework.data.domain.Page;

import java.text.ParseException;


public interface OrderService {

    /**
     * 添加订单
     * @param orderDTO
     * @return
     */
    JsonResponse insertOrder(OrderDTO orderDTO) throws ParseException;

    /**
     * 订单星级等回复
     * @param code
     * @param rate
     * @param remark
     * @return
     */
    Boolean operateRate(String code, Integer rate, String remark);

    /**
     * 订单状态操作
     * @param code
     * @param status
     */
    void operateOrder(String code, OrderStatus status);

    /**
     * 分页查询订单
     *
     * @param orderQueryDTO 订单查询分页构造器
     * @return Page<Order>
     */
    Page<Order> getOrder(OrderQueryDTO orderQueryDTO);
}
