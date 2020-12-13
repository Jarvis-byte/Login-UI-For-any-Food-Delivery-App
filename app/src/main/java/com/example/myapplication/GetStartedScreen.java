package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GetStartedScreen extends AppCompatActivity {
    TextView get_started_button, already_member_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_screen);
        get_started_button = findViewById(R.id.get_started_button);
        already_member_button = findViewById(R.id.already_member_button);

         //Spanable for Already A Member**********************

        Spannable word = new SpannableString("Already a Tiffina member?");
        word.setSpan(new ForegroundColorSpan(Color.BLACK), 0, word.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        already_member_button.setText(word);

        Spannable wordTwo = new SpannableString(" LOGIN");
        wordTwo.setSpan(new ForegroundColorSpan(Color.parseColor("#FF9933")), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordTwo.setSpan(new StyleSpan(Typeface.BOLD), 0, wordTwo.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        wordTwo.setSpan(new RelativeSizeSpan(1.3f), 0,wordTwo.length(), 0);
        already_member_button.append(wordTwo);

        //******************************************************************

        //************* Spannable for Get Started **********************************

        Spannable first = new SpannableString("GET STARTED\n");
        first.setSpan(new ForegroundColorSpan(Color.WHITE), 0, first.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        first.setSpan(new StyleSpan(Typeface.BOLD), 0, first.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        get_started_button.setText(first);

        Spannable secondBelow = new SpannableString("Avail Best Offers & Discounts Only For You");
        secondBelow.setSpan(new ForegroundColorSpan(Color.parseColor("#505050")), 0, secondBelow.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        secondBelow.setSpan(new RelativeSizeSpan(0.3f), 0,secondBelow.length(), 0);
        get_started_button.append(secondBelow);

        //*****************************************************************

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