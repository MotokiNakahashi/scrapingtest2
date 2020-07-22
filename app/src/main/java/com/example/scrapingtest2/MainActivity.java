package com.example.scrapingtest2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity {
    String url1 = "https://manaba.lms.tokushima-u.ac.jp/ct/home";
    @SuppressLint("WrongThread")


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void changeLabel(View view) {
        try {
            new AsyncHttpRequest(this).execute(new URL(url1));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
