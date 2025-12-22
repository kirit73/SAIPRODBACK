package com.sai.inventory.controller;

import com.sai.inventory.domain.login.request.LoginRequest;
import com.sai.inventory.domain.login.response.LoginResponse;
import com.sai.inventory.service.JwtService;
import com.sai.inventory.service.LoginService;
import com.sai.inventory.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    AuthenticationManager  authenticationManager;
    @Autowired
    private LoginService loginService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity authenticateUser(@RequestBody LoginRequest req) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUserCd(), req.getPassword()));
            LoginResponse o = (LoginResponse) loginService.authenticateUser(req);
            o.setToken(jwtService.generateToken(o.getUserDetails().getId(), req.getUserCd(), o.getOrganizationDetails().getId(), o.getUserDetails().getUserType()));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return ResponseBuilder.getSuccessResponse(o);
        }catch (BadCredentialsException exception){
            return ResponseBuilder.getErrorResponse("Invalid login credentials");
        }
    }
}
