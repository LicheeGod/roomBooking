package com.demo.roombooking.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoomDTO {
    /**
     *房间号
     **/
    private String roomNo;
    /**
     *价格/晚
     **/
    private String price;
    /**
     * 可入住的人的最大数量
     */
    private Integer people;
    /**
     * 客房名称
     */
    private String name;
    /**
     * 房间照片
     **/
    private List<String> roomImgUrl;
    /**
     * 客房介绍
     * */
    private String introduction;
    /**
     *房间类型
     */
    private String type;
}
