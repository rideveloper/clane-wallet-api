package com.ridwan.api.clanewalletapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridwan.api.clanewalletapi.enums.Status;
import com.ridwan.api.clanewalletapi.request.UserRequest;
import com.ridwan.api.clanewalletapi.response.GenericResponse;
import com.ridwan.api.clanewalletapi.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {ClaneWalletApiApplication.class})
class ClaneWalletApiApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(userService).isNotNull();
    }

    @Test
    @DisplayName("Register User Test")
    void test_register_user() throws JsonProcessingException {
        UserRequest user = new UserRequest();
        user.setFirstName("First Name");
        user.setLastName("Last Name");
        user.setEmail("email@test.com");
        user.setAddress("Address");
        user.setPhoneNumber("09029281829");

        GenericResponse response = userService.createUser(user);

        assertNotNull(response, "Create User response is null");
        assertEquals(response.getStatus(), Status.SUCCESS);

        System.out.println(objectMapper.writeValueAsString(response));
    }

}
