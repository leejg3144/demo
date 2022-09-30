package com.example.demo.domain.login.dto;

import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto implements UserDetails {

  private String userId;

  private String password;

  private Role role;

  public UserLoginDto(User user) {
    this.userId = user.getUserId();
    this.password = user.getPassword();
    this.role = user.getRole();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.asList(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return role.getKey();
      }
    });
  }

  @Override
  public String getUsername() {
    return this.userId;
  }

  @Override
  public String getPassword() { return this.password; }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
