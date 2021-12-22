package com.justino.sistempakar.MenuBerita;

import static com.google.gson.stream.JsonReader.*;

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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.justino.sistempakar.R;
import com.google.gson.stream.JsonReader;
import com.justino.sistempakar.Config;
import com.justino.sistempakar.MenuPenyakit.EditDataPenyakit;
import com.justino.sistempakar.MenuUser.EditUser;
import com.justino.sistempakar.MenuUser.TampilUser;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import com.justino.sistempakar.berita.PostPutDelBerita;
import com.justino.sistempakar.user.PostUser;

import java.io.File;

import io.github.muddz.styleabletoast.StyleableToast;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditDataBerita extends AppCompatActivity {

    EditText nama_berita, det_berita,penerbit;
    Button btnSimpan, btnGallery, backeditberita,btnDelete;
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
            updateBerita();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_databerita);

        //Inisialisasi komponen form
        nama_berita = (EditText) findViewById(R.id.namaProduk);
        det_berita = (EditText) findViewById(R.id.det_berita);
        penerbit = (EditText) findViewById(R.id.penerbit);
        gambar = (ImageView) findViewById(R.id.fotoProduk);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        backeditberita = (Button) findViewById(R.id.backeditproduk);

        //Inisialisasi intent ke komponen form
        Intent mIntent = getIntent();
        ID = mIntent.getStringExtra("kode_berita");
        nama_berita.setText(mIntent.getStringExtra("nama_berita"));
        det_berita.setText(mIntent.getStringExtra("det_berita"));
        penerbit.setText(mIntent.getStringExtra("penerbit"));
        //Input gambar ke imgview
        Glide.with(EditDataBerita.this)
                .load(Config.IMAGES_URL + mIntent.getStringExtra("gambar_berita"))
                .apply(new RequestOptions().override(350, 550))
                .into(gambar);

        // Definisi API
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        backeditberita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditDataBerita.this, TampilDataBeritaPakar.class);
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
                new RequestOptions().override(600, 600);
            }
        });

        // Fungsi Tombol Update
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateBerita();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteberita();
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

    private void updateBerita() {

        if (mediaPath== null)
        {
            ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
            Call<PostPutDelBerita> postHerosCall = mApiInterface.postUpdateBeritaNoPhoto(ID,nama_berita.getText().toString(),penerbit.getText().toString(),det_berita.getText().toString());
            postHerosCall.enqueue(new Callback<PostPutDelBerita>() {
                @Override
                public void onResponse(Call<PostPutDelBerita> call, Response<PostPutDelBerita> response) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    StyleableToast.makeText(EditDataBerita.this, "Berita berhasil diperbaharui", R.style.ToastSucces).show();
                    TampilDataBeritaPakar.ma.tampilberita();
                    finish();
                }

                @Override
                public void onFailure(Call<PostPutDelBerita> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    //Log.d("RETRO", "ON FAILURE : " + t.getCause());
                    StyleableToast.makeText(EditDataBerita.this, "Berita gagal diperbaharui", R.style.ToastWrong).show();
                }
            });
        }
        else {
            File imagefile = new File(mediaPath);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
            MultipartBody.Part partImage = MultipartBody.Part.createFormData("gambar_berita", imagefile.getName(), reqBody);
            Call<PostPutDelBerita> postHerosCall = mApiInterface.postUpdateBerita(partImage, RequestBody.create(MediaType.parse("text/plain"), ID), RequestBody.create(MediaType.parse("text/plain"), nama_berita.getText().toString()), RequestBody.create(MediaType.parse("text/plain"),penerbit.getText().toString()),RequestBody.create(MediaType.parse("text/plain"), det_berita.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), UPDATE_FLAG));
            postHerosCall.enqueue(new Callback<PostPutDelBerita>() {
                @Override
                public void onResponse(Call<PostPutDelBerita> call, Response<PostPutDelBerita> response) {
                        Log.d("RETRO", "ON SUCCESS : " + response.message());
                        StyleableToast.makeText(EditDataBerita.this, "Sukses Update", R.style.ToastSucces).show();
                        TampilDataBeritaPakar.ma.tampilberita();
                        finish();
                    }

                @Override
                public void onFailure(Call<PostPutDelBerita> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
//                    StyleableToast.makeText(EditDataBerita.this, "Berita gagal diperbaharui", R.style.ToastWrong).show();
                    StyleableToast.makeText(EditDataBerita.this, "Sukses Update", R.style.ToastSucces).show();
                    TampilDataBeritaPakar.ma.tampilberita();
                    finish();
                }
            });
            //            Call<PostPutDelberita> postHerosCall = mApiInterface.postUpdateberitat( RequestBody.create(MediaType.parse("text/plain"), ID), RequestBody.create(MediaType.parse("text/plain"), nama_berita.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), det_berita.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), srn_berita.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), UPDATE_FLAG));
//            postHerosCall.enqueue(new Callback<PostPutDelberita>() {
//                @Override
//                public void onResponse(Call<PostPutDelberita> call, Response<PostPutDelberita> response) {
//                    Log.d("RETRO", "ON SUCCESS : " + response.message());
//                    StyleableToast.makeText(EditDataberita.this, "berita berhasil diperbaharui", R.style.ToastSucces).show();
//                    TampilDataberita.ma.tampilberita();
//                    finish();
//                }
//
//                @Override
//                public void onFailure(Call<PostPutDelberita> call, Throwable t) {
//                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
//                    //Log.d("RETRO", "ON FAILURE : " + t.getCause());
//                    StyleableToast.makeText(EditDataberita.this, "berita gagal diperbaharui", R.style.ToastWrong).show();
//                }
//            });
//            postHerosCall.enqueue(new Callback<PostPutDelBerita>() {
//                @Override
//                public void onResponse(Call<PostPutDelBerita> call, Response<PostPutDelBerita> response) {
//                    Log.d("RETRO", "ON SUCCESS : " + response.message());
//                    StyleableToast.makeText(EditDataBerita.this, "Berita berhasil diperbaharui", R.style.ToastSucces).show();
//                    TampilDataBeritaPakar.ma.tampilberita();
//                    finish();
//                }
//
//                @Override
//                public void onFailure(Call<PostPutDelBerita> call, Throwable t) {
//                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
//                    //Log.d("RETRO", "ON FAILURE : " + t.getCause());
//                    StyleableToast.makeText(EditDataBerita.this, "Berita gagal diperbaharui", R.style.ToastWrong).show();
//                }
//            });
        }
    }
    // Cek Versi Android untuk meminta izin
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            updateBerita();
        }
    }

    private void deleteberita() {
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
                                Call<PostPutDelBerita> deleteBerita = mApiInterface.deleteBerita(ID);
                                deleteBerita.enqueue(new Callback<PostPutDelBerita>() {
                                    @Override
                                    public void onResponse(Call<PostPutDelBerita> call, Response<PostPutDelBerita> response) {
                                        StyleableToast.makeText(EditDataBerita.this, "Produk berhasil dihapus", R.style.ToastWrong).show();
                                        Intent intent = new Intent(EditDataBerita.this, TampilDataBeritaPakar.class);
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onFailure(Call<PostPutDelBerita> call, Throwable t) {
                                        // Toasty.error(getApplicationContext(), "Produk gagal di hapus", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(EditDataBerita.this, TampilDataBeritaPakar.class);
                                        startActivity(intent);
                                    }
                                });
                            }else{
                                StyleableToast.makeText(EditDataBerita.this, "Error", R.style.ToastWrong).show();
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
