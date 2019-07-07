package com.demo.roombooking.entity;

import com.demo.roombooking.entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "t_order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    /**
     * 生成32位随机字符串的主键
     */
    @Id
    @GenericGenerator(name = "user-uuid", strategy = "uuid")
    @GeneratedValue(generator = "user-uuid")
    @Column(name = "code", length = 32)
    private String code;
    /**
     * 经手人
     */
    private Long managerId;
    /**
     * 订单生成时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 预定入住时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkInTime;
    /**
     * 预定退房时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkOutTime;
    /**
     * 订单状态：未入住、入住、完成、取消
     */
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    /**
     * 用户的评价星级1-5
     */
    private Integer rate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 级联关联用户
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private User user;
    /**
     * 级联关联客房
     */
    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "t_orders_rooms", joinColumns = @JoinColumn(name = "order_code", referencedColumnName = "code"), inverseJoinColumns = @JoinColumn(name = "room_id", referencedColumnName = "id"))
    private List<Room> rooms;

    @PrePersist
    private void prePersist() {
        this.createTime = new Date();
        this.status = OrderStatus.UNENTER;
    }

}
