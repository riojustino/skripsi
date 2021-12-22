package com.justino.sistempakar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.justino.sistempakar.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.justino.sistempakar.auth.LoginRegisterUsers;
import com.justino.sistempakar.auth.Users;
import com.justino.sistempakar.berita.GetResult;
import com.justino.sistempakar.gejala.Gejala;
import com.justino.sistempakar.gejala.GetGejala;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import com.justino.sistempakar.penyakit.GetPenyakit;
import com.justino.sistempakar.penyakit.Penyakit;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeteksiCoba extends AppCompatActivity {
    public String NamaPenyakit = "";
    String gejala1 = "";
    LinearLayout lin1,lin2;
    ApiInterface mApiInterface;
    ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
    double tampungangka,tampungangka1,tampungangka2,tampungangka3,tampungangka4,tampungangka5,tampungangka6,tampungangka7,tampungangka8,tampungangka9,tampungangka10,
            tampungangka11,tampungangka12,tampungangka13,tampungangka14,tampungangka15,tampungangka16,tampungangka17,tampungangka18,tampungangka19,tampungangka20,
            tampungangka21,tampungangka22,tampungangka23,tampungangka24,tampungangka25,tampungangka26,tampungangka27,tampungangka28,tampungangka29,tampungangka30,
            tampungangka31,tampungangka32,tampungangka33,tampungangka34,tampungangka35,tampungangka36,tampungangka37,tampungangka38,tampungangka39,tampungangka40,
            tampungangka41,tampungangka42,tampungangka43,tampungangka44,tampungangka45,tampungangka46,tampungangka47,tampungangka48,tampungangka49,tampungangka50,
            tampungangka51,tampungangka52,tampungangka53,tampungangka54,tampungangka55,tampungangka56,tampungangka57,tampungangka58,tampungangka59,tampungangka60,
            tampungangka61,tampungangka62,tampungangka63,tampungangka64;
    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7,spinner8,spinner9,spinner10,
            spinner11,spinner12,spinner13,spinner14,spinner15,spinner16,spinner17,spinner18,spinner19,spinner20,
            spinner21,spinner22,spinner23,spinner24,spinner25,spinner26,spinner27,spinner28,spinner29,spinner30,
            spinner31,spinner32,spinner33,spinner34,spinner35,spinner36,spinner37,spinner38,spinner39,spinner40,
            spinner41,spinner42,spinner43,spinner44,spinner45,spinner46,spinner47,spinner48,spinner49,spinner50,
            spinner51,spinner52,spinner53,spinner54,spinner55,spinner56,spinner57,spinner58,spinner59,spinner60,
            spinner61,spinner62,spinner63,spinner64;
    Button btnproses,btnback,btnbantu;
    Switch switch1, switch2, switch3, switch4, switch5, switch6,switch7,switch8,switch9,switch10,
            switch11, switch12, switch13, switch14, switch15, switch16,switch17,switch18,switch19,switch20,
            switch21,switch22,switch23,switch24,switch25,switch26,switch27,switch28,switch29,switch30,
            switch31,switch32,switch33,switch34,switch35,switch36,switch37,switch38,switch39,switch40,
            switch41,switch42,switch43,switch44,switch45,switch46,switch47,switch48,switch49,switch50,
            switch51,switch52,switch53,switch54,switch55,switch56,switch57,switch58,switch59,switch60,
            switch61,switch62,switch63,switch64;
    SharedPreferences sharedPreferences;
    SharedPreferences sharedPref;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_PENYAKIT = "namapenyakit";
    private static final String KEY_HASIL = "hasilgejala";

    String namapenyakit1 = ""; String namapenyakit5 = ""; String namapenyakit9 = ""; String namapenyakit12 = ""; String namapenyakit15 = "";
    String namapenyakit2 = ""; String namapenyakit6 = ""; String namapenyakit10 = ""; String namapenyakit13 = ""; String namapenyakit16 = "";
    String namapenyakit3 = ""; String namapenyakit7 = ""; String namapenyakit11 = ""; String namapenyakit14 = ""; String namapenyakit17 = "";
    String namapenyakit4 = ""; String namapenyakit8 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deteksicoba);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        sharedPref = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        btnproses   = (Button)findViewById(R.id.button);
        btnbantu   = (Button)findViewById(R.id.bantuan);
        btnback   = (Button)findViewById(R.id.button2);
        lin1   = (LinearLayout) findViewById(R.id.lin1);
        lin2   = (LinearLayout) findViewById(R.id.lin2);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);
        spinner4 = (Spinner) findViewById(R.id.spinner4);
        spinner5 = (Spinner) findViewById(R.id.spinner5);
        spinner6 = (Spinner) findViewById(R.id.spinner6);
        spinner7 = (Spinner) findViewById(R.id.spinner7);
        spinner8 = (Spinner) findViewById(R.id.spinner8);
        spinner9 = (Spinner) findViewById(R.id.spinner9);
        spinner10 = (Spinner) findViewById(R.id.spinner10);
        spinner11 = (Spinner) findViewById(R.id.spinner11);
        spinner12 = (Spinner) findViewById(R.id.spinner12);
        spinner13 = (Spinner) findViewById(R.id.spinner13);
        spinner14 = (Spinner) findViewById(R.id.spinner14);
        spinner15 = (Spinner) findViewById(R.id.spinner15);
        spinner16 = (Spinner) findViewById(R.id.spinner16);
        spinner17 = (Spinner) findViewById(R.id.spinner17);
        spinner18 = (Spinner) findViewById(R.id.spinner18);
        spinner19 = (Spinner) findViewById(R.id.spinner19);
        spinner20 = (Spinner) findViewById(R.id.spinner20);
        spinner21 = (Spinner) findViewById(R.id.spinner21);
        spinner22 = (Spinner) findViewById(R.id.spinner22);
        spinner23 = (Spinner) findViewById(R.id.spinner23);
        spinner24 = (Spinner) findViewById(R.id.spinner24);
        spinner25 = (Spinner) findViewById(R.id.spinner25);
        spinner26 = (Spinner) findViewById(R.id.spinner26);
        spinner27 = (Spinner) findViewById(R.id.spinner27);
        spinner28 = (Spinner) findViewById(R.id.spinner28);
        spinner29 = (Spinner) findViewById(R.id.spinner29);
        spinner30 = (Spinner) findViewById(R.id.spinner30);
        spinner31 = (Spinner) findViewById(R.id.spinner31);
        spinner32 = (Spinner) findViewById(R.id.spinner32);
        spinner33 = (Spinner) findViewById(R.id.spinner33);
        spinner34 = (Spinner) findViewById(R.id.spinner34);
        spinner35 = (Spinner) findViewById(R.id.spinner35);
        spinner36 = (Spinner) findViewById(R.id.spinner36);
        spinner37 = (Spinner) findViewById(R.id.spinner37);
        spinner38 = (Spinner) findViewById(R.id.spinner38);
        spinner39 = (Spinner) findViewById(R.id.spinner39);
        spinner40 = (Spinner) findViewById(R.id.spinner40);
        spinner41 = (Spinner) findViewById(R.id.spinner41);
        spinner42 = (Spinner) findViewById(R.id.spinner42);
        spinner43 = (Spinner) findViewById(R.id.spinner43);
        spinner44 = (Spinner) findViewById(R.id.spinner44);
        spinner45 = (Spinner) findViewById(R.id.spinner45);
        spinner46 = (Spinner) findViewById(R.id.spinner46);
        spinner47 = (Spinner) findViewById(R.id.spinner47);
        spinner48 = (Spinner) findViewById(R.id.spinner48);
        spinner49 = (Spinner) findViewById(R.id.spinner49);
        spinner50 = (Spinner) findViewById(R.id.spinner50);
        spinner51 = (Spinner) findViewById(R.id.spinner51);
        spinner52 = (Spinner) findViewById(R.id.spinner52);
        spinner53 = (Spinner) findViewById(R.id.spinner53);
        spinner54 = (Spinner) findViewById(R.id.spinner54);
        spinner55 = (Spinner) findViewById(R.id.spinner55);
        spinner56 = (Spinner) findViewById(R.id.spinner56);
        spinner57 = (Spinner) findViewById(R.id.spinner57);
        spinner58 = (Spinner) findViewById(R.id.spinner58);
        spinner59 = (Spinner) findViewById(R.id.spinner59);
        spinner60 = (Spinner) findViewById(R.id.spinner60);
        spinner61 = (Spinner) findViewById(R.id.spinner61);
        spinner62 = (Spinner) findViewById(R.id.spinner62);
        spinner63 = (Spinner) findViewById(R.id.spinner63);
        spinner64 = (Spinner) findViewById(R.id.spinner64);

        switch1  = (Switch) findViewById(R.id.switch1);
        switch2  = (Switch) findViewById(R.id.switch2);
        switch3  = (Switch) findViewById(R.id.switch3);
        switch4  = (Switch) findViewById(R.id.switch4);
        switch5  = (Switch) findViewById(R.id.switch5);
        switch6  = (Switch) findViewById(R.id.switch6);
        switch7  = (Switch) findViewById(R.id.switch7);
        switch8 = (Switch) findViewById(R.id.switch8);
        switch9 = (Switch) findViewById(R.id.switch9);
        switch10 = (Switch) findViewById(R.id.switch10);
        switch11 = (Switch) findViewById(R.id.switch11);
        switch12 = (Switch) findViewById(R.id.switch12);
        switch13 = (Switch) findViewById(R.id.switch13);
        switch14 = (Switch) findViewById(R.id.switch14);
        switch15 = (Switch) findViewById(R.id.switch15);
        switch16 = (Switch) findViewById(R.id.switch16);
        switch17 = (Switch) findViewById(R.id.switch17);
        switch18 = (Switch) findViewById(R.id.switch18);
        switch19 = (Switch) findViewById(R.id.switch19);
        switch20 = (Switch) findViewById(R.id.switch20);
        switch21 = (Switch) findViewById(R.id.switch21);
        switch22 = (Switch) findViewById(R.id.switch22);
        switch23 = (Switch) findViewById(R.id.switch23);
        switch24 = (Switch) findViewById(R.id.switch24);
        switch25 = (Switch) findViewById(R.id.switch25);
        switch26 = (Switch) findViewById(R.id.switch26);
        switch27 = (Switch) findViewById(R.id.switch27);
        switch28 = (Switch) findViewById(R.id.switch28);
        switch29 = (Switch) findViewById(R.id.switch29);
        switch30 = (Switch) findViewById(R.id.switch30);
        switch31 = (Switch) findViewById(R.id.switch31);
        switch32 = (Switch) findViewById(R.id.switch32);
        switch33 = (Switch) findViewById(R.id.switch33);
        switch34 = (Switch) findViewById(R.id.switch34);
        switch35 = (Switch) findViewById(R.id.switch35);
        switch36 = (Switch) findViewById(R.id.switch36);
        switch37 = (Switch) findViewById(R.id.switch37);
        switch38 = (Switch) findViewById(R.id.switch38);
        switch39 = (Switch) findViewById(R.id.switch39);
        switch40 = (Switch) findViewById(R.id.switch40);
        switch41 = (Switch) findViewById(R.id.switch41);
        switch42 = (Switch) findViewById(R.id.switch42);
        switch43 = (Switch) findViewById(R.id.switch43);
        switch44 = (Switch) findViewById(R.id.switch44);
        switch45 = (Switch) findViewById(R.id.switch45);
        switch46 = (Switch) findViewById(R.id.switch46);
        switch47 = (Switch) findViewById(R.id.switch47);
        switch48 = (Switch) findViewById(R.id.switch48);
        switch49 = (Switch) findViewById(R.id.switch49);
        switch50 = (Switch) findViewById(R.id.switch50);
        switch51 = (Switch) findViewById(R.id.switch51);
        switch52 = (Switch) findViewById(R.id.switch52);
        switch53 = (Switch) findViewById(R.id.switch53);
        switch54 = (Switch) findViewById(R.id.switch54);
        switch55 = (Switch) findViewById(R.id.switch55);
        switch56 = (Switch) findViewById(R.id.switch56);
        switch57 = (Switch) findViewById(R.id.switch57);
        switch58 = (Switch) findViewById(R.id.switch58);
        switch59 = (Switch) findViewById(R.id.switch59);
        switch60 = (Switch) findViewById(R.id.switch60);
        switch61 = (Switch) findViewById(R.id.switch61);
        switch62 = (Switch) findViewById(R.id.switch62);
        switch63 = (Switch) findViewById(R.id.switch63);
        switch64 = (Switch) findViewById(R.id.switch64);



        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);

        tampilgejala();
        tampilpenyakit();

        OkHttpClient client = new OkHttpClient.Builder()
                .callTimeout(3, TimeUnit.MINUTES)
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100,TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create(new Gson())).build();



        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner1.getSelectedItem();
                tampungangka1 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner2.getSelectedItem();
                tampungangka2 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner3.getSelectedItem();
                tampungangka3 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);
        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner4.getSelectedItem();
                tampungangka4 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner5.setAdapter(adapter5);
        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner5.getSelectedItem();
                tampungangka5 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner6.setAdapter(adapter6);
        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner6.getSelectedItem();
                tampungangka6 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter7 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner7.setAdapter(adapter7);
        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner7.getSelectedItem();
                tampungangka7 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter8 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner8.setAdapter(adapter8);
        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner8.getSelectedItem();
                tampungangka8 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter9 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter9.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner9.setAdapter(adapter9);
        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner9.getSelectedItem();
                tampungangka9 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter10 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter10.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner10.setAdapter(adapter10);
        spinner10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner10.getSelectedItem();
                tampungangka10 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter11 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter11.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner11.setAdapter(adapter11);
        spinner11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner11.getSelectedItem();
                tampungangka11 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter12 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter12.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner12.setAdapter(adapter12);
        spinner12.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner12.getSelectedItem();
                tampungangka12 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter13 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter13.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner13.setAdapter(adapter13);
        spinner13.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner13.getSelectedItem();
                tampungangka13 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter14 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter14.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner14.setAdapter(adapter14);
        spinner14.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner14.getSelectedItem();
                tampungangka14 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter15 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter15.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner15.setAdapter(adapter15);
        spinner15.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner15.getSelectedItem();
                tampungangka15 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter16 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter16.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner16.setAdapter(adapter16);
        spinner16.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner16.getSelectedItem();
                tampungangka16 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter17 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter17.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner17.setAdapter(adapter17);
        spinner17.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner17.getSelectedItem();
                tampungangka17 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter18 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter18.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner18.setAdapter(adapter18);
        spinner18.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner18.getSelectedItem();
                tampungangka18 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter19 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner19.setAdapter(adapter19);
        spinner19.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner19.getSelectedItem();
                tampungangka19 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter20 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner20.setAdapter(adapter20);
        spinner20.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner20.getSelectedItem();
                tampungangka20 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter21 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter21.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner21.setAdapter(adapter21);
        spinner21.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner21.getSelectedItem();
                tampungangka21 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter22 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter22.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner22.setAdapter(adapter22);
        spinner22.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner22.getSelectedItem();
                tampungangka22 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter23 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter23.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner23.setAdapter(adapter23);
        spinner23.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner23.getSelectedItem();
                tampungangka23 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter24 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter24.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner24.setAdapter(adapter24);
        spinner24.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner24.getSelectedItem();
                tampungangka24 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter25 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter25.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner25.setAdapter(adapter25);
        spinner25.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner25.getSelectedItem();
                tampungangka25 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter26 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter26.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner26.setAdapter(adapter26);
        spinner26.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner26.getSelectedItem();
                tampungangka26 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter27 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter27.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner27.setAdapter(adapter27);
        spinner27.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner27.getSelectedItem();
                tampungangka27 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter28 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter28.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner28.setAdapter(adapter28);
        spinner28.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner28.getSelectedItem();
                tampungangka28 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter29 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter29.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner29.setAdapter(adapter29);
        spinner29.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner29.getSelectedItem();
                tampungangka29 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter30 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter30.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner30.setAdapter(adapter30);
        spinner30.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner30.getSelectedItem();
                tampungangka30 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter31 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter31.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner31.setAdapter(adapter31);
        spinner31.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner31.getSelectedItem();
                tampungangka31 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter32 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter32.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner32.setAdapter(adapter32);
        spinner32.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner32.getSelectedItem();
                tampungangka32 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter33 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter33.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner33.setAdapter(adapter33);
        spinner33.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner33.getSelectedItem();
                tampungangka33 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter34 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter34.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner34.setAdapter(adapter34);
        spinner34.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner34.getSelectedItem();
                tampungangka34 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter35 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter35.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner35.setAdapter(adapter35);
        spinner35.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner35.getSelectedItem();
                tampungangka35 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter36 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter36.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner36.setAdapter(adapter36);
        spinner36.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner36.getSelectedItem();
                tampungangka36 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter37 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter37.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner37.setAdapter(adapter37);
        spinner37.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner37.getSelectedItem();
                tampungangka37 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter38 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter38.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner38.setAdapter(adapter38);
        spinner38.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner38.getSelectedItem();
                tampungangka38 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter39 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter39.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner39.setAdapter(adapter39);
        spinner39.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner39.getSelectedItem();
                tampungangka39 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter40 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter40.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner40.setAdapter(adapter40);
        spinner40.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner40.getSelectedItem();
                tampungangka40 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter41 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter41.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner41.setAdapter(adapter41);
        spinner41.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner41.getSelectedItem();
                tampungangka41 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter42 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter42.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner42.setAdapter(adapter42);
        spinner42.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner42.getSelectedItem();
                tampungangka42 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter43 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter43.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner43.setAdapter(adapter43);
        spinner43.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner43.getSelectedItem();
                tampungangka43 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter44 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter44.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner44.setAdapter(adapter44);
        spinner44.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner44.getSelectedItem();
                tampungangka44 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter45 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter45.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner45.setAdapter(adapter45);
        spinner45.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner45.getSelectedItem();
                tampungangka45 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter46 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter46.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner46.setAdapter(adapter46);
        spinner46.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner46.getSelectedItem();
                tampungangka46 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter47 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter47.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner47.setAdapter(adapter47);
        spinner47.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner47.getSelectedItem();
                tampungangka47 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter48 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter48.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner48.setAdapter(adapter48);
        spinner48.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner48.getSelectedItem();
                tampungangka48 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter49 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter49.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner49.setAdapter(adapter49);
        spinner49.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner49.getSelectedItem();
                tampungangka49 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter50 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter50.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner50.setAdapter(adapter50);
        spinner50.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner50.getSelectedItem();
                tampungangka50 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter51 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter51.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner51.setAdapter(adapter51);
        spinner51.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner51.getSelectedItem();
                tampungangka51 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter52 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter52.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner52.setAdapter(adapter52);
        spinner52.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner52.getSelectedItem();
                tampungangka52 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter53 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter53.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner53.setAdapter(adapter53);
        spinner53.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner53.getSelectedItem();
                tampungangka53 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter54 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter54.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner54.setAdapter(adapter54);
        spinner54.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner54.getSelectedItem();
                tampungangka54 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter55 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter55.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner55.setAdapter(adapter55);
        spinner55.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner55.getSelectedItem();
                tampungangka55 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter56 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter56.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner56.setAdapter(adapter56);
        spinner56.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner56.getSelectedItem();
                tampungangka56 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter57 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter57.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner57.setAdapter(adapter57);
        spinner57.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner57.getSelectedItem();
                tampungangka57 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter58 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter58.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner58.setAdapter(adapter58);
        spinner58.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner58.getSelectedItem();
                tampungangka58 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter59 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter59.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner59.setAdapter(adapter59);
        spinner59.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner59.getSelectedItem();
                tampungangka59 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter60 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter60.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner60.setAdapter(adapter60);
        spinner60.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner60.getSelectedItem();
                tampungangka60 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter61 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter61.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner61.setAdapter(adapter61);
        spinner61.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner61.getSelectedItem();
                tampungangka61 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter62 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter62.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner62.setAdapter(adapter62);
        spinner62.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner62.getSelectedItem();
                tampungangka62 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter63 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter63.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner63.setAdapter(adapter63);
        spinner63.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner63.getSelectedItem();
                tampungangka63 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayAdapter<CharSequence> adapter64 = ArrayAdapter.createFromResource(this, R.array.kondisi, R.layout.spinneritem);
        adapter64.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner64.setAdapter(adapter64);
        spinner64.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tampung = (String) spinner64.getSelectedItem();
                tampungangka64 = getkondisi(tampung);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnproses.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                NamaPenyakit = "";
                String HasilPenyakit = "";
                String Gaada = "Pilih Kondisi Dahulu!";

                //  Nilai dari PAKAR / AHLINYA


                // Nilai Inputan dari PASIEN / USER
                double doubleGejala1 = tampungangka1;
                double doubleGejala2 = tampungangka2;
                double doubleGejala3 = tampungangka3;
                double doubleGejala4 = tampungangka4;
                double doubleGejala5 = tampungangka5;
                double doubleGejala6 = tampungangka6;
                double doubleGejala7 = tampungangka7;
                double doubleGejala8 = tampungangka8;
                double doubleGejala9 = tampungangka9;
                double doubleGejala10 = tampungangka10;
                double doubleGejala11 = tampungangka11;
                double doubleGejala12 = tampungangka12;
                double doubleGejala13 = tampungangka13;
                double doubleGejala14 = tampungangka14;
                double doubleGejala15 = tampungangka15;
                double doubleGejala16 = tampungangka16;
                double doubleGejala17 = tampungangka17;
                double doubleGejala18 = tampungangka18;
                double doubleGejala19 = tampungangka19;
                double doubleGejala20 = tampungangka20;
                double doubleGejala21 = tampungangka21;
                double doubleGejala22 = tampungangka22;
                double doubleGejala23 = tampungangka23;
                double doubleGejala24 = tampungangka24;
                double doubleGejala25 = tampungangka25;
                double doubleGejala26 = tampungangka26;
                double doubleGejala27 = tampungangka27;
                double doubleGejala28 = tampungangka28;
                double doubleGejala29 = tampungangka29;
                double doubleGejala30 = tampungangka30;
                double doubleGejala31 = tampungangka31;
                double doubleGejala32 = tampungangka32;
                double doubleGejala33 = tampungangka33;
                double doubleGejala34 = tampungangka34;
                double doubleGejala35 = tampungangka35;
                double doubleGejala36 = tampungangka36;
                double doubleGejala37 = tampungangka37;
                double doubleGejala38 = tampungangka38;
                double doubleGejala39 = tampungangka39;
                double doubleGejala40 = tampungangka40;
                double doubleGejala41 = tampungangka41;
                double doubleGejala42 = tampungangka42;
                double doubleGejala43 = tampungangka43;
                double doubleGejala44 = tampungangka44;
                double doubleGejala45 = tampungangka45;
                double doubleGejala46 = tampungangka46;
                double doubleGejala47 = tampungangka47;
                double doubleGejala48 = tampungangka48;
                double doubleGejala49 = tampungangka49;
                double doubleGejala50 = tampungangka50;
                double doubleGejala51 = tampungangka51;
                double doubleGejala52 = tampungangka52;
                double doubleGejala53 = tampungangka53;
                double doubleGejala54 = tampungangka54;
                double doubleGejala55 = tampungangka55;
                double doubleGejala56 = tampungangka56;
                double doubleGejala57 = tampungangka57;
                double doubleGejala58 = tampungangka58;
                double doubleGejala59 = tampungangka59;
                double doubleGejala60 = tampungangka60;
                double doubleGejala61 = tampungangka61;
                double doubleGejala62 = tampungangka62;
                double doubleGejala63 = tampungangka63;
                double doubleGejala64 = tampungangka64;

                // AND && dan OR ||
                if (switch6.isChecked() || switch3.isChecked() || switch64.isChecked() || switch4.isChecked() || switch5.isChecked() || switch7.isChecked() || switch8.isChecked()){

                    double nilaiGejala6 = 1;
                    double nilaiGejala3 = 1;
                    double nilaiGejala64 = 1;
                    double nilaiGejala4 = 0.4;
                    double nilaiGejala5 = 1;
                    double nilaiGejala7 = 1;
                    double nilaiGejala8 = 0.2;
                    double hasilHitunganGejala1 = nilaiGejala6 * doubleGejala6;
                    double hasilHitunganGejala2 = nilaiGejala3 * doubleGejala3;
                    double hasilHitunganGejala3 = nilaiGejala64 * doubleGejala64;
                    double hasilHitunganGejala4 = nilaiGejala4 * doubleGejala4;
                    double hasilHitunganGejala5 = nilaiGejala5 * doubleGejala5;
                    double hasilHitunganGejala6 = nilaiGejala7 * doubleGejala7;
                    double hasilHitunganGejala7 = nilaiGejala8 * doubleGejala8;

                    double Combine_CF1_CF2  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CFold_CF3  = Combine_CF1_CF2 + hasilHitunganGejala3 * (1 - Combine_CF1_CF2);
                    double Combine_CFold_CF4  = Combine_CFold_CF3 + hasilHitunganGejala4 * (1 - Combine_CFold_CF3);
                    double Combine_CFold_CF5  = Combine_CFold_CF4 + hasilHitunganGejala5 * (1 - Combine_CFold_CF4);
                    double Combine_CFold_CF6  = Combine_CFold_CF5 + hasilHitunganGejala6 * (1 - Combine_CFold_CF5);
                    double Combine_CFold_CF7  = Combine_CFold_CF6 + hasilHitunganGejala7 * (1 - Combine_CFold_CF6);


                    String hasilHitungGejalaPenyakitA = String.valueOf((Combine_CFold_CF7 * 100));

                    NamaPenyakit +="\n"+  namapenyakit2;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitA+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit2,hasilHitungGejalaPenyakitA);


                }

                if (switch9.isChecked() || switch7.isChecked() || switch5.isChecked() || switch10.isChecked() || switch2.isChecked() || switch3.isChecked() || switch6.isChecked() || switch11.isChecked() || switch12.isChecked() || switch13.isChecked()){

                    double nilaiGejala9 = 0.4;
                    double nilaiGejala7 = 1;
                    double nilaiGejala5 = 1;
                    double nilaiGejala10 = 1;
                    double nilaiGejala2 = 0.6;
                    double nilaiGejala3 = 0.6;
                    double nilaiGejala6 = 0.6;
                    double nilaiGejala11 = 0.8;
                    double nilaiGejala12 = 0.2;
                    double nilaiGejala13 = 1;
                    double hasilHitunganGejala1 = nilaiGejala9 * doubleGejala9;
                    double hasilHitunganGejala2 = nilaiGejala7 * doubleGejala7;
                    double hasilHitunganGejala3 = nilaiGejala5 * doubleGejala5;
                    double hasilHitunganGejala4 = nilaiGejala10 * doubleGejala10;
                    double hasilHitunganGejala5 = nilaiGejala2 * doubleGejala2;
                    double hasilHitunganGejala6 = nilaiGejala3 * doubleGejala3;
                    double hasilHitunganGejala7 = nilaiGejala6 * doubleGejala6;
                    double hasilHitunganGejala8 = nilaiGejala11 * doubleGejala11;
                    double hasilHitunganGejala9 = nilaiGejala12 * doubleGejala12;
                    double hasilHitunganGejala10 = nilaiGejala13 * doubleGejala13;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);
                    double Combine_CF6  = Combine_CF5 + hasilHitunganGejala7 * (1 - Combine_CF5);
                    double Combine_CF7  = Combine_CF6 + hasilHitunganGejala8 * (1 - Combine_CF6);
                    double Combine_CF8  = Combine_CF7 + hasilHitunganGejala9 * (1 - Combine_CF7);
                    double Combine_CF9  = Combine_CF8 + hasilHitunganGejala10 * (1 - Combine_CF8);

                    String hasilHitungGejalaPenyakitB = String.valueOf((Combine_CF9 * 100));

                    NamaPenyakit +="\n"+ namapenyakit3;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitB+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit3,hasilHitungGejalaPenyakitB);

                }

                if (switch1.isChecked() || switch2.isChecked() || switch3.isChecked() || switch14.isChecked() || switch6.isChecked() || switch4.isChecked() || switch16.isChecked()){

                    double nilaiGejala4 = 0.4;
                    double nilaiGejala14 = 0.6;
                    double nilaiGejala3 = 0.6;
                    double nilaiGejala1 = 1;
                    double nilaiGejala6 = 1;
                    double nilaiGejala2 = 1;
                    double nilaiGejala16 = 0.4;
                    double hasilHitunganGejala1 = nilaiGejala4 * doubleGejala4;
                    double hasilHitunganGejala2 = nilaiGejala14 * doubleGejala14;
                    double hasilHitunganGejala3 = nilaiGejala3 * doubleGejala3;
                    double hasilHitunganGejala4 = nilaiGejala1 * doubleGejala1;
                    double hasilHitunganGejala5 = nilaiGejala6 * doubleGejala6;
                    double hasilHitunganGejala6 = nilaiGejala2 * doubleGejala2;
                    double hasilHitunganGejala7 = nilaiGejala16 * doubleGejala16;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);
                    double Combine_CF6  = Combine_CF5 + hasilHitunganGejala7 * (1 - Combine_CF5);

                    String hasilHitungGejalaPenyakitC = String.valueOf((Combine_CF6 * 100));

                    NamaPenyakit +="\n"+ namapenyakit4;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitC+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit4,hasilHitungGejalaPenyakitC);

                }

                if (switch17.isChecked() || switch18.isChecked() || switch19.isChecked() || switch20.isChecked() || switch7.isChecked() || switch5.isChecked()){

                    double nilaiGejala17 = 1;
                    double nilaiGejala18 = 1;
                    double nilaiGejala19 = 1;
                    double nilaiGejala20 = 0.6;
                    double nilaiGejala7 = 0.6;
                    double nilaiGejala5 = 1;

                    double hasilHitunganGejala1 = nilaiGejala17 * doubleGejala17;
                    double hasilHitunganGejala2 = nilaiGejala18 * doubleGejala18;
                    double hasilHitunganGejala3 = nilaiGejala19 * doubleGejala19;
                    double hasilHitunganGejala4 = nilaiGejala20 * doubleGejala20;
                    double hasilHitunganGejala5 = nilaiGejala7 * doubleGejala7;
                    double hasilHitunganGejala6 = nilaiGejala5 * doubleGejala5;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);

                    String hasilHitungGejalaPenyakitD = String.valueOf((Combine_CF5 * 100));

                    NamaPenyakit +="\n"+ namapenyakit5;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitD+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit5,hasilHitungGejalaPenyakitD);

                }

                if (switch20.isChecked() || switch21.isChecked() || switch14.isChecked() || switch15.isChecked() || switch22.isChecked() || switch4.isChecked()){

                    double nilaiGejala20 = 0.4;
                    double nilaiGejala21 = 0.2;
                    double nilaiGejala14 = 0.4;
                    double nilaiGejala15 = 1;
                    double nilaiGejala22 = 1;
                    double nilaiGejala4 = 1;

                    double hasilHitunganGejala1 = nilaiGejala20 * doubleGejala20;
                    double hasilHitunganGejala2 = nilaiGejala21 * doubleGejala21;
                    double hasilHitunganGejala3 = nilaiGejala14 * doubleGejala14;
                    double hasilHitunganGejala4 = nilaiGejala15 * doubleGejala15;
                    double hasilHitunganGejala5 = nilaiGejala22 * doubleGejala22;
                    double hasilHitunganGejala6 = nilaiGejala4 * doubleGejala4;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);

                    String hasilHitungGejalaPenyakitE = String.valueOf((Combine_CF5 * 100));

                    NamaPenyakit +="\n"+ namapenyakit6;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitE+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit6,hasilHitungGejalaPenyakitE);

                }

                if (switch23.isChecked() || switch24.isChecked() || switch25.isChecked() || switch26.isChecked() || switch27.isChecked()){

                    double nilaiGejala23 = 1;
                    double nilaiGejala24 = 1;
                    double nilaiGejala25 = 1;
                    double nilaiGejala26 = 0.6;
                    double nilaiGejala27 = 0.6;

                    double hasilHitunganGejala1 = nilaiGejala23 * doubleGejala23;
                    double hasilHitunganGejala2 = nilaiGejala24 * doubleGejala24;
                    double hasilHitunganGejala3 = nilaiGejala25 * doubleGejala25;
                    double hasilHitunganGejala4 = nilaiGejala26 * doubleGejala26;
                    double hasilHitunganGejala5 = nilaiGejala27 * doubleGejala27;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);

                    String hasilHitungGejalaPenyakitF = String.valueOf((Combine_CF4 * 100));

                    NamaPenyakit +="\n"+ namapenyakit7;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitF+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit7,hasilHitungGejalaPenyakitF);

                }

                if (switch28.isChecked() || switch3.isChecked() || switch29.isChecked() || switch21.isChecked() || switch30.isChecked() || switch31.isChecked()){

                    double nilaiGejala28 = 0.6;
                    double nilaiGejala3 = 0.8;
                    double nilaiGejala29 = 0.6;
                    double nilaiGejala21 = 0.4;
                    double nilaiGejala30 = 0.4;
                    double nilaiGejala31 = 0.8;

                    double hasilHitunganGejala1 = nilaiGejala28 * doubleGejala28;
                    double hasilHitunganGejala2 = nilaiGejala3 * doubleGejala3;
                    double hasilHitunganGejala3 = nilaiGejala29 * doubleGejala29;
                    double hasilHitunganGejala4 = nilaiGejala21 * doubleGejala21;
                    double hasilHitunganGejala5 = nilaiGejala30 * doubleGejala30;
                    double hasilHitunganGejala6 = nilaiGejala31 * doubleGejala31;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);

                    String hasilHitungGejalaPenyakitG = String.valueOf((Combine_CF5 * 100));

                    NamaPenyakit +="\n"+ namapenyakit8;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitG+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit8,hasilHitungGejalaPenyakitG);

                }

                if (switch5.isChecked() || switch7.isChecked() || switch32.isChecked() || switch3.isChecked() || switch33.isChecked() || switch4.isChecked() || switch34.isChecked() || switch35.isChecked()){

                    double nilaiGejala5 = 0.6;
                    double nilaiGejala7 = 0.8;
                    double nilaiGejala32 = 0.8;
                    double nilaiGejala3 = 0.6;
                    double nilaiGejala33 = 0.6;
                    double nilaiGejala4 = 1;
                    double nilaiGejala34 = 1;
                    double nilaiGejala35 = 1;

                    double hasilHitunganGejala1 = nilaiGejala5 * doubleGejala5;
                    double hasilHitunganGejala2 = nilaiGejala7 * doubleGejala7;
                    double hasilHitunganGejala3 = nilaiGejala32 * doubleGejala32;
                    double hasilHitunganGejala4 = nilaiGejala3 * doubleGejala3;
                    double hasilHitunganGejala5 = nilaiGejala33 * doubleGejala33;
                    double hasilHitunganGejala6 = nilaiGejala4 * doubleGejala4;
                    double hasilHitunganGejala7 = nilaiGejala34 * doubleGejala34;
                    double hasilHitunganGejala8 = nilaiGejala35 * doubleGejala35;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);
                    double Combine_CF6  = Combine_CF5 + hasilHitunganGejala7 * (1 - Combine_CF5);
                    double Combine_CF7  = Combine_CF6 + hasilHitunganGejala8 * (1 - Combine_CF6);

                    String hasilHitungGejalaPenyakitH = String.valueOf((Combine_CF7 * 100));

                    NamaPenyakit +="\n"+ namapenyakit9;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitH+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit9,hasilHitungGejalaPenyakitH);

                }

                if (switch36.isChecked() || switch5.isChecked() || switch29.isChecked() || switch37.isChecked() || switch3.isChecked() || switch4.isChecked() || switch7.isChecked() || switch38.isChecked()){

                    double nilaiGejala36 = 0.6;
                    double nilaiGejala5 = 0.8;
                    double nilaiGejala29 = 0.6;
                    double nilaiGejala37 = 0.6;
                    double nilaiGejala3 = 0.8;
                    double nilaiGejala4 = 0.6;
                    double nilaiGejala7 = 0.6;
                    double nilaiGejala38 = 0.4;

                    double hasilHitunganGejala1 = nilaiGejala36 * doubleGejala36;
                    double hasilHitunganGejala2 = nilaiGejala5 * doubleGejala5;
                    double hasilHitunganGejala3 = nilaiGejala29 * doubleGejala29;
                    double hasilHitunganGejala4 = nilaiGejala37 * doubleGejala37;
                    double hasilHitunganGejala5 = nilaiGejala3 * doubleGejala3;
                    double hasilHitunganGejala6 = nilaiGejala4 * doubleGejala4;
                    double hasilHitunganGejala7 = nilaiGejala7 * doubleGejala7;
                    double hasilHitunganGejala8 = nilaiGejala38 * doubleGejala38;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);
                    double Combine_CF6  = Combine_CF5 + hasilHitunganGejala7 * (1 - Combine_CF5);
                    double Combine_CF7  = Combine_CF6 + hasilHitunganGejala8 * (1 - Combine_CF6);

                    String hasilHitungGejalaPenyakitI = String.valueOf((Combine_CF7 * 100));

                    NamaPenyakit +="\n"+ namapenyakit10;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitI+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit10,hasilHitungGejalaPenyakitI);

                }

                if (switch6.isChecked() || switch28.isChecked() || switch39.isChecked()){

                    double nilaiGejala6 = 1;
                    double nilaiGejala28 = 0.8;
                    double nilaiGejala39 = 0.8;

                    double hasilHitunganGejala1 = nilaiGejala6 * doubleGejala6;
                    double hasilHitunganGejala2 = nilaiGejala28 * doubleGejala28;
                    double hasilHitunganGejala3 = nilaiGejala39 * doubleGejala39;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);

                    String hasilHitungGejalaPenyakitJ = String.valueOf((Combine_CF2 * 100));

                    NamaPenyakit +="\n"+ namapenyakit11;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitJ+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit11,hasilHitungGejalaPenyakitJ);

                }

                if (switch15.isChecked() || switch40.isChecked() || switch41.isChecked() || switch42.isChecked() || switch43.isChecked() || switch3.isChecked() || switch6.isChecked() || switch2.isChecked() || switch14.isChecked() || switch5.isChecked()){

                    double nilaiGejala2 = 0.6;
                    double nilaiGejala15 = 1;
                    double nilaiGejala40 = 1;
                    double nilaiGejala41 = 1;
                    double nilaiGejala42 = 1;
                    double nilaiGejala3 = 0.6;
                    double nilaiGejala6 = 0.6;
                    double nilaiGejala43 = 0.8;
                    double nilaiGejala14 = 0.6;
                    double nilaiGejala5 = 0.6;

                    double hasilHitunganGejala1 = nilaiGejala2 * doubleGejala2;
                    double hasilHitunganGejala2 = nilaiGejala15 * doubleGejala15;
                    double hasilHitunganGejala3 = nilaiGejala40 * doubleGejala40;
                    double hasilHitunganGejala4 = nilaiGejala41 * doubleGejala41;
                    double hasilHitunganGejala5 = nilaiGejala42 * doubleGejala42;
                    double hasilHitunganGejala6 = nilaiGejala3 * doubleGejala3;
                    double hasilHitunganGejala7 = nilaiGejala6 * doubleGejala6;
                    double hasilHitunganGejala8 = nilaiGejala43 * doubleGejala43;
                    double hasilHitunganGejala9 = nilaiGejala14 * doubleGejala14;
                    double hasilHitunganGejala10 = nilaiGejala5 * doubleGejala5;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);
                    double Combine_CF6  = Combine_CF5 + hasilHitunganGejala7 * (1 - Combine_CF5);
                    double Combine_CF7  = Combine_CF6 + hasilHitunganGejala8 * (1 - Combine_CF6);
                    double Combine_CF8  = Combine_CF7 + hasilHitunganGejala9 * (1 - Combine_CF7);
                    double Combine_CF9  = Combine_CF8 + hasilHitunganGejala10 * (1 - Combine_CF8);

                    String hasilHitungGejalaPenyakitK = String.valueOf((Combine_CF9 * 100));

                    NamaPenyakit +="\n"+ namapenyakit12;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitK+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit12,hasilHitungGejalaPenyakitK);

                }

                if (switch6.isChecked() || switch44.isChecked() || switch45.isChecked() || switch46.isChecked()){

                    double nilaiGejala6 = 0.8;
                    double nilaiGejala44 = 0.2;
                    double nilaiGejala45 = 0.2;
                    double nilaiGejala46 = 0.8;

                    double hasilHitunganGejala1 = nilaiGejala6 * doubleGejala6;
                    double hasilHitunganGejala2 = nilaiGejala44 * doubleGejala44;
                    double hasilHitunganGejala3 = nilaiGejala45 * doubleGejala45;
                    double hasilHitunganGejala4 = nilaiGejala46 * doubleGejala46;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);

                    String hasilHitungGejalaPenyakitL = String.valueOf((Combine_CF3 * 100));

                    NamaPenyakit +="\n"+ namapenyakit13;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitL+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit13,hasilHitungGejalaPenyakitL);

                }

                if (switch47.isChecked() || switch48.isChecked() || switch2.isChecked() || switch49.isChecked() || switch27.isChecked()){

                    double nilaiGejala2 = 1;
                    double nilaiGejala47 = 1;
                    double nilaiGejala48 = 1;
                    double nilaiGejala49 = 0.6;
                    double nilaiGejala27 = 0.6;

                    double hasilHitunganGejala1 = nilaiGejala2 * doubleGejala2;
                    double hasilHitunganGejala2 = nilaiGejala47 * doubleGejala47;
                    double hasilHitunganGejala3 = nilaiGejala48 * doubleGejala48;
                    double hasilHitunganGejala4 = nilaiGejala49 * doubleGejala49;
                    double hasilHitunganGejala5 = nilaiGejala27 * doubleGejala27;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);

                    String hasilHitungGejalaPenyakitM = String.valueOf((Combine_CF4 * 100));

                    NamaPenyakit +="\n"+ namapenyakit14;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitM+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit14,hasilHitungGejalaPenyakitM);

                }

                if (switch50.isChecked() || switch51.isChecked() || switch52.isChecked() || switch53.isChecked() || switch54.isChecked()){

                    double nilaiGejala50 = 0.6;
                    double nilaiGejala51 = 0.6;
                    double nilaiGejala52 = 0.6;
                    double nilaiGejala53 = 1;
                    double nilaiGejala54 = 1;

                    double hasilHitunganGejala1 = nilaiGejala50 * doubleGejala50;
                    double hasilHitunganGejala2 = nilaiGejala51 * doubleGejala51;
                    double hasilHitunganGejala3 = nilaiGejala52 * doubleGejala52;
                    double hasilHitunganGejala4 = nilaiGejala53 * doubleGejala53;
                    double hasilHitunganGejala5 = nilaiGejala54 * doubleGejala54;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);

                    String hasilHitungGejalaPenyakitN = String.valueOf((Combine_CF4 * 100));

                    NamaPenyakit +="\n"+ namapenyakit15;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitN+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit15,hasilHitungGejalaPenyakitN);

                }

                if (switch55.isChecked() || switch56.isChecked() || switch57.isChecked() || switch58.isChecked() || switch59.isChecked() || switch60.isChecked()){

                    double nilaiGejala55 = 1;
                    double nilaiGejala56 = 1;
                    double nilaiGejala57 = 0.6;
                    double nilaiGejala58 = 0.6;
                    double nilaiGejala59 = 1;
                    double nilaiGejala60 = 0.8;

                    double hasilHitunganGejala1 = nilaiGejala55 * doubleGejala55;
                    double hasilHitunganGejala2 = nilaiGejala56 * doubleGejala56;
                    double hasilHitunganGejala3 = nilaiGejala57 * doubleGejala57;
                    double hasilHitunganGejala4 = nilaiGejala58 * doubleGejala58;
                    double hasilHitunganGejala5 = nilaiGejala59 * doubleGejala59;
                    double hasilHitunganGejala6 = nilaiGejala60 * doubleGejala60;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);
                    double Combine_CF4  = Combine_CF3 + hasilHitunganGejala5 * (1 - Combine_CF3);
                    double Combine_CF5  = Combine_CF4 + hasilHitunganGejala6 * (1 - Combine_CF4);

                    String hasilHitungGejalaPenyakitO = String.valueOf((Combine_CF5 * 100));

                    NamaPenyakit +="\n"+ namapenyakit16;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitO+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit16,hasilHitungGejalaPenyakitO);

                }

                if (switch61.isChecked() || switch62.isChecked() || switch63.isChecked() || switch55.isChecked()){

                    double nilaiGejala61 = 1;
                    double nilaiGejala62 = 1;
                    double nilaiGejala63 = 1;
                    double nilaiGejala55 = 1;

                    double hasilHitunganGejala1 = nilaiGejala61 * doubleGejala61;
                    double hasilHitunganGejala2 = nilaiGejala62 * doubleGejala62;
                    double hasilHitunganGejala3 = nilaiGejala63 * doubleGejala63;
                    double hasilHitunganGejala4 = nilaiGejala55 * doubleGejala55;

                    double Combine_CF1  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CF2  = Combine_CF1 + hasilHitunganGejala3 * (1 - Combine_CF1);
                    double Combine_CF3  = Combine_CF2 + hasilHitunganGejala4 * (1 - Combine_CF2);

                    String hasilHitungGejalaPenyakitP = String.valueOf((Combine_CF3 * 100));

                    NamaPenyakit +="\n"+ namapenyakit17;
                    HasilPenyakit +="\n"+hasilHitungGejalaPenyakitP+" %";
                    SharedPreferences.Editor editorr = sharedPref.edit();
                    editorr.putString(KEY_HASIL,HasilPenyakit);
                    editorr.apply();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();
                    addResult(namapenyakit17,hasilHitungGejalaPenyakitP);

                }

                if (!switch1.isChecked() && !switch2.isChecked() && !switch3.isChecked() && !switch4.isChecked() && !switch5.isChecked() && !switch6.isChecked() && !switch7.isChecked() && !switch8.isChecked() && !switch9.isChecked() && !switch10.isChecked() &&
                !switch11.isChecked() && !switch12.isChecked() && !switch13.isChecked() && !switch14.isChecked() && !switch15.isChecked() && !switch16.isChecked() && !switch17.isChecked() && !switch18.isChecked() && !switch19.isChecked() && !switch20.isChecked() &&
                !switch21.isChecked() && !switch22.isChecked() && !switch23.isChecked() && !switch24.isChecked() && !switch25.isChecked() && !switch26.isChecked() && !switch27.isChecked() && !switch28.isChecked() && !switch29.isChecked() && !switch30.isChecked() &&
                !switch31.isChecked() && !switch32.isChecked() && !switch33.isChecked() && !switch34.isChecked() && !switch35.isChecked() && !switch36.isChecked() && !switch37.isChecked() && !switch38.isChecked() && !switch39.isChecked() && !switch40.isChecked() &&
                !switch41.isChecked() && !switch42.isChecked() && !switch43.isChecked() && !switch44.isChecked() && !switch45.isChecked() && !switch46.isChecked() && !switch47.isChecked() && !switch48.isChecked() && !switch49.isChecked() && !switch50.isChecked() &&
                !switch51.isChecked() && !switch52.isChecked() && !switch53.isChecked() && !switch54.isChecked() && !switch55.isChecked() && !switch56.isChecked() && !switch57.isChecked() && !switch58.isChecked() && !switch59.isChecked() && !switch60.isChecked() &&
                !switch61.isChecked() && !switch62.isChecked() && !switch63.isChecked())
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,Gaada);
                    editor.apply();
                }



                Intent intent = new Intent(DeteksiCoba.this, ShowDeteksi.class);
                startActivity(intent);


            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeteksiCoba.this, Dashboard.class);
                startActivity(intent);
            }
        });

        btnbantu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeteksiCoba.this, Tutorial.class);
                startActivity(intent);
            }
        });
    }
    private void addResult(String nama, String hasil){
        Call<GetResult> postResult = apiInterface.tambahResult(nama,hasil);
        postResult.enqueue(new Callback<GetResult>() {
            @Override
            public void onResponse(Call<GetResult> call, Response<GetResult> response) {
                //Log.d("response", " "+response.body().getStatus());
                if(response.isSuccessful()) {
                    StyleableToast.makeText(DeteksiCoba.this, "Berhasil disimpan", R.style.ToastSucces).show();
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(DeteksiCoba.this, "Gagal disimpan", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<GetResult> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(DeteksiCoba.this, "Gagal", R.style.ToastWrong).show();
            }
        });
    }
    public double getkondisi(String pilihan) {
        if (pilihan.equalsIgnoreCase("Pasti ya")) {
            tampungangka = 1;
        } else if (pilihan.equalsIgnoreCase("Hampir pasti ya")) {
            tampungangka = 0.9;
        } else if (pilihan.equalsIgnoreCase("Kemungkinan besar ya")) {
            tampungangka = 0.8;
        } else if (pilihan.equalsIgnoreCase("Mungkin ya")) {
            tampungangka = 0.7;
        } else if (pilihan.equalsIgnoreCase("Tidak tahu")) {
            tampungangka = 0.6;
        } else if (pilihan.equalsIgnoreCase("Mungkin tidak")) {
            tampungangka = 0.5;
        } else if (pilihan.equalsIgnoreCase("Kemungkinan besar tidak")) {
            tampungangka = 0.4;
        } else if (pilihan.equalsIgnoreCase("Hampir pasti tidak")) {
            tampungangka = 0.3;
        } else if (pilihan.equalsIgnoreCase("Pasti tidak")) {
            tampungangka = 0.2;
        }
        return tampungangka;
        }

    public void tampilpenyakit() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetPenyakit> call = mApiInterface.getPenyakit();
        call.enqueue(new Callback<GetPenyakit>() {
            @Override
            public void onResponse(Call<GetPenyakit> call, Response<GetPenyakit>
                    response) {
                List<Penyakit> penyakitList = response.body().getListDataPenyakit();
                for (int i =0;i< penyakitList.size();i++){
                    if(penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("1")){
                        namapenyakit1 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("2")) {
                        namapenyakit2 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("3")) {
                        namapenyakit3 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("4")) {
                        namapenyakit4 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("5")) {
                        namapenyakit5 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("6")) {
                        namapenyakit6 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("7")) {
                        namapenyakit7 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("8")) {
                        namapenyakit8 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("9")) {
                        namapenyakit9 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("10")) {
                        namapenyakit10 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("11")) {
                        namapenyakit11 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("12")) {
                        namapenyakit12 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("13")) {
                        namapenyakit13 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("14")) {
                        namapenyakit14 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("15")) {
                        namapenyakit15 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("16")) {
                        namapenyakit16 = penyakitList.get(i).getNama_penyakit();
                    } else if (penyakitList.get(i).getKode_penyakit().equalsIgnoreCase("17")) {
                        namapenyakit17 = penyakitList.get(i).getNama_penyakit();
                    }
                }

            }

            @Override
            public void onFailure(Call<GetPenyakit> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                StyleableToast.makeText(DeteksiCoba.this, "Gagal memuat penyakit", R.style.ToastWrong).show();
            }
        });
    }

    public void tampilgejala() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetGejala> call = mApiInterface.getGejala();
        call.enqueue(new Callback<GetGejala>() {
            @Override
            public void onResponse(Call<GetGejala> call, Response<GetGejala>
                    response) {
                List<Gejala> gejalaList = response.body().getListDataGejala();
                for (int i =0;i< gejalaList.size();i++){
                    if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("1")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch1.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("2")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch2.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("4")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch3.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("5")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch4.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("6")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch5.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("7")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch6.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("8")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch7.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("9")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch8.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("10")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch9.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("11")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch10.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("12")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch11.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("13")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch12.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("14")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch13.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("15")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch14.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("16")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch15.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("17")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch16.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("18")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch17.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("19")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch18.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("20")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch19.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("21")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch20.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("22")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch21.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("23")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch22.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("24")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch23.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("25")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch24.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("26")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch25.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("27")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch26.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("28")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch27.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("29")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch28.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("30")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch29.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("31")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch30.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("32")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch31.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("33")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch32.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("34")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch33.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("35")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch34.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("36")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch35.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("37")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch36.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("38")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch37.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("39")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch38.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("40")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch39.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("41")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch40.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("42")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch41.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("43")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch42.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("44")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch43.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("45")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch44.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("46")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch45.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("47")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch46.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("48")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch47.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("49")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch48.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("50")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch49.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("51")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch50.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("52")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch51.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("53")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch52.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("54")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch53.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("55")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch54.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("56")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch55.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("57")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch56.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("58")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch57.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("59")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch58.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("60")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch59.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("61")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch60.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("62")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch61.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("63")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch62.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("64")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch63.setText(gejala1);
                    } else if(gejalaList.get(i).getKodeGejala().equalsIgnoreCase("65")){
                        gejala1 = gejalaList.get(i).getNamaGejala();
                        switch64.setText(gejala1);
                    }
                }

            }

            @Override
            public void onFailure(Call<GetGejala> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                StyleableToast.makeText(DeteksiCoba.this, "Gagal memuat penyakit", R.style.ToastWrong).show();
            }
        });
    }
}


