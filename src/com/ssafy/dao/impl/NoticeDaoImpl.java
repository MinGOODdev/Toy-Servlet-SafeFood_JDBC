package com.ssafy.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.dao.NoticeDao;
import com.ssafy.vo.Notice;

public class NoticeDaoImpl implements NoticeDao {
    private List<Notice> notices;
    private static NoticeDaoImpl noticeDao;

    public static NoticeDaoImpl getInstance() {
        if (noticeDao == null) noticeDao = new NoticeDaoImpl();
        return noticeDao;
    }

    private NoticeDaoImpl() {
        notices = new ArrayList<>();
    }

    @Override
    public List<Notice> searchAll() {
        return notices;
    }

    @Override
    public Notice search(String title) {
        if (notices != null) {
            for (Notice n : notices) {
                if (n.getTitle().equals(title)) return n;
            }
        }
        return null;
    }

    @Override
    public void registerNotice(Notice notice) {
        notices.add(notice);
    }

    @Override
    public void deleteNotice(String title) {
        int index = -1;
        if (notices != null) {
            for (int i = 0; i < notices.size(); i++) {
                if (notices.get(i).getTitle().equals(title)) {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) return;
        else notices.remove(index);
    }

}
