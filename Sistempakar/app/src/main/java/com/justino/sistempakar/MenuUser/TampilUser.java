package com.justino.sistempakar.MenuUser;

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

import com.justino.sistempakar.Dashboard;
import com.justino.sistempakar.DashboardAdmin;
import com.justino.sistempakar.TampilRegistrasi;
import com.justino.sistempakar.R;
import com.justino.sistempakar.UserAdapter;
import com.justino.sistempakar.user.PostUser;
import com.justino.sistempakar.user.User;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import java.util.List;

import io.github.muddz.styleabletoast.StyleableToast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TampilUser extends AppCompatActivity {
    ApiInterface mApiInterface;
    EditText edtsearchuser;
    Button btnsearchuser;
    Button backlistuser, btnTampilUserRegister;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ID = "id";
    public static TampilUser ii;
    String nama_user = "";
    String iduser = "";
    List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_datauser);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_user);
        btnTampilUserRegister = (Button) findViewById(R.id.btnTampilUserRegister);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        edtsearchuser = (EditText) findViewById(R.id.edtsearchuser);
        btnsearchuser = (Button) findViewById(R.id.btnsearchuser);
        backlistuser = (Button) findViewById(R.id.backlistuserr);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        nama_user = sharedPreferences.getString(KEY_USERNAME,null);
        iduser = sharedPreferences.getString(KEY_ID,null);
        ii=this;
        tampiluser();

        backlistuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilUser.this, DashboardAdmin.class);
                startActivity(intent);
            }
        });

        btnsearchuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                searchuser();
            }
        });

        btnTampilUserRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TampilUser.this, TampilRegistrasi.class);
                startActivity(intent);
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

    }

    public void tampiluser() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<PostUser> call = mApiInterface.showuser("1");
        call.enqueue(new Callback<PostUser>() {
            @Override
            public void onResponse(Call<PostUser> call, Response<PostUser>
                    response) {
                if(response.isSuccessful()){
                    userList = response.body().getUserList();
                    Log.d("Retrofit Get", "Jumlah user: " +
                            String.valueOf(userList.size()));
                    mAdapter = new UserAdapter(userList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<PostUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                StyleableToast.makeText(TampilUser.this, "Gagal memuat user", R.style.ToastWrong).show();
            }
        });
    }

    public void searchuser() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<PostUser> call = mApiInterface.searchuser(edtsearchuser.getText().toString());
        call.enqueue(new Callback<PostUser>() {
            @Override
            public void onResponse(Call<PostUser> call, Response<PostUser>
                    response) {
                List<User> userList = response.body().getUserList();
                mAdapter = new UserAdapter(userList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<PostUser> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                StyleableToast.makeText(TampilUser.this, "Gagal memuat user", R.style.ToastWrong).show();
            }
        });
    }

}
