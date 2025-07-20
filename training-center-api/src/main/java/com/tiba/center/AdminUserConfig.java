package com.tiba.center;

import java.util.List;

import com.tiba.center.user.Role;
import com.tiba.center.user.RoleRepository;
import com.tiba.center.user.User;
import com.tiba.center.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserConfig {

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private UserRepository userRepository;

  @Autowired RoleRepository roleRepository;

  @Bean
  public CommandLineRunner createAdminUser() {
    return args -> {
      if (!roleRepository.findByName("ROLE_ADMIN").isPresent()) {
        roleRepository.save(Role.builder().name("ROLE_ADMIN").build());
      }

      if (userRepository.findByUserName("admin").isEmpty()) {

        User admin = new User();
        admin.setUserName("admin");
        admin.setPassword(passwordEncoder.encode("12345678"));
        admin.setRole(roleRepository.findByName("ROLE_ADMIN").get());
        admin.setEnabled(true);
        userRepository.save(admin);
      }
    };
  }
}
