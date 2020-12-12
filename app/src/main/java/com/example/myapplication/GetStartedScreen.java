package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GetStartedScreen extends AppCompatActivity {
    TextView get_started_button,already_member_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_screen);
        get_started_button = findViewById(R.id.get_started_button);
        already_member_button=findViewById(R.id.already_member_button);
        already_member_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStartedScreen.this, loginPage.class);
                startActivity(intent);
            }
        });
        get_started_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetStartedScreen.this, loginPage.class);
                startActivity(intent);
            }
        });
    }
}