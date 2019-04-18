package com.ssafy.dao;

import com.ssafy.vo.Food;
import com.ssafy.vo.FoodPageBean;

import java.util.List;

public interface FoodDao {

    /**
     * 검색 조건(key) 검색 단어(word)에 해당하는 식품 정보(Food)를  검색해서 반환.
     *
     * @param bean 검색 조건과 검색 단어가 있는 객체
     * @return 조회한 식품 목록
     */
    List<Food> searchAll(FoodPageBean bean);

    /**
     * 식품 코드에 해당하는 식품정보를 검색해서 반환.
     *
     * @param code 검색할 식품 코드
     * @return 식품 코드에 해당하는 식품 정보, 없으면 null이 리턴됨
     */
    Food search(int code);

}
