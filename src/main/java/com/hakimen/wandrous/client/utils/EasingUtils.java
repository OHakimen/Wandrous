package com.hakimen.wandrous.client.utils;

public class EasingUtils {

    public static double easeInOutQuart(float x){
        return x < 0.5 ? 8 * x * x * x * x : 1 - Math.pow(-2 * x + 2, 4) / 2;
    }

}
