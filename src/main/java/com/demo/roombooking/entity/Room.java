package com.demo.roombooking.entity;

import com.demo.roombooking.entity.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_room")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    /**
     *主键
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
    *房间号
    **/
    @Column(length = 6)
    private String roomNo;
    /**
    *价格
    **/
    private Double price;
    /**
     * 床的数量
     */
    @Column(length = 1)
    private int bed;
    /**
     * 客房名称
     */
    @Column(length = 20)
    private String name;//
    /**
    *房间类型
    */
    @Enumerated(EnumType.STRING)
    private RoomType type;
    /**
     *房间是否为空
     */
    private int state;//0为空，1非空
    /**
     * 删除标志
     */
    @Column(length = 1)
    private int isDel;//删除标志 1删了/0

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<Order>();
}
