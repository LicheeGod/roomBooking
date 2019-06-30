package com.demo.roombooking.entity.dto;

import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.enums.Sex;
import com.demo.roombooking.entity.enums.UserState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long id;
    private Long managerId;
    private Date checkInTime;
    private Date checkOutTime;
    private char state;
    private int rate;
    private String remark;
    private Long roomId;
}
