package com.example.customdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity { //임플리먼트는 생략

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View v){
        Dialog loginDialog = new Dialog(this); //회색은 선언은 했지만 사용되지 않고 있다는 것!
        loginDialog.setContentView(R.layout.custom_dialog);

        Button login = loginDialog.findViewById(R.id.login);
        Button cancel = loginDialog.findViewById(R.id.cancel);
        EditText userName = loginDialog.findViewById(R.id.userName);
        EditText password = loginDialog.findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName.getText().toString().trim().length()>0
                && password.getText().toString().trim().length()>0){
                    Toast.makeText(getApplicationContext(), "로그인 성공",
                            Toast.LENGTH_LONG).show();
                    loginDialog.dismiss();
                } else{
                    Toast.makeText(getApplicationContext(), "다시 입력하세요.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginDialog.dismiss();
            }
        });
        loginDialog.show();
    }
}