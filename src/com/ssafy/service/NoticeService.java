package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Notice;

public interface NoticeService {

    List<Notice> findAll() throws Exception;

    Notice findById(int id) throws Exception;

    void registerNotice(Notice notice) throws Exception;

    void update(Notice notice) throws Exception;

    void deleteById(int id) throws Exception;

}
