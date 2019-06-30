package com.demo.roombooking.entity.dto;

import com.demo.roombooking.common.util.PageableBuilder;
import com.demo.roombooking.entity.Order;
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
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderQueryDTO {
    private Long managerId;
    private List<String> createTime;
    private List<String> checkInTime;
    private List<String> checkOutTime;
    private char state;
    private int rate;
    private String remark;
    private Long roomId;

    @JsonIgnore
    public Specification<Order> getQuerySpecification() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return (Specification<Order>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(this.getManagerId())) {
                predicates.add(criteriaBuilder.like(root.get("managerId"), "%" + this.getManagerId() + "%"));
            }
            if (!StringUtils.isEmpty(this.getRemark())) {
                predicates.add(criteriaBuilder.like(root.get("remark"), "%" + this.getRemark() + "%"));
            }
            if (!StringUtils.isEmpty(this.getRoomId())) {
                predicates.add(criteriaBuilder.like(root.get("roomId"), "%" + this.getRoomId() + "%"));
            }
            if (!StringUtils.isEmpty(this.getState())) {
                predicates.add(criteriaBuilder.equal(root.get("state"), this.getState()));
            }
            if (!StringUtils.isEmpty(this.getRate())) {
                predicates.add(criteriaBuilder.equal(root.get("rate"), this.getRate()));
            }

            if (!StringUtils.isEmpty(this.getCreateTime().get(0)) && !StringUtils.isEmpty(this.getCreateTime().get(1))) {
                try {
                    predicates.add(criteriaBuilder.between(root.get("createTime"),
                            simpleDateFormat.parse(this.getCreateTime().get(0)),
                            simpleDateFormat.parse(this.getCreateTime().get(1))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (!StringUtils.isEmpty(this.getCheckInTime().get(0)) && !StringUtils.isEmpty(this.getCheckInTime().get(1))) {
                try {
                    predicates.add(criteriaBuilder.between(root.get("checkInTime"),
                            simpleDateFormat.parse(this.getCheckInTime().get(0)),
                            simpleDateFormat.parse(this.getCheckInTime().get(1))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (!StringUtils.isEmpty(this.getCheckOutTime().get(0)) && !StringUtils.isEmpty(this.getCheckOutTime().get(1))) {
                try {
                    predicates.add(criteriaBuilder.between(root.get("checkOutTime"),
                            simpleDateFormat.parse(this.getCheckOutTime().get(0)),
                            simpleDateFormat.parse(this.getCheckOutTime().get(1))));
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
