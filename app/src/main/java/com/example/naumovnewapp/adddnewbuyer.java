package com.example.naumovnewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class adddnewbuyer extends AppCompatActivity {
    private EditText textinputsurname;
    private EditText textinputname;
    private EditText textinputmiddle_name;
    private EditText textinputphone;
    private ImageView imageView;
    private Button btnback;
    private Button btnaddnew;
    private Intent MainAct;
    private Connection connect;
    private SqlCon con;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adddnewbuyer);

        MainAct = new Intent(this, MainActivity.class);

        textinputsurname =(EditText) findViewById(R.id.textinputsurname);
        textinputname = (EditText)findViewById(R.id.textinputname);
        textinputmiddle_name = (EditText)findViewById(R.id.textinputmiddle_name);
        textinputphone = (EditText)findViewById(R.id.textinputphone);
        btnaddnew = (Button) findViewById(R.id.btnaddnew);
        btnback = (Button) findViewById(R.id.btnback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MainAct);
            }
        });
        btnaddnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}