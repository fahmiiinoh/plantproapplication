package com.example.plantpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_signInScreen = findViewById(R.id.btn_signInScreen);
        Button btn_registerScreen = findViewById(R.id.btn_registerScreen);

        btn_signInScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,loginpage.class);
                startActivity(intent);
            }
        });
        btn_registerScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,registerpage.class);
                startActivity(intent);
            }
        });
    }
}