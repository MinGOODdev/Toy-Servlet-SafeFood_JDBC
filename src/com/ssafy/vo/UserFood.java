package com.ssafy.vo;

import java.util.List;

public class UserFood {
    private Food food;
    private int count;
    private List<Allergy> allergyList;

    public UserFood() {
    }

    public UserFood(Food food, int count, List<Allergy> allergies) {
        this.food = food;
        this.count = count;
        this.allergyList = allergies;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Allergy> getAllergyList() {
        return allergyList;
    }

    public void setAllergyList(List<Allergy> allergyList) {
        this.allergyList = allergyList;
    }
}
