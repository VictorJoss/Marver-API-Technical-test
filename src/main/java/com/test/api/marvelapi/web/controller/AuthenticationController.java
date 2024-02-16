package com.test.api.marvelapi.web.controller;

import com.test.api.marvelapi.dto.security.LoginRequest;
import com.test.api.marvelapi.dto.security.LoginResponse;
import com.test.api.marvelapi.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll()")
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginRequest loginRequest){

        return ResponseEntity.ok(authenticationService.autheticate(loginRequest));
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/logout")
    public void logout() throws Exception {
        authenticationService.logout();
    }
}
