package com.example.phonecall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNormal = findViewById(R.id.btnNormal);//정상클래스
        Button btnAnonymous = findViewById(R.id.btnAnonymous);//익명클래스

        ImageView imageView = findViewById(R.id.imageView);//이미지

        Listener listener = new Listener();//이벤트 처리 객체 생성하는 부분
        btnNormal.setOnClickListener(listener);
        btnAnonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "익명클래스로 이벤트 처리", Toast.LENGTH_LONG).show();
            }//이런 식으로 많이 사용하게 될 것이다.
        });
        imageView.setOnClickListener(new View.OnClickListener() {//이미지 뷰에 클릭 이벤트 부여
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.pic2);//나중에는 메소드만 잘 찾으면 된다.
            }
        });
    }

    public void onClick(View v){//75페이지
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1234-5678"));
        startActivity(intent);//전화거는 화면으로 전환 - 버튼을 조작할 필요가 없으면 버튼 생성 굳이
    }//3/14

    class Listener implements View.OnClickListener {//만들어놓고 사용하지 않으면 회색이다.
    //인터페이스 구현 - 꼭 오버라이드!
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(), "정상클래스로 이벤트 처리", Toast.LENGTH_LONG).show();
        }
    }//3/21 - 3장 내용
}