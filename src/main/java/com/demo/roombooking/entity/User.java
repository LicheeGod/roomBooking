package com.demo.roombooking.entity;

import com.demo.roombooking.entity.enums.Sex;
import com.demo.roombooking.entity.enums.UserState;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 账号
     */
    @Column(length = 20)
    private String userName;
    /**
     * 昵称
     */
    @Column(length = 20)
    private String nickName;
    /**
     * 密码
     */
    @Column(length = 36)
    private String password;
    /**
     * 用户头像
     */
    private String userImg;
    /**
     * 真实姓名
     */
    @Column(length = 20)
    private String realName;
    /**
     * 电子邮箱
     */
    @Column(length = 20)
    private String email;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 性别
     */
    @Enumerated(EnumType.STRING)
    private Sex gender;
    /**
     * 出生年月
     */
    private String birthday;
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
    private String paymentPassword;//?
    /**
     * 认证状态[实名/未实名/黑名单]
     */
    private UserState state;
    /**
     * 注册时间
     */
    private Date registerTime;
    /**
     * 上次登陆时间
     */
    private Date updateTime;

    @PrePersist
    private void perPersist() {
        this.setNickName("游客");
        this.setUserImg("/");
        this.setGender(Sex.HIDE);
        this.setIsVip(false);
        this.setState(UserState.NOTPASS_VERIFIED);
        this.setRegisterTime(new Date());
    }

    @PreUpdate
    private void preUpdate() {
        this.setUpdateTime(new Date());
    }

    /**
     * 关联订单
     */
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orderList= new ArrayList<Order>();


}
