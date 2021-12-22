package com.justino.sistempakar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.justino.sistempakar.berita.Berita;
import com.justino.sistempakar.riwayat.Riwayat;

import java.util.ArrayList;
import java.util.List;


public class RiwayatAdapter extends RecyclerView.Adapter {

    List<Riwayat> mriwayatList;

    public RiwayatAdapter(List<Riwayat> riwayatList) {
        this.mriwayatList = riwayatList;
    }

    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data_riwayat, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder,int position){
        MyViewHolder myViewHolder = (MyViewHolder) holder;

        int rowPos = myViewHolder.getAbsoluteAdapterPosition();

        if (rowPos == 0) {

            myViewHolder.txtId.setBackgroundResource(R.drawable.table_header_cell_bg);
            myViewHolder.txtNama.setBackgroundResource(R.drawable.table_header_cell_bg);
            myViewHolder.txtHasil.setBackgroundResource(R.drawable.table_header_cell_bg);
            myViewHolder.txtDate.setBackgroundResource(R.drawable.table_header_cell_bg);

            myViewHolder.txtId.setText("ID");
            myViewHolder.txtId.setTextColor(Color.WHITE);
            myViewHolder.txtId.setTypeface(Typeface.DEFAULT_BOLD);
            myViewHolder.txtNama.setText("Nama Penyakit");
            myViewHolder.txtNama.setTextColor(Color.WHITE);
            myViewHolder.txtNama.setTypeface(Typeface.DEFAULT_BOLD);
            myViewHolder.txtHasil.setText("Nilai Hasil");
            myViewHolder.txtHasil.setTextColor(Color.WHITE);
            myViewHolder.txtHasil.setTypeface(Typeface.DEFAULT_BOLD);
            myViewHolder.txtDate.setText("Tanggal dan Waktu");
            myViewHolder.txtDate.setTextColor(Color.WHITE);
            myViewHolder.txtDate.setTypeface(Typeface.DEFAULT_BOLD);
        } else {
            Riwayat modal = mriwayatList.get(rowPos - 1);

            myViewHolder.txtId.setBackgroundResource(R.drawable.table_content_cell_bg);
            myViewHolder.txtNama.setBackgroundResource(R.drawable.table_content_cell_bg);
            myViewHolder.txtHasil.setBackgroundResource(R.drawable.table_content_cell_bg);
            myViewHolder.txtDate.setBackgroundResource(R.drawable.table_content_cell_bg);

            myViewHolder.txtId.setText(modal.getId());
            myViewHolder.txtNama.setText(modal.getNamapenyakit());
            myViewHolder.txtHasil.setText(modal.getHasilpenyakit() + " %");
            myViewHolder.txtDate.setText(modal.getDate());
        }
    }

    @Override
    public int getItemCount() {
        return mriwayatList.size()+1;
    }

//    public void filterlist(ArrayList<Riwayat> filteredList){
//        mriwayatList = filteredList;
//        notifyDataSetChanged();
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtId;
        TextView txtNama;
        TextView txtHasil;
        TextView txtDate;

        MyViewHolder(View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtRank);
            txtNama = itemView.findViewById(R.id.txtMovieName);
            txtHasil = itemView.findViewById(R.id.txtYear);
            txtDate = itemView.findViewById(R.id.txtCost);
        }
    }
}

