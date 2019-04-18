package com.ssafy.service;

import com.ssafy.vo.User;

import java.util.List;

public interface UserService {

    List<User> findAll() throws Exception;

    void insert(User user) throws Exception;

    void delete(String id) throws Exception;

    void update(User user) throws Exception;

    User findByUserId(String userId) throws Exception;

}
