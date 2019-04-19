package com.ssafy.vo;

public class FoodHasAllergy {
    private int foodCode;
    private int allergyIdx;

    public FoodHasAllergy() {
    }

    public FoodHasAllergy(int foodCode, int allergyIdx) {
        this.foodCode = foodCode;
        this.allergyIdx = allergyIdx;
    }

    public int getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(int foodCode) {
        this.foodCode = foodCode;
    }

    public int getAllergyIdx() {
        return allergyIdx;
    }

    public void setAllergyIdx(int allergyIdx) {
        this.allergyIdx = allergyIdx;
    }

    @Override
    public String toString() {
        return "FoodHasAllergy{" +
                "foodCode=" + foodCode +
                ", allergyIdx=" + allergyIdx +
                '}';
    }
}
