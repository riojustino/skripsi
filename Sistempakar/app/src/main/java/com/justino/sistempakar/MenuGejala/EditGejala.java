package com.justino.sistempakar.MenuGejala;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.justino.sistempakar.Config;
import com.justino.sistempakar.R;
import com.justino.sistempakar.gejala.PostGejala;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import io.github.muddz.styleabletoast.StyleableToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditGejala extends AppCompatActivity {
    EditText namaGejala;
    Button btnEditGejala, backeditgejala, btnDeleteGejala;
    String ID;
    ApiInterface mApiInterface;

    private final int ALERT_DIALOG_CLOSE = Config.ALERT_DIALOG_CLOSE;
    private final int ALERT_DIALOG_DELETE = Config.ALERT_DIALOG_DELETE;
    private static final String UPDATE_FLAG = Config.UPDATE_FLAG;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_gejala);

        //Inisialisasi komponen form
        namaGejala = (EditText) findViewById(R.id.namaGejala);
        btnEditGejala = (Button) findViewById(R.id.btnEditGejala);
        btnDeleteGejala = (Button) findViewById(R.id.btnDeleteGejala);
        backeditgejala = (Button) findViewById(R.id.backeditgejala);
        //Inisialisasi intent ke komponen form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("kode_gejala");
        namaGejala.setText(mIntent.getStringExtra("nama_gejala"));
        // Definisi API
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);

        backeditgejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditGejala.this, TampilGejala.class);
                startActivity(intent);
            }
        });
        // Fungsi Tombol Update
        btnEditGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namaGejala.getText().toString().isEmpty()){
                    StyleableToast.makeText(EditGejala.this, "Masukkan Nama Gejala Dahulu", R.style.ToastWrong).show();
                }else {
                            updategejala();
                        }
                    }

        });

        btnDeleteGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    deletegejala();
            }

        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }


    private void updategejala() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<PostGejala> postGejalaCall = mApiInterface.updategejala(ID, namaGejala.getText().toString());
        postGejalaCall.enqueue(new Callback<PostGejala>() {
            @Override
            public void onResponse(Call<PostGejala> call, Response<PostGejala> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(EditGejala.this, "Sukses Update", R.style.ToastSucces).show();
                    Intent intent = new Intent(EditGejala.this, TampilGejala.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(EditGejala.this, "Gagal Update", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<PostGejala> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(EditGejala.this, "Error", R.style.ToastWrong).show();
            }
        });
    }


    private void deletegejala() {
        showAlertDialog(ALERT_DIALOG_DELETE);
    }

    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan gejala?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus gejala ini?";
            dialogTitle = "Hapus gejala";
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (isDialogClose) {
                            finish();
                        } else {
                            // Kode Hapus
                            if (ID.trim().isEmpty()==false){
                                Call<PostGejala> postGejalaCall = mApiInterface.deletegejala(ID, namaGejala.getText().toString());
                                postGejalaCall.enqueue(new Callback<PostGejala>() {
                                    @Override
                                    public void onResponse(Call<PostGejala> call, Response<PostGejala> response) {
                                        if(response.isSuccessful()) {
                                            Log.d("RETRO", "ON SUCCESS : " + response.message());
                                            StyleableToast.makeText(EditGejala.this, "Sukses Hapus", R.style.ToastSucces).show();
                                            Intent intent = new Intent(EditGejala.this, TampilGejala.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Log.d("RETRO", "ON FAIL : " + response.message());
                                            StyleableToast.makeText(EditGejala.this, "Gagal Hapus", R.style.ToastWrong).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<PostGejala> call, Throwable t) {
                                        Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                                        StyleableToast.makeText(EditGejala.this, "Error", R.style.ToastWrong).show();
                                    }
                                });
                            }else{
                                StyleableToast.makeText(EditGejala.this, "Error", R.style.ToastWrong).show();
                            }
                        }
                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
