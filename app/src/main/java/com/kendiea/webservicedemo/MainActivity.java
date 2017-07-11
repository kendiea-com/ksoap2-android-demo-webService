package com.kendiea.webservicedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

public class MainActivity extends Activity {
    public Spinner sp_province, sp_city;
    public SoapObject detail;
    public TextView txt_today, txt_tomorrow, txt_afterday, txt_current;
    public ImageView img_today1, img_today2, img_tomorrow1, img_tomorrow2,
            img_afterday1, img_afterday2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initControls();

        ProvinceAsyncTask task = new ProvinceAsyncTask(this, sp_province);
        task.execute();
        sp_province.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> source, View parent, int position, long id) {
                // TODO Auto-generated method stub
                CityAsyncTask task = new CityAsyncTask(MainActivity.this, sp_city, sp_province);
                task.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        sp_city.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> source, View parent, int position, long id) {
                // TODO Auto-generated method stub
                String city = sp_city.getSelectedItem().toString();
                WeatherAsyncTask task = new WeatherAsyncTask(MainActivity.this, city, detail);
                Toast.makeText(MainActivity.this, city, Toast.LENGTH_SHORT).show();
                task.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

    }

    private void initControls() {
        sp_province = (Spinner) findViewById(R.id.sp_province);
        sp_city = (Spinner) findViewById(R.id.sp_city);
        txt_today = (TextView) findViewById(R.id.weatherToday);
        txt_tomorrow = (TextView) findViewById(R.id.weatherTomorrow);
        txt_afterday = (TextView) findViewById(R.id.weatherAfterday);
        txt_current = (TextView) findViewById(R.id.weatherCurrent);
        img_today1 = (ImageView) findViewById(R.id.todayWhIcon1);
        img_today2 = (ImageView) findViewById(R.id.todayWhIcon2);
        img_tomorrow1 = (ImageView) findViewById(R.id.tomorrowWhIcon1);
        img_tomorrow2 = (ImageView) findViewById(R.id.tomorrowWhIcon2);
        img_afterday1 = (ImageView) findViewById(R.id.afterdayWhIcon1);
        img_afterday2 = (ImageView) findViewById(R.id.afterdayWhIcon2);
    }

}