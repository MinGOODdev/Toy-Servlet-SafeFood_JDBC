package com.ssafy.util;

import com.ssafy.vo.Food;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * FoodInfo.xml 파일에서 식품 정보를 읽어 파싱하는 핸들러 클래스
 */
public class FoodSAXHandler extends DefaultHandler {
    private Map<String, Food> foods;    // 파싱한 식품 정보를 저장하는 맵, 식품 이름으로 식품 정보를 검색하기 위해 맵에 담는다.
    private Food food;                  // 파싱한 식품 영양 정보
    private String temp;                // 태그 바디 정보를 임시로 저장

    public FoodSAXHandler() {
        foods = new HashMap<String, Food>();
    }

    public void startElement(String uri, String localName, String qName, Attributes att) {
        if (qName.equals("food")) {
            food = new Food();
        }
    }

    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "code": food.setCode(Integer.parseInt(temp)); break;
            case "name": food.setName(temp); break;
            case "maker": food.setMaker(temp); break;
            case "material": food.setMaterial(temp); break;
            case "image": food.setImg(temp); break;
            case "food": foods.put(food.getName(), food); break;
        }
    }

    public void characters(char[] ch, int start, int length) {
        temp = new String(ch, start, length);
    }

    public Map<String, Food> getFoods() {
        return foods;
    }

    public void setFoods(Map<String, Food> foods) {
        this.foods = foods;
    }
}





