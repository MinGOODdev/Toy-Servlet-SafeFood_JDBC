package com.ssafy.vo;

import java.util.ArrayList;

/**
 * 식품 정보
 */
public class Food {
    private int code;
    private String name;
    private double supportpereat;   // 일회 제공량
    private double calory;          // 일회 제공 칼로리
    private double carbo;           // 탄수화물
    private double protein;         // 단백질
    private double fat;             // 지방
    private double sugar;           // 당류
    private double natrium;         // 나트륨
    private double chole;           // 콜레스테롤
    private double fattyacid;       // 포화지방산
    private double transfat;        // 트랜스지방
    private String maker;           // 제조사
    private String material;        // 원재료
    private String img;             // 이미지 경로
    private String allergy;

    public Food() {
    }

    public Food(int code) {
        super();
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSupportpereat() {
        return supportpereat;
    }

    public void setSupportpereat(double supportpereat) {
        this.supportpereat = supportpereat;
    }

    public double getCalory() {
        return calory;
    }

    public void setCalory(double calory) {
        this.calory = calory;
    }

    public double getCarbo() {
        return carbo;
    }

    public void setCarbo(double carbo) {
        this.carbo = carbo;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getNatrium() {
        return natrium;
    }

    public void setNatrium(double natrium) {
        this.natrium = natrium;
    }

    public double getChole() {
        return chole;
    }

    public void setChole(double chole) {
        this.chole = chole;
    }

    public double getFattyacid() {
        return fattyacid;
    }

    public void setFattyacid(double fattyacid) {
        this.fattyacid = fattyacid;
    }

    public double getTransfat() {
        return transfat;
    }

    public void setTransfat(double transfat) {
        this.transfat = transfat;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	@Override
	public String toString() {
		return "Food [code=" + code + ", name=" + name + ", supportpereat=" + supportpereat + ", calory=" + calory
				+ ", carbo=" + carbo + ", protein=" + protein + ", fat=" + fat + ", sugar=" + sugar + ", natrium="
				+ natrium + ", chole=" + chole + ", fattyacid=" + fattyacid + ", transfat=" + transfat + ", maker="
				+ maker + ", material=" + material + ", img=" + img + ", allergy=" + allergy + "]";
	}
}
