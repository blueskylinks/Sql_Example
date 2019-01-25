package com.blueskylinks.sql_example;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Name, Pass,updateold, updatenew, delete;
    myDbAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name= (EditText) findViewById(R.id.editName);
        Pass= (EditText) findViewById(R.id.editPass);
        updateold= (EditText) findViewById(R.id.editText3);
        updatenew= (EditText) findViewById(R.id.editText5);
        helper = new myDbAdapter(this);
    }
    public void viewdata(View view)
    {
      //  String data = helper.getData();
      //  Toast.makeText(this, data, Toast.LENGTH_LONG).show();
      //  Log.i("::",data);

        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
    public void addUser(View view)
    {
        String t1 = Name.getText().toString();
        String t2 = Pass.getText().toString();
        if(t1.isEmpty() || t2.isEmpty())
        {
            Toast.makeText(this, "Enter Both name and password", Toast.LENGTH_LONG).show();
        }
        else
        {
            long id = helper.insertData(t1,t2);
            if(id<=0)
            {
                Toast.makeText(this, "insertion unsucessful", Toast.LENGTH_LONG).show();
                Name.setText("");
                Pass.setText("");
            } else
            {
                Toast.makeText(this, "insertion sucessful", Toast.LENGTH_LONG).show();
                Name.setText("");
                Pass.setText("");
            }
        }
    }

    public void onButtonShowPopupWindowClick(View view) {

        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 10, 20);

        // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
    }
    public void update( View view)
    {
        String u1 = updateold.getText().toString();
        String u2 = updatenew.getText().toString();
        if(u1.isEmpty() || u2.isEmpty())
        {
            Toast.makeText(this,"Enter Data",Toast.LENGTH_LONG).show();
        }
        else
        {
            int a= helper.updateName( u1, u2);
            if(a<=0)
            {
               Toast.makeText(this,"unsucessful",Toast.LENGTH_LONG).show();
                updateold.setText("");
                updatenew.setText("");
            } else {
               Toast.makeText(this,"updated",Toast.LENGTH_LONG).show();
                updateold.setText("");
                updatenew.setText("");
            }
        }

    }
  /*  public void delete( View view)
    {
        String uname = delete.getText().toString();
        if(uname.isEmpty())
        {
            Toast.makeText(this,"Enter data",Toast.LENGTH_LONG).show();
        }
        else{
            int a= helper.delete(uname);
            if(a<=0)
            {
                Toast.makeText(this, "unsucessful", Toast.LENGTH_SHORT).show();
                delete.setText("");
            }
            else
            {
                Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show();
                delete.setText("");
            }
        }
    }*/

}
