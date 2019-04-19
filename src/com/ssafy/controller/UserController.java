package com.ssafy.controller;

import com.ssafy.service.EatFoodService;
import com.ssafy.service.FoodService;
import com.ssafy.service.UserService;
import com.ssafy.service.impl.EatFoodServiceImpl;
import com.ssafy.service.impl.FoodServiceImpl;
import com.ssafy.service.impl.UserServiceImpl;
import com.ssafy.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserController {
	private UserService userService;
	private FoodService foodService;
	private EatFoodService eatFoodService;

	/**
	 * 싱글톤
	 */
	private static UserController userController;

	public static UserController getInstance() {
		if (userController == null) userController = new UserController();
		return userController;
	}

	private UserController() {
		foodService = FoodServiceImpl.getInstance();
		userService = UserServiceImpl.getInstance();
		eatFoodService = EatFoodServiceImpl.getInstance();
	}

	/**
	 * 해당 회원의 구매(섭취) 내역 조회
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public PageInfo getPurchaseListByUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String) request.getSession().getAttribute("userId");
		int id = userService.findByUserId(userId).getId();
		List<EatFood> eatFoods = eatFoodService.findAllByUserId(id);
		List<Food> foods = new ArrayList<>();
		List<Integer> count = new ArrayList<>();
		for (EatFood eatFood : eatFoods) {
			foods.add(foodService.search(eatFood.getFoodCode()));
			count.add(eatFood.getCount());
		}
		request.setAttribute("purchaseList", foods);
		return new PageInfo(true, "WEB-INF/user/orderList.jsp");
	}

	/**
	 * 식품 구매(섭취)
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public PageInfo doPurchase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String) request.getSession().getAttribute("userId");
		int id = userService.findByUserId(userId).getId();
		int code = Integer.parseInt(request.getParameter("code"));

		EatFood eatFood = eatFoodService.findOne(id, code);
		if (eatFood == null) eatFoodService.insert(new EatFood(id, code, 1));
		else if (eatFood != null) eatFoodService.update(eatFood);

		return new PageInfo("main.do?action=foodList");
	}

	/**
	 * 섭취한 식품 삭제
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public PageInfo deletePurchase(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = (String) request.getSession().getAttribute("userId");
		int id = userService.findByUserId(userId).getId();
		int code = Integer.parseInt(request.getParameter("code"));

		eatFoodService.delete(id, code);
		return new PageInfo("main.do?action=orderList");
	}
}
