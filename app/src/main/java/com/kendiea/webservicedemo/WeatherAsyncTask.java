package com.kendiea.webservicedemo;

import android.os.AsyncTask;

import org.ksoap2.serialization.SoapObject;

import java.util.List;


public class WeatherAsyncTask extends AsyncTask<String, String, List<String>> {
	
	private MainActivity activity;
	private SoapObject detail;
	private String city;
	private String date;
	
	public WeatherAsyncTask (MainActivity activity,String city,SoapObject detail){
		this.activity = activity;
		this.city = city;
		this.detail = detail;
	}

	@Override
	protected List<String> doInBackground(String... params) { 
		// TODO Auto-generated method stub
        detail = WebServiceUtil.getWeatherByCity(city);
        return null;
	}

	@Override
	protected void onPostExecute(List<String> result) {
		// TODO Auto-generated method stub
		String weatherCurrent = detail.getProperty(4).toString();
		activity.txt_current.setText(weatherCurrent);
		date = detail.getProperty(7).toString();
		WeatherToday();
		WeatherTomorrow();
		WeatherAfterday();
	}
	
	private void WeatherToday(){
		
		
		String weatherToday = "今天：" + date.split(" ")[0];
		weatherToday = weatherToday + "\n天气：" + date.split(" ")[1];
		weatherToday = weatherToday + "\n气温："
				+ detail.getProperty(8).toString();
		weatherToday = weatherToday + "\n风力："
				+ detail.getProperty(9).toString() + "\n";
		int iconToday[] = new int[2];
		iconToday[0] = parseIcon(detail.getProperty(10).toString());
		iconToday[1] = parseIcon(detail.getProperty(11).toString());
		activity.txt_today.setText(weatherToday);
		activity.img_today1.setImageResource(iconToday[0]);
		activity.img_today2.setImageResource(iconToday[1]);
	}
	
	private void WeatherTomorrow(){
		date = detail.getProperty(12).toString();
		String weatherTomorrow = "明天：" + date.split(" ")[0];
		weatherTomorrow = weatherTomorrow + "\n天气：" + date.split(" ")[1];
		weatherTomorrow = weatherTomorrow + "\n气温："
				+ detail.getProperty(13).toString();
		weatherTomorrow = weatherTomorrow + "\n风力："
				+ detail.getProperty(14).toString() + "\n";
		int iconTomorrow[] = new int[2];
		iconTomorrow[0] = parseIcon(detail.getProperty(15).toString());
		iconTomorrow[1] = parseIcon(detail.getProperty(16).toString());
		activity.txt_tomorrow.setText(weatherTomorrow);
		activity.img_tomorrow1.setImageResource(iconTomorrow[0]);
		activity.img_tomorrow2.setImageResource(iconTomorrow[1]);
	}
	
	
	
	private void WeatherAfterday(){
		date = detail.getProperty(17).toString();
		String weatherAfterday = "后天：" + date.split(" ")[0];
		weatherAfterday = weatherAfterday + "\n天气：" + date.split(" ")[1];
		weatherAfterday = weatherAfterday + "\n气温："
				+ detail.getProperty(18).toString();
		weatherAfterday = weatherAfterday + "\n风力："
				+ detail.getProperty(19).toString() + "\n";
		int iconAfterday[] = new int[2];
		iconAfterday[0] = parseIcon(detail.getProperty(20).toString());
		iconAfterday[1] = parseIcon(detail.getProperty(21).toString());
		activity.txt_afterday.setText(weatherAfterday);
		activity.img_afterday1.setImageResource(iconAfterday[0]);
		activity.img_afterday2.setImageResource(iconAfterday[1]);
	}
	
	
	// 工具方法，该方法负责把返回的天气图标字符串，转换为程序的图片资源ID。
		private int parseIcon(String strIcon) {
			if (strIcon == null)
				return -1;
			if ("0.gif".equals(strIcon))
				return R.drawable.a_0;
			if ("1.gif".equals(strIcon))
				return R.drawable.a_1;
			if ("2.gif".equals(strIcon))
				return R.drawable.a_2;
			if ("3.gif".equals(strIcon))
				return R.drawable.a_3;
			if ("4.gif".equals(strIcon))
				return R.drawable.a_4;
			if ("5.gif".equals(strIcon))
				return R.drawable.a_5;
			if ("6.gif".equals(strIcon))
				return R.drawable.a_6;
			if ("7.gif".equals(strIcon))
				return R.drawable.a_7;
			if ("8.gif".equals(strIcon))
				return R.drawable.a_8;
			if ("9.gif".equals(strIcon))
				return R.drawable.a_9;
			if ("10.gif".equals(strIcon))
				return R.drawable.a_10;
			if ("11.gif".equals(strIcon))
				return R.drawable.a_11;
			if ("12.gif".equals(strIcon))
				return R.drawable.a_12;
			if ("13.gif".equals(strIcon))
				return R.drawable.a_13;
			if ("14.gif".equals(strIcon))
				return R.drawable.a_14;
			if ("15.gif".equals(strIcon))
				return R.drawable.a_15;
			if ("16.gif".equals(strIcon))
				return R.drawable.a_16;
			if ("17.gif".equals(strIcon))
				return R.drawable.a_17;
			if ("18.gif".equals(strIcon))
				return R.drawable.a_18;
			if ("19.gif".equals(strIcon))
				return R.drawable.a_19;
			if ("20.gif".equals(strIcon))
				return R.drawable.a_20;
			if ("21.gif".equals(strIcon))
				return R.drawable.a_21;
			if ("22.gif".equals(strIcon))
				return R.drawable.a_22;
			if ("23.gif".equals(strIcon))
				return R.drawable.a_23;
			if ("24.gif".equals(strIcon))
				return R.drawable.a_24;
			if ("25.gif".equals(strIcon))
				return R.drawable.a_25;
			if ("26.gif".equals(strIcon))
				return R.drawable.a_26;
			if ("27.gif".equals(strIcon))
				return R.drawable.a_27;
			if ("28.gif".equals(strIcon))
				return R.drawable.a_28;
			if ("29.gif".equals(strIcon))
				return R.drawable.a_29;
			if ("30.gif".equals(strIcon))
				return R.drawable.a_30;
			if ("31.gif".equals(strIcon))
				return R.drawable.a_31;
			return 0;
		}
}
