package com.ssafy.vo;

import java.util.List;

public class User {
    private String id;
    private String pw;
    private String name;
    private int age;
    private String gender;
    private List<Food> foodList;
    private List<String> allergyList;

    public User(String id, String pw, String name, int age, String gender, List<Food> foodList, List<String> allergyList) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.foodList = foodList;
        this.allergyList = allergyList;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public void setAllergyList(List<String> allergyList) {
        this.allergyList = allergyList;
    }

    public List<String> getAllergyList() {
        return allergyList;
    }
    
}
