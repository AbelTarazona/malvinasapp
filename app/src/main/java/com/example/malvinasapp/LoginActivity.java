package com.example.malvinasapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.malvinasapp.model.User;
import com.example.malvinasapp.utils.IMyAPI;
import com.example.malvinasapp.utils.RetrofitClient;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnInicioSesion;
    LinearLayout mainly;
    LottieAnimationView loader;
    IMyAPI iMyAPI;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        Retrofit retrofit = RetrofitClient.getInstance();
        iMyAPI = retrofit.create(IMyAPI.class);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnInicioSesion = findViewById(R.id.btnInicioSesion);
        btnInicioSesion.setOnClickListener(this);
        mainly = findViewById(R.id.mainly);
        loader = findViewById(R.id.loader);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInicioSesion:

                login();


                break;
        }
    }

    public void login() {
        mainly.setVisibility(View.GONE);
        loader.setVisibility(View.VISIBLE);


        Call<List<User>> call = iMyAPI.auth_login(username.getText().toString(),password.getText().toString());

        Log.d("call", call.toString());

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if(response.body().size() == 0) {
                    mainly.setVisibility(View.VISIBLE);
                    loader.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
                } else {

                    //Guardado de datos
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("Name",response.body().get(0).getName());
                    editor.putString("Photo",response.body().get(0).getPhoto());
                    editor.putString("Type",response.body().get(0).getType());
                    editor.putInt("ID",response.body().get(0).getId());
                    editor.apply();
                    //Cambio de pantalla
                    Intent i = new Intent(LoginActivity.this, MapActivity.class);
                    startActivity(i);
                    mainly.setVisibility(View.VISIBLE);
                    loader.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                mainly.setVisibility(View.VISIBLE);
                loader.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
