package com.ssafy.service;

import com.ssafy.vo.Allergy;
import com.ssafy.vo.UserHasAllergy;

import java.util.List;

public interface UserHasAllergyService {

    void insert(int id, List<Allergy> allergyList) throws Exception;

    List<UserHasAllergy> findAllByUserId(int userId) throws Exception;

    void deleteByUserId(int userId) throws Exception;

}
