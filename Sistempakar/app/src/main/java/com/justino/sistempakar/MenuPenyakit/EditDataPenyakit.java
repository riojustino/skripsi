package com.justino.sistempakar.MenuPenyakit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import io.github.muddz.styleabletoast.StyleableToast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.justino.sistempakar.Config;
import com.justino.sistempakar.R;
import com.justino.sistempakar.penyakit.PostPutDelPenyakit;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDataPenyakit extends AppCompatActivity {

    EditText nama_penyakit, det_penyakit, srn_penyakit;
    Button btnSimpan, btnGallery, backeditpenyakit,btnDelete;
    ImageView gambar;
    String ID;
    private String mediaPath;
    private String postPath;
    ApiInterface mApiInterface;
    private static final int REQUEST_PICK_PHOTO = Config.REQUEST_PICK_PHOTO;
    private static final int REQUEST_WRITE_PERMISSION = Config.REQUEST_WRITE_PERMISSION;
    private final int ALERT_DIALOG_CLOSE = Config.ALERT_DIALOG_CLOSE;
    private final int ALERT_DIALOG_DELETE = Config.ALERT_DIALOG_DELETE;
    private static final String UPDATE_FLAG = Config.UPDATE_FLAG;

    // Akses Izin Ambil Gambar dari Storage

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            updatePenyakit();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_datapenyakit);

        //Inisialisasi komponen form
        nama_penyakit = (EditText) findViewById(R.id.namaProduk);
        det_penyakit = (EditText) findViewById(R.id.hargaProduk);
        srn_penyakit = (EditText) findViewById(R.id.biayaProduk);
        gambar = (ImageView) findViewById(R.id.fotoProduk);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        backeditpenyakit = (Button) findViewById(R.id.backeditproduk);

        //Inisialisasi intent ke komponen form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("kode_penyakit");
        nama_penyakit.setText(mIntent.getStringExtra("nama_penyakit"));
        det_penyakit.setText(mIntent.getStringExtra("det_penyakit"));
        srn_penyakit.setText(mIntent.getStringExtra("srn_penyakit"));
        //Input gambar ke imgview
        Glide.with(EditDataPenyakit.this)
                .load(Config.IMAGES_URL + mIntent.getStringExtra("gambar"))
                .apply(new RequestOptions().override(350, 550))
                .into(gambar);

        // Definisi API
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        backeditpenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDataPenyakit.this, TampilDataPenyakit.class);
                startActivity(intent);
            }
        });
        // Fungsi Tombol Pilih Galery
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });

        // Fungsi Tombol Update
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePenyakit();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletepenyakit();
            }

        });

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_PICK_PHOTO){
                if(data!=null){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    gambar.setImageURI(data.getData());
                    cursor.close();

                    postPath = mediaPath;
                }
            }
        }
    }

    private void updatePenyakit() {
        if (mediaPath== null)
        {
            ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
            Call<PostPutDelPenyakit> postHerosCall = mApiInterface.postUpdatePenyakitNoPhoto(ID,nama_penyakit.getText().toString(),det_penyakit.getText().toString(),srn_penyakit.getText().toString());
            postHerosCall.enqueue(new Callback<PostPutDelPenyakit>() {
                @Override
                public void onResponse(Call<PostPutDelPenyakit> call, Response<PostPutDelPenyakit> response) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(EditDataPenyakit.this, "Penyakit berhasil diperbaharui", R.style.ToastSucces).show();
                    TampilDataPenyakit.ma.tampilpenyakit();
                    finish();
                }
                @Override
                public void onFailure(Call<PostPutDelPenyakit> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    //Log.d("RETRO", "ON FAILURE : " + t.getCause());
                    StyleableToast.makeText(EditDataPenyakit.this, "Penyakit gagal diperbaharui", R.style.ToastWrong).show();
                }
            });
        }
        else {
            File imagefile = new File(mediaPath);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
            MultipartBody.Part partImage = MultipartBody.Part.createFormData("gambar", imagefile.getName(), reqBody);

            Call<PostPutDelPenyakit> postHerosCall = mApiInterface.postUpdatePenyakit(partImage, RequestBody.create(MediaType.parse("text/plain"), ID), RequestBody.create(MediaType.parse("text/plain"), nama_penyakit.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), det_penyakit.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), srn_penyakit.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), UPDATE_FLAG));
            postHerosCall.enqueue(new Callback<PostPutDelPenyakit>() {
                @Override
                public void onResponse(Call<PostPutDelPenyakit> call, Response<PostPutDelPenyakit> response) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(EditDataPenyakit.this, "Penyakit berhasil diperbaharui", R.style.ToastSucces).show();
                    TampilDataPenyakit.ma.tampilpenyakit();
                    finish();
                }

                @Override
                public void onFailure(Call<PostPutDelPenyakit> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    //Log.d("RETRO", "ON FAILURE : " + t.getCause());
                    StyleableToast.makeText(EditDataPenyakit.this, "Penyakit gagal diperbaharui", R.style.ToastWrong).show();
                }
            });
        }
    }

    //            Call<PostPutDelPenyakit> postHerosCall = mApiInterface.postUpdatePenyakitt( RequestBody.create(MediaType.parse("text/plain"), ID), RequestBody.create(MediaType.parse("text/plain"), nama_penyakit.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), det_penyakit.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), srn_penyakit.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), UPDATE_FLAG));
//            postHerosCall.enqueue(new Callback<PostPutDelPenyakit>() {
//                @Override
//                public void onResponse(Call<PostPutDelPenyakit> call, Response<PostPutDelPenyakit> response) {
//                    Log.d("RETRO", "ON SUCCESS : " + response.message());
//                    StyleableToast.makeText(EditDataPenyakit.this, "Penyakit berhasil diperbaharui", R.style.ToastSucces).show();
//                    TampilDataPenyakit.ma.tampilpenyakit();
//                    finish();
//                }
//
//                @Override
//                public void onFailure(Call<PostPutDelPenyakit> call, Throwable t) {
//                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
//                    //Log.d("RETRO", "ON FAILURE : " + t.getCause());
//                    StyleableToast.makeText(EditDataPenyakit.this, "Penyakit gagal diperbaharui", R.style.ToastWrong).show();
//                }
//            });
    // Cek Versi Android untuk meminta izin
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            updatePenyakit();
        }
    }

    private void deletepenyakit() {
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
                                Call<PostPutDelPenyakit> deletePenyakit = mApiInterface.deletePenyakit(ID);
                                deletePenyakit.enqueue(new Callback<PostPutDelPenyakit>() {
                                    @Override
                                    public void onResponse(Call<PostPutDelPenyakit> call, Response<PostPutDelPenyakit> response) {
                                        StyleableToast.makeText(EditDataPenyakit.this, "Produk berhasil dihapus", R.style.ToastWrong).show();
                                        Intent intent = new Intent(EditDataPenyakit.this, TampilDataPenyakit.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<PostPutDelPenyakit> call, Throwable t) {
                                        // Toasty.error(getApplicationContext(), "Produk gagal di hapus", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(EditDataPenyakit.this, TampilDataPenyakit.class);
                                        startActivity(intent);
                                    }
                                });
                            }else{
                                StyleableToast.makeText(EditDataPenyakit.this, "Error", R.style.ToastWrong).show();
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
