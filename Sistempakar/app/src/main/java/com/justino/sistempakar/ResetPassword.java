package com.justino.sistempakar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.justino.sistempakar.R;
import com.justino.sistempakar.auth.LoginRegisterUsers;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword extends AppCompatActivity {
    EditText passwordbaru;
    Button btnresetpassword,btnback;
    String username = "";
    String pass = "";
    SharedPreferences sharedPreferences;
    ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        passwordbaru = (EditText) findViewById(R.id.passwordbaru);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        username = sharedPreferences.getString(KEY_EMAIL,null);
        pass = sharedPreferences.getString(KEY_PASSWORD,null);
        btnback = (Button) findViewById(R.id.btnback);
        btnresetpassword = (Button) findViewById(R.id.btnresetpassword);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResetPassword.this, VerifikasiPassword.class);
                startActivity(intent);
            }
        });

        btnresetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetpassword();
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void resetpassword(){
        Log.d("reset ","Username "+username+" Password baru "+passwordbaru.getText().toString());
        Call<LoginRegisterUsers> postUsersCall = apiInterface.resetpass(username,passwordbaru.getText().toString());
        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                if(response.body().getStatus().equals("berhasil")) {
                    SharedPreferences preferences =getSharedPreferences("mypref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.clear();
                    editor.apply();
                    StyleableToast.makeText(ResetPassword.this, "Berhasil reset,silahlkan login dengan password baru", R.style.ToastSucces).show();
                    finish();
                    Intent intent = new Intent(ResetPassword.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(ResetPassword.this, "Reset gagal", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(ResetPassword.this, "Error", R.style.ToastWrong).show();
            }
        });
    }
}
