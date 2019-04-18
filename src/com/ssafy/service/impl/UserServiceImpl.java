package com.ssafy.service.impl;

import com.ssafy.dao.UserDao;
import com.ssafy.dao.impl.UserDaoImpl;
import com.ssafy.service.UserHasAllergyService;
import com.ssafy.service.UserService;
import com.ssafy.vo.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private UserHasAllergyService userHasAllergyService;

    /** 싱글톤 **/
    private static UserServiceImpl userService;
    public static UserServiceImpl getInstance() {
        if (userService == null) userService = new UserServiceImpl();
        return userService;
    }

    private UserServiceImpl() {
        userDao = UserDaoImpl.getInstance();
        userHasAllergyService = UserHasAllergyServiceImpl.getInstance();
    }

    @Override
    public List<User> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void insert(User user) throws Exception {
        userDao.insert(user);
    }

    @Override
    public void delete(String userId) throws Exception {
        int id = userDao.searchByUserId(userId).getId();
        userHasAllergyService.deleteByUserId(id);
        userDao.delete(userId);
    }

    @Override
    public void update(User user) throws Exception {
        userDao.update(user);
    }

    @Override
    public User searchByUserId(String userId) throws Exception {
        return userDao.searchByUserId(userId);
    }
}
