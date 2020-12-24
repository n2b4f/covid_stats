package com.example.kursach_covid_stats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Document doc;
    private Thread threadForGet;
    private Runnable runnable;
    private ListView listView;
    private CustomArrayAdapter adapter;
    private List<ListItemClass> arrayList;
    private OneObjectEx1 one_element;
    private TextView text_no_connection;
    private boolean internet_connect;
    private boolean internet_connect2;
    ConnectionDec cd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text_no_connection = findViewById(R.id.textView_no_connection);
        text_no_connection.setText("Соединение с сайтом, подождите");
        internet_connect = connection_check_second();
        internet_connect2 = false;
        init();


    }

    public void click_world(View view){
        if (internet_connect2) {
            arrayList.clear();
            adapter.notifyDataSetChanged();
            parse(0, 35);
        }
    }

    public void click_russia(View view){
        if (internet_connect2) {
            arrayList.clear();
            adapter.notifyDataSetChanged();
            parse(1, 8);
        }
    }




    private boolean connection_check_second(){
        cd = new ConnectionDec(this);
        if (cd.isConnected()) {
            return true;
        } else {
            text_no_connection = findViewById(R.id.textView_no_connection);
            text_no_connection.setText("Нет соединения с интернетом, подключитесь к сети и нажмите на кнопки сверху для обновления");
            return false;
        }

    }

    private void init(){
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        adapter = new CustomArrayAdapter(this, R.layout.list_item_1,arrayList ,getLayoutInflater());
        listView.setAdapter(adapter);
        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        threadForGet = new Thread(runnable);
        threadForGet.start();

    }

    private void parse(int number_of_table, int i_max) {
        for(int i=0; i<i_max; i++) {

            one_element = new OneObjectEx1(doc, number_of_table, i);

            ListItemClass items = new ListItemClass();
            items.setData_2(one_element.get_name());
            items.setData_3(one_element.get_cases());
            items.setData_5(one_element.get_died());
            items.setData_6(one_element.get_recovered());
            arrayList.add(items);

        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                text_no_connection = findViewById(R.id.textView_no_connection);
                text_no_connection.setText("");
            }
        });

    }


    private void getWeb(){
        try {
            doc = Jsoup.connect("https://koronavirustoday.ru/info/koronavirus-tablicza-po-stranam-mira-na-segodnya/").get();
            parse(0,35);
            internet_connect2 = internet_connect;


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openActivity1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}