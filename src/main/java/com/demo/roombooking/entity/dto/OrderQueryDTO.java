package com.demo.roombooking.entity.dto;

import com.demo.roombooking.common.util.PageableBuilder;
import com.demo.roombooking.entity.Order;
import com.demo.roombooking.entity.enums.OrderStatus;
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
public class OrderQueryDTO {
    /**
     * 订单号
     */
//    private String code;
    /**
     * 订单生成时间段
     */
    private List<String> createTimes;
    /**
     * 预定入住时间段
     */
    private List<String> checkInTimes;
    /**
     * 预定退房时间段
     */
    private List<String> checkOutTimes;
    /**
     * 订单状态：未入住、入住、完成、取消
     */
    private String status;

    @JsonIgnore
    public Specification<Order> getQuerySpecification() {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return (Specification<Order>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

//            if (!StringUtils.isEmpty(this.getCode())) {
//                predicates.add(
//                        criteriaBuilder.equal(
//                                root.get("code"), this.getCode()));
//            }
            if (!StringUtils.isEmpty(this.getCreateTimes().get(0)) && !StringUtils.isEmpty(this.getCreateTimes().get(1))) {
                try {
                    predicates.add(
                            criteriaBuilder.between(
                                    root.get("createTime"), simpleDateFormat.parse(this.getCreateTimes().get(0)), simpleDateFormat.parse(this.getCreateTimes().get(1))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (!StringUtils.isEmpty(this.getCheckInTimes().get(0)) && !StringUtils.isEmpty(this.getCheckInTimes().get(1))) {
                try {
                    predicates.add(
                            criteriaBuilder.between(
                                    root.get("checkInTime"), simpleDateFormat.parse(this.getCheckInTimes().get(0)), simpleDateFormat.parse(this.getCheckInTimes().get(1))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (!StringUtils.isEmpty(this.getCheckOutTimes().get(0)) && !StringUtils.isEmpty(this.getCheckOutTimes().get(1))) {
                try {
                    predicates.add(
                            criteriaBuilder.between(
                                    root.get("checkOutTime"), simpleDateFormat.parse(this.getCheckOutTimes().get(0)), simpleDateFormat.parse(this.getCheckOutTimes().get(1))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (!StringUtils.isEmpty(this.getStatus())) {
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("status"), OrderStatus.valueOf(this.getStatus())));
            }

            Predicate[] p = new Predicate[predicates.size()];
            return query.where(predicates.toArray(p)).distinct(true).getRestriction();
        };
    }

    @JsonProperty("pageBuilder")
    private PageableBuilder pageableBuilder;
}
