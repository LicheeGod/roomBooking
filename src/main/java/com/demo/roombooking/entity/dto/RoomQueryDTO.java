package com.demo.roombooking.entity.dto;

import com.demo.roombooking.common.util.PageableBuilder;
import com.demo.roombooking.entity.Room;
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
    private List<String> prices;
    /**
     * 可入住的人的数量
     */
    private List<Integer> peoples;
    /**
     * 客房名称
     */
    private String name;
    /**
     *房间类型
     */
    private String type;

    @JsonIgnore
    public Specification<Room> getQuerySpecification() {

        return (Specification<Room>) (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(this.getRoomNo())) {
                predicates.add(
                        criteriaBuilder.like(
                                root.get("roomNo"), "%" + this.getRoomNo() + "%"));
            }

            if(!StringUtils.isEmpty(this.getPrices().get(0)) && !StringUtils.isEmpty(this.getPrices().get(1))){
                predicates.add(
                        criteriaBuilder.between(
                                root.get("price"), this.getPrices().get(0), this.getPrices().get(1)));
            }

            if(!StringUtils.isEmpty(this.getPeoples().get(0)) && !StringUtils.isEmpty(this.getPeoples().get(1))){
                predicates.add(
                        criteriaBuilder.between(
                                root.get("people"), this.getPeoples().get(0), this.getPeoples().get(1)));
            }

            if(!StringUtils.isEmpty(this.getName())){
                predicates.add(
                        criteriaBuilder.like(
                                root.get("name"), "%"+ this.getName()+ "%"));
            }

            if(!StringUtils.isEmpty(this.getType())){
                predicates.add(
                        criteriaBuilder.equal(
                                root.get("type"),  RoomType.valueOf(this.type)));
            }

            Predicate[] p = new Predicate[predicates.size()];
            return query.where(predicates.toArray(p)).distinct(true).getRestriction();
        };
    }

    @JsonProperty("pageBuilder")
    private PageableBuilder pageableBuilder;
}
