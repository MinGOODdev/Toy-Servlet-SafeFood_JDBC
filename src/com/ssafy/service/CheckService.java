package com.ssafy.service;

import java.util.HashMap;

public interface CheckService {

    boolean checkAccount(String id, String pw);

    HashMap<String, String> checkNullForLogin(String id, String pw);

    HashMap<String, String> checkForSignUp(String id, String pw, String name, int age, String gender);

    String findPassword(String id, String name);

	HashMap<String, String> checkFindPw(String id, String name);

}
