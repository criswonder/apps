package com.hongyun.service;

import java.util.Calendar;

public class WeatherUtil {
    public static boolean isDay(){
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        if (hour >= 20 || hour <= 7) {
            return false;
        }
        return true;
    }
}
