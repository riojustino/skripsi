package com.justino.sistempakar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.justino.sistempakar.berita.GetResult;
import com.justino.sistempakar.berita.Result;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import com.justino.sistempakar.penyakit.GetPenyakit;
import com.justino.sistempakar.penyakit.Penyakit;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowDeteksi extends AppCompatActivity {

    Button btnback2;
    TextView tvOutput;
    TextView tvOutput2;
    TextView tv2;
    TextView detail;
    TextView saran;
    String penyakitnew = "";
    String penyakitneww = "";
    String penyakitatas = "Hasil Lainnya adalah:";
    String gambar = "";
    ImageView img;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPref;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String SHARED_PREF_HASIL = "hasil";
    private static final String KEY_PENYAKIT = "namapenyakit";
    private static final String KEY_HASIL = "hasilgejala";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hasil);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        penyakitnew = sharedPreferences.getString(KEY_PENYAKIT,null);
        sharedPref = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        penyakitneww = sharedPreferences.getString(KEY_HASIL,null);
        btnback2   = (Button)findViewById(R.id.button3);
        tvOutput =(TextView)findViewById(R.id.textView2);
        tvOutput2 =(TextView)findViewById(R.id.textView1);
        img= (ImageView) findViewById(R.id.imageView1);
        detail = (TextView)findViewById(R.id.detail);
        saran =(TextView)findViewById(R.id.saran);

        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100,TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();

        tampilResult();
//        tvOutput.setText(penyakitnew);
        tvOutput2.setText(penyakitatas);
        tv2 = (TextView)findViewById(R.id.tes);
//        tv2.setText(penyakitneww);
//        penyakitatas = penyakitnew.substring(0,10);
//            if(penyakitatas.equalsIgnoreCase("Canine Pa")){
//                penyakitatas = "Canine Parvovirus";
//            }else if (penyakitatas.equalsIgnoreCase("Canine Di")){
//            penyakitatas = "Canine Distemper Virus";
//        }else if (penyakitatas.equalsIgnoreCase("Erlichia C")){
//            penyakitatas = "Erlichia Canis";
//        }



        btnback2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ShowDeteksi.this, Dashboard.class);
                startActivity(intent);
            }
        });

    }

    public void tampilResult() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetResult> call = mApiInterface.getResult();
        call.enqueue(new Callback<GetResult>() {
            @Override
            public void onResponse(Call<GetResult> call, Response<GetResult>
                    response) {
                List<Result> resultList = response.body().getListDataResult();
                tv2.setText(resultList.get(0).getNamapenyakit() + "\n"+resultList.get(0).getHasilpenyakit() + " %");
                Log.d("Iterasi2", ""+resultList.get(0).getNamapenyakit().toString());
                for(Result result : resultList) {
                    String content = "";
                    content += result.getNamapenyakit() + "\n";
                    content += result.getHasilpenyakit() + " %"+"\n";
                    tvOutput.append(content);
                }
                if(resultList.get(0).getNamapenyakit().equalsIgnoreCase("Canine Parvovirus")){
                     getGambar("Canine Parvovirus");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Erlichia Canis")) {
                    getGambar("Erlichia Canis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Canine Distemper Virus")) {
                    getGambar("Canine Distemper Virus");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Urolithiasis")) {
                    getGambar("Urolithiasis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Pyometra")) {
                    getGambar("Pyometra");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Manifestasi Ektoparasit")) {
                    getGambar("Manifestasi Ektoparasit");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Gagal Ginjal Akut dan Kronis")) {
                    getGambar("Gagal Ginjal Akut dan Kronis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Leptospirosis")) {
                    getGambar("Leptospirosis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Hepatitis")) {
                    getGambar("Hepatitis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Giardiasis")) {
                    getGambar("Giardiasis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Helminthiasis")) {
                    getGambar("Helminthiasis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Amoebiasis")) {
                    getGambar("Amoebiasis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Tracheal Collapse")) {
                    getGambar("Tracheal Collapse");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Kornea Ulcer")) {
                    getGambar("Kornea Ulcer");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Otitis")) {
                    getGambar("Otitis");
                } else if (resultList.get(0).getNamapenyakit().equalsIgnoreCase("Auricular Hematoma")) {
                    getGambar("Auricular Hematoma");
                }
                addRiwayat(resultList.get(0).getNamapenyakit().toString(),resultList.get(0).getHasilpenyakit().toString());
                deleteResult();

            }
            @Override
            public void onFailure(Call<GetResult> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }
        });
    }

    private void addRiwayat(String nama, String hasil){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetResult> postResult = mApiInterface.tambahriwayat(nama,hasil);
        postResult.enqueue(new Callback<GetResult>() {
            @Override
            public void onResponse(Call<GetResult> call, Response<GetResult> response) {
                //Log.d("response", " "+response.body().getStatus());
                if(response.isSuccessful()) {
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<GetResult> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
            }
        });
    }

    public void deleteResult() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetResult> call = mApiInterface.deleteResult();
        call.enqueue(new Callback<GetResult>() {
            @Override
            public void onResponse(Call<GetResult> call, Response<GetResult>
                    response) {

            }

            @Override
            public void onFailure(Call<GetResult> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }
        });
    }

    public void getGambar(String penampung) {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetPenyakit> call = mApiInterface.getPenyakit();
        call.enqueue(new Callback<GetPenyakit>() {
            @Override
            public void onResponse(Call<GetPenyakit> call, Response<GetPenyakit>
                    response) {
                List<Penyakit> penyakitList = response.body().getListDataPenyakit();
                for (int i =0;i< penyakitList.size();i++) {
                    if (penyakitList.get(i).getNama_penyakit().equalsIgnoreCase(penampung)) {
                        detail.setText(penyakitList.get(i).getDet_penyakit());
                        saran.setText(penyakitList.get(i).getSrn_penyakit());
                        Glide.with(getApplicationContext())
                                .load(Config.IMAGES_URL + penyakitList.get(i).getGambar())
                                .apply(new RequestOptions().override(600, 600))
                                .into(img);
//                    } else if (penyakitList.get(i).getNama_penyakit().equalsIgnoreCase(penampung)) {
//                        detail.setText(penyakitList.get(i).getDet_penyakit());
//                        saran.setText(penyakitList.get(i).getSrn_penyakit());
//                        Glide.with(getApplicationContext())
//                                .load(Config.IMAGES_URL + penyakitList.get(i).getGambar())
//                                .apply(new RequestOptions().override(600, 600))
//                                .into(img);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPenyakit> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());

            }
        });
    }
}

