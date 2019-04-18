package com.ssafy.service;

import java.util.List;

import com.ssafy.vo.Food;
import com.ssafy.vo.FoodPageBean;

public interface FoodService {

    List<Food> searchAll(FoodPageBean bean);

    Food search(int code);

    String oversearch(int code, String id);

}
