/**
* @Title: CurrentConditions.java
* @Package com.hongyun.entity
* @Description: TODO(用一句话描述该文件做什么)
* @author andymao@qq.com
* @date May 10, 2012 8:41:55 AM
* @version V1.0
*/
package com.hongyun.entity;

/**
 * @ClassName: CurrentConditions
 * @Description: TODO:
 * @author: andymao@qq.com
 * @date May 10, 2012 8:41:55 AM
 *
 */
public class CurrentConditions {
    private String condition;
    private String temp_f;
    private String temp_c;
    private String humidity;
    private String icon;
    private String wind_condition;
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getTemp_f() {
        return temp_f;
    }
    public void setTemp_f(String temp_f) {
        this.temp_f = temp_f;
    }
    public String getTemp_c() {
        return temp_c;
    }
    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }
    public String getHumidity() {
        return humidity;
    }
    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getWind_condition() {
        return wind_condition;
    }
    public void setWind_condition(String wind_condition) {
        this.wind_condition = wind_condition;
    }


}
