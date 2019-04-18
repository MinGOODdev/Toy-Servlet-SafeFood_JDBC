package com.ssafy.controller;

import com.ssafy.service.CheckService;
import com.ssafy.service.UserService;
import com.ssafy.service.impl.CheckServiceImpl;
import com.ssafy.service.impl.UserServiceImpl;
import com.ssafy.vo.Food;
import com.ssafy.vo.PageInfo;
import com.ssafy.vo.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountController {
    private CheckService checkService;
    private UserService userService;

    private static AccountController accountController;

    public static AccountController getInstance() {
        if (accountController == null) accountController = new AccountController();
        return accountController;
    }

    private AccountController() {
        checkService = CheckServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
    }

    /**
     * 로그인
     *
     * @param request
     * @param response
     * @return
     */
    public PageInfo login(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String check = request.getParameter("idSave");

        HashMap<String, String> errorMessages = checkService.checkNullForLogin(id, pw);
        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return new PageInfo(true, "login.jsp");
        }

        boolean flag = checkService.checkAccount(id, pw);
        if (flag) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", id);

            if (check != null && check.equals("on")) {
                Cookie c = new Cookie("userId", id);
                response.addCookie(c);
            }
            return new PageInfo("main.do?action=foodList");
        } else
            return new PageInfo(true, "login.jsp");
    }

    /**
     * 로그아웃
     *
     * @param request
     * @param response
     * @return
     */
    public PageInfo logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new PageInfo("login.jsp");
    }
    
    /**
     * 비밀번호 찾기
     */
    public PageInfo findPw(HttpServletRequest request, HttpServletResponse response) {
    	String id = request.getParameter("id");
        String name = request.getParameter("name");
        
        HashMap<String, String> errorMessages = checkService.checkFindPw(id, name);
        
        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return new PageInfo(true, "findPw.jsp");
        }
        
        String pw = checkService.findPassword(id, name);
        if (pw != null) {
        	return new PageInfo("main.do?action=yourPwHere&pw=" + pw);
        } else {
        	return new PageInfo(true, "findPw.jsp");
        }        
    }
    
    /**
     * 회원가입
     *
     * @param request
     * @param response
     * @return
     */
    public PageInfo signUp(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String gender = request.getParameter("gender");
        String[] allergy  = request.getParameterValues("allergy[]");

        List<String> allergies = new ArrayList<>();
        if(allergy != null) {
            for(String a : allergy)
                allergies.add(a);
        }

        HashMap<String, String> errorMessages = checkService.checkForSignUp(id, pw, name, age, gender);
        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return new PageInfo(true, "signUp.jsp");
        }

        User user = new User(id, pw, name, age, gender, new ArrayList<Food>(), allergies);
        userService.add(user);
        return new PageInfo("login.jsp");
    }

    /**
     * 회원 정보 수정 (GET)
     *
     * @param request
     * @param response
     * @return
     */
    public PageInfo getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String id = (String) request.getSession().getAttribute("userId");
        User user = userService.searchById(id);
        request.setAttribute("user", user);
        return new PageInfo(true, "WEB-INF/user/mypage.jsp");
    }

    /**
     * 회원 정보 수정 (POST)
     *
     * @param request
     * @param response
     * @return
     */
    public PageInfo updateUser(HttpServletRequest request, HttpServletResponse response) {
        String id = (String) request.getSession().getAttribute("userId");
        User user = userService.searchById(id);
        int age = Integer.parseInt(request.getParameter("age"));
        String[] allergy = request.getParameterValues("allergy[]");

        List<String> allergies = new ArrayList<>();
        if(allergy != null) {
            for(String a : allergy)
                allergies.add(a);
        }

        user.setAge(age);
        user.setAllergyList(allergies);
        return new PageInfo("main.do?action=mypage");
    }

    /**
     * 회원 삭제
     *
     * @param request
     * @param response
     * @return
     */
    public PageInfo deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        userService.delete(id);
        return new PageInfo("main.do?action=userList");
    }

    /**
     * 회원 전체 목록 조회
     *
     * @param request
     * @param response
     * @return
     */
    public PageInfo getUserList(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        return new PageInfo(true, "WEB-INF/user/list.jsp");
    }

	public PageInfo yourPwHere(HttpServletRequest request, HttpServletResponse response) {
		String pw = request.getParameter("pw");
		System.out.println(pw);
		request.setAttribute("pw", pw);
        return new PageInfo(true, "yourPwHere.jsp");
	}
}
