package org.duckdns.wru.app.user.controller;

import lombok.RequiredArgsConstructor;
import org.duckdns.wru.global.auth.dto.JoinRequest;
import org.duckdns.wru.app.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


}
