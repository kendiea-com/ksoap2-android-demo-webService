package com.kendiea.webservicedemo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebServiceUtil {
    // 定义webservice的命名空间
    public static final String SERVICE_NAMESPACE = "http://WebXml.com.cn/";
    // 定义webservice提供服务的url
    public static final String SERVICE_URL = "http://ws.webxml.com.cn/WebServices/WeatherWS.asmx";

    // 调用远程webservice获取省份列表
    public static List<String> getProvinceList() {
        // 调用 的方法
        String methodName = "getRegionProvince";
        // 创建HttpTransportSE传输对象
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
        try {
            ht.debug = true;
            // 使用SOAP1.1协议创建Envelop对象
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            // 实例化SoapObject对象
            SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, methodName);
            envelope.bodyOut = soapObject;
            // 设置与.NET提供的webservice保持较好的兼容性
            envelope.dotNet = true;

            // 调用webservice
            ht.call(SERVICE_NAMESPACE + methodName, envelope);
            if (envelope.getResponse() != null) {
                // 获取服务器响应返回的SOAP消息
                SoapObject result = (SoapObject) envelope.bodyIn;
                SoapObject detail = (SoapObject) result.getProperty(methodName + "Result");
                // 解析服务器响应的SOAP消息
                return parseProvinceOrCity(detail);
            }
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 根据省份获取城市列表
    public static List<String> getCityListsByProvince(String province) {
        // 调用的方法
        String methodName = "getSupportCityString";
        // 创建httptransportSE传输对象
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL);
        ht.debug = true;
        // 实例化SoapObject对象
        SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, methodName);
        // 添加一个请求参数
        soapObject.addProperty("theRegionCode", province);
        // 使用soap1.1协议创建envelop对象
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        // 设置与.NET提供的webservice保持较好的兼容性
        envelope.dotNet = true;

        // 调用webservice
        try {
            ht.call(SERVICE_NAMESPACE + methodName, envelope);
            if (envelope.getResponse() != null) {
                // 获取服务器响应返回的SOAP消息
                SoapObject result = (SoapObject) envelope.bodyIn;
                SoapObject detail = (SoapObject) result.getProperty(methodName + "Result");
                return parseProvinceOrCity(detail);// 解析服务器响应的SOAP消息
            }
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // 解析省份或城市
    public static List<String> parseProvinceOrCity(SoapObject detail) {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < detail.getPropertyCount(); i++) {
            // 解析出每个省份
            result.add(detail.getProperty(i).toString().split(",")[0]);
        }
        return result;
    }

    // 根据城市字符串获取相应天气情况
    public static SoapObject getWeatherByCity(String cityName) {

        String methodName = "getWeather";// 调用的方法
        HttpTransportSE ht = new HttpTransportSE(SERVICE_URL); // 创建httptransportSE传输对象
        ht.debug = true;
        SoapObject soapObject = new SoapObject(SERVICE_NAMESPACE, methodName); // 实例化SoapObject对象
        soapObject.addProperty("theCityCode", cityName); // 添加一个请求参数
        //请去http://WebXml.com.cn/网站注册一个账号，试用数据5天
        soapObject.addProperty("theUserID", "5e3a289435db4977b4fe1c3c73fb2ffb"); // 添加一个请求参数

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = soapObject;
        envelope.dotNet = true;

        try {
            ht.call(SERVICE_NAMESPACE + methodName, envelope);
            if (envelope.getResponse() != null) {
                SoapObject result = (SoapObject) envelope.bodyIn;
                SoapObject detail = (SoapObject) result.getProperty(methodName + "Result"); //getWeatherResult
                return detail;
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
