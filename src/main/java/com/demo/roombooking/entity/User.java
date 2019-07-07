package com.demo.roombooking.entity;

import com.demo.roombooking.entity.enums.Sex;
import com.demo.roombooking.entity.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private UserStatus state;
    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registerTime;
    /**
     * 上次登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @OneToOne(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Privilege privilege;
    /**
     * 关联订单
     */
    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    public User(String userName, String password, Boolean isVip, String nickName, Privilege privilege) {
        this.userName = userName;
        this.password = password;
        this.isVip = isVip;
        this.nickName = nickName;
        this.privilege = privilege;
    }

    @PrePersist
    private void perPersist() {
        if (this.nickName == null) {
            this.setNickName("游客");
        }
        this.setUserImg("/");
        this.setGender(Sex.HIDE);
        if (this.isVip == null) {
            this.setIsVip(false);
        }
        this.setState(UserStatus.NOTPASS_VERIFIED);
        this.setRegisterTime(new Date());
    }

    @PreUpdate
    private void preUpdate() {
        this.setUpdateTime(new Date());
    }



}
