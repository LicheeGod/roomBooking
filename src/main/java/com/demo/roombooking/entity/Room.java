package com.demo.roombooking.entity;

import com.demo.roombooking.entity.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_room")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    /**
     * 主键
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
    * 房间号
    **/
    @Column(length = 20)
    private String roomNo;
    /**
    * 价格/晚
    **/
    private String price;
    /**
     * 可入住的人的最大数量
     */
    @Column(length = 5)
    private Integer people;
    /**
     * 客房名称
     */
    @Column(length = 20)
    private String name;
    /**
     * 房间照片(多张用分号隔开)
     **/
    @Lob
    private List<String> roomImgUrl;
    /**
     * 客房介绍
     * */
    @Column(length = 50)
    private String introduction;
    /**
    * 房间类型
    */
    @Enumerated(EnumType.STRING)
    private RoomType type;
    /**
     * 屏蔽标志
     */
    @Column(length = 1)
    private Boolean isHide;
    /**
     * 订单关联
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "rooms", targetEntity = Order.class)
    private List<Order> orders;

    @PrePersist
    private void prePersist() {
        this.isHide = false;
    }
}
