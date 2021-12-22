package com.justino.sistempakar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.getbase.floatingactionbutton.FloatingActionButton;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.justino.sistempakar.auth.Login;
import com.justino.sistempakar.auth.LoginUsers;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    FloatingActionButton btnLogin;
    TextInputEditText textUsername,textPassword;
    Button btnRegister,btnResetp,btnBeranda;
    String toastLengkap = "Lengkapi Data!";
    String toastSucces = "Login Sukses!";
    SharedPreferences sharedPreferences;
    ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_JABATAN = "jabatan";
    String jabatan = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        textUsername = (TextInputEditText) findViewById(R.id.Username);
        textPassword = (TextInputEditText) findViewById(R.id.Password);
        btnLogin = (FloatingActionButton) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegisterr);
        btnBeranda = (Button) findViewById(R.id.btnBeranda);
        btnResetp = (Button) findViewById(R.id.btnResetp);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        jabatan = sharedPreferences.getString(KEY_JABATAN,null);
//        Button button = (Button) findViewById(R.id.btnRegisterr);
//        button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textUsername.getText().toString().isEmpty() || textPassword.getText().toString().isEmpty()) {
                    StyleableToast.makeText(LoginActivity.this, toastLengkap, R.style.ToastLengkap).show();
                    return;
                }else{
                    Login();
                }
            }
        });

        btnBeranda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                //StyleableToast.makeText(LoginActivity.this, "Error", R.style.ToastWrong).show();
            }
        });

        btnResetp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LupaPassword.class);
                startActivity(intent);
                //StyleableToast.makeText(LoginActivity.this, "Error", R.style.ToastWrong).show();
            }
        });
    }


    private void Login(){
        Call<Login> postUsersCall = apiInterface.loginUsers(textUsername.getText().toString(), textPassword.getText().toString());
        postUsersCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()) {
                    List<LoginUsers> usersList = response.body().getLoginUsers();
                    String jabatan = usersList.get(0).getJabatan_user();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_USERNAME, textUsername.getText().toString());
                    editor.putString(KEY_JABATAN, jabatan);
                    editor.apply();
                    if(jabatan.equals("1")){
                        StyleableToast.makeText(LoginActivity.this, toastSucces,R.style.ToastSucces).show();
                        Intent intent = new Intent(LoginActivity.this, DashboardAdmin.class);
                        startActivity(intent);
                    }
//                    if(jabatan.equals("2")){
//                        StyleableToast.makeText(LoginActivity.this, toastSucces,R.style.ToastSucces).show();
//                        Intent intent = new Intent(LoginActivity.this, Dashboard.class);
//                        startActivity(intent);
//                    }
                    if(jabatan.equals("3")){
                        StyleableToast.makeText(LoginActivity.this, toastSucces,R.style.ToastSucces).show();
                        Intent intent = new Intent(LoginActivity.this, DashboardPakar.class);
                        startActivity(intent);
                    }
                }
                    else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(LoginActivity.this, "Login Gagal : Pastikan user telah terdaftar dan sudah dikonfirmasi", R.style.ToastWrong).show();                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Toast.makeText(LoginActivity.this, "Error "+t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }
}

