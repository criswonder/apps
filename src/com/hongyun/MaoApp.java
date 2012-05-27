/**
* @Title: MaoApp.java
* @Package com.hongyun
* @Description: TODO(用一句话描述该文件做什么)
* @author andymao@qq.com
* @date May 10, 2012 8:18:53 AM
* @version V1.0
*/
package com.hongyun;

import android.app.AlertDialog;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

/**
 * @ClassName: MaoApp
 * @Description: TODO:
 * @author: andymao@qq.com
 * @date May 10, 2012 8:18:53 AM
 *
 */
public class MaoApp extends Application implements OnSharedPreferenceChangeListener {
    private SharedPreferences prefs;
    private String TAG = this.getClass().getSimpleName();
    private String defaultCity;
    public SharedPreferences getPrefs() {
        return prefs;
    }

    public void setPrefs(SharedPreferences prefs) {
        this.prefs = prefs;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        this.prefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.prefs.registerOnSharedPreferenceChangeListener(this);
        Log.i(TAG , "----------------------onCreated-------------------------");
        defaultCity = prefs.getString("defaultCity", "");
        Log.i(TAG , defaultCity);
        if(defaultCity.length()==0){
//            showAlertBox();
        }
    }



    public String getDefaultCity() {
        return defaultCity;
    }

    public void setDefaultCity(String defaultCity) {
        this.defaultCity = defaultCity;
    }

    /**
     * 当preference 改变的时候会执行的方法
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // TODO Auto-generated method stub
        Log.i(TAG , "----------------------onSharedPreferenceChanged-------------------------");

    }

}
