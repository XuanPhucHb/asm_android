package com.nxp.asm_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//import com.nxp.asm_android.database.DBHelper;

public class MainActivity extends AppCompatActivity {

//    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbHelper = new DBHelper();
    }
}