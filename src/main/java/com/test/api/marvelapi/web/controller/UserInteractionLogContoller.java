package com.test.api.marvelapi.web.controller;

import com.test.api.marvelapi.dto.GetUserInteractionLogDto;
import com.test.api.marvelapi.service.UserInteractionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users-interactions")
public class UserInteractionLogContoller {

    @Autowired
    private UserInteractionLogService userInteractionLogService;

    @GetMapping
    public ResponseEntity<Page<GetUserInteractionLogDto>> findAll(
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit
    ){

        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findAll(pageable));
    }

    private static Pageable buildPageable(int offset, int limit) {
        Pageable pageable;

        if(offset < 0 ){
            throw new IllegalArgumentException("The offset attribute cannot be less than zero");
        }

        if(limit <= 0){
            throw new IllegalArgumentException("The limit attribute cannot be less than or equal to zero");
        }

        if(offset == 0) {
            pageable = PageRequest.of(0, limit);
        } else {
            pageable = PageRequest.of(offset / limit, limit);
        }

        return pageable;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Page<GetUserInteractionLogDto>> findByUsername(
            @PathVariable String username,
            @RequestParam(defaultValue = "0") int offset,
            @RequestParam(defaultValue = "20") int limit
    ){
        Pageable pageable = buildPageable(offset, limit);
        return ResponseEntity.ok(userInteractionLogService.findByUsername(pageable, username));
    }
}
