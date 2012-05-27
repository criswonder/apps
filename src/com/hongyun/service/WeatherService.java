/**
* @Title: WeatherService.java
* @Package com.hongyun.service
* @Description: TODO(用一句话描述该文件做什么)
* @author andymao@qq.com
* @date May 10, 2012 8:22:18 AM
* @version V1.0
*/
package com.hongyun.service;

import com.hongyun.entity.Weather;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @ClassName: WeatherService
 * @Description: TODO:
 * @author: andymao@qq.com
 * @date May 10, 2012 8:22:18 AM
 *
 */
public class WeatherService {
    public Weather getWeather(String city) throws IOException, SAXException, ParserConfigurationException{
        CustomXmlParser cxp = new CustomXmlParser();
        // encoder city
        try {
            city = URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser sp = spf.newSAXParser();
        XMLReader reader = sp.getXMLReader();
        reader.setContentHandler(cxp);
        URL url = new URL(Constant.GOOGLE_WEATHER_URL_CN+city); //http://www.google.com/ig/api?hl=zh-cn&weather=%E6%9D%AD%E5%B7%9E
        InputStream is = url.openStream();
        InputStreamReader isr = new InputStreamReader(is,"GB2312");
        InputSource source = new InputSource(isr);
        reader.parse(source);
        return cxp.getWeather();
    }
}
