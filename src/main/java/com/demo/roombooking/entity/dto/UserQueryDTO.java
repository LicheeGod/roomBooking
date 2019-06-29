package com.demo.roombooking.entity.dto;

import com.demo.roombooking.common.util.PageableBuilder;
import com.demo.roombooking.entity.User;
import com.demo.roombooking.entity.enums.Sex;
import com.demo.roombooking.entity.enums.UserState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryDTO {
    /**
     * 账号
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 性别
     */
    private Sex gender;
    /**
     * 是否会员
     */
    private Boolean isVip;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 认证状态[实名/未实名/黑名单]
     */
    private UserState state;
    /**
     * 注册时间段
     */
    private List<String> registerTimes;

    @JsonIgnore
    public Specification<User> getQuerySpecification() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return (Specification<User>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(this.getUserName())) {
                predicates.add(criteriaBuilder.like(root.get("userName"), "%" + this.getUserName() + "%"));
            }
            if (!StringUtils.isEmpty(this.getNickName())) {
                predicates.add(criteriaBuilder.like(root.get("nickName"), "%" + this.getNickName() + "%"));
            }
            if (!StringUtils.isEmpty(this.getRealName())) {
                predicates.add(criteriaBuilder.like(root.get("realName"), "%" + this.getRealName() + "%"));
            }
            if (!StringUtils.isEmpty(this.getPhone())) {
                predicates.add(criteriaBuilder.equal(root.get("phone"), this.getPhone()));
            }
            if (!StringUtils.isEmpty(this.getGender())) {
                predicates.add(criteriaBuilder.equal(root.get("gender"), this.getGender()));
            }
            if (!StringUtils.isEmpty(this.getIsVip())) {
                predicates.add(criteriaBuilder.equal(root.get("isVip"), this.getIsVip()));
            }
            if (!StringUtils.isEmpty(this.getIdCard())) {
                predicates.add(criteriaBuilder.equal(root.get("idCard"), this.getIdCard()));
            }
            // 按状态查询用户时，若状态值为空，则排除被删除[屏蔽]的用户
            if (StringUtils.isEmpty(this.getState())) {
                predicates.add(criteriaBuilder.notEqual(root.get("state"), UserState.HAS_DELETE));
            } else {
                predicates.add(criteriaBuilder.equal(root.get("state"), this.getState()));
            }
            if (!StringUtils.isEmpty(this.getRegisterTimes().get(0)) && !StringUtils.isEmpty(this.getRegisterTimes().get(1))) {
                try {
                    predicates.add(criteriaBuilder.between(root.get("registerTime"),
                            simpleDateFormat.parse(this.getRegisterTimes().get(0)),
                            simpleDateFormat.parse(this.getRegisterTimes().get(1))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            Predicate[] p = new Predicate[predicates.size()];
            return query.where(predicates.toArray(p)).distinct(true).getRestriction();
        };
    }

    @JsonProperty("pageBuilder")
    private PageableBuilder pageableBuilder;
}
