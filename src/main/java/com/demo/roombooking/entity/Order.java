package com.demo.roombooking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//主键

    @Column(name = "orderNo", unique = true, nullable = false, length = 20)
    private int orderNo;//订单编号

    private int roomId;//房间id

    private int managerId;//经手人Id

    @Column(name="create_time")
    private Date createTime;//订单生成时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInTime;//预定入住时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutTime;//预定退房时间

    private char state;//0-未入住 1-已入住 2-完成 3-取消

    private int rate;//用户的评价1-5

    private String remark;//备注

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "orderList")
    private List<Room> roomList;

    @PrePersist
//    @PreUpdate
    private void prePersist() {
        this.createTime = new Date();
    }

}
