package com.hongyun;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.hongyun.entity.Weather;

/**
 *
 * @author xiajun
 * @version 1.0
 * 资源设配器
 */
public class ResourceAdapter extends BaseAdapter {
	private Context context;
	public static int imgs[];
	private static Weather weather;
	private static int messageCode;
	public static int getMessageCode() {
		return messageCode;
	}

	public static void setMessageCode(int messageCode) {
		ResourceAdapter.messageCode = messageCode;
	}

	public ResourceAdapter(Context context){
	    imgs = new int[5];
		this.context = context;
	}

	public int getCount() {
		return imgs.length;
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

//	public Weather getWeatherByIndex(int index) {
//		return baseWeather.getWeatherList().get(index);
//	}

	public View getView(int index, View arg1, ViewGroup arg2) {
		ImageView imageview = new ImageView(context);
		/* 设置图片给imageView 对象*/
		imageview.setImageResource(imgs[index]);
		/* 重新设置图片的宽高*/
		imageview.setScaleType(ImageView.ScaleType.FIT_XY);
		/* 重新设置Layout 的宽高*/
		imageview.setLayoutParams(new Gallery.LayoutParams(87, 60));
		/* 设置Gallery 背景图*/
		return imageview;
	}

    public static Weather getWeather() {
        return weather;
    }

    public static void setWeather(Weather weather) {
        ResourceAdapter.weather = weather;
    }
}
