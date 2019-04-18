package com.ssafy.util;

import com.ssafy.vo.Food;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * FoodNutritionInfo.xml 파일에서 식품 영양 정보를 읽어 파싱하는 핸들러 클래스
 */
public class FoodNutritionSAXHandler extends DefaultHandler {
    private List<Food> list;    // 파싱한 식품 영양 정보를 담는 리스트
    private Food food;          // 파싱한 식품 영양 정보
    private String temp;        // 태그 바디 정보를 임시 저장

    public FoodNutritionSAXHandler() {
        list = new ArrayList<>();
    }

    public void startElement(String uri, String localName, String qName, Attributes att) {
        if (qName.equals("item")) {
            food = new Food();
        }
    }

    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "DESC_KOR": food.setName(temp); break;
            case "SERVING_WT": food.setSupportpereat(dataParsingDouble(temp)); break;
            case "NUTR_CONT1": food.setCalory(dataParsingDouble(temp)); break;
            case "NUTR_CONT2": food.setCarbo(dataParsingDouble(temp)); break;
            case "NUTR_CONT3": food.setProtein(dataParsingDouble(temp)); break;
            case "NUTR_CONT4": food.setFat(dataParsingDouble(temp)); break;
            case "NUTR_CONT5": food.setSugar(dataParsingDouble(temp)); break;
            case "NUTR_CONT6": food.setNatrium(dataParsingDouble(temp)); break;
            case "NUTR_CONT7": food.setChole(dataParsingDouble(temp)); break;
            case "NUTR_CONT8": food.setFattyacid(dataParsingDouble(temp)); break;
            case "NUTR_CONT9": food.setTransfat(dataParsingDouble(temp)); break;
            case "item": list.add(food); break;
        }
    }

    private double dataParsingDouble(String data) {
        if (data.equals("") || data.equalsIgnoreCase("N/A")) return 0;
        else return Double.parseDouble(data);
    }

    public void characters(char[] ch, int start, int length) {
        temp = new String(ch, start, length).trim();
    }

    public List<Food> getList() {
        return list;
    }

    public void setList(List<Food> list) {
        this.list = list;
    }

}





