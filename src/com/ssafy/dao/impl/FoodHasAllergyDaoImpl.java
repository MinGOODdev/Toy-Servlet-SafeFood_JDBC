package com.ssafy.dao.impl;

import com.ssafy.dao.FoodHasAllergyDao;
import com.ssafy.util.DBUtil;
import com.ssafy.vo.FoodHasAllergy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FoodHasAllergyDaoImpl implements FoodHasAllergyDao {
    private static FoodHasAllergyDaoImpl foodHasAllergyDao;

    public static FoodHasAllergyDaoImpl getInstance() {
        if (foodHasAllergyDao == null) foodHasAllergyDao = new FoodHasAllergyDaoImpl();
        return foodHasAllergyDao;
    }

    private FoodHasAllergyDaoImpl() {

    }

    @Override
    public List<FoodHasAllergy> findAllByFoodCode(int foodCode) throws Exception {
        ArrayList<FoodHasAllergy> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT food_Code, allergy_Idx FROM FOOD_HAS_ALLERGY WHERE food_Code = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, foodCode);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new FoodHasAllergy(
                        rs.getInt(1),
                        rs.getInt(2)
                ));
            }

        } finally {
            DBUtil.close(rs);
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
        return list;
    }
}
