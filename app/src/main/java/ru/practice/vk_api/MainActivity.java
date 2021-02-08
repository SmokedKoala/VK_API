package ru.practice.vk_api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText searchField;
    private Button searchButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.button_for_search:
//                        заглушка
                        resultText.setText("Всё работает");
                        break;
                }
            }
        };

        setContentView(R.layout.activity_main);
        searchField= findViewById(R.id.search_field);
        searchButton = findViewById(R.id.button_for_search);
        resultText = findViewById(R.id.result);
        searchButton.setOnClickListener(onClickListener);

    }

}