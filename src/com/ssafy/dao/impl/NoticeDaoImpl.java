package com.ssafy.dao.impl;

import com.ssafy.dao.NoticeDao;
import com.ssafy.util.DBUtil;
import com.ssafy.vo.Notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NoticeDaoImpl implements NoticeDao {
    private static NoticeDaoImpl noticeDao;

    public static NoticeDaoImpl getInstance() {
        if (noticeDao == null) noticeDao = new NoticeDaoImpl();
        return noticeDao;
    }

    private NoticeDaoImpl() {

    }

    @Override
    public List<Notice> findAll() throws Exception {
        ArrayList<Notice> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id, title, contents, writer, createdAt, updatedAt FROM NOTICE";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                list.add(new Notice(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
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
    public Notice findById(int id) throws Exception {
        Notice notice = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT id, title, contents, writer, createdAt, updatedAt FROM NOTICE WHERE id = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                notice = new Notice(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)
                );
            }

        } finally {
            DBUtil.close(rs);
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
        return notice;
    }

    @Override
    public void registerNotice(Notice notice) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "INSERT INTO NOTICE (title, contents, writer, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?)";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, notice.getTitle());
            stmt.setString(2, notice.getContents());
            stmt.setString(3, notice.getWriter());
            stmt.setString(4, notice.getCreatedAt());
            stmt.setString(5, notice.getUpdatedAt());
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

    @Override
    public void update(Notice notice) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "UPDATE NOTICE SET title = ?, contents = ?, updatedAt = ? WHERE id = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, notice.getTitle());
            stmt.setString(2, notice.getContents());
            stmt.setString(3, notice.getUpdatedAt());
            stmt.setInt(4, notice.getId());
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }

    @Override
    public void deleteById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = "DELETE FROM NOTICE WHERE id = ?";

        try {
            conn = DBUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } finally {
            DBUtil.close(stmt);
            DBUtil.close(conn);
        }
    }
}
