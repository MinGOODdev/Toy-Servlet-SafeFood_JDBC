package com.ssafy.controller;

import com.ssafy.service.FoodService;
import com.ssafy.service.impl.FoodServiceImpl;
import com.ssafy.vo.Food;
import com.ssafy.vo.FoodPageBean;
import com.ssafy.vo.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class FoodController {
    private FoodService foodService;

    /**
     * 싱글톤
     */
    private static FoodController foodController;
    public static FoodController getInstance() {
        if (foodController == null) foodController = new FoodController();
        return foodController;
    }

    private FoodController() {
        foodService = FoodServiceImpl.getInstance();
    }

    /**
     * 모든 식품 조회
     */
    public PageInfo getFoodList(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("foods", foodService.searchAll(new FoodPageBean()));
        return new PageInfo(true, "WEB-INF/food/list.jsp");
    }

    /**
     * 식품 상세 조회
     */
    public PageInfo getFoodDetail(HttpServletRequest request, HttpServletResponse response) {
        String code = request.getParameter("code");
        Food food = foodService.search(Integer.parseInt(code));
        String id = (String) request.getSession().getAttribute("userId");
        String overList = foodService.oversearch(Integer.parseInt(code), id);
        request.setAttribute("food", food);
        request.setAttribute("overList", overList);
        return new PageInfo(true, "WEB-INF/food/view.jsp");
    }

    /**
     * 검색 기준에 따른 검색
     *
     * @param request
     * @param response
     * @return
     */
    public PageInfo searchBy(HttpServletRequest request, HttpServletResponse response) {
        String sb = request.getParameter("sb");
        String st = request.getParameter("st");

        List<Food> temp = new ArrayList<>();
        switch (sb) {
            case "all":
                request.setAttribute("foods", foodService.searchAll(new FoodPageBean()));
                break;
            case "code":
                Food food = foodService.search(Integer.parseInt(st));
                temp.add(food);
                request.setAttribute("foods", temp);
                break;
            case "name":
            case "maker":
            case "material":
                for (Food f : foodService.searchAll(new FoodPageBean(sb, st))) {
                    temp.add(f);
                }
                request.setAttribute("foods", temp);
                break;

        }
        return new PageInfo(true, "WEB-INF/food/list.jsp");
    }

//    /**
//     * 식품 삭제
//     *
//     * @param request
//     * @param response
//     * @return
//     */
//    public PageInfo delete(HttpServletRequest request, HttpServletResponse response) {
//        String code = request.getParameter("code");
//        bookMgr.delete(isbn);
//        return new PageInfo("main.do?action=foodList");
//    }

}
