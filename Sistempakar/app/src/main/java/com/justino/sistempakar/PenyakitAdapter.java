package com.justino.sistempakar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

//`import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.justino.sistempakar.R;
import com.justino.sistempakar.penyakit.Penyakit;
import com.justino.sistempakar.MenuPenyakit.EditDataPenyakit;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class PenyakitAdapter extends RecyclerView.Adapter<PenyakitAdapter.MyViewHolder>{
    List<Penyakit> mpenyakitList;

    public PenyakitAdapter(List<Penyakit> penyakitList) {
        mpenyakitList = penyakitList;
    }


    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data_penyakit, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.nama_penyakit.setText(mpenyakitList.get(position).getNama_penyakit());
        holder.det_penyakit.setText(mpenyakitList.get(position).getDet_penyakit());
        holder.srn_penyakit.setText(mpenyakitList.get(position).getSrn_penyakit());
        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + mpenyakitList.get(position).getGambar())
                .apply(new RequestOptions().override(600, 600))
//                .apply(RequestOptions.circleCropTransform())
                .into(holder.gambar);

        boolean isExpandable = mpenyakitList.get(position).isExpandableLayout();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditDataPenyakit.class);
                mIntent.putExtra("kode_penyakit", mpenyakitList.get(position).getKode_penyakit());
                mIntent.putExtra("nama_penyakit", mpenyakitList.get(position).getNama_penyakit());
                mIntent.putExtra("det_penyakit", mpenyakitList.get(position).getDet_penyakit());
                mIntent.putExtra("srn_penyakit", mpenyakitList.get(position).getSrn_penyakit());
                mIntent.putExtra("gambar", mpenyakitList.get(position).getGambar());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mpenyakitList.size();
    }

    public void filterlist(ArrayList<Penyakit> filteredList){
        mpenyakitList = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama_penyakit, det_penyakit, srn_penyakit;
        public ImageView gambar;
        LinearLayout test;
        RelativeLayout expandableLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama_penyakit = (TextView) itemView.findViewById(R.id.namaPenyakit);
            det_penyakit = (TextView) itemView.findViewById(R.id.detPenyakit);
            srn_penyakit = (TextView) itemView.findViewById(R.id.srnPenyakit);
            gambar = (ImageView) itemView.findViewById(R.id.imgProduk);

            test = itemView.findViewById(R.id.test);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Penyakit penyakit = mpenyakitList.get(getAdapterPosition());
                    penyakit.setExpandableLayout(!penyakit.isExpandableLayout());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
