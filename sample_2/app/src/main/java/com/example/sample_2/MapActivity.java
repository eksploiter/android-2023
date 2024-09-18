package com.example.sample_2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    private Button button5Seconds;
    private Button button7Seconds;
    private Button button10Seconds;

    private boolean buttonsLocked = false; // 버튼 잠금 상태

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        button5Seconds = findViewById(R.id.button5Seconds);
        button7Seconds = findViewById(R.id.button7Seconds);
        button10Seconds = findViewById(R.id.button10Seconds);

        // 버튼 클릭 이벤트 처리
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!buttonsLocked) { // 버튼이 잠겨있지 않은 경우에만 동작하도록 체크

                    // 클릭한 버튼의 ID를 확인하여 메시지 생성
                    String message = "";
                    switch (v.getId()) {
                        case R.id.button5Seconds:
                            message = "5초 버튼 클릭: 신호등 근처로 와주세요.";
                            break;
                        case R.id.button7Seconds:
                            message = "7초 버튼 클릭: 신호등 근처로 와주세요.";
                            break;
                        case R.id.button10Seconds:
                            message = "10초 버튼 클릭: 신호등 근처로 와주세요.";
                            break;
                    }

                    showAlertDialog(message);

                    lockButtons(); // 다른 버튼들을 잠그는 메서드 호출
                } else {
                    showWaitDialog();
                }
            }
        };

        // 각각의 버튼에 클릭 리스너 설정
        button5Seconds.setOnClickListener(clickListener);
        button7Seconds.setOnClickListener(clickListener);
        button10Seconds.setOnClickListener(clickListener);

    }

    // 대화상자(Dialog) 표시 메서드
    private void showAlertDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림")
                .setMessage(message)
                .setPositiveButton("확인", null)
                .show();
    }

    // 다른 버튼들을 잠그는 메서드
    private void lockButtons() {
        buttonsLocked = true; // 잠금 상태로 변경

        final long LOCK_DURATION_MS = 2 * 60 * 1000; // 2분 (밀리초 단위)

        Runnable unlockRunnable = new Runnable() {
            @Override
            public void run() {
                buttonsLocked = false; // 일정 시간 후에 잠금 해제

                // 여기에서 원하는 추가 작업 수행 가능

            }
        };

        getWindow().getDecorView().postDelayed(unlockRunnable, LOCK_DURATION_MS);
    }

    // 대기 대화상자(Dialog) 표시 메서드
    private void showWaitDialog() {
        final int WAIT_TIME_SECONDS = 120; // 대기 시간 (초 단위)

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this)
                        .setTitle("잠시만 기다려주세요")
                        .setMessage("버튼이 현재 비활성화되어 있습니다.")
                        .setPositiveButton("확인", null);

        final Dialog dialog=builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                final CountDownTimer countDownTimer=new CountDownTimer(WAIT_TIME_SECONDS * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished){

                        int secondsRemaining=(int)(millisUntilFinished/1000);

                        String timeRemaining=getFormattedTime(secondsRemaining);

                        if(dialog.getWindow()!=null){
                            TextView messageTextView=dialog.findViewById(android.R.id.message);
                            if(messageTextView!=null){
                                messageTextView.setText(
                                        "버튼이 현재 비활성화되어 있습니다.\n" + timeRemaining);
                            }
                        }
                    }

                    @Override
                    public void onFinish(){
                        dialog.dismiss();
                    }
                };

                countDownTimer.start();
            }
        });

        dialog.show();
    }

    private String getFormattedTime(int seconds){
        int minutes=seconds/60;
        int remainingSecs=seconds%60;

        return String.format("%02d:%02d", minutes, remainingSecs);
    }
}