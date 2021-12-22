package com.justino.sistempakar;

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

public class VerifikasiPassword extends AppCompatActivity {
    EditText forgetkode;
    Button btnkonfirmasiforget,btnback;
    String email = "";
    SharedPreferences sharedPreferences;
    ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifikasi_kode);
        forgetkode = (EditText) findViewById(R.id.forgetkode);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        email = sharedPreferences.getString(KEY_EMAIL,null);
        btnback = (Button) findViewById(R.id.btnback);
        btnkonfirmasiforget = (Button) findViewById(R.id.btnkonfirmasiforget);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifikasiPassword.this, LupaPassword.class);
                startActivity(intent);
            }
        });

        btnkonfirmasiforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifikasikode();
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void verifikasikode(){
        Call<LoginRegisterUsers> postUsersCall = apiInterface.verifikasikode(forgetkode.getText().toString());
        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                if(response.isSuccessful()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PASSWORD,forgetkode.getText().toString());
                    editor.apply();
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(VerifikasiPassword.this, "Verifikasi berhasil", R.style.ToastSucces).show();
                    Intent intent = new Intent(VerifikasiPassword.this, ResetPassword.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(VerifikasiPassword.this, "Verifikasi gagal", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(VerifikasiPassword.this, "Error", R.style.ToastSucces).show();
            }
        });
    }
}
