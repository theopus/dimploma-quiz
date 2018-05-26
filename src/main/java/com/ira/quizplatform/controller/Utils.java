package com.ira.quizplatform.controller;

import com.ira.quizplatform.entity.Result;

import java.util.stream.Stream;

public class Utils {

    public static float avg(Stream<Result> resultStream){
        return (float) resultStream.mapToInt(value -> value.getBalance()).average().orElse(0.0);
    }
}
