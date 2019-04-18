package com.ssafy.test;

import com.ssafy.service.impl.NoticeServiceImpl;
import com.ssafy.service.impl.UserServiceImpl;
import com.ssafy.vo.Food;
import com.ssafy.vo.Notice;
import com.ssafy.vo.User;

import java.util.ArrayList;
import java.util.List;

public class DataInit {

    public static void init() throws Exception {
//        userInit();
//        noticeInit();
    }

    private static void userInit() throws Exception {
        User user = new User("1", "1", "싸피");
        UserServiceImpl.getInstance().insert(user);
    }

    private static void noticeInit() {
        Notice notice1 = new Notice("SSAFY 교육생만을 위한 첫번째 혜택",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        Notice notice2 = new Notice("SSAFY 교육생만을 위한 두번째 혜택", "4반 체고");
        Notice notice3 = new Notice("SSAFY 교육생만을 위한 세번째 혜택", "00000000000000000000000000000000234123482341234189237410823417234712347132412");
        NoticeServiceImpl.getInstance().registerNotice(notice1);
        NoticeServiceImpl.getInstance().registerNotice(notice2);
        NoticeServiceImpl.getInstance().registerNotice(notice3);
    }

}
