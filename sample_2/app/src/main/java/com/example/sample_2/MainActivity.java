package com.example.sample_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML 레이아웃에서 UI 요소를 찾습니다.
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);
        signUpTextView = findViewById(R.id.textViewSignUp);

        // 로그인 버튼 클릭 이벤트 처리
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isValidLogin(username, password)) {
                    // 로그인 성공 시 다음 페이지로 이동
                    showAlertDialog("로그인 성공!", "로그인에 성공했습니다.");
                    startActivity(new Intent(MainActivity.this, MapActivity.class));
                } else {
                    // 로그인 실패 시
                    showAlertDialog("로그인 실패", "아이디 또는 비밀번호가 올바르지 않습니다.");
                }
            }
        });

        // 회원가입 텍스트뷰 클릭 이벤트 처리
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        // 건너가기 버튼 클릭 이벤트 처리
        Button skipButton = findViewById(R.id.buttonSkip);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAppDescriptionDialog();
            }
        });
    }

    private boolean isValidLogin(String username, String password) {
        return username.equals("minsu") && password.equals("1234");
    }

    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("확인", null)
                .show();
    }

    private void showAppDescriptionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("건너가유란?")
                .setMessage("이 앱은 사용자 인증 및 지도 기능을 제공합니다.\n" +
                        "몸이 불편하신 분들이 횡단보도를\n" +
                        "조금 더 수월하게 건널 수 있도록\n" +
                        "도와주는 도우미앱 입니다.")
                .setPositiveButton("닫기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        // 대화상자의 글씨 색상 설정
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                TextView messageTextView = alertDialog.findViewById(android.R.id.message);
                if (messageTextView != null) {
                    messageTextView.setTextColor(getResources().getColor(R.color.your_text_color));
                }
            }
        });

        // 대화상자의 배경 색상 설정
        alertDialog.getWindow().setBackgroundDrawableResource(R.drawable.your_background_drawable);

        // 대화상자 보여주기
        alertDialog.show();
    }
}