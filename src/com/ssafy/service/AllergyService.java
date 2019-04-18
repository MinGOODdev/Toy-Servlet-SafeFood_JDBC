package com.ssafy.service;

import com.ssafy.vo.Allergy;

import java.util.List;

public interface AllergyService {

    List<Allergy> findAll() throws Exception;

    Allergy findByIdx(int idx) throws Exception;

}
