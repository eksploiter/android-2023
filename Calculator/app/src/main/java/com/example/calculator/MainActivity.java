package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtNum1, edtNum2, edtResult;   //세개를 변수로 지정
    private Float result;   //임시 저장 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNum1 = findViewById(R.id.edtNum1);
        edtNum2 = findViewById(R.id.edtNum2);
        edtResult = findViewById(R.id.edtResult);
    }

    public void onClick(View view) {

        String num1 = edtNum1.getText().toString();
        String num2 = edtNum2.getText().toString();

        switch (view.getId()) {
            case R.id.btnAdd:
                result = Float.parseFloat(num1) + Float.parseFloat(num2);
                break;
            case R.id.btnsub:
                result = Float.parseFloat(num1) - Float.parseFloat(num2);
                break;
            case R.id.btnmul:
                result = Float.parseFloat(num1) * Float.parseFloat(num2);
                break;
            case R.id.btndiv:
                result = Float.parseFloat(num1) / Float.parseFloat(num2);
                break;
        }

        edtResult.setText(Float.toString(result));

    }
}