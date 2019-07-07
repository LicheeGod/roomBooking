package com.demo.roombooking.service.Impl;


import com.demo.roombooking.common.resp.JsonResponse;
import com.demo.roombooking.dao.UserRepository;
import com.demo.roombooking.entity.User;
import com.demo.roombooking.entity.dto.UserQueryDTO;
import com.demo.roombooking.entity.enums.UserStatus;
import com.demo.roombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

//    private HashMap<String, String> storage = new HashMap<>();

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String userName, String password) {

        User user = userRepository.findByUserName(userName).get();

        if (user.getPassword().equals(password)) {

            // 模拟token令牌
//            storage.put(userName, Token.getToken());

            return user;
        }

        return null;
    }

    @Override
    public void exit(String userName) {
//        storage.remove(userName);
    }

    /**
     * 添加用户
     */
    @Override
    public JsonResponse insertUser(User user) {

        User checkUser = userRepository.findByUserName(user.getUserName()).get();

        return new JsonResponse(JsonResponse.FAILURE, "当前用户已存在");

        // 默认初始化的信息有: 昵称、头像、性别、非会员、认证状态

    }

    /**
     * 修改基本信息
     * 用户能修改的基本信息有: 昵称、头像、电子邮箱、联系电话、性别、出生年月、地址
     */
    @Override
    public User updateUser(User user) {

        User checkUser = userRepository.findByUserName(user.getUserName()).get();

        user.setId(checkUser.getId());
        user.setUserName(checkUser.getUserName());
        user.setPassword(checkUser.getPassword());
        user.setRealName(checkUser.getRealName());
        user.setIsVip(checkUser.getIsVip());
        user.setIdCard(checkUser.getIdCard());
        user.setIdCardImg(checkUser.getIdCardImg());
        user.setState(checkUser.getState());

        return userRepository.saveAndFlush(user);
    }

    /**
     * 修改密码
     */
    @Override
    public User updatePassword(String userName, String oldPassword, String newPassword) {

        User checkUser = userRepository.findByUserName(userName).get();

        if (oldPassword.equals(checkUser.getPassword())) {
            checkUser.setPassword(newPassword);
        }

        return userRepository.saveAndFlush(checkUser);
    }

    /**
     * 实名认证
     * 真实姓名(分开功能)、身份证号(分开功能)、身份证图(分开功能)
     */
    @Override
    public User verifiedUser(User user) {

        User checkUser = userRepository.findByUserName(user.getUserName()).get();

        // todo 实名认证！！！！
        checkUser.setRealName(user.getRealName());
        checkUser.setIdCard(user.getIdCard());
        checkUser.setIdCardImg(user.getIdCardImg());

        return userRepository.saveAndFlush(checkUser);
    }

    /**
     * 屏蔽用户
     */
    @Override
    public void deleteUser(String userName) {

        User checkUser = userRepository.findByUserName(userName).get();

        checkUser.setState(UserStatus.HAS_DELETE);

        userRepository.saveAndFlush(checkUser);
    }

    /**
     * 用户查询
     */
    @Override
    public Page<User> getUser(UserQueryDTO userQueryDTO) {
        return userRepository.findAll(userQueryDTO.getQuerySpecification(), userQueryDTO.getPageableBuilder().getPageable());
    }
}
