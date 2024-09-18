package com.example.mymenu; //2019041061 김민수

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text; //왜 이곳에 선언 했을까? 다른 메소드 에서도 사용 하니까!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.tvHello);
        registerForContextMenu(text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_search:
                Toast.makeText(this,"search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_delete:
                Toast.makeText(this,"delete", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_info:
                Toast.makeText(this,"info", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("컨텍스트 메뉴");
        menu.add(0, 1, 0, "배경색 : RED");
        menu.add(0, 2, 0, "배경색 : GREEN");
        menu.add(0, 3, 0, "배경색 : BLUE");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                text.setBackgroundColor(Color.RED);
                return true;
            case 2:
                text.setBackgroundColor(Color.GREEN);
                return true;
            case 3:
                text.setBackgroundColor(Color.BLUE);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void onClick(View button) {
        PopupMenu popup = new PopupMenu(this, button);
        popup.getMenuInflater().inflate(R.menu.popup, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(getApplicationContext(), "클릭된 팝업 메뉴: " + item.getTitle(),
                        Toast.LENGTH_LONG).show();
                return true;
            }
        });
        popup.show();
    }
}