package com.ssafy.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.util.DBUtil;
import com.ssafy.util.FoodSaxParser;
import com.ssafy.vo.Food;
import com.ssafy.vo.PageInfo;

public class FoodDBController {
	private static List<Food> foods;
	private static FoodDBController foodDBController;

	public static FoodDBController getInstance() {
		if (foodDBController == null) foodDBController = new FoodDBController();
		return foodDBController;
	}


	public static PageInfo insertFoodDB() throws SQLException {
		FoodSaxParser parser = FoodSaxParser.getInstance();
		foods = parser.getFoods();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO FOOD VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.prepareStatement(sql);
			for(Food food : foods) {
				System.out.println(food.getCode() + "번 음식");
				stmt.setInt(1, food.getCode());
				stmt.setString(2, food.getName());
				stmt.setDouble(3, food.getSupportpereat());
				stmt.setDouble(4, food.getCarbo());
				stmt.setDouble(5, food.getCalory());
				stmt.setDouble(6, food.getProtein());
				stmt.setDouble(7, food.getFat());
				stmt.setDouble(8, food.getSugar());
				stmt.setDouble(9, food.getNatrium());
				stmt.setDouble(10, food.getChole());
				stmt.setDouble(11, food.getFattyacid());
				stmt.setDouble(12, food.getTransfat());
				stmt.setString(13, food.getMaker());
				stmt.setString(14, food.getMaterial());
				stmt.setString(15, food.getImg());
				stmt.addBatch();
			}
			stmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			conn.rollback(); // 롤백이 있기 때문에 catch를 지우면 안됨.
			throw e; // 예외 던짐
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		for(Food food : foods) {
			System.out.println(food.getCode() + "번 음식");
			String materialStr = food.getMaterial();
			String[] strArr = materialStr.split(",");

			for(int i = 0; i < strArr.length; i++) {
				String material = null;
				String origin = null; 
				try {
					if(strArr[i].contains("(")) {
						// 잘라야함
						System.out.println(strArr[i]);
						material = strArr[i].substring(0, strArr[i].indexOf("("));
						origin = strArr[i].substring(strArr[i].indexOf(("("))+ 1, strArr[i].indexOf(")"));
					}else {
						System.out.println(strArr[i]);
						material = strArr[i];
					}
					sql = "INSERT INTO MATERIAL (food_code, name,origin) VALUES (?, ?, ?)";
					conn = DBUtil.getConnection();
					stmt = conn.prepareStatement(sql);
					stmt.setInt(1, food.getCode());
					stmt.setString(2, material);
					stmt.setString(3, origin);
					stmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBUtil.close(stmt);
					DBUtil.close(conn);
				}
			}
		}
		
		try {
			sql = "insert into food_has_allergy select m.food_code,a.idx from (select food_code,name from material where name = any(( select name from allergy ))) m, allergy a where m.name = a.name";
			conn = DBUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}

		return new PageInfo(false, "main.do?action=foodList");
	}

}
