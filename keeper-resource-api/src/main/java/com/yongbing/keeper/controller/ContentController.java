package com.yongbing.keeper.controller;

import com.yongbing.keeper.common.KpResult;
import com.yongbing.keeper.domain.KpContent;
import com.yongbing.keeper.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @description
 */
@RestController
public class ContentController {

    @Autowired
    private ContentService contentService;

    //get all of the content
    @GetMapping(value = "/content/list")
    @ResponseBody
    public List<KpContent> getContent(Authentication authentication) {
        String email = authentication.getName();
        return contentService.selectToday(email);
    }

    //add new one
    @PostMapping(value="/content/save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public KpResult saveContent(Authentication authentication, KpContent kpContent) {
        String email = authentication.getName();
        return contentService.saveContent(email, kpContent);
    }

    //delete one
    @PostMapping(value = "/content/delete", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public KpResult deleteContent(Authentication authentication, KpContent kpContent) {
        String email = authentication.getName();
        return contentService.deleteContent(email, kpContent);
    }

}
