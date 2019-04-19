package com.ssafy.controller;

import com.ssafy.service.*;
import com.ssafy.service.impl.*;
import com.ssafy.vo.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class UserController {
	private UserService userService;
	private FoodService foodService;
	private EatFoodService eatFoodService;
	private UserHasAllergyService userHasAllergyService;
	private AllergyService allergyService;
	private FoodHasAllergyService foodHasAllergyService;

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
		userHasAllergyService = UserHasAllergyServiceImpl.getInstance();
		allergyService = AllergyServiceImpl.getInstance();
		foodHasAllergyService = FoodHasAllergyServiceImpl.getInstance();
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

		// 사용자의 알러지 목록
		List<UserHasAllergy> userHasAllergies = userHasAllergyService.findAllByUserId(id);
		List<Allergy> userAllergy = new ArrayList<>();
		for (UserHasAllergy userHasAllergy : userHasAllergies) {
			Allergy allergy = allergyService.findByIdx(userHasAllergy.getAllergyIdx());
			userAllergy.add(allergy);
		}

		List<EatFood> eatFoods = eatFoodService.findAllByUserId(id);
		List<UserFood> userFoods = new ArrayList<>();	// 뷰를 위한 변수
		for (EatFood eatFood : eatFoods) {
			Food food = foodService.search(eatFood.getFoodCode());
			int count = eatFood.getCount();

			List<FoodHasAllergy> foodHasAllergies = foodHasAllergyService.findAllByFoodCode(eatFood.getFoodCode());

			List<Allergy> allergies = new ArrayList<>();
			for (FoodHasAllergy foodHasAllergy : foodHasAllergies) {
				int allergyIdx = foodHasAllergy.getAllergyIdx();
				Allergy allergy = allergyService.findByIdx(allergyIdx);
				for (Allergy temp : userAllergy) {
					if (temp.getName().equals(allergy.getName())) allergies.add(allergy);
				}
			}
			System.out.println(allergies);

			userFoods.add(new UserFood(food, count, allergies));
		}
		request.setAttribute("purchaseList", userFoods);
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
