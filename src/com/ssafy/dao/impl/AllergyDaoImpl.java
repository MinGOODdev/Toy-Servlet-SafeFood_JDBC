package com.ssafy.dao.impl;

import com.ssafy.dao.AllergyDao;
import com.ssafy.util.DBUtil;
import com.ssafy.vo.Allergy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AllergyDaoImpl implements AllergyDao {
    private static AllergyDaoImpl allergyDao;

    public static AllergyDaoImpl getInstance() {
        if (allergyDao == null) allergyDao = new AllergyDaoImpl();
        return allergyDao;
    }

    @Override
    public List<Allergy> findAll() throws Exception {
        ArrayList<Allergy> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT idx, name FROM ALLERGY";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Allergy(
                        rs.getInt(1),
                        rs.getString(2)
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
    public Allergy findByIdx(int idx) throws Exception {
        Allergy allergy = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT idx, name FROM ALLERGY WHERE idx = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idx);
            rs = stmt.executeQuery();

            if (rs.next()) {
                allergy = new Allergy(
                        rs.getInt(1),
                        rs.getString(2)
                );
            }

        } finally {
            DBUtil.close(rs);
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
        return allergy;
    }
}
