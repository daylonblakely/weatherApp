package com.example.weatherapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class weatherJson{

    private JSONObject weather;

    public weatherJson(String data){
        try {
            weather = new JSONObject(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName() throws JSONException {
        return (String) weather.get("name");
    }

    public String getDescription() throws JSONException {
        JSONArray jArr= (JSONArray) weather.get("weather");

        JSONObject weatherObj = jArr.getJSONObject(0);

        return (String) weatherObj.get("description");
    }

    public String getTemp() throws JSONException {
        JSONObject weatherObj = (JSONObject) weather.get("main");

        double temp = (double) weatherObj.get("temp") * 9 / 5 - 459.67;

        return String.valueOf(Math.round(temp));
    }
}
