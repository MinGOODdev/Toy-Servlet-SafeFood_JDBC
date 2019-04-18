package com.ssafy.dao;

import com.ssafy.vo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> findAll() throws Exception;

    void insert(User user) throws Exception;

    void update(User user) throws Exception;

    void delete(String userId) throws Exception;

    User findByUserId(String userId) throws Exception;

}
