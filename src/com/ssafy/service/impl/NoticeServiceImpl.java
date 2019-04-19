package com.ssafy.service.impl;

import java.util.List;

import com.ssafy.dao.NoticeDao;
import com.ssafy.dao.impl.NoticeDaoImpl;
import com.ssafy.service.NoticeService;
import com.ssafy.vo.Notice;

public class NoticeServiceImpl implements NoticeService {
    private NoticeDao noticeDao;

    /**
     * 싱글톤
     */
    private static NoticeServiceImpl noticeService;

    public static NoticeServiceImpl getInstance() {
        if (noticeService == null) noticeService = new NoticeServiceImpl();
        return noticeService;
    }

    private NoticeServiceImpl() {
        noticeDao = NoticeDaoImpl.getInstance();
    }

    @Override
    public List<Notice> findAll() throws Exception {
        return noticeDao.findAll();
    }

    @Override
    public Notice findById(int id) throws Exception {
        return noticeDao.findById(id);
    }

    @Override
    public void registerNotice(Notice notice) throws Exception {
        noticeDao.registerNotice(notice);
    }

    @Override
    public void update(Notice notice) throws Exception {
        noticeDao.update(notice);
    }

    @Override
    public void deleteById(int id) throws Exception {
        noticeDao.deleteById(id);
    }

}
