package com.example.intenttest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final int GET_RESULT = 1;
    TextView tvModifiedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvModifiedName = findViewById(R.id.tvModifiedName);
        Button btnOpen = findViewById(R.id.btnOpen);
        EditText edtName = findViewById(R.id.edtName);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("fileName", edtName.getText().toString());
                startActivityForResult(intent, GET_RESULT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_RESULT)
            if (requestCode == RESULT_OK)
                tvModifiedName.setText(data.getStringExtra("RESULT"));
    }
}