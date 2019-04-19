package com.ssafy.dao.impl;

import com.ssafy.dao.EatFoodDao;
import com.ssafy.util.DBUtil;
import com.ssafy.vo.EatFood;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EatFoodDaoImpl implements EatFoodDao {
    private static EatFoodDaoImpl eatFoodDao;

    public static EatFoodDaoImpl getInstance() {
        if (eatFoodDao == null) eatFoodDao = new EatFoodDaoImpl();
        return eatFoodDao;
    }

    private EatFoodDaoImpl() {

    }

    @Override
    public List<EatFood> findAllByUserId(int userId) throws Exception {
        ArrayList<EatFood> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT userId, foodCode, count FROM EATFOOD WHERE userId = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new EatFood(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                ));
            }

        } finally {
            DBUtil.close(rs);
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
        return list;
    }

    @Override
    public EatFood findOne(int userId, int foodCode) throws Exception {
        EatFood eatFood = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT userId, foodCode, count FROM EATFOOD WHERE userId = ? AND foodCode = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, foodCode);
            rs = stmt.executeQuery();

            while (rs.next()) {
                eatFood = new EatFood(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3)
                );
            }

        } finally {
            DBUtil.close(rs);
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
        return eatFood;
    }

    @Override
    public void insert(EatFood eatFood) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO EATFOOD (userId, foodCode, count) VALUES (?, ?, ?)";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, eatFood.getUserId());
            stmt.setInt(2, eatFood.getFoodCode());
            stmt.setInt(3, eatFood.getCount());
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

    @Override
    public void update(EatFood eatFood) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE EATFOOD SET count = ? WHERE userId = ? AND foodCode = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, eatFood.getCount() + 1);
            stmt.setInt(2, eatFood.getUserId());
            stmt.setInt(3, eatFood.getFoodCode());
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

    @Override
    public void delete(int userId, int foodCode) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM EATFOOD WHERE userId = ? AND foodCode = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.setInt(2, foodCode);
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }
}
