package com.demo.roombooking.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    /**
     * 生成32位随机字符串的主键
     */
    private String code;
    /**
     * 经手人用户名
     */
    private String managerName;
    /**
     * 预定入住时间
     */
    private String checkInTime;
    /**
     * 预定退房时间
     */
    private String checkOutTime;
    /**
     * 用户的评价星级1-5
     */
    private Integer rate;
    /**
     * 备注
     */
    private String remark;
    /**
     * 下单的用户名
     */
    private String userName;
    /**
     * 房间号
     */
    private List<String> roomNos;
}
