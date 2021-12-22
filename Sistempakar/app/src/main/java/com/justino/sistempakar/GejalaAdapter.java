package com.justino.sistempakar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.justino.sistempakar.MenuGejala.EditGejala;
import com.justino.sistempakar.R;
import com.justino.sistempakar.gejala.Gejala;

import java.util.List;

public class GejalaAdapter extends RecyclerView.Adapter<GejalaAdapter.MyViewHolder> {
    List<Gejala> gejalaList;
    public static String idkuu = "";
    public GejalaAdapter(List<Gejala> gejala) {
        gejalaList = gejala;
    }


    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data_gejala, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        holder.kodegejala.setText(gejalaList.get(position).getKodeGejala());
        holder.namagejala.setText(gejalaList.get(position).getNamaGejala());
        idkuu = gejalaList.get(position).getKodeGejala();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditGejala.class);
                mIntent.putExtra("kode_gejala", gejalaList.get(position).getKodeGejala());
                mIntent.putExtra("nama_gejala", gejalaList.get(position).getNamaGejala());
                view.getContext().startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return gejalaList.size();
    }

    public String getIddelete(int position){
        return gejalaList.get(position).getKodeGejala();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView kodegejala, namagejala;
        public MyViewHolder(View itemView) {
            super(itemView);
            kodegejala = (TextView) itemView.findViewById(R.id.kodegejala);
            namagejala = (TextView) itemView.findViewById(R.id.namagejala);
        }
    }
}
