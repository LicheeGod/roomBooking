package com.demo.roombooking.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    /**
     * 账号*
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码*
     */
    private String password;
    /**
     * 用户头像
     */
    private String userImg;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 电子邮箱*
     */
    private String email;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 性别
     */
    private String gender;
    /**
     * 出生年月
     */
    private String Birthday;
    /**
     * 是否会员
     */
    private Boolean isVip;
    /**
     * 地址
     */
    private String address;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 身份证图
     */
    private String idCardImg;
    /**
     * 支付密码
     */
    private String paymentPassword;
    /**
     * 认证状态[屏蔽/实名/未实名/黑名单]
     */
    private String state;
    /**
     * 注册时间
     */
    private String registerTime;
}
