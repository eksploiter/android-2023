package com.example.filetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    String FILENAME = "test.txt";
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)==false){
            Toast.makeText(this, "외부 스토리지 실패", Toast.LENGTH_LONG).show();
        }//추가

        edit = findViewById(R.id.EditText01);
        Button readButton = findViewById(R.id.read);
        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileInputStream fis = openFileInput(FILENAME);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    edit.setText(new String(buffer));
                    fis.close();
                }catch (IOException e){

                }
            }
        });

        Button writeButton = findViewById(R.id.write);
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(edit.getText().toString().getBytes());
                    fos.close();
                } catch (IOException e){

                }
            }
        });

        Button extReadButton = findViewById(R.id.extRead);
        extReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    InputStream is;
                    is = new FileInputStream(file);
                    byte[] buffer = new byte[is.available()];
                    is.read(buffer);
                    edit.setText(new String(buffer));
                    is.close();
                }catch (Exception e){
                    e.getStackTrace();
                }
            }
        });

        Button extWriteButton = findViewById(R.id.extWrite);
        extWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File file = new File(getExternalFilesDir(null), FILENAME);
                try {
                    OutputStream os;
                    os = new FileOutputStream(file);
                    os.write(edit.getText().toString().getBytes());
                    os.close();
                }catch (Exception e){
                    e.getStackTrace();
                }

            }
        });
    }
}