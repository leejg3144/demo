package com.example.demo.domain.login.service;

import com.example.demo.common.jwt.CookieUtil;
import com.example.demo.common.jwt.JwtUtil;
import com.example.demo.domain.login.dto.UserLoginDto;
import com.example.demo.domain.login.dto.UserSaveDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoginService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final CookieUtil cookieUtil;

  public Cookie loginUser(UserLoginDto user) {
    log.info("#### request login user = {}", user.toString());

    User loginUser = userRepository.findByUserId(user.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("Not found User"));

    if (!passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
      throw new IllegalArgumentException("Passwords do not Match.");
    }

    String token = jwtUtil.generateToken(loginUser);

    loginUser.loginDate();

    return cookieUtil.createCookie("accessToken", token);
  }

  public void save(UserSaveDto user) {
    log.info("#### joinUser = {}", user.toString());

    try {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user.toEntity()).getId();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
