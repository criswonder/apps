/**
* @Title: ForecastConditions.java
* @Package com.hongyun.entity
* @Description: TODO(用一句话描述该文件做什么)
* @author andymao@qq.com
* @date May 10, 2012 8:42:12 AM
* @version V1.0
*/
package com.hongyun.entity;

/**
 * @ClassName: ForecastConditions
 * @Description: TODO:
 * @author: andymao@qq.com
 * @date May 10, 2012 8:42:12 AM
 *
 */
public class ForecastConditions {
    private String day_of_week;
    private String low;
    private String high;
    private String icon;
    private String condition;
    public String getDay_of_week() {
        return day_of_week;
    }
    public void setDay_of_week(String day_of_week) {
        this.day_of_week = day_of_week;
    }
    public String getLow() {
        return low;
    }
    public void setLow(String low) {
        this.low = low;
    }
    public String getHigh() {
        return high;
    }
    public void setHigh(String high) {
        this.high = high;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }

}
