package com.bharath.springcloud.security.event;

import com.bharath.springcloud.security.entities.Role;
import com.bharath.springcloud.security.entities.User;
import com.bharath.springcloud.security.repos.RoleRepo;
import com.bharath.springcloud.security.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(Objects.isNull(userRepo.findByEmail("user1@gmail.com"))) {
            Role role = new Role();
            role.setName("ROLE_USER");
            Role savedRole = roleRepo.save(role);

            Set<Role> roles = new HashSet<>();
            roles.add(savedRole);


            User user = new User();
            user.setEmail("user1@gmail.com");
            user.setPassword(passwordEncoder.encode("password@1"));
            user.setFirstName("Peter");
            user.setLastName("Parker");
            user.setRoles(roles);

            userRepo.save(user);
        }
    }
}
