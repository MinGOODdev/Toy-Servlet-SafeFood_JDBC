package com.ssafy.vo;

import java.io.Serializable;

/**
 * 식품 내역 페이지 정보를 표하는 클래스
 */
public class FoodPageBean implements Serializable {
    private String key;         // 상품 조회 조건
    private String word;        // 조회할 단어

    public FoodPageBean() {
        setKey("all");
        setWord("");
    }

    public FoodPageBean(String key, String word) {
        setKey(key);
        setWord(word);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        if (key == null) this.key = "all";
        else this.key = key;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        if (word == null) this.word = "";
        else this.word = word;
    }
}










