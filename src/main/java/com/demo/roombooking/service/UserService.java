package com.demo.roombooking.service;


import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.entity.User;
import com.demo.roombooking.entity.dto.UserQueryDTO;
import org.springframework.data.domain.Page;


public interface UserService {

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return User
     */
    User login(String userName, String password);

    /**
     * 注销
     * @param userName 用户名
     * @return Boolean
     */
    void exit(String userName);

    /**
     * 添加用户
     * @param user 用户信息
     * @return JsonResponse
     */
    JsonResponse insertUser(User user);

    /**
     * 修改用户信息
     * @param user 用户信息
     * @return User
     */
    User updateUser(User user);

    /**
     * 修改密码
     * @param userName 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return User
     */
    User updatePassword(String userName, String oldPassword, String newPassword);

    /**
     * 用户认证
     * @param user 用户信息
     * @return User
     */
    User verifiedUser(User user);

    /**
     * 屏蔽用户[假删除]
     * @param userName 用户账号
     */
    void deleteUser(String userName);



    /**
     * 分页查询用户
     * @param userQueryDTO 用户查询分页构造器
     * @return Page<User>
     */
    Page<User> getUser(UserQueryDTO userQueryDTO);
}
