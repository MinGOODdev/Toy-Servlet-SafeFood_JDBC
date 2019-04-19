package com.ssafy.vo;

public class Allergy {
    private int idx;
    private String name;

    public Allergy() {
    }

    public Allergy(int idx, String name) {
        this.idx = idx;
        this.name = name;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Allergy{" +
                "idx=" + idx +
                ", name='" + name + '\'' +
                '}';
    }
}
