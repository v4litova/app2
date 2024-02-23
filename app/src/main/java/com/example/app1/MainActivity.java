package com.example.app1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app1.databinding.LinearLayoutBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "app1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayoutBinding binding = LinearLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Инициализация текстового компонента строковым ресурсом
        binding.textView.setText(getString(R.string.library_title));

        // Инициализация компонента изображения ресурсом картинки
        binding.imageView.setImageResource(R.drawable.books);

        /*setContentView(R.layout.linear_layout);
        TextView textView = findViewById(R.id.textView);
        textView.setText(R.string.library_title);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.books);*/

        /*Button button = findViewById(R.id.button);
        Intent intent = new Intent(this, SecondActivity.class);
        // Программный способ: установка обработчика событий в коде
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("userName", "Valitova Diana");
                //startActivity(intent);
                mStartForResult.launch(intent);
                Log.d("MainActivity", "Кнопка была нажата");
            }
        };
        button.setOnClickListener(listener);*/

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("userName", "Valitova Diana");
                mStartForResult.launch(intent);
                Log.d("MainActivity", "Кнопка была нажата");
            }
        });

        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_SHORT).show();
        Log.v(TAG, "App is created");
    }

    private ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        // Обработка успешного результата от второй Activity
                        Intent data = result.getData();
                        String returnedData = data.getStringExtra("returned_data_key");
                        Log.d(TAG, "Received data from SecondActivity: " + returnedData);
                    } else {
                        Log.d(TAG, "SecondActivity returned with result code: " + result.getResultCode());
                    }
                }
            });

    // Декларативный способ: использование атрибута onClick в XML
    public void onButtonClick(View view)
    {
        Log.d("MainActivity", "Кнопка была нажата");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "App is started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show();
        Log.w(TAG, "App is stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show();
        Log.w(TAG, "App is destroyed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "App is paused");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "App is resumed");
    }
}