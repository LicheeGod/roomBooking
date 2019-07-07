package com.demo.roombooking.entity.dto;


import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.sql.rowset.Predicate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class RoomDTO {
    /**
    * 房间号
    **/
    private String roomNo;
    /**
     *价格
     **/
    private Double price;
    /**
     * 可入住的人的数量
     */
    private int people;
    /**
     * 客房名称
     */
    private String name;
    /**
     *房间类型
     */
    private RoomType type;
    /**
     * 房间照片(多张用分号隔开)
     **/
    private List<String> roomImgUrl;

    /**
     * 房间介绍
     * */
    private String introduction;
    /**
     *房间是否为空
     */
    private int state;//0为空，1非空


}
