package com.example.natalka.terra24h;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Result_chart extends AppCompatActivity {

    ArrayList<Data> receivedData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_chart);

        receivedData = new ArrayList<Data>();
        receivedData= (ArrayList<Data>) getIntent().getSerializableExtra("Dane");




    }
}
