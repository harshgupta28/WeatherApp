package com.bwap.weatherapp.WeatherApp.controller;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class WeatherServices {
	
	    private OkHttpClient client;
	    private Response response;
	    
		private String cityName;
	    private String unit;
	    private String APIkey = "xxx"; //replace xxx by your api key
	    
	    public JSONObject getWeather()
	    {
	    	System.out.println("getWeather called");
	    	client = new OkHttpClient();  //using OKHTTP dependency . You have to add this manually form OKHTTP website
	        Request request = new Request.Builder()
	        		.url("http://api.openweathermap.org/data/2.5/weather?q="+getCityName()+"&units="+getUnit()+"&appid="+APIkey)
	        		.build();
	        
	        try 
	        {
	            response = client.newCall(request).execute();
	            return new JSONObject(response.body().string());
	        }
	        catch (IOException | JSONException e)
	        {
	            e.printStackTrace();
	        }
	        return null;
	    }
	    
	    public String getCityName() {
			return cityName;
		}

		public void setCityName(String cityName) {
			this.cityName = cityName;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

	    
	    public JSONArray returnWeatherArray() throws JSONException {
	        JSONArray weatherJsonArray = getWeather().getJSONArray("weather");
	        return weatherJsonArray;
	    }

	    public JSONObject returnMainObject() throws JSONException {
	        JSONObject mainObject = getWeather().getJSONObject("main");
	        return mainObject;
	    }


	    public JSONObject returnWindObject() throws JSONException {
	        JSONObject wind = getWeather().getJSONObject("wind");
	        return wind;
	        }

	    public JSONObject returnSysObject() throws JSONException{
	        JSONObject sys = getWeather().getJSONObject("sys");
	        return sys;
	        } // to  pull the values of Sys from JSON



}
