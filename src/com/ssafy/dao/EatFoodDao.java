package com.ssafy.dao;

import com.ssafy.vo.EatFood;

import java.util.List;

public interface EatFoodDao {

    List<EatFood> findAllByUserId(int userId) throws Exception;

    EatFood findOne(int userId, int foodCode) throws Exception;

    void insert(EatFood eatFood) throws Exception;

    void update(EatFood eatFood) throws Exception;

    void delete(int userId, int foodCode) throws Exception;

}
