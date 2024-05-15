package com.test.api.marvelapi.service;

import com.test.api.marvelapi.dto.security.LoginRequest;
import com.test.api.marvelapi.dto.security.LoginResponse;
import com.test.api.marvelapi.persistence.entity.User;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private HttpSecurity http;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    public LoginResponse autheticate(LoginRequest loginRequest) {

        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.username());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user, loginRequest.password(), user.getAuthorities());

        authenticationManager.authenticate(authentication);

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));

        return new LoginResponse(jwt);

    }

    private Map<String, Object> generateExtraClaims(UserDetails user) {

        Map<String, Object> extraClaims = new HashMap<>();

        String roleName = ((User) user).getRole().getName().name();

        extraClaims.put("role", roleName);
        extraClaims.put("authorities", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return extraClaims;
    }

    public void logout() {
        try {
            http.logout(logoutConfig -> {
                logoutConfig.deleteCookies("JSESSIONID")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserDetails getUserLoggedIn() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if( !(authentication instanceof UsernamePasswordAuthenticationToken) ){
            throw new AuthenticationCredentialsNotFoundException("Required complete authentication");
        }

        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        return (UserDetails) authToken.getPrincipal();
    }
}
