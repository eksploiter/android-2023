package com.example.intenttest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

        Intent intent = getIntent();
        EditText edtFileName = findViewById(R.id.edtFileName);
        edtFileName.setText(intent.getStringExtra("fileName"));
        Button btnModify = findViewById(R.id.btnModify);
        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent();
                in.putExtra("RESULT", edtFileName.getText().toString());
                setResult(RESULT_OK, in);
                finish();
            }
        });
        Button btnClose = findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); //액티비티 종료
            }
        });
    }
}
