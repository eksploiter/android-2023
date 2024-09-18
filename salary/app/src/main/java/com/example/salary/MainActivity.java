package com.example.salary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText companyEditText;
    private EditText hourlyRateEditText;
    private EditText hoursEditText;
    private EditText daysEditText;
    private Button calculateButton;
    private TextView resultTextView;


    private void saveCompanyInfo(String companyName) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("companyName", companyName);
        editor.apply();
    }

    private String getSavedCompanyInfo() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        return sharedPreferences.getString("companyName", "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companyEditText = findViewById(R.id.companyEditText);
        hourlyRateEditText = findViewById(R.id.hourlyRateEditText);
        hoursEditText = findViewById(R.id.hoursEditText);
        daysEditText = findViewById(R.id.daysEditText);
        calculateButton = findViewById(R.id.calculateButton);
        resultTextView = findViewById(R.id.resultTextView);
        String savedCompany = getSavedCompanyInfo();
        companyEditText.setText(savedCompany);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyName = companyEditText.getText().toString();
                int workingHours = Integer.parseInt(hoursEditText.getText().toString());
                int workingDays = Integer.parseInt(daysEditText.getText().toString());

                double monthlySalary = calculateSalary(workingHours, workingDays);

                String result = companyName + "의 한 달 급여는 " + monthlySalary + "원입니다.";
                resultTextView.setText(result);
                saveCompanyInfo(companyName);
            }
        });
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyName = companyEditText.getText().toString();
                String hoursText = hoursEditText.getText().toString();
                String daysText = daysEditText.getText().toString();

                if (companyName.isEmpty() || hoursText.isEmpty() || daysText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "모든 필드에 값을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                int workingHours = Integer.parseInt(hoursText);
                int workingDays = Integer.parseInt(daysText);

                double hourlyRate = Double.parseDouble(hourlyRateEditText.getText().toString());
                double monthlySalary = calculateSalary(workingHours, workingDays);

                String result = companyName + "의 한 달 급여는 " + monthlySalary + "원입니다.";
                resultTextView.setText(result);
            }

        });

    }

    private double calculateSalary(int workingHours, int workingDays) {
        double hourlyRate = Double.parseDouble(hourlyRateEditText.getText().toString());
        double monthlySalary = hourlyRate * workingHours * workingDays;

        // 세금 공제
        double tax = monthlySalary * 0.033; // 3.3% 세금으로 예시로 설정합니다.
        monthlySalary -= tax;

        return monthlySalary;
    }
}
