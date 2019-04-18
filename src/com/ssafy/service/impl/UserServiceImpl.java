package com.ssafy.service.impl;

import com.ssafy.dao.UserDao;
import com.ssafy.dao.impl.UserDaoImpl;
import com.ssafy.service.UserService;
import com.ssafy.vo.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    /**
     * 싱글톤
     */
    private static UserServiceImpl userService;
    public static UserServiceImpl getInstance() {
        if (userService == null) userService = new UserServiceImpl();
        return userService;
    }

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public User[] searchByName(String name) {
        return userDao.searchByName(name);
    }

    @Override
    public User searchById(String id) {
        return userDao.searchById(id);
    }
}
