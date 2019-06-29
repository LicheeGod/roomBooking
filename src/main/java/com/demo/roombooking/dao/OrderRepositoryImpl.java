package com.demo.roombooking.dao;

import com.demo.roombooking.entity.Order;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepositoryImpl extends PagingAndSortingRepository<Order,Long>, JpaSpecificationExecutor<Order> {

}
