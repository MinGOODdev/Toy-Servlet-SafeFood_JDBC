package com.ssafy.vo;

public class EatFood {
    private int userId;
    private int foodCode;
    private int count;

    public EatFood() {
    }

    public EatFood(int userId, int foodCode, int count) {
        this.userId = userId;
        this.foodCode = foodCode;
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(int foodCode) {
        this.foodCode = foodCode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
