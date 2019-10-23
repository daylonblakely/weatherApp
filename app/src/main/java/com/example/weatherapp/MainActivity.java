package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.weatherapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /** Called when the user taps the Submit button */
    public void sendZip(View view) {
        Intent intent = new Intent(this, DisplayWeather.class);

        EditText zipText = findViewById(R.id.editZip);
        String zipcode = zipText.getText().toString();

        //Run network action in separate thread
        weatherHttpReq req = new weatherHttpReq(zipcode);
        Thread reqThread = new Thread(req);
        reqThread.start();
        //wait for thread to complete
        try {
            reqThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String weatherData = req.getWeather();

        intent.putExtra(EXTRA_MESSAGE, weatherData);
        startActivity(intent);
    }
}
