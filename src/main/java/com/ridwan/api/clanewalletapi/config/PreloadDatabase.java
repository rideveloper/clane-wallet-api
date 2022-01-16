package com.ridwan.api.clanewalletapi.config;

import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class PreloadDatabase {

    @Bean
    CommandLineRunner initDatabase(UserService userService) {
        return args -> {
//            UserRequest user1 = new UserRequest();
//            user1.setFirstName("Sam");
//            user1.setMiddleName("Walter");
//            user1.setLastName("White");
//            user1.setEmail("sam@testmail.test");
//            user1.setPhoneNumber("00016252617");
//            user1.setAddress("Floor 3, Haze PentHouse");
//
//            UserRequest user2 = new UserRequest();
//            user2.setFirstName("Fatima");
//            user2.setLastName("Asuma");
//            user2.setEmail("fati@testmail.test");
//            user2.setPhoneNumber("00016222617");
//            user2.setAddress("1, Aso Rock");
//
//            UserRequest user3 = new UserRequest();
//            user3.setFirstName("Ben");
//            user3.setLastName("Jerry");
//            user3.setEmail("ben@testmail.test");
//            user3.setPhoneNumber("00216252617");
//            user3.setAddress("Under the bridge");
//
//            log.info("Preloading user"+ userService.createUser(user1));
//            log.info("Preloading user"+ userService.createUser(user2));
//            log.info("Preloading user"+ userService.createUser(user3));

        };
    }
}
