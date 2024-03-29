package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;

public class DisplayWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_weather);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //create json object
        weatherJson weather = new weatherJson(message);
        String name = "";
        String temp = "";
        String description = "";
        try {
            name = weather.getName();
            temp = weather.getTemp()+ "\u00B0" + "F";
            description = weather.getDescription();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Capture the layout's TextView and set the string as its text
        TextView nameText = findViewById(R.id.nameTextView);
        nameText.setText(name);

        TextView descText = findViewById(R.id.descTextView);
        descText.setText(description);

        TextView tempText = findViewById(R.id.tempTextView);
        tempText.setText(temp);
    }
}
