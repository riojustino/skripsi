package com.justino.sistempakar.MenuGejala;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.justino.sistempakar.MenuBerita.TambahDataBerita;
import com.justino.sistempakar.MenuBerita.TampilDataBeritaPakar;
import com.justino.sistempakar.R;
import com.justino.sistempakar.gejala.PostGejala;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import io.github.muddz.styleabletoast.StyleableToast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahGejala extends AppCompatActivity {

    EditText gejala;
    Button btntambah,btnback;
    ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambahgejala);

        gejala = (EditText) findViewById(R.id.gejalabaru);
        btntambah = (Button) findViewById(R.id.btntambah);
        btnback = (Button) findViewById(R.id.btnback);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahGejala.this, TampilGejala.class);
                startActivity(intent);
            }
        });
        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gejala.getText().toString().isEmpty())
                {
                    StyleableToast.makeText(TambahGejala.this, "Isi Gejala", R.style.ToastLengkap).show();
                    return;
                }else{
                    tambahgejala();
                }
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    void tambahgejala(){
        Call<PostGejala> postGejalaCall = apiInterface.tambahgejala(gejala.getText().toString());

        postGejalaCall.enqueue(new Callback<PostGejala>() {
            @Override
            public void onResponse(Call<PostGejala> call, Response<PostGejala> response) {
                if(response.body().getStatus().equals("Berhasil")) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(TambahGejala.this, "Penambahan gejala berhasil", R.style.ToastSucces).show();
                    Intent intent = new Intent(TambahGejala.this, TampilGejala.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(TambahGejala.this, "Penambahan gagal"+ response.message() , R.style.ToastWrong).show();
                    Intent intent = new Intent(TambahGejala.this, TampilGejala.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PostGejala> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(TambahGejala.this, "Error", R.style.ToastWrong).show();
            }
        });
    }
}





