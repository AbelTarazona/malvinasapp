package com.example.malvinasapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class ExperienciaActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnContinuar;
    LinearLayout mainly;
    LottieAnimationView loader;
    Integer count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_experiencia);

        btnContinuar = findViewById(R.id.btnContinuar);
        btnContinuar.setOnClickListener(this);
        mainly = findViewById(R.id.mainly);
        loader = findViewById(R.id.loader);

    }

    public void outletTypeClick(View view) {
        if (view instanceof Button) {
            Button b = (Button) view;

            if (b.isSelected()) {
                count--;
                b.setTextColor(getResources().getColor(R.color.grey_40));
            } else {
                count++;
                b.setTextColor(Color.WHITE);
            }
            b.setSelected(!b.isSelected());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnContinuar:
                mainly.setVisibility(View.GONE);
                loader.setVisibility(View.VISIBLE);
                btnContinuar.setVisibility(View.GONE);

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        Intent i = new Intent(ExperienciaActivity.this, MainActivity.class);

                        startActivity(i);
                    }
                }, 3000);

                break;
        }
    }
}
