package com.hongyun.service;

import com.hongyun.R;

import java.util.HashMap;


/**
 *
 * @author xiajun
 * @version 1.0
 */
public class Constant {
	/* Google天气 API地址 */
	public static final String GOOGLE_WEATHER_URL_CN = "http://www.google.com/ig/api?hl=zh-cn&weather=";
	/* 今天日期节点 */
	//public static final String FIRST_DATE_PATH = "/xml_api_reply/weather/forecast_information/forecast_date/@data";
	public static final String FIRST_DATE_PATH = "forecast_date";
	/* 最低温度节点 */
	//public static final String TEMPERATURE_MIN_PATH = "/xml_api_reply/weather/forecast_conditions/low/@data";
	public static final String TEMPERATURE_MIN_PATH = "low";
	/* 最高温度节点 */
	//public static final String TEMPERATURE_MAX_PATH = "/xml_api_reply/weather/forecast_conditions/high/@data";
	public static final String TEMPERATURE_MAX_PATH = "high";
	/* 天气图片文件名 */
//	public static final String WEATHER_IMAGE_PATH = "/xml_api_reply/weather/forecast_conditions/icon/@data";
	public static final String WEATHER_IMAGE_PATH = "icon";
	/* 星期节点 */
	//public static final String DAY_WEEK_PATH = "/xml_api_reply/weather/forecast_conditions/day_of_week/@data";
	public static final String DAY_WEEK_PATH = "day_of_week";
	/* 提示信息节点 */
	//public static final String CONDITION_PATH = "/xml_api_reply/weather/forecast_conditions/condition/@data";
	public static final String CONDITION_PATH = "condition";
	/* 错误信息节点 */
	//public static final String ERROR_PATH = "/xml_api_reply/weather/problem_cause/@data";
	public static final String ERROR_PATH = "problem_cause";
	/* 城市名称节点 */
	//public static final String CITY_NAME_PATH = "/xml_api_reply/weather/forecast_information/postal_code/@data";
	public static final String CITY_NAME_PATH = "postal_code";
	/* 节点属性名称 */
	public static final String ATTRIBUTE_NAME = "data";
	/* 信息节点*/
	public static final String INFORMATION_PATH = "forecast_information";
	/* 节点属性名称 */
	public static final String FORECAST_CONDITION = "forecast_conditions";
	/* 节点属性名称 */
	public static final String CURRENT_CONDITIONS = "current_conditions";
	/* 月份对应天数 */
	public static final HashMap<Integer, Integer> MONTH_MAP = new HashMap<Integer, Integer>(
			12);
	static {
		MONTH_MAP.put(1, 31);
		MONTH_MAP.put(2, 28);
		MONTH_MAP.put(3, 31);
		MONTH_MAP.put(4, 30);
		MONTH_MAP.put(5, 31);
		MONTH_MAP.put(6, 30);
		MONTH_MAP.put(7, 31);
		MONTH_MAP.put(8, 31);
		MONTH_MAP.put(9, 30);
		MONTH_MAP.put(10, 31);
		MONTH_MAP.put(11, 30);
		MONTH_MAP.put(12, 31);
	}
	public static final HashMap<String, Integer> DAY_IMAGE_MAP = new HashMap<String, Integer>();
	static {
		DAY_IMAGE_MAP.put("chance_of_rain.gif", R.drawable.d_rain);
		DAY_IMAGE_MAP.put("chance_of_snow.gif", R.drawable.snow);
		DAY_IMAGE_MAP.put("chance_of_storm.gif", R.drawable.d_rain);
		DAY_IMAGE_MAP.put("cloudy.gif", R.drawable.d_cloudy);
		DAY_IMAGE_MAP.put("cn_cloudy.gif", R.drawable.d_cloudy);
		DAY_IMAGE_MAP.put("cn_heavyrain.gif", R.drawable.d_rain);
		DAY_IMAGE_MAP.put("cn_heavysnow.gif", R.drawable.snow);
		DAY_IMAGE_MAP.put("cn_lightrain.gif", R.drawable.d_rain);
		DAY_IMAGE_MAP.put("cn_overcast.gif", R.drawable.d_cloudy);
		DAY_IMAGE_MAP.put("cn_showers.gif", R.drawable.d_rain);
		//DAY_IMAGE_MAP.put("fog.gif", R.drawable.haze);
		//DAY_IMAGE_MAP.put("haze.gif", R.drawable.haze);
		DAY_IMAGE_MAP.put("icy.gif", R.drawable.icy);
		DAY_IMAGE_MAP.put("mist.gif", R.drawable.d_rain);
		DAY_IMAGE_MAP.put("mostly_cloudy.gif", R.drawable.d_sunny_cloudy);
		DAY_IMAGE_MAP.put("mostly_sunny.gif", R.drawable.d_sunny_cloudy);
		DAY_IMAGE_MAP.put("partly_cloudy.gif", R.drawable.d_sunny_cloudy);
		DAY_IMAGE_MAP.put("sleet.gif", R.drawable.snow);
		//DAY_IMAGE_MAP.put("smoke.gif", R.drawable.haze);
		DAY_IMAGE_MAP.put("snow.gif", R.drawable.snow);
		DAY_IMAGE_MAP.put("storm.gif", R.drawable.d_rain);
		DAY_IMAGE_MAP.put("sunny.gif", R.drawable.d_sunny);
		DAY_IMAGE_MAP.put("sunny_cloudy.gif", R.drawable.d_sunny_cloudy);
		DAY_IMAGE_MAP.put("thunderstorm.gif", R.drawable.thunderstorm);
	}
	public static final HashMap<String, Integer> NIGHT_IMAGE_MAP = new HashMap<String, Integer>();
	static {
		NIGHT_IMAGE_MAP.put("chance_of_rain.gif", R.drawable.n_rain);
		NIGHT_IMAGE_MAP.put("chance_of_snow.gif", R.drawable.snow);
		NIGHT_IMAGE_MAP.put("chance_of_storm.gif", R.drawable.n_rain);
		NIGHT_IMAGE_MAP.put("cloudy.gif", R.drawable.n_cloudy);
		NIGHT_IMAGE_MAP.put("cn_cloudy.gif", R.drawable.n_cloudy);
		NIGHT_IMAGE_MAP.put("cn_heavyrain.gif", R.drawable.n_rain);
		NIGHT_IMAGE_MAP.put("cn_heavysnow.gif", R.drawable.snow);
		NIGHT_IMAGE_MAP.put("cn_lightrain.gif", R.drawable.n_rain);
		NIGHT_IMAGE_MAP.put("cn_overcast.gif", R.drawable.n_cloudy);
		NIGHT_IMAGE_MAP.put("cn_showers.gif", R.drawable.n_rain);
	//	NIGHT_IMAGE_MAP.put("fog.gif", R.drawable.haze);
	//	NIGHT_IMAGE_MAP.put("haze.gif", R.drawable.haze);
		NIGHT_IMAGE_MAP.put("icy.gif", R.drawable.icy);
		NIGHT_IMAGE_MAP.put("mist.gif", R.drawable.n_rain);
		NIGHT_IMAGE_MAP.put("mostly_cloudy.gif", R.drawable.n_sunny_cloudy);
		NIGHT_IMAGE_MAP.put("mostly_sunny.gif", R.drawable.n_sunny_cloudy);
		NIGHT_IMAGE_MAP.put("partly_cloudy.gif", R.drawable.n_sunny_cloudy);
		NIGHT_IMAGE_MAP.put("sleet.gif", R.drawable.snow);
	//	NIGHT_IMAGE_MAP.put("smoke.gif", R.drawable.haze);
		NIGHT_IMAGE_MAP.put("snow.gif", R.drawable.snow);
		NIGHT_IMAGE_MAP.put("storm.gif", R.drawable.n_rain);
		NIGHT_IMAGE_MAP.put("sunny.gif", R.drawable.n_sunny);
		NIGHT_IMAGE_MAP.put("sunny_cloudy.gif", R.drawable.n_sunny_cloudy);
		NIGHT_IMAGE_MAP.put("thunderstorm.gif", R.drawable.thunderstorm);
	}
	public static final String T_SIGN = "°C";
	public static final int DEFAULT_IMG = R.drawable.d_cloudy;
	//网络连接错误码
	public static final int NET_LINK_ERROR = 1;
	//城市存不在错误码
	public static final int CITY_NOT_EXIST = 2;
	//操作成功
	public static final int SUCCESS_FULL = 3;
}
