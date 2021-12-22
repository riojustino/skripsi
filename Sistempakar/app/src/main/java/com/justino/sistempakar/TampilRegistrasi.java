package com.justino.sistempakar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.justino.sistempakar.MenuUser.TampilUser;
import com.justino.sistempakar.R;
import com.justino.sistempakar.auth.LoginRegisterUsers;
import com.justino.sistempakar.auth.Users;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import io.github.muddz.styleabletoast.StyleableToast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilRegistrasi extends AppCompatActivity {
    ApiInterface mApiInterface;
    Button backlistpendaftar;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ID = "id";
    public static TampilRegistrasi mii;
    List<Users> usersList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_datauser_register);
        backlistpendaftar = (Button) findViewById(R.id.backlistpendaftar);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_userregister);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        mii=this;
        tampilregistrasi();

        backlistpendaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilRegistrasi.this, TampilUser.class);
                startActivity(intent);
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

    }

    public void tampilregistrasi() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<LoginRegisterUsers> call = mApiInterface.tampilregistrasiuser("2");
        call.enqueue(new Callback<LoginRegisterUsers>() {
            @Override
            public void onResponse(Call<LoginRegisterUsers> call, Response<LoginRegisterUsers>
                    response) {
                List<Users> usersList = response.body().getListDataUsers();
                Log.d("Retrofit Get", "Jumlah user: " +
                        String.valueOf(usersList.size()));
                mAdapter = new UsersRegistrasiAdapter(usersList);
                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<LoginRegisterUsers> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                StyleableToast.makeText(TampilRegistrasi.this, "Gagal memuat user", R.style.ToastWrong).show();
            }
        });
    }


}