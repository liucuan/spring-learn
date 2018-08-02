package com.tone.service;

import java.beans.ConstructorProperties;

/**
 * @author zhaoxiang.liu
 * @date 2018/7/31
 */
public class ExampleBean {

    private final int years;

    private final String ultimateAnswer;

    @ConstructorProperties({"years", "ultimateAnswer"})
    public ExampleBean(int years, String ultimateAnswer) {
        this.years = years;
        this.ultimateAnswer = ultimateAnswer;
    }

    public int getYears() {
        return years;
    }

    public String getUltimateAnswer() {
        return ultimateAnswer;
    }
}
