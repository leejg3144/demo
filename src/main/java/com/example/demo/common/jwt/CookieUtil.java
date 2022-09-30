package com.example.demo.common.jwt;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class CookieUtil {

  public Cookie createCookie(String cookieName, String value) {
    Cookie token = new Cookie(cookieName, value);
    token.setHttpOnly(true);
    token.setMaxAge((int) JwtUtil.ACCESS_TOKEN_VALIDATION_SECOND);
    token.setPath("/");
    return token;
  }

  public Cookie getCookie(HttpServletRequest request, String cookieName) {
    Cookie[] cookies = request.getCookies();
    if(cookies == null) return null;
    for(Cookie cookie : cookies) {
      if(cookie.getName().equals(cookieName)) {
        return cookie;
      }
    }
    return null;
  }
}
