package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.os.Handler;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_second);
        TextView textView = new TextView(this);
        textView.setTextSize(20);
        textView.setPadding(16, 16, 16, 16);
        //textView.setText("SecondActivity");
        //setContentView(textView);

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("userName").toString();
        textView.append("\nUser Name: " + name);
        setContentView(textView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                closeActivityWithResult();
            }
        }, 5000);
    }

    // Метод для обработки закрытия активности
    private void closeActivityWithResult() {
        Intent intent = new Intent();
        intent.putExtra("returned_data_key", "Returned data from SecondActivity");
        setResult(RESULT_OK, intent); // Устанавливаем результат и данные для возврата
        finish(); // Закрываем активность
    }

}