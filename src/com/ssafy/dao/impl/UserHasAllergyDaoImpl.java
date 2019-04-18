package com.ssafy.dao.impl;

import com.ssafy.dao.UserHasAllergyDao;
import com.ssafy.util.DBUtil;
import com.ssafy.vo.Allergy;
import com.ssafy.vo.UserHasAllergy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHasAllergyDaoImpl implements UserHasAllergyDao {
    private static UserHasAllergyDaoImpl userHasAllergyDao;

    public static UserHasAllergyDaoImpl getInstance() {
        if (userHasAllergyDao == null) userHasAllergyDao = new UserHasAllergyDaoImpl();
        return userHasAllergyDao;
    }

    @Override
    public void insert(int id, List<Allergy> result) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO USER_HAS_ALLERGY (user_id, allergy_idx) VALUES (?, ?)";

        try {
            conn = DBUtil.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(sql);

            for (Allergy a : result) {
                System.out.println(a.getIdx());
                stmt.setInt(1, id);
                stmt.setInt(2, a.getIdx());
                stmt.addBatch();
            }
            stmt.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
            throw e;

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

    @Override
    public List<UserHasAllergy> findAllByUserId(int userId) throws Exception {
        ArrayList<UserHasAllergy> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT user_id, allergy_idx FROM USER_HAS_ALLERGY WHERE user_id = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            rs = stmt.executeQuery();

            while(rs.next()) {
                list.add(new UserHasAllergy(
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

    @Override
    public void deleteByUserId(int userId) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM USER_HAS_ALLERGY WHERE user_id = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

}
