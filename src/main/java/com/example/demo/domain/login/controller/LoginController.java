package com.example.demo.domain.login.controller;

import com.example.demo.domain.login.dto.UserLoginDto;
import com.example.demo.domain.login.dto.UserSaveDto;
import com.example.demo.domain.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class LoginController {

  private final LoginService loginService;

  // 로그인
  @PostMapping("/login")
  public String login(@RequestBody UserLoginDto user, HttpServletResponse response) {
    Cookie token = loginService.loginUser(user);
    response.addCookie(token);
    return token.getValue();
  }

  // 회원가입
  @PostMapping("/join")
  public void join(@RequestBody UserSaveDto user) {
    loginService.save(user);
  }

  @GetMapping("/hello")
  public String hello() {
    return "hello World!";
  }
}
