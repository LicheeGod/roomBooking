package com.demo.roombooking.controller;


import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.entity.Order;
import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.User;
import com.demo.roombooking.entity.dto.OrderDTO;
import com.demo.roombooking.entity.dto.OrderQueryDTO;
import com.demo.roombooking.service.Impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@Transactional
public class OrderController {

    private OrderServiceImpl orderService;

    @Autowired
    private void setOrderService(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    /**
     * 添加订单
     *
     * @param orderDTO 订单基本信息
     * @return JsonResponse
     */
    @RequestMapping("/order/insert")
    public JsonResponse insertOrder(@RequestBody OrderDTO orderDTO,@RequestParam("userName") String userName) {
        User user=new User();
        user.setUserName(userName);
        Room room = new Room();
        room.setId(orderDTO.getRoomId());
        return orderService.insertOrder(Order.builder()
                .user(user)
                .managerId(orderDTO.getManagerId())
                .checkInTime(orderDTO.getCheckInTime())
                .checkOutTime(orderDTO.getCheckOutTime())
                .state(orderDTO.getState())
                .remark(orderDTO.getRemark())
                .room(room).build());
    }

    /**
     * 修改订单基本信息
     *
     * @param orderDTO 订单基本信息
     * @return JsonResponse
     */
    @RequestMapping("/order/updateInfo")
    public JsonResponse updateOrder(@RequestBody OrderDTO orderDTO) {
        return new JsonResponse(JsonResponse.SUCCESS,
                orderService.updateOrder(
                        Order.builder()
                                .id(orderDTO.getId())
                                .managerId(orderDTO.getManagerId())
                                .state(orderDTO.getState())
                                .rate(orderDTO.getRate())
                                .remark(orderDTO.getRemark()).build()));
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
