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
import com.justino.sistempakar.berita.Berita;
import com.justino.sistempakar.MenuBerita.EditDataBerita;
//import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

//import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class BeritaPakarAdapter extends RecyclerView.Adapter<BeritaPakarAdapter.MyViewHolder>{
    List<Berita> mberitaList;

    public BeritaPakarAdapter(List<Berita> beritaList) {
        mberitaList = beritaList;
    }


    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data_beritapakar, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder (MyViewHolder holder,final int position){
        holder.nama_berita.setText(mberitaList.get(position).getNama_berita());
        holder.det_berita.setText(mberitaList.get(position).getDet_berita());
        holder.penerbit.setText(mberitaList.get(position).getPenerbit());
        holder.tanggal.setText(mberitaList.get(position).getTanggal());
        Glide.with(holder.itemView.getContext())
                .load(Config.IMAGES_URL + mberitaList.get(position).getGambar())
                .apply(new RequestOptions().override(600, 600))
//                .apply(RequestOptions.circleCropTransform())
                .into(holder.gambar);

        boolean isExpandable = mberitaList.get(position).isExpandableLayout();
        holder.expandableLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditDataBerita.class);
                mIntent.putExtra("kode_berita", mberitaList.get(position).getKode_berita());
                mIntent.putExtra("nama_berita", mberitaList.get(position).getNama_berita());
                mIntent.putExtra("det_berita", mberitaList.get(position).getDet_berita());
                mIntent.putExtra("penerbit", mberitaList.get(position).getPenerbit());
                mIntent.putExtra("gambar_berita", mberitaList.get(position).getGambar());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mberitaList.size();
    }

    public void filterlist(ArrayList<Berita> filteredList){
        mberitaList = filteredList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nama_berita, det_berita,penerbit,tanggal;
        public ImageView gambar;
        LinearLayout test;
        RelativeLayout expandableLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            nama_berita = (TextView) itemView.findViewById(R.id.namaBerita);
            det_berita = (TextView) itemView.findViewById(R.id.detBerita);
            penerbit = (TextView) itemView.findViewById(R.id.penerbit);
            tanggal = (TextView) itemView.findViewById(R.id.tanggal);
            gambar = (ImageView) itemView.findViewById(R.id.imgBerita);

            test = itemView.findViewById(R.id.test);
            expandableLayout = itemView.findViewById(R.id.expandableLayout);

            test.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Berita berita = mberitaList.get(getAdapterPosition());
                    berita.setExpandableLayout(!berita.isExpandableLayout());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
