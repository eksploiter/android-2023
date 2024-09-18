package com.example.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;
    private TextView time;
    private Calendar calendar;
    private  String format = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = findViewById(R.id.timePicker);
        time = findViewById(R.id.tvDisplaytime);
        calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);
    }

    public void displayTime(View view){
        int hour = timePicker.getHour();
        int min = timePicker.getMinute();
        showTime(hour, min);
    }

    public void showTime(int hour, int min){
        if(hour == 0){
            hour += 12;
            format = "AM";
        } else if (hour == 12)
            format = "PM";
        else if (hour > 12){
            hour -= 12;
            format = "PM";
        } else
            format = "AM";

        time.setText(new StringBuilder().append(hour).append(" : ").append(min).append(" ").append(format));
    }

}