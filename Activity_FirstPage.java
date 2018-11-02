package com.example.natalka.terra24h;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Activity_FirstPage extends AppCompatActivity {
     int czujnik_numer;
     EditText dataEnd, dataStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__first_page);


    }


    public void goToSensor(View view) {
        Intent intent = new Intent(Activity_FirstPage.this, Sensor.class);

        switch (view.getId()) {
            case R.id.button: {

                czujnik_numer=1;
                break;
            }
            case R.id.button2: {
                czujnik_numer=2;
                break;
            }

            case R.id.button3: {
                czujnik_numer=3;
                break;
            }

            case R.id.button4: {
                czujnik_numer=4;
                break;
            }
            case R.id.button5: {
                czujnik_numer=5;
                break;
            }
        }
        intent.putExtra("czujnik_numer",czujnik_numer);
        startActivity(intent);
    }











}