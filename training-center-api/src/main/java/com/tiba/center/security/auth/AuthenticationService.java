package com.tiba.center.security.auth;

import java.util.HashMap;

import com.tiba.center.security.auth.dto.AuthenticationRequest;
import com.tiba.center.security.auth.dto.AuthenticationResponse;
import com.tiba.center.security.jwt.JwtService;
import com.tiba.center.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    var auth =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
    var claims = new HashMap<String, Object>();
    var user = ((User) auth.getPrincipal());
    claims.put("userName", user.getUsername());

    var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
    return AuthenticationResponse.builder().token(jwtToken).build();
  }
}
