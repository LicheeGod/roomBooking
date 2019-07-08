package com.demo.roombooking.service.Impl;

import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.dao.RoomRepository;
import com.demo.roombooking.entity.Room;
import com.demo.roombooking.entity.dto.RoomDTO;
import com.demo.roombooking.entity.dto.RoomQueryDTO;
import com.demo.roombooking.entity.enums.RoomType;
import com.demo.roombooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoomServiceImpl implements RoomService{

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    @Override
    public JsonResponse insertRoom(Room room) {

        if (hasRoom(room.getRoomNo())) {
            return new JsonResponse(JsonResponse.FAILURE, "房间已存在");
        }

        roomRepository.save(room);

        return new JsonResponse(JsonResponse.SUCCESS);
    }

    @Override
    public JsonResponse updateRoom(RoomDTO roomDTO) {

        if (!hasRoom(roomDTO.getRoomNo())) {
            return new JsonResponse(JsonResponse.FAILURE, "房间不存在");
        }

        Room room = roomRepository.findRoomByRoomNo(roomDTO.getRoomNo()).get();

        room.setPrice(roomDTO.getPrice());
        room.setPeople(roomDTO.getPeople());
        room.setName(roomDTO.getName());
        room.setRoomImgUrl(roomDTO.getRoomImgUrl());
        room.setIntroduction(roomDTO.getIntroduction());
        room.setType(RoomType.valueOf(roomDTO.getType()));

        roomRepository.saveAndFlush(room);

        return new JsonResponse(JsonResponse.SUCCESS);
    }

    @Override
    public void hideRoom(String roomNo) {

        Map<String, Boolean> m = new HashMap<>();
        roomRepository.findRoomByRoomNo(roomNo).ifPresent(room -> {
//            boolean ishide = room.getIsHide();
//            room.setIsHide(!ishide);
            if (room.getIsHide()) {
                room.setIsHide(false);
            } else {
                room.setIsHide(true);
            }
            m.put("hide", room.getIsHide());
            roomRepository.saveAndFlush(room);
        });

        System.out.println();
    }

    @Override
    public Page<Room> getRooms(RoomQueryDTO roomQueryDTO) {
        return roomRepository.findAll(roomQueryDTO.getQuerySpecification(), roomQueryDTO.getPageableBuilder().getPageable());
    }

    private Boolean hasRoom(String roomNo) {
        return roomRepository.findRoomByRoomNo(roomNo).isPresent();
    }
}
