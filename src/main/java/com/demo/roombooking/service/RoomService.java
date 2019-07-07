package com.demo.roombooking.service;

import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.dto.RoomDTO;
import com.demo.roombooking.entity.dto.RoomQueryDTO;
import org.springframework.data.domain.Page;

public interface RoomService {

    /**
     * 添加新客房
     * @param room
     */
    JsonResponse insertRoom(Room room);

    /**
     * 修改客房信息
     * @param roomDTO
     */
    JsonResponse updateRoom(RoomDTO roomDTO);

    /**
     * 暂停使用客房
     * @param roomNo
     */
    void hideRoom(String roomNo);

    /**
     * 查询动态客房
     * @param roomQueryDTO
     * @return
     */
    Page<Room> getRooms(RoomQueryDTO roomQueryDTO);
}
