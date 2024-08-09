package com.e2on.assignment.entity;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ClsCode {

    PERSON("사람", 0),
    BICYCLE("자전거", 1),
    CAR("차", 2),
    MOTORCYCLE("오토바이", 3),
    AIRPLANE("비행기", 4),
    BUS("버스", 5),
    DOG("강아지", 16),
    HANDBAG("핸드백", 26),
    TIE("넥타이", 27);

    private String mean;
    private Integer value;

    ClsCode(String mean, Integer value) {
        this.mean = mean;
        this.value = value;
    }

    public static ClsCode ofValue(Integer value) {
        return Arrays.stream(ClsCode.values())
                .filter(v -> v.getValue().equals(value))
                .findAny()
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("%d의 클래스 코드는 존재하지 않습니다.", value)));
    }
}
