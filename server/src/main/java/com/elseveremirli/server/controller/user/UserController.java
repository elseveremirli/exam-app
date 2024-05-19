package com.elseveremirli.server.controller.user;

import com.elseveremirli.server.dto.UserLogin;
import com.elseveremirli.server.dto.UserRequest;
import com.elseveremirli.server.dto.UserResponse;
import com.elseveremirli.server.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationService authenticationService;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserLogin userLogin) {
        return authenticationService.auth(userLogin);
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody UserRequest userRequest) {
        return authenticationService.save(userRequest);
    }

}
