package com.ssafy.service.impl;

import com.ssafy.dao.FoodHasAllergyDao;
import com.ssafy.dao.impl.FoodHasAllergyDaoImpl;
import com.ssafy.service.FoodHasAllergyService;
import com.ssafy.vo.FoodHasAllergy;

import java.util.List;

public class FoodHasAllergyServiceImpl implements FoodHasAllergyService {
    private FoodHasAllergyDao foodHasAllergyDao;

    private static FoodHasAllergyServiceImpl foodHasAllergyService;
    public static FoodHasAllergyServiceImpl getInstance() {
        if (foodHasAllergyService == null) foodHasAllergyService = new FoodHasAllergyServiceImpl();
        return foodHasAllergyService;
    }

    private FoodHasAllergyServiceImpl() {
        foodHasAllergyDao = FoodHasAllergyDaoImpl.getInstance();
    }

    @Override
    public List<FoodHasAllergy> findAllByFoodCode(int foodCode) throws Exception {
        return foodHasAllergyDao.findAllByFoodCode(foodCode);
    }
}
