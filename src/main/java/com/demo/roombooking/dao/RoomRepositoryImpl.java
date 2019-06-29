package com.demo.roombooking.dao;

import com.demo.roombooking.entity.Room;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoomRepositoryImpl extends PagingAndSortingRepository<Room,Long>, JpaSpecificationExecutor<Room> {
    
}
