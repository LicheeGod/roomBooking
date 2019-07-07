package com.demo.roombooking.entity;

import com.demo.roombooking.entity.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_room")
@Data
@Builder
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
     * 可入住的人的数量
     */
    @Column(length = 2)
    private int people;
    /**
     * 客房名称
     */
    @Column(length = 20)
    private String name;

    /**
     * 房间照片(多张用分号隔开)
     **/
    @Lob
    @Column
    private List<String> roomImgUrl;

    /**
     * 客房介绍
     * */
    private String introduction;

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

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "room", fetch = FetchType.LAZY)
    private List<Order> orderList= new ArrayList<>();


}
