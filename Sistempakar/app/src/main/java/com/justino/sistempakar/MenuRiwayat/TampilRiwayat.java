package com.justino.sistempakar.MenuRiwayat;

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

import com.justino.sistempakar.BeritaAdapter;
import com.justino.sistempakar.Dashboard;
import com.justino.sistempakar.DashboardPakar;
import com.justino.sistempakar.GejalaAdapter;
import com.justino.sistempakar.MenuGejala.TambahGejala;
import com.justino.sistempakar.MenuGejala.TampilGejala;
import com.justino.sistempakar.R;
import com.justino.sistempakar.RiwayatAdapter;
import com.justino.sistempakar.gejala.Gejala;
import com.justino.sistempakar.gejala.PostGejala;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import com.justino.sistempakar.riwayat.GetRiwayat;
import com.justino.sistempakar.riwayat.PostRiwayat;
import com.justino.sistempakar.riwayat.Riwayat;

import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilRiwayat extends AppCompatActivity {
    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<Riwayat> riwayatList;
    Button btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_datariwayat);
        btnback = (Button) findViewById(R.id.btnback);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewDeliveryProductList);
        tampilriwayat();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TampilRiwayat.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }

    public void tampilriwayat() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetRiwayat> call = mApiInterface.getRiwayat();
        call.enqueue(new Callback<GetRiwayat>() {
            @Override
            public void onResponse(Call<GetRiwayat> call, Response<GetRiwayat>
                    response) {
                if(response.isSuccessful()){
                    riwayatList = response.body().getListDataRiwayat();
                    Log.d("Retrofit Get", "Jumlah Riwayat: " +
                            String.valueOf(riwayatList.size()));
                    mAdapter = new RiwayatAdapter(riwayatList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<GetRiwayat> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                StyleableToast.makeText(TampilRiwayat.this, "Gagal memuat gejala", R.style.ToastWrong).show();
            }
        });
    }

}
