package com.demo.roombooking.config;

import com.demo.roombooking.dao.PrivilegeRepository;
import com.demo.roombooking.dao.UserRepository;
import com.demo.roombooking.entity.Privilege;
import com.demo.roombooking.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitConfig implements ApplicationRunner {

    private PrivilegeRepository privilegeRepository;
    private UserRepository userRepository;

    @Autowired
    public InitConfig(PrivilegeRepository privilegeRepository, UserRepository userRepository) {
        this.privilegeRepository = privilegeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 初始化权限
        if (privilegeRepository.count() == 0) {
            privilegeRepository.save(new Privilege("USER"));
            privilegeRepository.save(new Privilege("ADMIN"));
        }

        // 初始化管理员用户
        if (userRepository.count() == 0) {
            userRepository.save(new User("admin","123456", true, "管理员", privilegeRepository.findByName("ADMIN")));
        }

    }

}
