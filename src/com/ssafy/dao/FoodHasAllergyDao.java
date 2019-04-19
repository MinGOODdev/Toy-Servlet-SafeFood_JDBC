package com.ssafy.dao;

import com.ssafy.vo.FoodHasAllergy;

import java.util.List;

public interface FoodHasAllergyDao {

    List<FoodHasAllergy> findAllByFoodCode(int foodCode) throws Exception;

}
