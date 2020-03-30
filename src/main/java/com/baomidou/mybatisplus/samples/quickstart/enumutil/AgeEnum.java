package com.baomidou.mybatisplus.samples.quickstart.enumutil;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AgeEnum implements IEnum<Integer> {
    ONE(1, "一岁"),
    TWO(2, "二岁"),
    THREE(3, "三岁");

    @EnumValue
    @JsonValue
    private int value;

    private String desc;

    AgeEnum(int i, String s) {
        this.value=i;
        this.desc=s;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}