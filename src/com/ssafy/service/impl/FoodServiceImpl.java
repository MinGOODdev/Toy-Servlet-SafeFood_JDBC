package com.ssafy.service.impl;

import com.ssafy.dao.FoodDao;
import com.ssafy.dao.UserDao;
import com.ssafy.dao.impl.FoodDaoImpl;
import com.ssafy.dao.impl.UserDaoImpl;
import com.ssafy.service.FoodService;
import com.ssafy.util.DBUtil;
import com.ssafy.vo.Food;
import com.ssafy.vo.User;
import com.ssafy.vo.FoodPageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FoodServiceImpl implements FoodService {
    private FoodDao fooddao;
    private UserDao userdao;
    /*    
	private String[] allergys = {"대두", "땅콩", "우유", "게", "새우",
    		"참치", "연어", "쑥", "소고기", "닭고기", "돼지고기",
    		"복숭아", "민들레", "계란흰자"};
    */
    private int[] manNut = {2600, 360, 55, 100};
    private int[] womanNut = {2100, 290, 50, 80};
    
    /**
     * 싱글톤
     */
    private static FoodServiceImpl foodService;

    public static FoodServiceImpl getInstance() {
        if (foodService == null) {
        	foodService = new FoodServiceImpl();
        }
        return foodService;
    }

    private FoodServiceImpl() {
        fooddao = FoodDaoImpl.getInstance();
        userdao = UserDaoImpl.getInstance();
    }

    @Override
    public List<Food> searchAll(FoodPageBean bean) {
        return fooddao.searchAll(bean);
    }

    @Override
    public Food search(int code) {
        String allergyList = "";
        Food food = fooddao.search(code);
        Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select name from allergy where idx = any((select ALLERGY_idx from food_has_allergy where FOOD_code = ?))";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, food.getCode());
			rs = stmt.executeQuery();

			while(rs.next()) {
				allergyList = allergyList + rs.getString(1) + " ";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
        // code에 맞는 식품 정보를 검색하고, 검색된 식품의 원재료에 알레르기 성분이 있는지 확인하여 Food 정보에 입력한다.
        food.setAllergy(allergyList);
        return food;
    }
    
    public String oversearch(int code, String id) {
    	String OverList = "";
    	Food food = fooddao.search(code);
//    	User user = userdao.searchById(id);
//
//    	if(user.getGender().equals("남")) {
//    		if(food.getCalory() > manNut[0]/2) OverList = OverList + "칼로리 ";
//            if(food.getCarbo() > manNut[1]/2) OverList = OverList + "탄수화물 ";
//            if(food.getProtein() > manNut[2]/2) OverList = OverList + "단백질 ";
//            if(food.getFat() > manNut[3]/2) OverList = OverList + "지방 ";
//    	} else {
//    		if(food.getCalory() > womanNut[0]/2) OverList = OverList + "칼로리 ";
//            if(food.getCarbo() > womanNut[1]/2) OverList = OverList + "탄수화물 ";
//            if(food.getProtein() > womanNut[2]/2) OverList = OverList + "단백질 ";
//            if(food.getFat() > womanNut[3]/2) OverList = OverList + "지방 ";
//    	}
		return OverList;
    }
}