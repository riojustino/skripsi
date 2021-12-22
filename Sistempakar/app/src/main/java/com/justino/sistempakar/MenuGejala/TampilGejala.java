package com.justino.sistempakar.MenuGejala;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.justino.sistempakar.DashboardPakar;
import com.justino.sistempakar.R;
import com.justino.sistempakar.GejalaAdapter;
import com.justino.sistempakar.gejala.PostGejala;
import com.justino.sistempakar.gejala.Gejala;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilGejala extends AppCompatActivity {
    ApiInterface mApiInterface;
    EditText edtsearchgejala;
    Button btnsearchgejala,btntambahgejala;
    Button backlistgejala;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAMAGE = "namage";
    private static final String KEY_ID = "id";
    public static TampilGejala ii;
    String nama_gejala = "";
    String kode_gejala = "";
    List<Gejala> gejalaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_datagejala);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_gejala);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        edtsearchgejala = (EditText) findViewById(R.id.edtsearchgejala);
        btntambahgejala = (Button) findViewById(R.id.btnTambahGejala);
        btnsearchgejala = (Button) findViewById(R.id.btnsearchgejala);
        backlistgejala = (Button) findViewById(R.id.backlistgejala);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        nama_gejala = sharedPreferences.getString(KEY_NAMAGE,null);
        kode_gejala = sharedPreferences.getString(KEY_ID,null);
        ii=this;
        tampilgejala();

        backlistgejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilGejala.this, DashboardPakar.class);
                startActivity(intent);
            }
        });

        btnsearchgejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                searchgejala();
            }
        });

        btntambahgejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilGejala.this, TambahGejala.class);
                startActivity(intent);
            }
        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

    }

    public void tampilgejala() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<PostGejala> call = mApiInterface.showgejala(kode_gejala,nama_gejala);
        call.enqueue(new Callback<PostGejala>() {
            @Override
            public void onResponse(Call<PostGejala> call, Response<PostGejala>
                    response) {
                if(response.isSuccessful()){
                    gejalaList = response.body().getGejalaList();
                    Log.d("Retrofit Get", "Jumlah gejala: " +
                            String.valueOf(gejalaList.size()));
                    mAdapter = new GejalaAdapter(gejalaList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<PostGejala> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                StyleableToast.makeText(TampilGejala.this, "Gagal memuat gejala", R.style.ToastWrong).show();
            }
        });
    }

    public void searchgejala() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<PostGejala> call = mApiInterface.searchgejala(edtsearchgejala.getText().toString());
        call.enqueue(new Callback<PostGejala>() {
            @Override
            public void onResponse(Call<PostGejala> call, Response<PostGejala>
                    response) {
                List<Gejala> gejalaList = response.body().getGejalaList();
                mAdapter = new GejalaAdapter(gejalaList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<PostGejala> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                StyleableToast.makeText(TampilGejala.this, "Gagal memuat gejala", R.style.ToastWrong).show();
            }
        });
    }

}
