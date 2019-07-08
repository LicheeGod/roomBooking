package com.demo.roombooking.controller;

import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.dto.RoomDTO;
import com.demo.roombooking.entity.dto.RoomQueryDTO;
import com.demo.roombooking.entity.enums.RoomType;
import com.demo.roombooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * 添加客房
     * @param roomDTO
     * @return
     */
    @PostMapping("/insert")
    public JsonResponse insertRoom(@RequestBody RoomDTO roomDTO) {

        synchronized (this) {
            return roomService.insertRoom(Room.builder()
                    .roomNo(roomDTO.getRoomNo())
                    .price(roomDTO.getPrice())
                    .people(roomDTO.getPeople())
                    .name(roomDTO.getName())
                    .roomImgUrl(roomDTO.getRoomImgUrl())
                    .introduction(roomDTO.getIntroduction())
                    .type(RoomType.valueOf(roomDTO.getType())).build());
        }

    }

    /**
     * 修改客房信息
     * @param roomDTO
     * @return
     */
    @PostMapping("/update")
    public JsonResponse updateRoom(@RequestBody RoomDTO roomDTO) {

        synchronized (this) {
            roomService.updateRoom(roomDTO);
        }

        return new JsonResponse(JsonResponse.SUCCESS);
    }

    /**
     * 屏蔽客房
     * @param roomNo
     * @return
     */
    @RequestMapping("/hide")
    public JsonResponse hideRoom(@RequestParam("roomNo") String roomNo) {
        roomService.hideRoom(roomNo);
        return new JsonResponse(JsonResponse.SUCCESS);
    }

    /**
     * 根据房间号获取房间信息
     * @param roomNo
     * @return
     */
    @RequestMapping("/getOne")
    public JsonResponse getRoom(@RequestParam("roomNo") String roomNo) {
        return new JsonResponse(JsonResponse.SUCCESS, roomService.getRoomByRoomNo(roomNo));
    }

    /**
     * 查询客房
     * @param roomQueryDTO
     * @return
     */
    @PostMapping("/get")
    public JsonResponse getRooms(@RequestBody RoomQueryDTO roomQueryDTO) {
        return new JsonResponse(JsonResponse.SUCCESS, roomService.getRooms(roomQueryDTO));
    }
}