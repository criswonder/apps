/**
* @Title: Weather.java
* @Package com.hongyun.entity
* @Description: TODO(用一句话描述该文件做什么)
* @author andymao@qq.com
* @date May 10, 2012 8:40:01 AM
* @version V1.0
*/
package com.hongyun.entity;

import java.util.List;

/**
 * @ClassName: Weather
 * @Description: TODO:
 * @author: andymao@qq.com
 * @date May 10, 2012 8:40:01 AM
 *
 */
public class Weather {
    private List<ForecastConditions> list_fc;
    private ForecastInformation fi;
    private CurrentConditions cc;
    public List<ForecastConditions> getList_fc() {
        return list_fc;
    }
    public void setList_fc(List<ForecastConditions> list_fc) {
        this.list_fc = list_fc;
    }
    public ForecastInformation getFi() {
        return fi;
    }
    public void setFi(ForecastInformation fi) {
        this.fi = fi;
    }
    public CurrentConditions getCc() {
        return cc;
    }
    public void setCc(CurrentConditions cc) {
        this.cc = cc;
    }


}
