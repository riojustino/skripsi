package com.justino.sistempakar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ExpandedMenuView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.Transformation;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.justino.sistempakar.MenuPenyakit.TampilDataPenyakitBeranda;
import com.justino.sistempakar.MenuRiwayat.TampilRiwayat;
import com.justino.sistempakar.R;
import com.justino.sistempakar.MenuBerita.TampilDataBerita;
import com.justino.sistempakar.MenuGejala.TampilGejala;
import com.justino.sistempakar.berita.Berita;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.synnapps.carouselview.ViewListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Dashboard extends AppCompatActivity {

    CarouselView customCarouselView;
    int NUMBER_OF_PAGES = 4;
    CarouselView carouselView;
    private CardView carddeteksi, cardberita, cardlogin,cardkeluar,cardabout,cardriwayat,cardpenyakit;
    TextView detailtext,detailtext2,btnjelas,btnjelas2;
    private LinearLayout mLinearLayout;
    int[] sampleImages = {R.drawable.berita1, R.drawable.berita2, R.drawable.berita3, R.drawable.berita4};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        carddeteksi = (CardView) findViewById(R.id.carddeteksi);
        cardberita = (CardView) findViewById(R.id.cardberita);
        cardriwayat = (CardView) findViewById(R.id.cardriwayat);
        cardlogin = (CardView) findViewById(R.id.cardlogin);
        cardkeluar = (CardView) findViewById(R.id.cardlogout);
        cardpenyakit = (CardView) findViewById(R.id.cardpenyakit);
        cardabout = (CardView) findViewById(R.id.cardabout);
        btnjelas = (TextView) findViewById(R.id.btnjelas);
        btnjelas2 = (TextView) findViewById(R.id.btnjelas2);
        detailtext = (TextView) findViewById(R.id.detailtext);
        detailtext2 = (TextView) findViewById(R.id.detailtext2);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        customCarouselView = (CarouselView) findViewById(R.id.carouselView);
        customCarouselView.setPageCount(NUMBER_OF_PAGES);
        // set ViewListener for custom view
        customCarouselView.setViewListener(viewListener);

        carouselView.setImageListener(imageListener);

        detailtext.setVisibility(View.INVISIBLE);
        detailtext2.setVisibility(View.INVISIBLE);

        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100,TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

        btnjelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnjelas.setVisibility(View.INVISIBLE);
                detailtext.setVisibility(View.VISIBLE);
                detailtext.setMaxLines(Integer.MAX_VALUE);
            }
        });

        detailtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailtext.setVisibility(View.INVISIBLE);
                btnjelas.setVisibility(View.VISIBLE);
            }
        });

        btnjelas2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnjelas2.setVisibility(View.INVISIBLE);
                detailtext2.setVisibility(View.VISIBLE);
                detailtext2.setMaxLines(Integer.MAX_VALUE);
            }
        });

        detailtext2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailtext2.setVisibility(View.INVISIBLE);
                btnjelas2.setVisibility(View.VISIBLE);
            }
        });

        carddeteksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, DeteksiCoba.class);
                startActivity(i);
            }
        });

        cardpenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, TampilDataPenyakitBeranda.class);
                startActivity(i);
            }
        });

        cardriwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, TampilRiwayat.class);
                startActivity(i);
            }
        });

        cardberita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, TampilDataBerita.class);
                startActivity(i);
            }
        });

        cardlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, LoginActivity.class);
                startActivity(i);
            }
        });

        cardabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, TentangKami.class);
                startActivity(i);
            }
        });

        customCarouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Intent i = new Intent(Dashboard.this, TampilDataBerita.class);
                startActivity(i);
            }
        });

        cardkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                StyleableToast.makeText(Dashboard.this, "Aplikasi dikeluarkan", R.style.ToastLengkap).show();
                startActivity(homeIntent);
            }
        });

//        cardbasis.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Dashboard.this, TampilBasis.class);
//                startActivity(i);
//            }
//        });
        }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    ViewListener viewListener = new ViewListener() {

        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.dashboard, null);
            //set view attributes here

            return customView;
        }
    };

    }

