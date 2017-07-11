package com.kendiea.webservicedemo;

import android.os.AsyncTask;
import android.widget.Spinner;

import java.util.List;

public class ProvinceAsyncTask extends AsyncTask<String, String, List<String>> {
	private MainActivity activity;
	private List<String> provinces;
	private Spinner sp_province;
	
	public ProvinceAsyncTask (MainActivity activity,Spinner sp_province){
		this.activity = activity;
		this.sp_province = sp_province;
	}

	@Override
	protected List<String> doInBackground(String... params) { 
		// TODO Auto-generated method stub
		provinces = WebServiceUtil.getProvinceList();
		return null;
	}

	@Override
	protected void onPostExecute(List<String> result) {
		// TODO Auto-generated method stub
		ListAdapter adapter = new ListAdapter(activity, provinces);
		sp_province.setAdapter(adapter);
		
	}
}
