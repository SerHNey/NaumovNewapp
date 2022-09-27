package com.example.naumovnewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    Connection cnt;
    SimpleAdapter adapter;
    Intent addnewbuyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addnewbuyer = new Intent(MainActivity.this,adddnewbuyer.class);
        Button btnaddnew = (Button)findViewById(R.id.buttonaddnew);
        Button btnload = (Button)findViewById(R.id.buttonload2);
        GridView list = (GridView)findViewById(R.id.gridview1);
        btnaddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(addnewbuyer);

            }
        });

        btnload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Map<String, String>> data = new ArrayList<Map<String, String>>();
                Statement statement;
                try {
                    cnt = SqlCon.connect();
                    if (cnt != null) {
                        String query = "select * from Buyer";
                        statement = cnt.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                        while (resultSet.next()) {
                            Map<String, String> tab = new HashMap<String, String>();
                            tab.put("Surname", resultSet.getString("Surname"));
                            tab.put("Name", resultSet.getString("Name"));
                            tab.put("Middle_name", resultSet.getString("Middle_name"));
                            tab.put("Phone_buyer", resultSet.getString("Phone_buyer"));
                            data.add(tab);


                        }
                        String[] from = {"Surname", "Name", "Middle_name", "Phone_buyer"};
                        int[] to = {R.id.Surname, R.id.Name, R.id.Middle_name, R.id.Phone_buyer};
                        adapter = new SimpleAdapter(MainActivity.this, data, R.layout.gridviewlayout, from, to);
                        list.setAdapter(adapter);
                        statement.close();
                    }

                } catch (Exception exception) {
                    Log.d("ERROR", exception.getMessage());
                }

            }
        });
    }
}
