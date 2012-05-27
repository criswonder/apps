package com.hongyun;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.hongyun.entity.ForecastConditions;
import com.hongyun.entity.Weather;
import com.hongyun.service.Constant;
import com.hongyun.service.WeatherService;
import com.hongyun.service.WeatherUtil;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends BaseActivity implements ViewFactory{
    private MaoApp app;
    private Gallery gallery; //顶部的天气图标
    private ImageSwitcher imageview;
    private TextView city_info;
    private TextView week_info;
    private TextView date_info;
    private TextView hint_info;
    private TextView temperature_view;
    private Button util_but;
    private Button exit_but;
    private TextView currently_time;
    private Weather weather;
    private ResourceAdapter adapter;
    private RelativeLayout mainLayout;
    private ProgressBar progressBar;
    protected String cityName;
    public String tag = this.getClass().getSimpleName();
    private static final String ERROR_STRING = "error_string";
     AlertDialog city_dialog;
     RelativeLayout layout_progressBar ;
    public void sendSMS(String msg){
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.putExtra("sms_body", msg);
        it.setType("vnd.android-dir/mms-sms");
        startActivity(it);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (MaoApp) this.getApplication();
        setContentView(R.layout.weather);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        city_info = (TextView) findViewById(R.id.city);
        week_info = (TextView) findViewById(R.id.week);
        date_info = (TextView) findViewById(R.id.date);
        hint_info = (TextView) findViewById(R.id.hint);
        temperature_view = (TextView) findViewById(R.id.temperature);
        gallery = (Gallery) findViewById(R.id.gally);
        imageview = (ImageSwitcher) findViewById(R.id.photo);
        imageview.setFactory(this);
        imageview.setInAnimation(AnimationUtils.loadAnimation(this,
                    android.R.anim.slide_in_left));
        imageview.setOutAnimation(AnimationUtils.loadAnimation(this,
                    android.R.anim.slide_out_right));
        if(app.getDefaultCity()!=null && app.getDefaultCity().trim().length()>0){
            cityName = app.getDefaultCity();
        }else{
            showAlertBox();
        }
    }
    private void initGallery() {
        gallery.setAdapter(adapter);
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                reInitOnGalleryItemClicked(position,v.getId());
            }
        });
    }
    /**
     * 在点击Gallery的时候，重新初始化中间的panel
     * andymao@qq.com, May 16, 2012 10:32:57 PM
     * @param position
     * @param id
     */
    protected void reInitOnGalleryItemClicked(int position, long id) {
            ForecastConditions fc = weather.getList_fc().get(position);
            week_info.setText(fc.getDay_of_week());
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, position);
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
            date_info.setText(sformat.format(cal.getTime()));
            temperature_view.setText("最高气温"+
                    fc.getHigh()+Constant.T_SIGN+" 最低气温"+fc.getLow()+Constant.T_SIGN);
                    hint_info.setText(fc.getCondition());
           imageview.setImageResource(ResourceAdapter.imgs[position]);
    }
    private void realInit(String cityName) {
//        getWeather(cityName);
        if(weather==null)
        {
            Toast.makeText(this, "初始化出错！", Toast.LENGTH_LONG);
            return;
        }
        city_info.setText(weather.getFi().getCity());
        ForecastConditions forecastConditions = weather.getList_fc().get(0);
        week_info.setText(forecastConditions.getDay_of_week());
        date_info.setText(weather.getFi().getForecat_date());
        temperature_view.setText("最高气温"+
        forecastConditions.getHigh()+Constant.T_SIGN+" 最低气温"+forecastConditions.getLow()+Constant.T_SIGN);
        hint_info.setText(weather.getCc().getCondition()+","+weather.getCc().getHumidity()+"," + weather.getCc().getWind_condition());

        adapter = new ResourceAdapter(this);

        //设置中间显示天气的图片; 晚上和白天显示的图片不同
        //-------------------------------------------------------------------------------------------
        // 在xml里面current_conditions 与forecast_conditions第一个节点是有点重复的味道；所以
        //中间那个panel显示的天气图片，使用第一个forecast_conditions中的icon
        //-------------------------------------------------------------------------------------------
        String iconPath = weather.getCc().getIcon();
        String key = iconPath.substring(iconPath.lastIndexOf("/")+1);
        Integer imageId=null;
        boolean dayFlag = WeatherUtil.isDay();
        if(dayFlag){
            imageId = Constant.DAY_IMAGE_MAP.get(key);
        }else{
            imageId = Constant.NIGHT_IMAGE_MAP.get(key);
        }
        if(imageId!=null){

        }

        //设置top的gallery
        int i = 0;
        for(ForecastConditions fc:weather.getList_fc() ){
            iconPath = fc.getIcon();
            key = iconPath.substring(iconPath.lastIndexOf("/")+1);
            if(dayFlag){
                imageId = Constant.DAY_IMAGE_MAP.get(key);
            }else{
                imageId = Constant.NIGHT_IMAGE_MAP.get(key);
            }
            if(imageId!=null){
                adapter.imgs[i] =imageId;
                if(i==0){
                    imageview.setImageResource(imageId);
                }
                i++;
            }
        }

        initGallery();
    }
    private void getWeather(String cityName) {
        WeatherService ws = new WeatherService();
        try {
             weather = ws.getWeather(cityName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch(ParseException e){
            e.printStackTrace();
        }
//        catch(IllegalArgumentException e){
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(MainActivity.this, "Can't find this city!!", Toast.LENGTH_LONG).show();
//                }
//            });
//        }
    }
    /**
     * TODO 搞清楚ViewFactory 接口中定义的下面这个方法 的用途。
     */
    @Override
    public View makeView() {
        ImageView img = new ImageView(this);
        return img;
    }

    /**
     * 弹出一个窗口 让用户输入默认城市
     * andymao@qq.com, May 14, 2012 10:54:16 PM
     */
    private void showAlertBox() {
//        if(city_dialog==null)
            city_dialog = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = LayoutInflater.from(this);
        View city_view = inflater.inflate(R.layout.city_dialog, null);
        city_dialog.setView(city_view);
        city_dialog.show();

        final EditText city_name = (EditText) city_view.findViewById(R.id.city_name);
        final CheckBox checkbox = (CheckBox) city_view.findViewById(R.id.set_checkbox);
        final TextView textView = (TextView) city_view.findViewById(R.id.textView_error);
        final LinearLayout error_img = (LinearLayout) city_view.findViewById(R.id.error_img);
        Button btn_ok = (Button) city_view.findViewById(R.id.opt_full);
        Button btn_cancel = (Button) city_view.findViewById(R.id.cancel_but);
        btn_ok.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                cityName = city_name.getText().toString();
                if(cityName==null ||cityName.length()==0){
                    textView.setText("城市名称不能为空！");
                    return;
                }
                city_dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                        WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                ProgressBar pb = new ProgressBar(city_dialog.getContext());
                /*error_img.removeAllViews();
                error_img.addView(pb);
                //如果不做下面的操作会有异常
                RelativeLayout obj = (RelativeLayout)error_img.getParent();
                obj.removeAllViews();
                city_dialog.setContentView(error_img);*/

                layout_progressBar = new RelativeLayout(city_dialog.getContext());
                layout_progressBar.addView(pb);
//                city_dialog.setContentView(layout_progressBar);

//                if(checkbox.isChecked()){
//                    savePreferences(cityName);
//                }
//                city_dialog.dismiss();
//                if(mainLayout.getVisibility()==View.VISIBLE){
//                    mainLayout.setVisibility(View.INVISIBLE);
//                }
//                if(progressBar.getVisibility() == View.GONE || progressBar.getVisibility() == View.INVISIBLE){
//                    progressBar.setVisibility(View.VISIBLE);
//                }
                WeatherGetter getter = new WeatherGetter();
                getter.start();
            }
        });

        btn_cancel.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                city_dialog.dismiss();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(cityName==null ||cityName.length()==0){
            return;
        }
        WeatherGetter getter = new WeatherGetter();
        getter.start();
    }
    private void savePreferences(String cityName){
        SharedPreferences.Editor editor = app.getPrefs().edit();
        editor.putString("defaultCity", cityName);
        editor.commit();
    }
    @Override
    protected void item1Clicked() {
        showAlertBox();
    }
    @Override
    protected void item2Clicked() {
        String msg = null;
        StringBuilder sb = new StringBuilder();
        sb.append(weather.getFi().getCity()+"今天" + weather.getCc().getCondition()+",");
        sb.append(weather.getCc().getTemp_c()+Constant.T_SIGN+","+weather.getCc().getHumidity());
        sb.append(","+weather.getCc().getWind_condition());
        for(ForecastConditions fc: weather.getList_fc()){
            sb.append("."+fc.getDay_of_week()+"最高温度为"+fc.getHigh()+Constant.T_SIGN+",最低温度为"+fc.getLow()+Constant.T_SIGN+fc.getCondition());
        }
        sendSMS(sb.toString());
        sb =null;
    }

    //-------------------------------------------------------------------------------------------
    // 自己new的Thread 里面不要更新UI, 如果要更新UI，应该到handler里面做
    //-------------------------------------------------------------------------------------------
    private class WeatherGetter extends   Thread{
        public void run() {
            //
            /*Log.i(tag, "--------------------------startSleep-------------------------");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.i(tag, "--------------------------weakUp-------------------------");*/
            try{
                getWeather(cityName);
            }catch(IllegalArgumentException e){
                Message ms = new Message();
                Bundle bd = new Bundle();
                bd.putString(ERROR_STRING, "城市不存在！");
                ms.setData(bd);
                handler.sendMessage(ms);
                return;
            }
            savePreferences(cityName);
            handler.sendEmptyMessage(1);
           /* mHandler.post(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(15500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    realInit(cityName);
                    savePreferences(cityName);
                    progressBar.setVisibility(View.GONE);
                    mainLayout.setVisibility(View.VISIBLE);
                }
            });*/
        }
    }
