/**
* @Title: ForecastInformation.java
* @Package com.hongyun.entity
* @Description: TODO(用一句话描述该文件做什么)
* @author andymao@qq.com
* @date May 10, 2012 8:41:14 AM
* @version V1.0
*/
package com.hongyun.entity;

/**
 * @ClassName: ForecastInformation
 * @Description: TODO:
 * @author: andymao@qq.com
 * @date May 10, 2012 8:41:14 AM
 *
 */
public class ForecastInformation {
    private String city;
    private String postalCode;
    private String latitude_e6;
    private String longitude_e6;
    private String forecat_date;
    private String current_date_time;
    private String data;
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getLatitude_e6() {
        return latitude_e6;
    }
    public void setLatitude_e6(String latitude_e6) {
        this.latitude_e6 = latitude_e6;
    }
    public String getLongitude_e6() {
        return longitude_e6;
    }
    public void setLongitude_e6(String longitude_e6) {
        this.longitude_e6 = longitude_e6;
    }
    public String getForecat_date() {
        return forecat_date;
    }
    public void setForecat_date(String forecat_date) {
        this.forecat_date = forecat_date;
    }
    public String getCurrent_date_time() {
        return current_date_time;
    }
    public void setCurrent_date_time(String current_date_time) {
        this.current_date_time = current_date_time;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }


}
