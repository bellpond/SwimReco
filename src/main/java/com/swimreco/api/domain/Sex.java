package com.swimreco.api.domain;

public enum Sex {
    Male("男性"),
    Female("女性"),
    Others("未選択");

    private String display;
    private Sex(String display) {
        this.display = display;
    }
}
