package com.ssafy.vo;

public class UserHasAllergy {
    private int userId;
    private int allergyIdx;

    public UserHasAllergy() {
    }

    public UserHasAllergy(int userId, int allergyIdx) {
        this.userId = userId;
        this.allergyIdx = allergyIdx;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAllergyIdx() {
        return allergyIdx;
    }

    public void setAllergyIdx(int allergyIdx) {
        this.allergyIdx = allergyIdx;
    }
}
