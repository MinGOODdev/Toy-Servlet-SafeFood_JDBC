package com.ssafy.controller;

import com.ssafy.service.NoticeService;
import com.ssafy.service.impl.NoticeServiceImpl;
import com.ssafy.vo.Notice;
import com.ssafy.vo.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeController {
    private NoticeService noticeService;

    /**
     * 싱글톤
     **/
    private static NoticeController noticeController;

    public static NoticeController getInstance() {
        if (noticeController == null) noticeController = new NoticeController();
        return noticeController;
    }

    private NoticeController() {
        noticeService = NoticeServiceImpl.getInstance();
    }

    /**
     * 모든 공지사항
     */
    public PageInfo getNoticeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("noticeList", noticeService.findAll());
        return new PageInfo(true, "WEB-INF/notice/noticeList.jsp");
    }

    /**
     * 해당 공지글 보기
     */
    public PageInfo getNoticeDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("noticeDetail", noticeService.findById(id));
        return new PageInfo(true, "WEB-INF/notice/noticeDetail.jsp");
    }

    /**
     * 공지 등록 (GET)
     */
    public PageInfo getWrite(HttpServletRequest request, HttpServletResponse response) {
        return new PageInfo(true, "WEB-INF/notice/noticeWrite.jsp");
    }

    /**
     * 공지 등록 (POST)
     */
    public PageInfo registerNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        String writer = request.getParameter("writer");

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
        String createdAt = simpleDateFormat.format(date);

        noticeService.registerNotice(new Notice(title, contents, writer, createdAt, createdAt));
        return new PageInfo("main.do?action=noticeList");
    }

    /**
     * 공지 수정 (GET)
     */
    public PageInfo getEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("notice", noticeService.findById(id));
        return new PageInfo(true, "WEB-INF/notice/noticeEdit.jsp");
    }

    /**
     * 공지 수정 (POST)
     */
    public PageInfo editNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm");
        String updatedAt = simpleDateFormat.format(date);

        Notice notice = noticeService.findById(id);
        notice.setTitle(request.getParameter("title"));
        notice.setContents(request.getParameter("contents"));
        notice.setUpdatedAt(updatedAt);

        noticeService.update(notice);
        return new PageInfo("main.do?action=noticeList");
    }

    /**
     * 공지 삭제
     */
    public PageInfo deleteNotice(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        noticeService.deleteById(id);
        return new PageInfo("main.do?action=noticeList");
    }


}
