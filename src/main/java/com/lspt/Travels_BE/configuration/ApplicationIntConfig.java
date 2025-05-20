package com.lspt.Travels_BE.configuration;

import com.lspt.Travels_BE.entity.User;
import com.lspt.Travels_BE.enums.Role;
import com.lspt.Travels_BE.repository.UserReponsitory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationIntConfig {
    PasswordEncoder passwordEncoder;

    //tạo tài khoản admin

    @Bean
    ApplicationRunner applicationRunner(UserReponsitory userReponsitory){
        return args -> {
           if(userReponsitory.findByUserName("admin@gmail.com").isEmpty()){
               var roles = new HashSet<String>();
               roles.add(Role.ADMIN.name());

               User user = User.builder()
                       .userName("admin@gmail.com")
                       .password(passwordEncoder.encode("admin123"))
                       .roles(roles)
                       .build();
               userReponsitory.save(user);
               log.warn("admin user has been created with default password: admin, please change it");
           }
        };
    }
}
