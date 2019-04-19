package com.ssafy.service.impl;

import com.ssafy.dao.EatFoodDao;
import com.ssafy.dao.impl.EatFoodDaoImpl;
import com.ssafy.service.EatFoodService;
import com.ssafy.vo.EatFood;

import java.util.List;

public class EatFoodServiceImpl implements EatFoodService {
    private EatFoodDao eatFoodDao;
    private static EatFoodServiceImpl eatFoodService;

    public static EatFoodServiceImpl getInstance() {
        if (eatFoodService == null) eatFoodService = new EatFoodServiceImpl();
        return eatFoodService;
    }

    private EatFoodServiceImpl() {
        eatFoodDao = EatFoodDaoImpl.getInstance();
    }

    @Override
    public List<EatFood> findAllByUserId(int userId) throws Exception {
        return eatFoodDao.findAllByUserId(userId);
    }

    @Override
    public EatFood findOne(int userId, int foodCode) throws Exception {
        return eatFoodDao.findOne(userId, foodCode);
    }

    @Override
    public void insert(EatFood eatFood) throws Exception {
        eatFoodDao.insert(eatFood);
    }

    @Override
    public void update(EatFood eatFood) throws Exception {
        eatFoodDao.update(eatFood);
    }

    @Override
    public void delete(int userId, int foodCode) throws Exception {
        eatFoodDao.delete(userId, foodCode);
    }
}
