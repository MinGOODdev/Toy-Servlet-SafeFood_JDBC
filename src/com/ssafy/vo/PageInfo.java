package com.ssafy.vo;

public class PageInfo {
    private boolean isForward;
    private String url;

    public PageInfo(boolean isForward, String url) {
        this.isForward = isForward;
        this.url = url;
    }

    public PageInfo(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return isForward + " " + url;
    }

    public boolean isForward() {
        return isForward;
    }

    public String getUrl() {
        return url;
    }
}
