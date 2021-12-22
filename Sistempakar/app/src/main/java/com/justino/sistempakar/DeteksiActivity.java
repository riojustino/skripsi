package com.justino.sistempakar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Switch;

import com.justino.sistempakar.R;

import java.util.Arrays;
import java.util.List;

public class DeteksiActivity extends AppCompatActivity {

    Button btnproses,btnback;
    Switch sw1, sw2, sw3, sw4, sw5, sw6;
    AutoCompleteTextView txtnilai1, txtnilai2, txtnilai3, txtnilai4, txtnilai5, txtnilai6;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_PENYAKIT = "namapenyakit";

    String[] nilaiKeyakinanGejala1 = {"", "0", "0.2", "0.4", "0.6", "0.8", "1"};
    String[] nilaiKeyakinanGejala2 = {"", "0", "0.2", "0.4", "0.6", "0.8", "1"};
    String[] nilaiKeyakinanGejala3 = {"", "0", "0.2", "0.4", "0.6", "0.8", "1"};
    String[] nilaiKeyakinanGejala4 = {"", "0", "0.2", "0.4", "0.6", "0.8", "1"};
    String[] nilaiKeyakinanGejala5 = {"", "0", "0.2", "0.4", "0.6", "0.8", "1"};
    String[] nilaiKeyakinanGejala6 = {"", "0", "0.2", "0.4", "0.6", "0.8", "1"};

//    List<String> listgejala1 = Arrays.asList(nilaiKeyakinanGejala1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deteksi);

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        btnproses   = (Button)findViewById(R.id.button);
        btnback   = (Button)findViewById(R.id.button2);

        sw1  = (Switch) findViewById(R.id.switch1);
        sw2  = (Switch) findViewById(R.id.switch2);
        sw3  = (Switch) findViewById(R.id.switch3);
        sw4  = (Switch) findViewById(R.id.switch4);
        sw5  = (Switch) findViewById(R.id.switch5);
        sw6  = (Switch) findViewById(R.id.switch6);

