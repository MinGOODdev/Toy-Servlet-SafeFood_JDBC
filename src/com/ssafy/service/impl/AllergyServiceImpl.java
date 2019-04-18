package com.ssafy.service.impl;

import com.ssafy.dao.AllergyDao;
import com.ssafy.dao.impl.AllergyDaoImpl;
import com.ssafy.service.AllergyService;
import com.ssafy.vo.Allergy;

import java.util.List;

public class AllergyServiceImpl implements AllergyService {
    private AllergyDao allergyDao;

    private static AllergyServiceImpl allergyService;
    public static AllergyServiceImpl getInstance() {
        if (allergyService == null) allergyService = new AllergyServiceImpl();
        return allergyService;
    }

    private AllergyServiceImpl() {
        allergyDao = AllergyDaoImpl.getInstance();
    }

    @Override
    public List<Allergy> findAll() throws Exception {
        return allergyDao.findAll();
    }

    public Allergy findByIdx(int idx) throws Exception {
        return allergyDao.findByIdx(idx);
    }

}
