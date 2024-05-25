package com.elseveremirli.server.controller.user;

import com.elseveremirli.server.dto.user.UserLogin;
import com.elseveremirli.server.dto.user.UserRequest;
import com.elseveremirli.server.dto.user.UserResponse;
import com.elseveremirli.server.entities.User;
import com.elseveremirli.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/getUser")
    public User  getUser(@AuthenticationPrincipal User user) {
        return user;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserLogin userLogin) {
        return userService.loginUser(userLogin);
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return userService.saveUser(userRequest);
    }

}
