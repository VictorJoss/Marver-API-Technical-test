package com.test.api.marvelapi.web.controller;

import com.test.api.marvelapi.dto.MyPageable;
import com.test.api.marvelapi.persistence.integration.marvel.dto.ComicDto;
import com.test.api.marvelapi.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comics")
public class ComicController {

    @Autowired
    private ComicService comicService;

    @PreAuthorize("hasAuthority('comic:read-all')")
    @GetMapping
    public ResponseEntity<List<ComicDto>>findAlL(
            @RequestParam(required = false)Long characterId,
            @RequestParam(defaultValue = "0")Long offset,
            @RequestParam(defaultValue = "20")Long limit
    ){
        MyPageable myPageable = new MyPageable(offset, limit);
        return ResponseEntity.ok(comicService.findAll(myPageable, characterId));
    }

    @PreAuthorize("hasAuthority('comic:read-by-id')")
    @GetMapping("/{comicId}")
    public ResponseEntity<ComicDto>findByiD(@PathVariable Long comicId){
        return ResponseEntity.ok(comicService.findById(comicId));
    }
}
