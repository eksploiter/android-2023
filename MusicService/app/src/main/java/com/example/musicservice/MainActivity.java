package com.example.musicservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//2019041061 김민수
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MusicService";
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
            Log.d(TAG, "onClick() start");
            startService(new Intent(this, MusicService.class));
            break;
            case R.id.stop:
            Log.d(TAG, "onClick() stop");
            stopService(new Intent(this, MusicService.class));
            break;
        }
    }
}