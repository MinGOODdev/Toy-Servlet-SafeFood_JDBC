package com.ssafy.controller;

import com.ssafy.service.NoticeService;
import com.ssafy.service.impl.NoticeServiceImpl;
import com.ssafy.vo.Notice;
import com.ssafy.vo.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoticeController {
    private NoticeService noticeService;

    /** 싱글톤 **/
    private static NoticeController noticeController;

    public static NoticeController getInstance() {
        if (noticeController == null) noticeController = new NoticeController();
        return noticeController;
    }

    private NoticeController() {
        noticeService = NoticeServiceImpl.getInstance();
    }

    //모든 공지사항
    public PageInfo getNoticeList(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("noticeList", noticeService.searchAll());
        return new PageInfo(true, "WEB-INF/notice/noticeList.jsp");
    }

    //공지사항 글 보기
    public PageInfo getNoticeDetail(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        request.setAttribute("noticeDetail", noticeService.search(title));
        return new PageInfo(true, "WEB-INF/notice/noticeDetail.jsp");
    }

    public PageInfo getWrite(HttpServletRequest request, HttpServletResponse response) {
        return new PageInfo(true, "WEB-INF/notice/noticeWrite.jsp");
    }

    public PageInfo registerNotice(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        Notice notice = new Notice(title, contents);
        noticeService.registerNotice(notice);
        return new PageInfo("main.do?action=noticeList");

    }

    public PageInfo deleteNotice(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter("title");
        noticeService.deleteNotice(title);
        return new PageInfo("main.do?action=noticeList");
    }
}
