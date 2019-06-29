package com.demo.roombooking.dao;


import com.demo.roombooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findUserById(Long id);

    User findByUserName(String userName);

    List<User> findByNickNameLike(String nickName);

    List<User> findAllByRealNameLike(String realName);

}
