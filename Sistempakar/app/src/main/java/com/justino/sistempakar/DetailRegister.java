package com.justino.sistempakar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.justino.sistempakar.auth.ConfirmDeleteUsers;
import com.justino.sistempakar.auth.Users;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import io.github.muddz.styleabletoast.StyleableToast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRegister extends AppCompatActivity {
    String toastSucces = "Sukses Nih";
    String toastFail = "Gagal Nih";
    String toastError = "Error Nih";
    TextView txtNamauser, txtJabatanuser, txtNohpuser, txtEmailuser;
    Button btnKonfirmasi, btnHapus, backuserdetail;
    String username = "";
    String jabatan = "";
    String iduser = "";
    ApiInterface mApiInterface;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    public static DetailRegister di;
    List<Users> usersList ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_konfirmuser);
        txtNamauser = (TextView) findViewById(R.id.txtNama);
        txtJabatanuser = (TextView) findViewById(R.id.txtJabatanuser);
        txtNohpuser = (TextView) findViewById(R.id.txtNohpuser);
        txtEmailuser = (TextView) findViewById(R.id.txtEmailuser);
        btnKonfirmasi = (Button) findViewById(R.id.btnKonfirm);
        btnHapus = (Button) findViewById(R.id.btnHapus);
        backuserdetail = (Button) findViewById(R.id.backuserdetail);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        username = sharedPreferences.getString(KEY_USERNAME,null);
        Intent mIntent = getIntent();
       // Log.d("XX", ""+mIntent.getStringExtra("nama_user")+mIntent.getStringExtra("noktp_user"));
        iduser = mIntent.getStringExtra("id_user");
        txtNamauser.setText(mIntent.getStringExtra("nama_user"));
        jabatan = mIntent.getStringExtra("jabatan_user");
        if(jabatan.equals("1")){
            txtJabatanuser.setText("Admin");
        }
        if(jabatan.equals("2")){
            txtJabatanuser.setText("Pemilik");
        }
        if(jabatan.equals("3")){
            txtJabatanuser.setText("Pakar");
        }
        txtEmailuser.setText(mIntent.getStringExtra("email_user"));
        txtNohpuser.setText(mIntent.getStringExtra("nohp_user"));
        di=this;
        backuserdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailRegister.this, TampilRegistrasi.class);
                startActivity(intent);
            }
        });
        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konfirmasiuser();
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                batalkankonfirmasi();
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    void konfirmasiuser(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ConfirmDeleteUsers> postkonfirmuser = mApiInterface.konfirmasiuser(iduser,txtNamauser.getText().toString());
        postkonfirmuser.enqueue(new Callback<ConfirmDeleteUsers>() {
            @Override
            public void onResponse(Call<ConfirmDeleteUsers> call, Response<ConfirmDeleteUsers> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(DetailRegister.this, toastSucces, R.style.ToastSucces).show();
                    Intent intent = new Intent(DetailRegister.this, TampilRegistrasi.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(DetailRegister.this, toastFail, R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<ConfirmDeleteUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(DetailRegister.this, toastError, R.style.ToastWrong).show();
            }
        });
    }

    void batalkankonfirmasi(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<ConfirmDeleteUsers> postkonfirmuser = mApiInterface.deletekonfirmasiuser(iduser,txtNamauser.getText().toString());
        postkonfirmuser.enqueue(new Callback<ConfirmDeleteUsers>() {
            @Override
            public void onResponse(Call<ConfirmDeleteUsers> call, Response<ConfirmDeleteUsers> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(DetailRegister.this,"Sukses Dibatalkan", R.style.ToastSucces).show();
                    Intent intent = new Intent(DetailRegister.this, TampilRegistrasi.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(DetailRegister.this, "Gagal Dibatalkan", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<ConfirmDeleteUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(DetailRegister.this, "Error", R.style.ToastWrong).show();
            }
        });
    }
}
