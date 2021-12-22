package com.justino.sistempakar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.github.muddz.styleabletoast.StyleableToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.justino.sistempakar.R;
import com.justino.sistempakar.auth.LoginRegisterUsers;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LupaPassword extends AppCompatActivity {
    EditText forgetusername;
    Button btnkirimemail, backloginforgetpassword;

    SharedPreferences sharedPreferences;
    ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);
        forgetusername = (EditText) findViewById(R.id.forgetusername);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        btnkirimemail = (Button) findViewById(R.id.btnkirimemail);
        backloginforgetpassword = (Button) findViewById(R.id.btnback);

        backloginforgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LupaPassword.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnkirimemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lupapassword();
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void lupapassword(){
        Call<LoginRegisterUsers> postUsersCall = apiInterface.forgotpass(forgetusername.getText().toString());
        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                //Log.d("response", " "+response.body().getStatus());
                if(response.isSuccessful()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_EMAIL,forgetusername.getText().toString());
                    editor.apply();
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(LupaPassword.this, "Email verifikasi berhasil dikirim", R.style.ToastSucces).show();
                    Intent intent = new Intent(LupaPassword.this, VerifikasiPassword.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(LupaPassword.this, "Format email salah", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(LupaPassword.this, "E-mail is not registered", R.style.ToastWrong).show();
            }
        });
    }
}
