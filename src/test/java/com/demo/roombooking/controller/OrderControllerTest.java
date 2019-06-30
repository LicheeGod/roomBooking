package com.demo.roombooking.controller;

import com.demo.roombooking.entity.dto.OrderDTO;
import com.demo.roombooking.entity.dto.OrderQueryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    private OrderController orderController;

    @Autowired
    private void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    @Test
    public void insertOrder() {
        orderController.insertOrder(OrderDTO.builder()
                .managerId(1L)
                .checkInTime(new Date())
                .checkOutTime(new Date())
                .state('0')
                .remark(null)
                .roomId(1L).build(), "aa");
    }

    @Test
    public void updateOrder() {
        orderController.updateOrder(OrderDTO.builder()
                .id(1L)
                .managerId(3L)
                .rate(3)
                .state('0')
                .remark(null).build());
    }

    /**
     * 报错 NullPointerExp，调试发现 pageableBuilder = null
     * 未解决
     */
    @Test
    public void getOrder() {
        System.out.println(orderController.getOrder(OrderQueryDTO.builder()
                .managerId(3L).build()));
    }
}