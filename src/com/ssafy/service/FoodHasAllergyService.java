package com.ssafy.service;

import com.ssafy.vo.FoodHasAllergy;

import java.util.List;

public interface FoodHasAllergyService {

    List<FoodHasAllergy> findAllByFoodCode(int foodCode) throws Exception;

}
