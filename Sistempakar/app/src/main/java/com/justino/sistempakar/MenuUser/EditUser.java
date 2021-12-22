package com.justino.sistempakar.MenuUser;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.justino.sistempakar.Config;
import com.justino.sistempakar.R;
import com.justino.sistempakar.user.PostUser;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import io.github.muddz.styleabletoast.StyleableToast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUser extends AppCompatActivity {
    EditText namaUser, nohpUser, emailUser;
    Button btnEditUser, backedituser;
    String ID;
    ApiInterface mApiInterface;

    private final int ALERT_DIALOG_CLOSE = Config.ALERT_DIALOG_CLOSE;
    private final int ALERT_DIALOG_DELETE = Config.ALERT_DIALOG_DELETE;
    private static final String UPDATE_FLAG = Config.UPDATE_FLAG;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);

        //Inisialisasi komponen form
        namaUser = (EditText) findViewById(R.id.namaUser);
        nohpUser = (EditText) findViewById(R.id.nohpUser);
        emailUser = (EditText) findViewById(R.id.emailUser);
        btnEditUser = (Button) findViewById(R.id.btnEditUser) ;
        backedituser = (Button) findViewById(R.id.backedituser);
        //Inisialisasi intent ke komponen form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("id_user");
        namaUser.setText(mIntent.getStringExtra("nama_user"));
        nohpUser.setText(mIntent.getStringExtra("nohp_user"));
        emailUser.setText(mIntent.getStringExtra("email_user"));
        // Definisi API
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);

        backedituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditUser.this, TampilUser.class);
                startActivity(intent);
            }
        });
        // Fungsi Tombol Update
        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(namaUser.getText().toString().isEmpty()){
                    StyleableToast.makeText(EditUser.this, "Masukkan Nama Dahulu", R.style.ToastWrong).show();
                } else {
                        if (nohpUser.getText().toString().isEmpty()) {
                            StyleableToast.makeText(EditUser.this, "Masukkan No Hp Dahulu", R.style.ToastWrong).show();
                        } else {
                            if (emailUser.getText().toString().isEmpty()) {
                                StyleableToast.makeText(EditUser.this, "Masukkan Email Dahulu", R.style.ToastWrong).show();
                            } else {
                                updateuser();
                            }
                        }
                    }
                }

        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }


    private void updateuser() {
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<PostUser> postUserCall = mApiInterface.updateuser(ID, namaUser.getText().toString(), nohpUser.getText().toString(), emailUser.getText().toString());
        postUserCall.enqueue(new Callback<PostUser>() {
            @Override
            public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(EditUser.this, "Sukses Update", R.style.ToastSucces).show();
                    Intent intent = new Intent(EditUser.this, TampilUser.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    StyleableToast.makeText(EditUser.this, "Gagal Update", R.style.ToastWrong).show();
                }
            }

            @Override
            public void onFailure(Call<PostUser> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                StyleableToast.makeText(EditUser.this, "Error", R.style.ToastWrong).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case android.R.id.home:
                showAlertDialog(ALERT_DIALOG_CLOSE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE);
    }

    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;

        if (isDialogClose) {
            dialogTitle = "Batal";
            dialogMessage = "Apakah anda ingin membatalkan perubahan user?";
        } else {
            dialogMessage = "Apakah anda yakin ingin menghapus user ini?";
            dialogTitle = "Hapus User";
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
                                Call<PostUser> postUserCall = mApiInterface.deleteuser(ID, namaUser.getText().toString());
                                postUserCall.enqueue(new Callback<PostUser>() {
                                    @Override
                                    public void onResponse(Call<PostUser> call, Response<PostUser> response) {
                                        if(response.isSuccessful()) {
                                            Log.d("RETRO", "ON SUCCESS : " + response.message());
                                            StyleableToast.makeText(EditUser.this, "Sukses Hapus", R.style.ToastSucces).show();
                                            Intent intent = new Intent(EditUser.this, TampilUser.class);
                                            startActivity(intent);
                                        }
                                        else {
                                            Log.d("RETRO", "ON FAIL : " + response.message());
                                            StyleableToast.makeText(EditUser.this, "Gagal Hapus", R.style.ToastWrong).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<PostUser> call, Throwable t) {
                                        Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                                        StyleableToast.makeText(EditUser.this, "Error", R.style.ToastWrong).show();
                                    }
                                });
                            }else{
                                StyleableToast.makeText(EditUser.this, "Error", R.style.ToastWrong).show();
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
