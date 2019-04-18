package com.ssafy.service.impl;

import com.ssafy.service.CheckService;
import com.ssafy.service.UserService;
import com.ssafy.vo.User;

import java.util.HashMap;

public class CheckServiceImpl implements CheckService {
    private UserService userService;

    /**
     * 싱글톤
     */
    private static CheckServiceImpl checkService;

    public static CheckServiceImpl getInstance() {
        if (checkService == null) checkService = new CheckServiceImpl();
        return checkService;
    }

    private CheckServiceImpl() {
        userService = UserServiceImpl.getInstance();
    }

    /**
     * 아이디, 비밀번호 유효성 검사
     **/
    @Override
    public boolean checkAccount(String userId, String pw) throws Exception {
        User user = userService.findByUserId(userId);
        if (user != null && user.getPassword().equals(pw)) return true;
        else return false;
    }

    /**
     * 비밀번호 찾기 검사
     **/
    @Override
    public String findPassword(String userId, String name) throws Exception {
        User user = userService.findByUserId(userId);
        if (user != null && user.getName().equals(name)) return user.getPassword();
        else return null;
    }

    /**
     * ID, NAME null 체크
     **/
    @Override
    public HashMap<String, String> checkFindPw(String userId, String name) {
        HashMap<String, String> errorMessages = new HashMap<>();
        if (userId == null || userId.trim().length() == 0) errorMessages.put("idError", "아이디가 입력되지 않았습니다.");
        if (name == null || name.trim().length() == 0) errorMessages.put("nameError", "이름이 입력되지 않았습니다.");
        return errorMessages;
    }

    /**
     * ID, PW null 체크
     **/
    @Override
    public HashMap<String, String> checkNullForLogin(String userId, String pw) {
        HashMap<String, String> errorMessages = new HashMap<>();
        if (userId == null || userId.trim().length() == 0) errorMessages.put("idError", "아이디가 입력되지 않았습니다.");
        if (pw == null || pw.trim().length() == 0) errorMessages.put("pwError", "비밀번호가 입력되지 않았습니다.");
        return errorMessages;
    }

    /**
     * ID, PW, NAME 체크
     */
    @Override
    public HashMap<String, String> checkForSignUp(String userId, String pw, String name) throws Exception {
        HashMap<String, String> errorMessages = this.checkNullForLogin(userId, pw);
        if (name == null || name.trim().length() == 0) errorMessages.put("nameError", "이름이 입력되지 않았습니다.");

        User user = userService.findByUserId(userId);
        if (user != null) errorMessages.put("idAlready", "입력한 아이디가 이미 존재합니다.");
        return errorMessages;
    }
}