        txtnilai1 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        txtnilai2 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView2);
        txtnilai3 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView3);
        txtnilai4 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView4);
        txtnilai5 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView5);
        txtnilai6 = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView6);

        final ArrayAdapter<String> adapterGejala1 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, nilaiKeyakinanGejala1);
        txtnilai1.setThreshold(1);
        txtnilai1.setAdapter(adapterGejala1);

        final ArrayAdapter<String> adapterGejala2 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, nilaiKeyakinanGejala2);
        txtnilai2.setThreshold(1);
        txtnilai2.setAdapter(adapterGejala2);

        final ArrayAdapter<String> adapterGejala3 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, nilaiKeyakinanGejala3);
        txtnilai3.setThreshold(1);
        txtnilai3.setAdapter(adapterGejala3);

        final ArrayAdapter<String> adapterGejala4 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, nilaiKeyakinanGejala4);
        txtnilai4.setThreshold(1);
        txtnilai4.setAdapter(adapterGejala4);

        final ArrayAdapter<String> adapterGejala5 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, nilaiKeyakinanGejala5);
        txtnilai5.setThreshold(1);
        txtnilai5.setAdapter(adapterGejala5);

        final ArrayAdapter<String> adapterGejala6 = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item, nilaiKeyakinanGejala6);
        txtnilai6.setThreshold(1);
        txtnilai6.setAdapter(adapterGejala6);

        txtnilai1.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   new AlertDialog.Builder(DeteksiActivity.this).setTitle("Pilihlah Kemungkinan Gejala 1").
                                                           setAdapter(adapterGejala1, new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog, int which) {
                                                                   txtnilai1.setText(nilaiKeyakinanGejala1[which].toString());
                                                                   dialog.dismiss();
                                                               }
                                                           }).create().show();
                                               }
                                           }
        );

        txtnilai2.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   new AlertDialog.Builder(DeteksiActivity.this).setTitle("Pilihlah Kemungkinan Gejala 2").
                                                           setAdapter(adapterGejala2, new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog, int which) {
                                                                   txtnilai2.setText(nilaiKeyakinanGejala2[which].toString());
                                                                   dialog.dismiss();
                                                               }
                                                           }).create().show();
                                               }
                                           }
        );

        txtnilai3.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   new AlertDialog.Builder(DeteksiActivity.this).setTitle("Pilihlah Kemungkinan Gejala 3").
                                                           setAdapter(adapterGejala3, new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog, int which) {
                                                                   txtnilai3.setText(nilaiKeyakinanGejala3[which].toString());
                                                                   dialog.dismiss();
                                                               }
                                                           }).create().show();
                                               }
                                           }
        );

        txtnilai4.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   new AlertDialog.Builder(DeteksiActivity.this).setTitle("Pilihlah Kemungkinan Gejala 4").
                                                           setAdapter(adapterGejala4, new DialogInterface.OnClickListener() {
                                                               @Override
                                                               public void onClick(DialogInterface dialog, int which) {
                                                                   txtnilai4.setText(nilaiKeyakinanGejala4[which].toString());
                                                                   dialog.dismiss();
                                                               }
                                                           }).create().show();
                                               }
                                           }
        );

        txtnilai5.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             new AlertDialog.Builder(DeteksiActivity.this).setTitle("Pilihlah Kemungkinan Gejala 5").
                                                     setAdapter(adapterGejala5, new DialogInterface.OnClickListener() {
                                                         @Override
                                                         public void onClick(DialogInterface dialog, int which) {
                                                             txtnilai5.setText(nilaiKeyakinanGejala5[which].toString());
                                                             dialog.dismiss();
                                                         }
                                                     }).create().show();
                                         }
                                     }
        );

        txtnilai6.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             new AlertDialog.Builder(DeteksiActivity.this).setTitle("Pilihlah Kemungkinan Gejala 6").
                                                     setAdapter(adapterGejala6, new DialogInterface.OnClickListener() {
                                                         @Override
                                                         public void onClick(DialogInterface dialog, int which) {
                                                             txtnilai6.setText(nilaiKeyakinanGejala6[which].toString());
                                                             dialog.dismiss();
                                                         }
                                                     }).create().show();
                                         }
                                     }
        );

        btnproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String NamaPenyakit = "Anda Menderita Penyakit :";
                String Gaada = "Gaada Isinya Pak";

                // AND && dan OR ||
                if (sw1.isChecked() && sw2.isChecked() && sw3.isChecked() && sw4.isChecked()){

                    //  Nilai dari PAKAR / AHLINYA
                    double nilaiGejala1 = 0.2;
                    double nilaiGejala2 = 0.4;
                    double nilaiGejala3 = 0.6;
                    double nilaiGejala4 = 0.4;

                    // Nilai Inputan dari PASIEN / USER
                    double doubleGejala1 = Double.parseDouble(txtnilai1.getText().toString());
                    double doubleGejala2 = Double.parseDouble(txtnilai2.getText().toString());
                    double doubleGejala3 = Double.parseDouble(txtnilai3.getText().toString());
                    double doubleGejala4 = Double.parseDouble(txtnilai4.getText().toString());

                    double hasilHitunganGejala1 = nilaiGejala1 * doubleGejala1;
                    double hasilHitunganGejala2 = nilaiGejala2 * doubleGejala2;
                    double hasilHitunganGejala3 = nilaiGejala3 * doubleGejala3;
                    double hasilHitunganGejala4 = nilaiGejala4 * doubleGejala4;

                    double Combine_CF1_CF2  = hasilHitunganGejala1 + hasilHitunganGejala2 * (1 - hasilHitunganGejala1);
                    double Combine_CFold_CF3  = Combine_CF1_CF2 + hasilHitunganGejala3 * (1 - Combine_CF1_CF2);
                    double Combine_CFold_CF4  = Combine_CFold_CF3 + hasilHitunganGejala4 * (1 - Combine_CFold_CF3);

                    String hasilHitungGejalaPenyakitA = String.valueOf((Combine_CFold_CF4 * 100));

                    NamaPenyakit += "\nBruCellosis"+"\n"+hasilHitungGejalaPenyakitA+" %";
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();


                }

                if (sw4.isChecked() && sw5.isChecked() && sw6.isChecked()){

                    //  Nilai dari PAKAR / AHLINYA
                    double nilaiGejala4 = 0.4;
                    double nilaiGejala5 = 0.2;
                    double nilaiGejala6 = 0.8;

                    // Nilai Inputan dari PASIEN / USER
                    double doubleGejala4 = Double.parseDouble(txtnilai4.getText().toString());
                    double doubleGejala5 = Double.parseDouble(txtnilai5.getText().toString());
                    double doubleGejala6 = Double.parseDouble(txtnilai6.getText().toString());

                    double hasilHitunganGejala4 = nilaiGejala4 * doubleGejala4;
                    double hasilHitunganGejala5 = nilaiGejala5 * doubleGejala5;
                    double hasilHitunganGejala6 = nilaiGejala6 * doubleGejala6;

                    double Combine_CF1_CF2_1  = hasilHitunganGejala4 + hasilHitunganGejala5 * (1 - hasilHitunganGejala4);
                    double Combine_CFold_CF3  = Combine_CF1_CF2_1 + hasilHitunganGejala6 * (1 - Combine_CF1_CF2_1);


                    String hasilHitungGejalaPenyakitB = String.valueOf((Combine_CFold_CF3 * 100));

                    NamaPenyakit += "\nInFection Bovine"+"\n"+hasilHitungGejalaPenyakitB+" %";
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();

                }

                if (sw1.isChecked() && sw3.isChecked()){

                    //  Nilai dari PAKAR / AHLINYA
                    double nilaiGejala1 = 0.2;
                    double nilaiGejala3 = 0.6;

                    // Nilai Inputan dari PASIEN / USER
                    double doubleGejala1 = Double.parseDouble(txtnilai1.getText().toString());
                    double doubleGejala3 = Double.parseDouble(txtnilai3.getText().toString());

                    double hasilHitunganGejala1 = nilaiGejala1 * doubleGejala1;
                    double hasilHitunganGejala3 = nilaiGejala3 * doubleGejala3;

                    double Combine_CF1_CF2_2  = hasilHitunganGejala1 + hasilHitunganGejala3 * (1 - hasilHitunganGejala1);


                    String hasilHitungGejalaPenyakitC = String.valueOf((Combine_CF1_CF2_2 * 100));

                    NamaPenyakit += "\nInfluenza"+"\n"+hasilHitungGejalaPenyakitC+" %";
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,NamaPenyakit);
                    editor.apply();

                }

                if (!sw1.isChecked() && !sw2.isChecked() && !sw3.isChecked() && !sw4.isChecked() && !sw5.isChecked() && !sw6.isChecked())
                {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_PENYAKIT,Gaada);
                    editor.apply();
                }


                Intent intent = new Intent(DeteksiActivity.this, ShowDeteksi.class);
                startActivity(intent);


            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeteksiActivity.this, Dashboard.class);
                startActivity(intent);
            }
        });


    }
}