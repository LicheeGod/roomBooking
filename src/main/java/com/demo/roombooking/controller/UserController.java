package com.demo.roombooking.controller;


import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.entity.User;
import com.demo.roombooking.entity.dto.UserDTO;
import com.demo.roombooking.entity.dto.UserQueryDTO;
import com.demo.roombooking.entity.enums.Sex;
import com.demo.roombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
public class UserController {

    private UserService userService;

    @Autowired
    private void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     * @return JsonResponse
     */
    @PostMapping("/user/login")
    public JsonResponse login(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        return new JsonResponse(JsonResponse.SUCCESS, userService.login(userName, password));
    }

    /**
     * 注销
     * @param userName
     * @return
     */
    @RequestMapping("/user/exit")
    public JsonResponse exit(@RequestParam("userName") String userName) {
        userService.exit(userName);
        return new JsonResponse(JsonResponse.SUCCESS);
    }

    /**
     * 添加用户
     * @param userDTO 用户基本信息
     * @return JsonResponse
     */
    @RequestMapping("/user/insert")
    public JsonResponse insertUser(@RequestBody UserDTO userDTO) {
        return userService.insertUser(User.builder()
                .userName(userDTO.getUserName())
                .password(userDTO.getPassword())
                .email(userDTO.getEmail()).build());
    }

    /**
     * 修改用户基本信息
     * @param userDTO 用户基本信息
     * @return JsonResponse
     */
    @RequestMapping("/user/updateInfo")
    public JsonResponse updateUser(@RequestBody UserDTO userDTO) {
        return new JsonResponse(JsonResponse.SUCCESS,
                userService.updateUser(
                        User.builder()
                                .userName(userDTO.getUserName())
                                .nickName(userDTO.getNickName())
                                .userImg(userDTO.getUserImg())
                                .realName(userDTO.getRealName())
                                .email(userDTO.getEmail())
                                .phone(userDTO.getPhone())
                                .gender(Sex.valueOf(userDTO.getGender()))
                                .birthday(userDTO.getBirthday())
                                .address(userDTO.getAddress()).build()));
    }

    /**
     * 修改密码
     * @param userName 用户账号
     * @param password 旧密码
     * @param newPassword 新密码
     * @return JsonResponse
     */
    @RequestMapping("/user/updatePassword")
    public JsonResponse updatePassword(@RequestParam("userName") String userName,
                                       @RequestParam("password") String password,
                                       @RequestParam("newPassword") String newPassword) {
        return new JsonResponse(JsonResponse.SUCCESS, userService.updatePassword(userName, password, newPassword));
    }

    /**
     * 实名认证【功能未实现】
     * @param userDTO 用户信息
     * @return JsonResponse
     */
    @RequestMapping("/user/verified")
    public JsonResponse verifiedUser(@RequestBody UserDTO userDTO) {
        return new JsonResponse(JsonResponse.SUCCESS,
                userService.verifiedUser(
                        User.builder()
                                .userName(userDTO.getUserName())
                                .realName(userDTO.getRealName())
                                .idCard(userDTO.getIdCard())
                                .idCardImg(userDTO.getIdCardImg()).build()));
    }

    /**
     * 屏蔽用户
     * @param userName 用户账号
     * @return JsonResponse
     */
    @RequestMapping("/user/delete")
    public JsonResponse deleteUser(@RequestParam("userName") String userName) {
        userService.deleteUser(userName);
        return new JsonResponse(JsonResponse.SUCCESS);
    }

    /**
     * 分页查询用户
     * @param userQueryDTO 用户分页查询构造器
     * @return JsonResponse
     */
    @RequestMapping("/user/get")
    public JsonResponse getUser(@RequestBody UserQueryDTO userQueryDTO) {
        return new JsonResponse(JsonResponse.SUCCESS, userService.getUser(userQueryDTO));
    }
}
