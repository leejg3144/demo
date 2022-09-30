package com.example.demo.domain.login.dto;

import com.example.demo.common.entity.enums.BaseStatus;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSaveDto {

  @NotBlank(message = "아이디는 필수입니다.")
  private String userId;

  @NotBlank(message = "이름은 필수입니다.")
  private String name;

  @Min(value= 8, message = "비밀번호는 8자리 이상입니다.")
  @NotBlank(message = "비밀번호는 필수입니다.")
  private String password;

  @NotBlank(message = "권한을 지정해 주세요")
  private Role role;

  public User toEntity() {
    return User.builder()
        .userId(userId)
        .name(name)
        .password(password)
        .role(Role.GUEST)
        .role(role)
        .state(BaseStatus.OPENED)
        .lastLoggedInAt(LocalDateTime.now())
        .build();
  }
}
