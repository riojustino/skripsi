package com.justino.sistempakar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.justino.sistempakar.MenuBerita.TampilDataBeritaPakar;
import com.justino.sistempakar.MenuUser.TampilUser;

import io.github.muddz.styleabletoast.StyleableToast;


public class DashboardAdmin extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button btnLog;
    CardView carduser,cardberita;
    String jabatan = "";
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_JABATAN = "jabatan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardadmin);

        carduser = (CardView) findViewById(R.id.carduser);
        cardberita = (CardView) findViewById(R.id.cardberita);
        btnLog = (Button) findViewById(R.id.btnLogout);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        jabatan = sharedPreferences.getString(KEY_JABATAN,null);

        cardberita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardAdmin.this, TampilDataBeritaPakar.class);
                startActivity(i);
            }
        });

        carduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardAdmin.this, TampilUser.class);
                startActivity(i);
            }
        });

        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =getSharedPreferences("mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                StyleableToast.makeText(DashboardAdmin.this, "Logout Berhasil", R.style.ToastSucces).show();
                finish();
                Intent intent = new Intent(DashboardAdmin.this, LoginActivity.class);
                startActivity(intent);
                Log.d("Session", ""+jabatan);
            }
        });
    }
}

