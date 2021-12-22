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

import com.justino.sistempakar.MenuGejala.TampilGejala;
import com.justino.sistempakar.MenuPenyakit.TampilDataPenyakit;

import io.github.muddz.styleabletoast.StyleableToast;


public class DashboardPakar extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Button btnLog;
    CardView cardpenyakit,cardgejala;
    String jabatan = "";
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_JABATAN = "jabatan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboardpakar);

        cardpenyakit = (CardView) findViewById(R.id.cardpenyakit);
        cardgejala = (CardView) findViewById(R.id.cardgejala);
        btnLog = (Button) findViewById(R.id.btnLogout);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        jabatan = sharedPreferences.getString(KEY_JABATAN,null);

        cardgejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardPakar.this, TampilGejala.class);
                startActivity(i);
            }
        });

        cardpenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DashboardPakar.this, TampilDataPenyakit.class);
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
                StyleableToast.makeText(DashboardPakar.this, "Logout Berhasil", R.style.ToastSucces).show();
                finish();
                Intent intent = new Intent(DashboardPakar.this, LoginActivity.class);
                startActivity(intent);
                Log.d("Session", ""+jabatan);
            }
        });
    }
}

