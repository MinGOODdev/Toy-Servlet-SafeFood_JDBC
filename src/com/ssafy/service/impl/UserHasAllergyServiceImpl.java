package com.ssafy.service.impl;

import com.ssafy.dao.UserHasAllergyDao;
import com.ssafy.dao.impl.UserHasAllergyDaoImpl;
import com.ssafy.service.UserHasAllergyService;
import com.ssafy.vo.Allergy;
import com.ssafy.vo.UserHasAllergy;

import java.util.List;

public class UserHasAllergyServiceImpl implements UserHasAllergyService {
    private UserHasAllergyDao userHasAllergyDao;

    private static UserHasAllergyServiceImpl userHasAllergyService;
    public static UserHasAllergyServiceImpl getInstance() {
        if (userHasAllergyService == null) userHasAllergyService = new UserHasAllergyServiceImpl();
        return userHasAllergyService;
    }

    private UserHasAllergyServiceImpl() {
        userHasAllergyDao = UserHasAllergyDaoImpl.getInstance();
    }


    @Override
    public void insert(int id, List<Allergy> allergyList) throws Exception {
        userHasAllergyDao.insert(id, allergyList);
    }

    @Override
    public List<UserHasAllergy> findAllByUserId(int userId) throws Exception {
        return userHasAllergyDao.findAllByUserId(userId);
    }

    @Override
    public void deleteByUserId(int userId) throws Exception {
        userHasAllergyDao.deleteByUserId(userId);
    }
}
