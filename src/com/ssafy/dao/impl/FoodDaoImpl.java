package com.ssafy.dao.impl;

import com.ssafy.dao.FoodDao;
import com.ssafy.util.DBUtil;
import com.ssafy.vo.Food;
import com.ssafy.vo.FoodPageBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FoodDaoImpl implements FoodDao {
	/**
	 * 싱글톤
	 */
	private static FoodDaoImpl foodDao;
	public static FoodDaoImpl getInstance() {
		if (foodDao == null) foodDao = new FoodDaoImpl();
		return foodDao;
	}

	private FoodDaoImpl() {

	}

	/**
	 * 식품 영양학 정보와 식품 정보를 DB에서 읽어온다.
	 */
	private List<Food> loadData() {
		List<Food> foods = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select * from FOOD";
		try {
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			while(rs.next()) {
				Food food = new Food();
				food.setCode(rs.getInt(1));
				food.setName(rs.getString(2));
				food.setSupportpereat(rs.getDouble(3));
				food.setCalory(rs.getDouble(4));
				food.setCarbo(rs.getDouble(5));
				food.setProtein(rs.getDouble(6));
				food.setFat(rs.getDouble(7));
				food.setSugar(rs.getDouble(8));
				food.setNatrium(rs.getDouble(9));
				food.setChole(rs.getDouble(10));
				food.setFattyacid(rs.getDouble(11));
				food.setTransfat(rs.getDouble(12));
				food.setMaker(rs.getString(13));
				food.setMaterial(rs.getString(14));
				food.setImg(rs.getString(15));
				foods.add(food);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return foods;
	}

	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 식품 정보(Food)를 검색해서 반환.
	 *
	 * @param bean 검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 식품 목록
	 */
	@Override
	public List<Food> searchAll(FoodPageBean bean) {
		List<Food> finds = new ArrayList<>();
		List<Food> foods = loadData();
		if (bean != null) {
            String key = bean.getKey();
            String word = bean.getWord();
            if (!key.equals("all") && word != null && !word.trim().equals("")) {
                switch (key) {
                    case "name":
                        for (Food f : foods) {
                            if (f.getName().contains(word)) finds.add(f);
                        }
                        break;
                    case "maker":
                        for (Food f : foods) {
                            if (f.getMaker().contains(word)) finds.add(f);
                        }
                        break;
                    case "material":
                        for (Food f : foods) {
                            if (f.getMaterial().contains(word)) finds.add(f);
                        }
                        break;
                }
                return finds;
            }else {
            	return foods;
            }
        }
        return finds;
	}

	/**
	 * 식품 코드에 해당하는 식품정보를 검색해서 반환.
	 *
	 * @param code 검색할 식품 코드
	 * @return 식품 코드에 해당하는 식품 정보, 없으면 null이 리턴됨
	 */
	@Override
	public Food search(int code) {
		List<Food> foods = loadData();
		for (Food food : foods) {
			if (food.getCode() == code) {
				return food;
			}
		}
		return null;
	}
}
