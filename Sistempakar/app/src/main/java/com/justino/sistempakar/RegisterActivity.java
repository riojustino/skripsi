package com.justino.sistempakar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.justino.sistempakar.auth.LoginRegisterUsers;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import com.getbase.floatingactionbutton.FloatingActionButton;

import io.github.muddz.styleabletoast.StyleableToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText Username, Password, No_hp, Email;
    Spinner spinnerJabatanUser;
    Button backregistrasilogin;
    FloatingActionButton btnRegister;
    ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        Username = (EditText) findViewById(R.id.Username);
        Email = (EditText) findViewById(R.id.Email);
        No_hp = (EditText) findViewById(R.id.No_hp);
        Password = (EditText) findViewById(R.id.Password);
        spinnerJabatanUser = (Spinner) findViewById(R.id.spinnerRole);
        btnRegister = (FloatingActionButton) findViewById(R.id.btnRegister);
        backregistrasilogin = (Button) findViewById(R.id.btnBack);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jabatan, R.layout.spinneritem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJabatanUser.setAdapter(adapter);

        String email = Email.getText().toString().trim();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        // onClick of button perform this simplest code.
        backregistrasilogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Username.getText().toString().isEmpty() ||
                        Password.getText().toString().isEmpty() ||
//                        spinnerJabatanUser.getSelectedItem().toString().isEmpty() ||
                        Email.getText().toString().isEmpty() ||
                        No_hp.getText().toString().isEmpty() ||
                        spinnerJabatanUser.getSelectedItem().toString().isEmpty())
                {
                    StyleableToast.makeText(RegisterActivity.this, "Lengkapi Data", R.style.ToastLengkap).show();
                    return;
                }else{
                    if(Email.getText().toString().trim().matches(emailPattern))
                    {
                        if(spinnerJabatanUser.getSelectedItem().toString().equals("Pakar")) {
                            getusernamepemilik();
                        }else if(spinnerJabatanUser.getSelectedItem().toString().equals("Admin")){
                            getusernamepakar();
                        }
                    }
                    else
                    {
                        StyleableToast.makeText(RegisterActivity.this, "Email Tidak Valid", R.style.ToastWrong).show();
                    }

                }
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    void getusernamepakar(){
        Call<LoginRegisterUsers> postUsersCall = apiInterface.getusername(
                Username.getText().toString());

        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    getemailpakar();
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(RegisterActivity.this, "Username tidak tersedia", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(RegisterActivity.this, "Error", R.style.ToastWrong).show();
            }
        });
    }

    void getusernamepemilik(){
        Call<LoginRegisterUsers> postUsersCall = apiInterface.getusername(
                Username.getText().toString());

        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    getemailpemilik();
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(RegisterActivity.this, "Username tidak tersedia", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(RegisterActivity.this, "Error", R.style.ToastWrong).show();
            }
        });
    }

    void getemailpakar(){
        Call<LoginRegisterUsers> postUsersCall = apiInterface.getemail(
                Email.getText().toString());

        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    RegistrasiUserpakar();
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(RegisterActivity.this, "Email sudah digunakan", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(RegisterActivity.this, "Error", R.style.ToastWrong).show();
            }
        });
    }

    void getemailpemilik(){
        Call<LoginRegisterUsers> postUsersCall = apiInterface.getemail(
                Email.getText().toString());

        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    RegistrasiUserpemilik();
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(RegisterActivity.this, "Email sudah digunakan", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(RegisterActivity.this, "Error", R.style.ToastWrong).show();            }
        });
    }

    void RegistrasiUserpemilik(){
        Call<LoginRegisterUsers> postUsersCall = apiInterface.regisUser(
                Username.getText().toString(),
                No_hp.getText().toString(),
                Email.getText().toString(),
                Password.getText().toString(),
                3);

        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                if(response.body().getStatus().equals("Berhasil")) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(RegisterActivity.this, "Registrasi Berhasil pemilik", R.style.ToastSucces).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(RegisterActivity.this, "Registrasi Gagal pemilik"+ response.message() , R.style.ToastWrong).show();
                    Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(RegisterActivity.this, "Error", R.style.ToastWrong).show();
            }
        });
    }
    void RegistrasiUserpakar(){
        Call<LoginRegisterUsers> postUsersCall = apiInterface.regisUser(
                Username.getText().toString(),
                No_hp.getText().toString(),
                Email.getText().toString(),
                Password.getText().toString(),
                1);

        postUsersCall.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(RegisterActivity.this, "Register pakar Sukses", R.style.ToastSucces).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(RegisterActivity.this, "Register Gagal", R.style.ToastWrong).show();
                    Intent intent = new Intent(RegisterActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(RegisterActivity.this, "Error", R.style.ToastWrong).show();
            }
        });
    }
}





