package com.example.demo.domain.login.service;

import com.example.demo.domain.login.dto.UserLoginDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    UserDetails userDetails = null;

    User user = userRepository.findByUserId(userId)
        .orElse(null);

    if(ObjectUtils.isEmpty(user))
      return userDetails;
    else
      return new UserLoginDto(user);
  }
}