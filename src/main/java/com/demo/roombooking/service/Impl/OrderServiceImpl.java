package com.demo.roombooking.service.Impl;


import com.demo.roombooking.common.exception.BusinessException;
import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.dao.OrderRepository;
import com.demo.roombooking.dao.RoomRepository;
import com.demo.roombooking.dao.UserRepository;
import com.demo.roombooking.entity.Order;
import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.User;
import com.demo.roombooking.entity.dto.OrderQueryDTO;
import com.demo.roombooking.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
    public JsonResponse insertOrder(Order order) {
        User user=userRepository.findByUserName(order.getUser().getUserName());
        order.setUser(user);
        Room room = roomRepository.getOne(order.getRoom().getId());
        order.setRoom(room);
        return new JsonResponse(JsonResponse.SUCCESS, orderRepository.saveAndFlush(order));
    }

    /**
     * 修改基本信息
     * 订单能修改的基本信息有: 经手人ID、状态、评价、备注
     */
    @Override
    public Order updateOrder(Order order) {

        Order checkOrder = orderRepository.getOne(order.getId());

        if (checkOrder == null) {
            throw new BusinessException("该订单不存在");
        }

        checkOrder.setManagerId(order.getManagerId());
        checkOrder.setState(order.getState());
        checkOrder.setRate(order.getRate());
        checkOrder.setRemark(order.getRemark());

        return orderRepository.saveAndFlush(checkOrder);
    }

    /**
     * 订单查询
     */
    @Override
    public Page<Order> getOrder(OrderQueryDTO orderQueryDTO) {
        return orderRepository.findAll(orderQueryDTO.getQuerySpecification(), orderQueryDTO.getPageableBuilder().getPageable());
    }
}
