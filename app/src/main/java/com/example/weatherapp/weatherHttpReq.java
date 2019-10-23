package com.example.weatherapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class weatherHttpReq implements Runnable{
    private String zipReq = "http://api.openweathermap.org/data/2.5/weather?zip=";
    private String apiKey = "&appid=d4d40ead11f72883138f3da559e2cb94";
    private String zipcode;
    private String weatherData;

    public weatherHttpReq(String zipcode){
        this.zipcode = zipcode;
    }

    public String getWeather(){
        return weatherData;
    }

    public void run(){
        HttpURLConnection con = null ;
        InputStream is = null;

        try {
            con = (HttpURLConnection) ( new URL(zipReq+zipcode+apiKey)).openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while (  (line = br.readLine()) != null )
                buffer.append(line + "\r\n");

            is.close();
            con.disconnect();
            weatherData = buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }
    }

}
