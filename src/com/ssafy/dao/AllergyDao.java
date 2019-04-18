package com.ssafy.dao;

import com.ssafy.vo.Allergy;

import java.util.List;

public interface AllergyDao {

    List<Allergy> findAll() throws Exception;

    Allergy findByIdx(int idx) throws Exception;

}
