/**
 * @Title: CustomXmlParser.java
 * @Package com.hongyun.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author andymao@qq.com
 * @date May 10, 2012 8:20:25 AM
 * @version V1.0
 */

package com.hongyun.service;

import android.util.Log;

import com.hongyun.entity.CurrentConditions;
import com.hongyun.entity.ForecastConditions;
import com.hongyun.entity.ForecastInformation;
import com.hongyun.entity.Weather;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: CustomXmlParser
 * @Description: TODO: 扩展Sax 的DefaultHandler重写startElement,
 *               endElement两个hook方法来获取需要的数据。 BaseWeather
 *               聚合了Weather，就是多了一个city的名称，例如北京。
 * @author: andymao@qq.com
 * @date May 7, 2012 9:57:45 PM
 */
public class CustomXmlParser extends DefaultHandler {
    private Weather weather; //initialized in constructor
    private boolean in_forecast_information; //initialized in constructor
    private boolean in_current_conditions; //initialized in constructor
    private boolean in_forecast_conditions; //initialized in constructor
    private List<ForecastConditions> list_fc; //initialized in constructor

    private ForecastInformation fi; //initialize when used
    private ForecastConditions fc; //initialize when used
    private CurrentConditions cc; //initialize when used
    private String tag = this.getClass().getSimpleName();

    /**
     *
     */
    public CustomXmlParser() {
        this.in_current_conditions = false;
        this.in_forecast_conditions = false;
        this.in_forecast_information = false;

        weather = new Weather();
        list_fc = new ArrayList<ForecastConditions>();
    }
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
        Log.d(tag , "------------------startElement------------------");
        String tagName = localName.length() != 0 ? localName : qName;
        Log.d(tag , tagName);
        tagName = tagName.toLowerCase();
         if(tagName.equals(Constant.ERROR_PATH)) {
             throw new IllegalArgumentException("the city is not existed!");
           }
        /*--------------------------------------------------------------------
         *
         --------------------------------------------------------------------*/
        if(tagName.equals(Constant.INFORMATION_PATH)) {
            in_forecast_information=true;
            fi = new ForecastInformation();
        }
        if(in_forecast_information){
             if(tagName.equals(Constant.CITY_NAME_PATH)){
                 fi.setCity(attributes.getValue(Constant.ATTRIBUTE_NAME));
             }
             if(tagName.equals(Constant.FIRST_DATE_PATH)){
                 fi.setForecat_date(attributes.getValue(Constant.ATTRIBUTE_NAME));
             }
        }
        /*--------------------------------------------------------------------
         *
         --------------------------------------------------------------------*/
        if(tagName.equals(Constant.CURRENT_CONDITIONS)) {
            in_current_conditions=true;
            cc = new CurrentConditions();
        }
        if(in_current_conditions){
            if(tagName.equals(Constant.CONDITION_PATH)){
                cc.setCondition(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }
            if(tagName.equals("temp_f")){
                cc.setTemp_f(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }
            if(tagName.equals("temp_c")){
                cc.setTemp_c(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }
            if(tagName.equals("temp_c")){
                cc.setTemp_c(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }
            if(tagName.equals("humidity")){
                cc.setHumidity(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }
            if(tagName.equals("icon")){
                cc.setIcon(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }
            if(tagName.equals("wind_condition")){
                cc.setWind_condition(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }

        }
        /*--------------------------------------------------------------------
        *
        --------------------------------------------------------------------*/
        if(tagName.equals(Constant.FORECAST_CONDITION)) {
            in_forecast_conditions = true;
            fc = new ForecastConditions();
        }

        if(in_forecast_conditions) {
            if(tagName.equals(Constant.DAY_WEEK_PATH)) {
                fc.setDay_of_week(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }else if(tagName.equals(Constant.TEMPERATURE_MIN_PATH)) {
                fc.setLow(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }else if(tagName.equals(Constant.TEMPERATURE_MAX_PATH)) {
                fc.setHigh(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }else if(tagName.equals(Constant.WEATHER_IMAGE_PATH)) {
                fc.setIcon(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }else if(tagName.equals(Constant.CONDITION_PATH)) {
                fc.setCondition(attributes.getValue(Constant.ATTRIBUTE_NAME));
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        Log.d(tag , "------------------endElement------------------");
        String tagName = localName.length() != 0 ? localName : qName;
        Log.d(tag , tagName);
        tagName = tagName.toLowerCase();
        /*--------------------------------------------------------------------
        *
        --------------------------------------------------------------------*/
        if(tagName.equals(Constant.INFORMATION_PATH)) {
            in_forecast_information=false;
        }
        /*--------------------------------------------------------------------
         *
        --------------------------------------------------------------------*/
        if(tagName.equals(Constant.CURRENT_CONDITIONS)) {
            in_current_conditions=false;
        }
        /*--------------------------------------------------------------------
         *
        --------------------------------------------------------------------*/
        if(tagName.equals(Constant.FORECAST_CONDITION)) {
            in_forecast_conditions = false;
            list_fc.add(fc);
        }
    }

    public Weather getWeather() {
        weather.setCc(cc);
        weather.setFi(fi);
        weather.setList_fc(list_fc);
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
