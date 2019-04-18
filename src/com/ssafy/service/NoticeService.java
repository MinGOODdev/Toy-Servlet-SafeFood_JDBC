package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Notice;

public interface NoticeService {

    List<Notice> searchAll();

    Notice search(String title);

    void registerNotice(Notice notice);

    void deleteNotice(String title);

}
