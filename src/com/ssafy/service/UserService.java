package com.ssafy.service;

import com.ssafy.vo.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void add(User user);

    void delete(String id);

    User[] searchByName(String name);

    User searchById(String id);

}
