package com.kendiea.webservicedemo;

import java.util.List;

import android.os.AsyncTask;
import android.widget.Spinner;

public class CityAsyncTask extends AsyncTask<String, String, List<String>> {

    private MainActivity activity;
    private List<String> cities;
    private Spinner sp_city, sp_province;

    public CityAsyncTask(MainActivity activity, Spinner sp_city,
                         Spinner sp_province) {
        this.activity = activity;
        this.sp_city = sp_city;
        this.sp_province = sp_province;
    }

    @Override
    protected List<String> doInBackground(String... params) {
        // TODO Auto-generated method stub
        cities = WebServiceUtil.getCityListsByProvince(sp_province.getSelectedItem().toString());
        return null;
    }

    @Override
    protected void onPostExecute(List<String> result) {
        // TODO Auto-generated method stub
        ListAdapter adapter = new ListAdapter(activity, cities);
        sp_city.setAdapter(adapter);

    }
}
