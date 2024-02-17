package com.test.api.marvelapi.web.interceptor;

import com.test.api.marvelapi.exception.ApiErrorException;
import com.test.api.marvelapi.persistence.entity.UserInteractionLog;
import com.test.api.marvelapi.persistence.repository.UserInteractionLogRepository;
import com.test.api.marvelapi.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Component
public class UserInteractionInterceptor implements HandlerInterceptor {

    @Value("${integration.marvel.api.base-path}")
    private String basePath;

    @Autowired
    private UserInteractionLogRepository userInteractionLogRepository;

    @Autowired
    @Lazy
    private AuthenticationService authenticationService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        if (requestURI.startsWith("/comics") || requestURI.startsWith("/characters")) {
            UserInteractionLog userLog = new UserInteractionLog();
            userLog.setHttpMethod(request.getMethod());
            userLog.setUrl(request.getRequestURL().toString());

            UserDetails user = authenticationService.getUserLoggedIn();
            userLog.setUsername(user.getUsername());
            userLog.setRemoteAddress(request.getRemoteAddr());
            userLog.setTimestamp(LocalDateTime.now());

            try {
                userInteractionLogRepository.save(userLog);
                return true;
            } catch (Exception exception) {
                throw new ApiErrorException("Error saving user interaction log.");
            }
        }

        return true;
    }
}
