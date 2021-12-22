package com.justino.sistempakar.MenuBerita;

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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.justino.sistempakar.R;
import com.justino.sistempakar.Config;
import com.justino.sistempakar.network.ApiHelper;
import com.justino.sistempakar.network.ApiInterface;
import com.justino.sistempakar.berita.PostPutDelBerita;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahDataBerita extends AppCompatActivity {
    EditText nama_berita, det_berita,penerbit;
    Button btnSimpan, btnGallery, backcreateproduk;
    ImageView gambar;

    private String mediaPath;
    private String postPath;

    ApiInterface mApiInterface;
    private static final int REQUEST_PICK_PHOTO = Config.REQUEST_PICK_PHOTO;
    private static final int REQUEST_WRITE_PERMISSION = Config.REQUEST_WRITE_PERMISSION;
    private static final String INSERT_FLAG = Config.INSERT_FLAG;

    // Akses Izin Ambil Gambar dari Storage
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            tambahberita();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_databerita);
        nama_berita = (EditText) findViewById(R.id.namaProduk);
        det_berita = (EditText) findViewById(R.id.hargaProduk);
        penerbit = (EditText) findViewById(R.id.penerbit);
        gambar = (ImageView) findViewById(R.id.fotoProduk);
        btnGallery = (Button) findViewById(R.id.btnGallery);
        btnSimpan = (Button) findViewById(R.id.btnSimpan);
        backcreateproduk = (Button) findViewById(R.id.backcreateproduk);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);

        //Buka gallery
        backcreateproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TambahDataBerita.this, TampilDataBeritaPakar.class);
                startActivity(intent);
            }
        });
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(albumIntent, REQUEST_PICK_PHOTO);
            }
        });
        //Simpan foto
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    //Akses izin ambil gambar dari penyimpanan

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICK_PHOTO) {
                if (data != null) {
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

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            tambahberita();
        }
    }

    //Simpan data
    private void tambahberita() {
//        final String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        if (mediaPath == null) {
            Toast.makeText(getApplicationContext(), "Pilih gambar dulu, baru simpan ...!", Toast.LENGTH_LONG).show();
        } else {
            File imagefile = new File(mediaPath);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
            MultipartBody.Part partImage = MultipartBody.Part.createFormData("gambar_berita", imagefile.getName(), reqBody);

            Call<PostPutDelBerita> postHerosCall = mApiInterface.postBerita(partImage, RequestBody.create(MediaType.parse("text/plain"), nama_berita.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), det_berita.getText().toString()), RequestBody.create(MediaType.parse("text/plain"),penerbit.getText().toString()), RequestBody.create(MediaType.parse("text/plain"), INSERT_FLAG));
            postHerosCall.enqueue(new Callback<PostPutDelBerita>() {
                @Override
                public void onResponse(Call<PostPutDelBerita> call, Response<PostPutDelBerita> response) {
                    Log.d("RETRO", "LOG : " + response.message());
                    Toast.makeText(getApplicationContext(), "Produk berhasil ditambah...!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(TambahDataBerita.this, TampilDataBeritaPakar.class);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Call<PostPutDelBerita> call, Throwable t) {
                    Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                    //Log.d("RETRO", "ON FAILURE : " + t.getCause());
                    Toast.makeText(getApplicationContext(), "Produk gagal ditambah!", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}

    // Cek Versi Android Tuk Minta Izin


    // Menu Kembali Ke Home
//    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
//////        switch (item.getItemId()) {
//////            case android.R.id.home:
//////                // API 5+ solution
//////                onBackPressed();
//////                return true;
//////
//////            default:
//////                return super.onOptionsItemSelected(item);
//////        }
//////    }
