package com.ssafy.controller;

import com.ssafy.dao.AllergyDao;
import com.ssafy.dao.UserHasAllergyDao;
import com.ssafy.dao.impl.AllergyDaoImpl;
import com.ssafy.dao.impl.UserHasAllergyDaoImpl;
import com.ssafy.service.AllergyService;
import com.ssafy.service.CheckService;
import com.ssafy.service.UserHasAllergyService;
import com.ssafy.service.UserService;
import com.ssafy.service.impl.AllergyServiceImpl;
import com.ssafy.service.impl.CheckServiceImpl;
import com.ssafy.service.impl.UserHasAllergyServiceImpl;
import com.ssafy.service.impl.UserServiceImpl;
import com.ssafy.vo.*;

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
    private AllergyService allergyService;
    private UserHasAllergyService userHasAllergyService;

    private static AccountController accountController;
    public static AccountController getInstance() {
        if (accountController == null) accountController = new AccountController();
        return accountController;
    }

    private AccountController() {
        checkService = CheckServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
        allergyService = AllergyServiceImpl.getInstance();
        userHasAllergyService = UserHasAllergyServiceImpl.getInstance();
    }

    /** 로그인 **/
    public PageInfo login(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    /** 로그아웃 **/
    public PageInfo logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new PageInfo("login.jsp");
    }

    /**
     * 비밀번호 찾기
     */
    public PageInfo findPw(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

    /** 화원가입 **/
    public PageInfo signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String[] allergy = request.getParameterValues("allergy[]");

        ArrayList<Allergy> result = new ArrayList<>();
        List<Allergy> allergies = allergyService.findAll();
        if (allergy != null) {
            for (int i = 0; i < allergy.length; ++i) {
                for (Allergy a : allergies) {
                    if (a.getName().equals(allergy[i])) result.add(a);
                }
            }
        }

        for (String s : allergy) System.out.println(s);
        System.out.println(allergies.toString());

        HashMap<String, String> errorMessages = checkService.checkForSignUp(userId, pw, name);
        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return new PageInfo(true, "signUp.jsp");
        }

        User user = new User(userId, pw, name);
        System.out.println(userId + " " + pw + " " + name);
        userService.insert(user);
        System.out.println(userService.searchByUserId(userId) + " " + userService.searchByUserId(userId).getId());
        userHasAllergyService.insert(userService.searchByUserId(userId).getId(), result);
        return new PageInfo("login.jsp");
    }

    /** 회원 정보 수정 (GET) **/
    public PageInfo getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getSession().getAttribute("userId");
        User user = userService.searchByUserId(userId);
        request.setAttribute("user", user);

        List<UserHasAllergy> list = userHasAllergyService.findAllByUserId(user.getId());
        List<Allergy> allergies = new ArrayList<>();
        for (UserHasAllergy temp : list) {
            allergies.add(allergyService.findByIdx(temp.getAllergyIdx()));
        }

        request.setAttribute("allergyList", allergies);
        return new PageInfo(true, "WEB-INF/user/mypage.jsp");
    }

    /** 회원 정보 수정 (POST) **/
    public PageInfo updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getSession().getAttribute("userId");
        User user = userService.searchByUserId(userId);
        String name = request.getParameter("name");
        String[] allergy = request.getParameterValues("allergy[]");

        ArrayList<Allergy> result = new ArrayList<>();
        List<Allergy> allergies = allergyService.findAll();
        if (allergy != null) {
            for (int i = 0; i < allergy.length; ++i) {
                for (Allergy a : allergies) {
                    if (a.getName().equals(allergy[i])) result.add(a);
                }
            }
        }

        user.setName(name);
        userService.update(user);
        userHasAllergyService.deleteByUserId(user.getId());
        userHasAllergyService.insert(user.getId(), result);

        return new PageInfo("main.do?action=mypage");
    }

    /** 회원 삭제 **/
    public PageInfo deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("id");
        userService.delete(userId);
        return new PageInfo("main.do?action=userList");
    }

    /** 회원 전체 목록 조회 **/
    public PageInfo getUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
