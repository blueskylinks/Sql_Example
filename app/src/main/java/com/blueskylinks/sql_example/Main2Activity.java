package com.blueskylinks.sql_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        helper = new myDbAdapter(this);
    }

    public void viewdata1(View view)
    {
        String s="sss";
        String data = helper.getData(s);
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        Log.i("::",data);
    }
}
