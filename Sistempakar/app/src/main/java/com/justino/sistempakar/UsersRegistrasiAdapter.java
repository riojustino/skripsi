package com.justino.sistempakar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.justino.sistempakar.R;
import com.justino.sistempakar.auth.Users;

import java.util.List;

public class UsersRegistrasiAdapter extends RecyclerView.Adapter<UsersRegistrasiAdapter.MyViewHolder>{
    List<Users> muserslist;

    public UsersRegistrasiAdapter(List<Users> usersList) {
        muserslist = usersList;
    }


    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data_user_register, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        holder.namauser.setText("Nama : "+muserslist.get(position).getNama_user());
        holder.nohpuser.setText("No Telp : "+muserslist.get(position).getNohp_user());
        if(muserslist.get(position).getJabatan_user().equals("3")){
            holder.jabatanuser.setText("Jabatan : Pakar");
        }else if (muserslist.get(position).getJabatan_user().equals("1")){
                holder.jabatanuser.setText("Jabatan : Admin");
        }else{
            holder.jabatanuser.setText("Jabatan : Pemilik");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), DetailRegister.class);
                mIntent.putExtra("id_user", muserslist.get(position).getId_user());
                mIntent.putExtra("nama_user", muserslist.get(position).getNama_user());
                mIntent.putExtra("nohp_user", muserslist.get(position).getNohp_user());
                mIntent.putExtra("jabatan_user", muserslist.get(position).getJabatan_user());
                mIntent.putExtra("email_user", muserslist.get(position).getEmail_user());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return muserslist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView namauser,nohpuser,jabatanuser;

        public MyViewHolder(View itemView) {
            super(itemView);
            namauser = (TextView) itemView.findViewById(R.id.namauser);
            nohpuser = (TextView) itemView.findViewById(R.id.nohpuser);
            jabatanuser = (TextView) itemView.findViewById(R.id.jabatanuser);

        }
    }
}
