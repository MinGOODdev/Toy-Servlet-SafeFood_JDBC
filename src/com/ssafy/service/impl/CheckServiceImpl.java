package com.ssafy.service.impl;

import com.ssafy.service.CheckService;
import com.ssafy.service.UserService;
import com.ssafy.vo.User;

import java.util.HashMap;
import java.util.List;

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
	 *
	 * @param id
	 * @param pw
	 * @return
	 */
	@Override
	public boolean checkAccount(String id, String pw) {
		List<User> users = userService.findAll();
		for (User u : users) {
			if (u.getId().equalsIgnoreCase(id) && u.getPw().equals(pw)) {
				System.out.println(u.getAllergyList());
				return true;
			}
		}
		return false;
	}

	/**
	 * 비밀번호 찾기 검사
	 *
	 * @param id
	 * @param pw
	 * @return
	 */
	@Override
	public String findPassword(String id, String name) {
		List<User> users = userService.findAll();
		for (User u : users) {
			if (u.getId().equalsIgnoreCase(id) && u.getName().equals(name)) {
				return u.getPw();
			}
		}
		return null;
	}
	
	/**
	 * ID, NAME null 체크
	 *
	 * @param id
	 * @param pw
	 * @return
	 */
	@Override
	public HashMap<String, String> checkFindPw(String id, String name) {
		HashMap<String, String> errorMessages = new HashMap<>();
		if (id == null || id.trim().length() == 0) errorMessages.put("idError", "아이디가 입력되지 않았습니다.");
		if (name == null || name.trim().length() == 0) errorMessages.put("nameError", "이름이 입력되지 않았습니다.");
		return errorMessages;
	}
	
	/**
	 * ID, PW null 체크
	 *
	 * @param id
	 * @param pw
	 * @return
	 */
	@Override
	public HashMap<String, String> checkNullForLogin(String id, String pw) {
		HashMap<String, String> errorMessages = new HashMap<>();
		if (id == null || id.trim().length() == 0) errorMessages.put("idError", "아이디가 입력되지 않았습니다.");
		if (pw == null || pw.trim().length() == 0) errorMessages.put("pwError", "비밀번호가 입력되지 않았습니다.");
		return errorMessages;
	}

	/**
	 * ID, PW, NAME, AGE, GENDER 체크
	 *
	 * @param id
	 * @param pw
	 * @param name
	 * @param age
	 * @param gender
	 * @return
	 */
	@Override
	public HashMap<String, String> checkForSignUp(String id, String pw, String name, int age, String gender) {
		HashMap<String, String> errorMessages = this.checkNullForLogin(id, pw);
		if (name == null || name.trim().length() == 0) errorMessages.put("nameError", "이름이 입력되지 않았습니다.");
		if (age <= 0) errorMessages.put("ageError", "나이를 올바르게 입력해주세요.");
		if (gender == null || gender.trim().length() == 0) errorMessages.put("genderError", "성별을 선택해주세요.");

		List<User> users = userService.findAll();
		for (User u : users) {
			if (u.getId().equalsIgnoreCase(id)) errorMessages.put("idAlready", "입력한 아이디가 이미 존재합니다.");
		}
		return errorMessages;
	}
}
