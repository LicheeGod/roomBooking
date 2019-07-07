package com.demo.roombooking.entity.dto;

import com.demo.roombooking.common.util.PageableBuilder;
import com.demo.roombooking.entity.Order;
import com.demo.roombooking.entity.enums.RoomType;
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
public class RoomQueryDTO {
    /**
     * 房间号
     **/
    private String roomNo;
    /**
     *价格区间
     **/
    private List<Double> price;
    /**
     * 可入住的人的数量
     */
    private Integer people;
    /**
     * 客房名称
     */
    private String name;
    /**
     *房间类型
     */
    private RoomType type;

    /**
     *房间是否为空
     */
    private Integer state;//0为空，1非空

     /*
     * 入住 离开时间
     *
    private List<String> dates;*/


    @JsonIgnore
    public Specification<Order> getQuerySpecification() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return (Specification<Order>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(this.getPeople())) {
                if(this.getPeople()<4){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("people"), this.getPeople())));
                }else
                    predicates.add(criteriaBuilder.gt(root.get("people"), this.getPeople()));
            }
            if(!StringUtils.isEmpty(this.getName())){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+ this.getName()+ "%"));
            }
            if (!StringUtils.isEmpty(this.getPrice().get(0)) && !StringUtils.isEmpty(this.getPrice().get(1))) {
                    predicates.add(criteriaBuilder.between(root.get("price"),
                            this.getPrice().get(0),
                            this.getPrice().get(1)));

            }
            if(!StringUtils.isEmpty(this.getType())){
                predicates.add(criteriaBuilder.like(root.get("type"), "%"+ this.getType()+ "%"));
            }
            if(!StringUtils.isEmpty(this.getRoomNo())){
                predicates.add(criteriaBuilder.like(root.get("roomNo"), "%"+ this.getRoomNo()+ "%"));
            }
            if(!StringUtils.isEmpty(this.getState())){
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("state"),  this.getState())));
            }

            /*if (!StringUtils.isEmpty(this.getDates().get(0)) && !StringUtils.isEmpty(this.getDates().get(1))) {
                try {
                    predicates.add(criteriaBuilder.between(root.get(""),
                            simpleDateFormat.parse(this.getDates().get(0)),
                            simpleDateFormat.parse(this.getDates().get(1))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }*/


            Predicate[] p = new Predicate[predicates.size()];
            return query.where(predicates.toArray(p)).distinct(true).getRestriction();
        };
    }

    @JsonProperty("pageBuilder")
    private PageableBuilder pageableBuilder;
}