//    private Handler mHandler = new Handler();

    //-------------------------------------------------------------------------------------------
    // Handler里面的操作会block 主线程
    //-------------------------------------------------------------------------------------------
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            //不管什么时候，调用handler更新UI的时候，layout_progressBar都不需要出现，而且不需要blur
            if(layout_progressBar!=null){
                layout_progressBar.setVisibility(View.GONE);
            }
            if(city_dialog!=null){
                //这种用法是没有效果的
                //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                Log.i(tag, "city_dialog.getWindow().equals(getWindow())="+city_dialog.getWindow().equals(getWindow()) );
                city_dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

            }
            if(msg.what==1){
                realInit(cityName);
                progressBar.setVisibility(View.GONE);

                mainLayout.setVisibility(View.VISIBLE);

                if(city_dialog!=null){
                    city_dialog.dismiss();
                }
//              if(mainLayout.getVisibility()==View.INVISIBLE || mainLayout.getVisibility() == View.GONE){
//                  mainLayout.setVisibility(View.VISIBLE);
//              }
//              if(progressBar.getVisibility() == View.VISIBLE ){
//                  progressBar.setVisibility(View.GONE);
//              }
            }
            else
            {
               if( msg.getData().getString(ERROR_STRING)==null){
                   realInit(cityName);
               }else{
                   if(layout_progressBar!=null && city_dialog!=null){
                   layout_progressBar.setVisibility(View.GONE);
                   city_dialog.dismiss(); //如果这里不这么做，你可以试试，会发现，点取消按钮后，mainlayout还会被遮着
                                           //因为showAlertBox 会再创建一个city_dialog，所以你dismiss的时候只消失了一个。
                   }
                   showAlertBox();
                   TextView error_tv =  (TextView)city_dialog.findViewById(R.id.textView_error);
                   EditText editText =  (EditText)city_dialog.findViewById(R.id.city_name);
                   editText.setText(cityName);
                   error_tv.setText(msg.getData().getString(ERROR_STRING));
               }
            }

         }
        };
}