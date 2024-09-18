package com.example.guessgame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.EdText);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.res);

        Random random = new Random();
        int secretKey = random.nextInt(20) + 1;
        Log.i("Result", secretKey + "");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = editText.getText().toString();

                try {
                    int intValue = Integer.parseInt(value);

                    if (intValue < 1 || intValue > 20) {
                        Toast.makeText(MainActivity.this, "Enter a number from 1 to 20", Toast.LENGTH_SHORT).show();
                    } else if (intValue < secretKey) {
                        Toast.makeText(MainActivity.this, "The number is less than the hidden number", Toast.LENGTH_SHORT).show();
                        textView.setText("Try again");
                    } else if (intValue > secretKey) {
                        Toast.makeText(MainActivity.this, "The number is greater than the hidden number", Toast.LENGTH_SHORT).show();
                        textView.setText("Try again");
                    } else {
                        // Число угадано
                        textView.setText("You guessed it!");
                    }

                } catch (NumberFormatException e) {
                    // Если пользователь ввел число выше указанного диапазона
                    Toast.makeText(MainActivity.this, "Please enter a valid number within the specified range ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
