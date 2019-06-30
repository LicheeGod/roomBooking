package com.demo.roombooking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//主键

    /*@Column(name = "orderNo", unique = true, nullable = false, length = 20)
    private String orderNo;//订单编号*/

    private Long managerId;//经手人Id

    @ManyToOne()
    private Room room;//客房

    @Column(name = "create_time")
    private Date createTime;//订单生成时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkInTime;//预定入住时间

    @Temporal(TemporalType.TIMESTAMP)
    private Date checkOutTime;//预定退房时间

    private char state;//0-未入住 1-已入住 2-完成 3-取消

    @Column(nullable = true)
    private int rate;//用户的评价1-5

    private String remark;//备注

    @ManyToOne()
    private User user;

    @PrePersist
//    @PreUpdate
    private void prePersist() {
        this.createTime = new Date();
    }

}
