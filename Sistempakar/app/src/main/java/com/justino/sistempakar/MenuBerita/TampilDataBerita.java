package com.justino.sistempakar.MenuBerita;

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

import com.justino.sistempakar.R;
import com.justino.sistempakar.Dashboard;
import com.justino.sistempakar.DashboardPakar;
import com.justino.sistempakar.BeritaAdapter;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import com.justino.sistempakar.berita.GetBerita;
import com.justino.sistempakar.berita.Berita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilDataBerita extends AppCompatActivity {
    ApiInterface mApiInterface;
    EditText searchberita;
    Button backlistmenu;
    Button btnsearchproduk;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static TampilDataBerita ma;
    List<Berita> BeritaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_databerita);
        searchberita = (EditText) findViewById(R.id.edtsearchproduk);
        btnsearchproduk = (Button) findViewById(R.id.btnsearchproduk);
        backlistmenu = (Button) findViewById(R.id.backlistmenu);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_berita);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
//        ma=this;
        tampilberita();
        backlistmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilDataBerita.this, Dashboard.class);
                startActivity(intent);
            }
        });
        btnsearchproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                cariberita();
            }
        });
//        btnTambahProduk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(TampilDataBerita.this, TambahDataBerita.class);
//                startActivity(intent);
//            }
//        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    public void tampilberita() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetBerita> call = mApiInterface.getBerita();
        call.enqueue(new Callback<GetBerita>() {
            @Override
            public void onResponse(Call<GetBerita> call, Response<GetBerita>
                    response) {
                        List<Berita> beritaList = response.body().getListDataBerita();
                        Log.d("Retrofit Get", "Jumlah data Produk: " +
                                String.valueOf(beritaList.size()));
                        mAdapter = new BeritaAdapter(beritaList);
                        mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<GetBerita> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(getApplicationContext(), "Gagal memuat Berita ...!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cariberita() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetBerita> call = mApiInterface.searchberita(searchberita.getText().toString());
        call.enqueue(new Callback<GetBerita>() {
            @Override
            public void onResponse(Call<GetBerita> call, Response<GetBerita>
                    response) {
                List<Berita> beritaList = response.body().getListDataBerita();
                Log.d("Retrofit Get", "Jumlah data Berita: " +
                        String.valueOf(beritaList.size()));
                Toast.makeText(TampilDataBerita.this, "Jumlah berita = " + (beritaList.size()), Toast.LENGTH_SHORT).show();
                mAdapter = new BeritaAdapter(beritaList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<GetBerita> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toast.makeText(TampilDataBerita.this, "Gagal memuat produk  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
