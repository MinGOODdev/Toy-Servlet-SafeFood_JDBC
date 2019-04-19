package com.ssafy.dao;

import com.ssafy.vo.Notice;

import java.util.List;

public interface NoticeDao {

    List<Notice> findAll() throws Exception;

    Notice findById(int id) throws Exception;

    void registerNotice(Notice notice) throws Exception;

    void update(Notice notice) throws Exception;

    void deleteById(int id) throws Exception;

}
