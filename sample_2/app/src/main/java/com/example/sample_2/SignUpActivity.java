package com.example.sample_2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // XML 레이아웃에서 UI 요소를 찾습니다.
        nameEditText = findViewById(R.id.editTextName);
        usernameEditText = findViewById(R.id.editTextUsernameSignUp);
        passwordEditText = findViewById(R.id.editTextPasswordSignUp);
        signUpButton = findViewById(R.id.buttonSignUp);

        // 회원가입 버튼 클릭 이벤트 처리
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (isValidSignUp(name, username, password)) {
                    // 회원가입 성공 시
                    showAlertDialog("회원가입 성공!", "회원가입이 성공적으로 완료되었습니다.");
                } else {
                    // 회원가입 실패 시
                    showAlertDialog("회원가입 실패", "모든 필드를 작성해주세요.");
                }
            }
        });
    }

    // 실제 회원가입 로직을 구현하는 메서드
    private boolean isValidSignUp(String name, String username, String password) {
        return !name.isEmpty() && !username.isEmpty() && !password.isEmpty();
    }

    // 대화상자(Dialog) 표시 메서드
    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (title.equals("회원가입 성공!")) {  // 회원가입이 성공한 경우에만 로그인 화면으로 이동합니다.
                            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                            finish();  // 현재 액티비티 종료
                        }
                    }
                })
                .show();
    }
}