package com.demo.roombooking.service.Impl;


import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.dao.OrderRepository;
import com.demo.roombooking.dao.RoomRepository;
import com.demo.roombooking.dao.UserRepository;
import com.demo.roombooking.entity.Order;
import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.dto.OrderDTO;
import com.demo.roombooking.entity.dto.OrderQueryDTO;
import com.demo.roombooking.entity.enums.OrderStatus;
import com.demo.roombooking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private RoomRepository roomRepository;

    @Autowired
    public void setRoomRepository(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * 添加订单
     */
    @Override
    public JsonResponse insertOrder(OrderDTO orderDTO) throws ParseException {



        Order order = new Order();

        // 设置经手人
        userRepository.findByUserName(orderDTO.getManagerName()).ifPresent(manager -> order.setManagerId(manager.getId()));
        // 预订房间号
        List<Room> rooms = new ArrayList<>();
        orderDTO.getRoomNos().forEach(roomNo -> roomRepository.findByRoomNo(roomNo).ifPresent(rooms::add));
        order.setRooms(rooms);
        // 下单用户
        userRepository.findByUserName(orderDTO.getUserName()).ifPresent(order::setUser);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // 预定入住时间、预定退房时间、备注
        order.setCheckInTime(simpleDateFormat.parse(orderDTO.getCheckInTime()));
        order.setCheckOutTime(simpleDateFormat.parse(orderDTO.getCheckOutTime()));
        order.setRemark(orderDTO.getRemark());

        return new JsonResponse(JsonResponse.SUCCESS, orderRepository.saveAndFlush(order));
    }

    /**
     * 订单回复
     * @param code
     * @param rate
     * @param remark
     * @return
     */
    @Override
    public Boolean operateRate(String code, Integer rate, String remark) {

        orderRepository.findOrderByCode(code).ifPresent(order -> {
            order.setRate(rate);
            order.setRemark(remark);
        });

        return true;
    }

    /**
     * 订单状态操作
     * @param code
     * @param status
     */
    @Override
    public void operateOrder(String code, OrderStatus status) {
        orderRepository.findOrderByCode(code).ifPresent(order -> {
            order.setStatus(status);
            orderRepository.saveAndFlush(order);
        });
    }

    /**
     * 订单查询
     */
    @Override
    public Page<Order> getOrder(OrderQueryDTO orderQueryDTO) {
        return orderRepository.findAll(orderQueryDTO.getQuerySpecification(), orderQueryDTO.getPageableBuilder().getPageable());
    }
}
