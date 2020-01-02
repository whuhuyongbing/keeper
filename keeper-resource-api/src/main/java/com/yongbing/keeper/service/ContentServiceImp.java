package com.yongbing.keeper.service;

import com.yongbing.keeper.common.KpResult;
import com.yongbing.keeper.domain.KpContent;
import com.yongbing.keeper.domain.KpUser;
import com.yongbing.keeper.mapper.ContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Yongbing Hu
 * @version 0.0.0
 * @description
 */
@Service
public class ContentServiceImp implements ContentService {


    @Autowired
    private ContentMapper contentMapper;

    @Override
    public List<KpContent> selectToday(String email) {
        KpUser user = contentMapper.selectByEmail(email);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentTime = sdf.format(date) + "%";

        return contentMapper.selectByDate(user.getId(), currentTime);
    }

    @Override
    public KpResult saveContent(String email, KpContent kpContent) {
        KpUser user = contentMapper.selectByEmail(email);
        kpContent.setUserID(user.getId());
        contentMapper.insert(kpContent);
        return KpResult.createResult(200, 0, "save success");
    }

    @Override
    public KpResult deleteContent(String email, KpContent kpContent) {
        KpUser user = contentMapper.selectByEmail(email);
        kpContent.setUserID(user.getId());
        contentMapper.deleteContent(kpContent);
        return KpResult.createResult(200, 0, "delete success");
    }
}
