package com.elseveremirli.server.controller.admin;

import com.elseveremirli.server.dto.Admin.AdminRequest;
import com.elseveremirli.server.dto.Admin.AdminResponse;
import com.elseveremirli.server.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register")
    public AdminResponse register(@RequestBody AdminRequest adminRequest){
        return adminService.saveAdmin(adminRequest);
    }
    @PostMapping("/login")
    public AdminResponse login(@RequestBody AdminRequest adminRequest){
        return adminService.loginAdmin(adminRequest);
    }

}
