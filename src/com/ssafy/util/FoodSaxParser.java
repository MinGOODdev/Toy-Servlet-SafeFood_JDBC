package com.ssafy.util;

import com.ssafy.vo.Food;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;
import java.util.Map;

/**
 * FoodNutritionSAXHandler, FoodSAXHandler를 활용하여 식품 정보를 load하는 SAX Parser
 */
public class FoodSaxParser {
    private String basePath = this.getClass().getResource("/").getPath();
    private String nutritionFilePath = basePath + "/FoodNutritionInfo.xml";
    private String foodFilePath = basePath + "/foodInfo.xml";
    private List<Food> foods;

    /**
     * 싱글톤
     */
    public static FoodSaxParser foodSaxParser;
    public static FoodSaxParser getInstance() {

        if (foodSaxParser == null) foodSaxParser = new FoodSaxParser();
        return foodSaxParser;
    }

    private FoodSaxParser() {
        loadData();
    }

    /**
     * FoodNutritionSAXHandler, FoodSAXHandler를 이용 파싱한 식품 정보와 식품 영양 정보를 Food에 병합한다.
     */
    public void loadData() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            FoodSAXHandler handler = new FoodSAXHandler();
            FoodNutritionSAXHandler nHandler = new FoodNutritionSAXHandler();
            
            parser.parse(foodFilePath, handler);
            parser.parse(nutritionFilePath, nHandler);
            Map<String, Food> foodMap = handler.getFoods();

            List<Food> temp = nHandler.getList();
            Food find = null;

            for (Food food : temp) {
                find = foodMap.get(food.getName());
                if (find != null) {
                    food.setCode(find.getCode());
                    food.setName(find.getName());
                    food.setMaker(find.getMaker());
                    food.setMaterial(find.getMaterial());
                    food.setImg(find.getImg());
                }
               // System.out.println(food);
            }

            setFoods(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Food> getFoods() {
        return foods;
    }

    private synchronized void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
