package com.justino.sistempakar.MenuPenyakit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.justino.sistempakar.DashboardPakar;
import com.justino.sistempakar.R;
import com.justino.sistempakar.PenyakitAdapter;
import com.justino.sistempakar.penyakit.GetPenyakit;
import com.justino.sistempakar.penyakit.Penyakit;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilDataPenyakit extends AppCompatActivity {
    ApiInterface mApiInterface;
    EditText searchpenyakit;
    Button backlistmenu, btnTambahProduk;
    Button btnsearchproduk;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static TampilDataPenyakit ma;
    List<Penyakit> penyakitList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_datapenyakit);
        btnTambahProduk = findViewById(R.id.btnTambahProduk);
        searchpenyakit = (EditText) findViewById(R.id.edtsearchproduk);
        btnsearchproduk = (Button) findViewById(R.id.btnsearchproduk);
        backlistmenu = (Button) findViewById(R.id.backlistmenu);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_produk);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        ma=this;
        tampilpenyakit();
        backlistmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilDataPenyakit.this, DashboardPakar.class);
                startActivity(intent);
            }
        });
        btnsearchproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                caripenyakit();
            }
        });
        btnTambahProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilDataPenyakit.this, TambahDataPenyakit.class);
                startActivity(intent);
            }
        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void tampilpenyakit() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetPenyakit> call = mApiInterface.getPenyakit();
        call.enqueue(new Callback<GetPenyakit>() {
            @Override
            public void onResponse(Call<GetPenyakit> call, Response<GetPenyakit>
                    response) {
                        List<Penyakit> penyakitList = response.body().getListDataPenyakit();
                        Log.d("Retrofit Get", "Jumlah data Produk: " +
                                String.valueOf(penyakitList.size()));
                        mAdapter = new PenyakitAdapter(penyakitList);
                        mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<GetPenyakit> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(getApplicationContext(), "Gagal memuat penyakit ...!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void caripenyakit() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetPenyakit> call = mApiInterface.searchpenyakit(searchpenyakit.getText().toString());
        call.enqueue(new Callback<GetPenyakit>() {
            @Override
            public void onResponse(Call<GetPenyakit> call, Response<GetPenyakit>
                    response) {
                List<Penyakit> penyakitList = response.body().getListDataPenyakit();
                Log.d("Retrofit Get", "Jumlah data Penyakit: " +
                        String.valueOf(penyakitList.size()));
                Toast.makeText(TampilDataPenyakit.this, "Jumlah penyakit = " + (penyakitList.size()), Toast.LENGTH_SHORT).show();
                mAdapter = new PenyakitAdapter(penyakitList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<GetPenyakit> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(TampilDataPenyakit.this, "Gagal memuat produk  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
