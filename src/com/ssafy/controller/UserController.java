package com.ssafy.controller;

import com.ssafy.service.FoodService;
import com.ssafy.service.UserService;
import com.ssafy.service.impl.FoodServiceImpl;
import com.ssafy.service.impl.UserServiceImpl;
import com.ssafy.vo.Food;
import com.ssafy.vo.FoodPageBean;
import com.ssafy.vo.PageInfo;
import com.ssafy.vo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserController {
	private UserService userService;
	private FoodService foodService;

	/**
	 * 싱글톤
	 */
	private static UserController userController;

	public static UserController getInstance() {
		if (userController == null)
			userController = new UserController();
		return userController;
	}

	private UserController() {
		foodService = FoodServiceImpl.getInstance();
		userService = UserServiceImpl.getInstance();
	}

	/**
	 * 해당 회원의 구매(섭취) 내역 조회
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public PageInfo getPurchaseListByUser(HttpServletRequest request, HttpServletResponse response) {
		String id = (String) request.getSession().getAttribute("userId");
		List<String> allergyCaution = new ArrayList<>();
		for (User u : userService.findAll()) {
			if (u.getId().equalsIgnoreCase(id)) {
				request.setAttribute("purchaseList", u.getFoodList());
				for (Food f : u.getFoodList()) {
					boolean check = false;
					String caution = "알러지 경고!! " + f.getName() + "에 ";
					for (String a : u.getAllergyList()) {
						if (f.getAllergy().contains(a)) {
							caution = caution + a + " ";
							check = true;
						}
					}
					caution = caution + "가 함유되어 있습니다.";
					if (check)
						allergyCaution.add(caution);
				}
				request.setAttribute("allergyCaution", allergyCaution);
			}
		}
		return new PageInfo(true, "WEB-INF/user/orderList.jsp");
	}

	/**
	 * 식품 구매(섭취)
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public PageInfo doPurchase(HttpServletRequest request, HttpServletResponse response) {
		String id = (String) request.getSession().getAttribute("userId");
		String code = request.getParameter("code");

		Food temp = null;
		for (Food b : foodService.searchAll(new FoodPageBean())) {
			if (b.getCode() == Integer.parseInt(code))
				temp = b;
		}

		for (User u : userService.findAll()) {
			if (u.getId().equalsIgnoreCase(id)) {
				u.getFoodList().add(temp);
			}
		}
		return new PageInfo("main.do?action=foodList");
	}

	/**
	 * 섭취한 식품 삭제
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public PageInfo deletePurchase(HttpServletRequest request, HttpServletResponse response) {
		String id = (String) request.getSession().getAttribute("userId");
		String code = request.getParameter("code");

		User user = null;
		for (User u : userService.findAll()) {
			if (u.getId().equalsIgnoreCase(id)) user = u;
		}

		List<Food> foods = user.getFoodList();
		for (int i = 0; i < foods.size(); ++i) {
			if (foods.get(i).getCode() == Integer.parseInt(code)) foods.remove(i);
		}
		return new PageInfo("main.do?action=orderList");
	}
}
