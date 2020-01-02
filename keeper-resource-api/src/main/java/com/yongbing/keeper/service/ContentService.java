package com.yongbing.keeper.service;

import com.yongbing.keeper.common.KpResult;
import com.yongbing.keeper.domain.KpContent;

import java.util.List;

public interface ContentService {
    List<KpContent> selectToday(String email);

    KpResult saveContent(String email, KpContent kpContent);

    KpResult deleteContent(String email, KpContent kpContent);
}
