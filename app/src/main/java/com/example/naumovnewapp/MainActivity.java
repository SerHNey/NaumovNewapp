package com.example.naumovnewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Connection cnt;
    SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnload = (Button) findViewById(R.id.buttonload);
        GridView list = (GridView) findViewById(R.id.gridview1);

        btnload.setOnClickListener(new View.OnClickListener(){
            @Override
                    public  void onClick(View v){
                List<Map<String,String>> data = new ArrayList<Map<String,String>>();
                try {
                    cnt = concClass();
                    if (cnt != null){
                        String query = "select * from Buyer";
                        Statement statement = cnt.createStatement();
                        ResultSet resultSet = statement.executeQuery(query);
                        while (resultSet.next()){
                            Map<String,String> tab = new HashMap<String,String>();
                            tab.put("Surname", resultSet.getString("Surname"));
                            tab.put("Name", resultSet.getString("Name"));
                            tab.put("Middle_name", resultSet.getString("Middle_name"));
                            tab.put("Phone_buyer", resultSet.getString("Phone_buyer"));
                            data.add(tab);

                        }
                        String[] from = {"Surname","Name","Middle_name","Phone_buyer"};
                        int[] to = {R.id.Surname, R.id.Name, R.id.Middle_name, R.id.Phone_buyer};
                        adapter = new SimpleAdapter(MainActivity.this, data , R.layout.gridviewlayout, from , to);
                        list.setAdapter(adapter);
                    }

                }
             catch (Exception exception) {
                Log.d("ERROR", exception.getMessage());
            }

            }
        });

    }
    private static final String LOG = "DEBUG";
    private static String ip = "ngknn.ru";
    private static String port = "1433";
    private static String classs = "net.sourceforge.jtds.jdbc.Driver";
    private static String db = "33П-КР-Ермолаев";
    private static String un = "33П";
    private static String password = "12357";

    @SuppressLint("NewApi")
    public Connection concClass() {
        Connection conn = null;
        String ConnURL = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(classs);
            ConnURL = "jdbc:jtds:sqlserver://" + ip +":"+port+";"
                    + "databaseName=" + db + ";user=" + un + ";password="
                    + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException e) {
            Log.d(LOG, e.getMessage());
        } catch (ClassNotFoundException e) {
            Log.d(LOG, e.getMessage());
        }
        return conn;
    }

}