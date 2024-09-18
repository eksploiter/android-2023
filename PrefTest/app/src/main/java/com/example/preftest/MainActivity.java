package com.example.preftest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "MyPrefs";
    TextView name;
    EditText value;
    String imagename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.TextView01);
        value = findViewById(R.id.EditText01);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        imagename = settings.getString("imageName", "");
        value.setText(imagename); //값을 읽어 오는 부분
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        imagename = value.getText().toString();
        editor.putString("imageName", imagename);
        editor.commit(); //값을 저장 하는 부분
    }   //2019041061_김민수_12주차_과제
}