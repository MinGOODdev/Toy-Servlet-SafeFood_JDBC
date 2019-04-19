package com.ssafy.dao.impl;

import com.ssafy.dao.UserDao;
import com.ssafy.util.DBUtil;
import com.ssafy.vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    /**
     * 싱글톤
     **/
    private static UserDaoImpl userDao;

    public static UserDaoImpl getInstance() {
        if (userDao == null) userDao = new UserDaoImpl();
        return userDao;
    }

    /**
     * 회원 전체 조회
     **/
    @Override
    public List<User> findAll() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id, userId, password, name, auth FROM USER";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                ));
            }

        } finally {
            DBUtil.close(rs);
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
        return list;
    }

    /**
     * 회원 추가
     **/
    @Override
    public void insert(User user) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO USER (userId, password, name) VALUES (?, ?, ?)";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

    /**
     * 회원 정보 수정
     **/
    @Override
    public void update(User user) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE USER SET name = ? WHERE id = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setInt(2, user.getId());
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

    /**
     * 회원 삭제
     **/
    @Override
    public void delete(String userId) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM USER WHERE userId = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

    /**
     * 회원 아이디로 검색
     **/
    @Override
    public User findByUserId(String userId) throws Exception {
        User user = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id, userId, password, name, auth FROM USER WHERE userId = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                );
            }

        } finally {
            DBUtil.close(rs);
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
        return user;
    }
}
