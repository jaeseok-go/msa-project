package com.jaeseok.userservice.controller;

import com.jaeseok.userservice.dto.UserDto;
import com.jaeseok.userservice.entity.UserEntity;
import com.jaeseok.userservice.mapper.UserMapper;
import com.jaeseok.userservice.service.UserService;
import com.jaeseok.userservice.vo.Greeting;
import com.jaeseok.userservice.vo.RequestUser;
import com.jaeseok.userservice.vo.ResponseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private Environment environment;

    @Autowired
    private Greeting greeting;

    @Autowired
    private UserService userService;

    @GetMapping("/health_check")
    public String status() {
        String statusMessage = String.format("user service is running on port : %s",
                environment.getProperty("local.server.port"));
        return statusMessage;
    }

    @GetMapping("/welcome")
    public String welcome() {
        String welcomeMessage1 = environment.getProperty("greeting.message");
        String welcomeMessage2 = greeting.getMessage();

        return welcomeMessage2;
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user) {
        UserDto userDto = UserMapper.INSTANCE.requestToDto(user);
        UserDto resultDto = userService.createdUser(userDto);

        ResponseUser responseUser = UserMapper.INSTANCE.dtoToResponse(resultDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<ResponseUser>> getUsers() {
        Iterable<UserEntity> userList = userService.getUserByAll();

        List<ResponseUser> result = new ArrayList<>();
        userList.forEach(u -> result.add(UserMapper.INSTANCE.entityToResponse(u)));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseUser> getUserByUserId(@PathVariable(name = "userId") String userId) {
        UserDto userDto = userService.getUserByUserId(userId);

        ResponseUser result = UserMapper.INSTANCE.dtoToResponse(userDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
