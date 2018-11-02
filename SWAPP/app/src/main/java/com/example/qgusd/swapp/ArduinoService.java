package com.example.qgusd.swapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ArduinoService extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Bluetooth.getInstance().findBT();
            Bluetooth.getInstance().openBT();

        } catch (Exception e) {
        }
    }
    public void onDestroy(){
        super.onDestroy();
    }
}
