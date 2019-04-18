package com.ssafy.dao;

import java.util.List;

import com.ssafy.vo.Notice;

public interface NoticeDao {

    List<Notice> searchAll();

    Notice search(String title);

    void registerNotice(Notice notice);

    void deleteNotice(String title);

}
