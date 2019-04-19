package com.ssafy.controller;

import com.ssafy.service.AllergyService;
import com.ssafy.service.CheckService;
import com.ssafy.service.UserHasAllergyService;
import com.ssafy.service.UserService;
import com.ssafy.service.impl.AllergyServiceImpl;
import com.ssafy.service.impl.CheckServiceImpl;
import com.ssafy.service.impl.UserHasAllergyServiceImpl;
import com.ssafy.service.impl.UserServiceImpl;
import com.ssafy.vo.Allergy;
import com.ssafy.vo.PageInfo;
import com.ssafy.vo.User;
import com.ssafy.vo.UserHasAllergy;

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

    /**
     * 로그인
     **/
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
        } else {
            return new PageInfo(true, "login.jsp");
        }
    }

    /**
     * 로그아웃
     **/
    public PageInfo logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return new PageInfo("login.jsp");
    }

    /**
     * 비밀번호 찾기
     **/
    public PageInfo findPw(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        HashMap<String, String> errorMessages = checkService.checkFindPw(id, name);
        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            return new PageInfo(true, "findPw.jsp");
        }

        String pw = checkService.findPassword(id, name);
        if (pw != null) return new PageInfo("main.do?action=yourPwHere&pw=" + pw);
        else return new PageInfo(true, "findPw.jsp");
    }

    /**
     * 회원가입 (GET)
     */
    public PageInfo getSignUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Allergy> allergies = allergyService.findAll();
        request.setAttribute("allergies", allergies);
        return new PageInfo(true, "signUp.jsp");
    }

    /**
     * 화원가입 (POST)
     **/
    public PageInfo signUp(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String[] allergy = request.getParameterValues("allergy[]");

        HashMap<String, String> errorMessages = checkService.checkForSignUp(userId, pw, name);
        if (errorMessages.size() > 0) {
            request.setAttribute("errorMessages", errorMessages);
            List<Allergy> result = this.selectedAllergies(allergy);
            userHasAllergyService.insert(userService.findByUserId(userId).getId(), result);
            return new PageInfo(true, "signUp.jsp");
        }

        User user = new User(userId, pw, name);
        userService.insert(user);

        List<Allergy> result = this.selectedAllergies(allergy);
        userHasAllergyService.insert(userService.findByUserId(userId).getId(), result);

        return new PageInfo("login.jsp");
    }

    /**
     * 회원 정보 수정 (GET)
     **/
    public PageInfo getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getSession().getAttribute("userId");
        User user = userService.findByUserId(userId);
        request.setAttribute("user", user);

        List<UserHasAllergy> list = userHasAllergyService.findAllByUserId(user.getId());
        List<Allergy> allergies = new ArrayList<>();
        for (UserHasAllergy temp : list) {
            allergies.add(allergyService.findByIdx(temp.getAllergyIdx()));
        }
        request.setAttribute("allergyList", allergies);
        request.setAttribute("allergies", allergyService.findAll());

        return new PageInfo(true, "WEB-INF/user/mypage.jsp");
    }

    /**
     * 회원 정보 수정 (POST)
     **/
    public PageInfo updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = (String) request.getSession().getAttribute("userId");
        User user = userService.findByUserId(userId);
        String name = request.getParameter("name");
        String[] allergy = request.getParameterValues("allergy[]");


        userHasAllergyService.deleteByUserId(user.getId());
        user.setName(name);
        userService.update(user);
        List<Allergy> result = this.selectedAllergies(allergy);
        userHasAllergyService.insert(user.getId(), result);

        return new PageInfo("main.do?action=mypage");
    }

    /**
     * 회원 삭제
     **/
    public PageInfo deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userId = request.getParameter("id");
        userService.delete(userId);
        return new PageInfo("main.do?action=userList");
    }

    /**
     * 회원 전체 목록 조회
     **/
    public PageInfo getUserList(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("users", userService.findAll());
        return new PageInfo(true, "WEB-INF/user/list.jsp");
    }

    public PageInfo yourPwHere(HttpServletRequest request, HttpServletResponse response) {
        String pw = request.getParameter("pw");
        request.setAttribute("pw", pw);
        return new PageInfo(true, "yourPwHere.jsp");
    }

    /**
     * 선택된 알러지만 포함된 리스트 만들기
     */
    private List<Allergy> selectedAllergies(String[] allergy) throws Exception {
        ArrayList<Allergy> result = new ArrayList<>();
        List<Allergy> allergies = allergyService.findAll();
        if (allergy != null) {
            for (int i = 0; i < allergy.length; ++i) {
                for (Allergy a : allergies) {
                    if (a.getName().equals(allergy[i])) result.add(a);
                }
            }
        }
        return result;
    }
}
