package com.justino.sistempakar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.justino.sistempakar.R;

public class TentangKami extends AppCompatActivity {

    Button btnback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tentangkami);

        btnback = (Button) findViewById(R.id.btnBeranda);

        btnback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TentangKami.this, Dashboard.class);
                startActivity(intent);
            }
        });
    }
}
