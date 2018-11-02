package com.example.natalka.terra24h;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class Sensor extends AppCompatActivity implements Serializable {

    String NR_Term;
    String data_start;
    String data_end;
    EditText dataEnd, dataStart;
    RadioButton R1, R2, R3, R4;
    ArrayList<Data> receivedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        receivedData=new ArrayList<>();

        int czujnik_numer = getIntent().getIntExtra("czujnik_numer", 1);
        NR_Term = Integer.toString(czujnik_numer);
        dataEnd = (EditText) findViewById(R.id.editText2);
        dataStart = (EditText) findViewById(R.id.editText);
        R1 = (RadioButton) findViewById(R.id.radioButton);
        R2 = (RadioButton) findViewById(R.id.radioButton2);
        R3 = (RadioButton) findViewById(R.id.radioButton3);
        R4 = (RadioButton) findViewById(R.id.radioButton4);


        final Calendar calendar = Calendar.getInstance();
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        final String currentDate = df.format(calendar.getTime());

        View.OnClickListener R1_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_end = currentDate;
                calendar.add(Calendar.DAY_OF_YEAR, -1);
                data_start = df.format(calendar.getTime());
            }
        };

        View.OnClickListener R2_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_end = currentDate;
                calendar.add(Calendar.DAY_OF_YEAR, -2);
                data_start = df.format(calendar.getTime());
            }
        };

        View.OnClickListener R3_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_end = currentDate;
                calendar.add(Calendar.DAY_OF_YEAR, -7);
                data_start = df.format(calendar.getTime());
            }
        };

        View.OnClickListener R4_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data_end = dataEnd.getText().toString();
                data_start = dataStart.getText().toString();
            }
        };
        R1.setOnClickListener(R1_listener);
        R2.setOnClickListener(R2_listener);
        R3.setOnClickListener(R3_listener);
        R4.setOnClickListener(R4_listener);


    }

    public void show(View view) {

        String type=" ";

        switch (view.getId()) {
            case R.id.wykres: {

                type = "w";
                break;
            }
            case R.id.lista: {
                type = "l";
                break;
            }
        }


        GetJSON getJSON = new GetJSON(this);
        getJSON.execute(NR_Term,data_start,data_end,type);

    }

}









