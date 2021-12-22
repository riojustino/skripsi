package com.justino.sistempakar;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.justino.sistempakar.MenuUser.EditUser;
import com.justino.sistempakar.R;
import com.justino.sistempakar.user.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    List<User> userList;
    public static String idkuu = "";
    public UserAdapter(List<User> users) {
        userList = users;
    }


    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data_user, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }


    @Override
    public void onBindViewHolder (MyViewHolder holder, final int position){
        holder.iduser.setText(userList.get(position).getIdUser());
        holder.namauser.setText(userList.get(position).getNamaUser());
        holder.nohpuser.setText(userList.get(position).getNohpUser());
        holder.jabatanuser.setText(userList.get(position).getJabatanUser());
        holder.emailuser.setText(userList.get(position).getEmailUser());
        if(userList.get(position).getJabatanUser().equals("3")){
            holder.jabatanuser.setText("Pakar");
        }else if (userList.get(position).getJabatanUser().equals("1")){
            holder.jabatanuser.setText("Admin");
        }else{
            holder.jabatanuser.setText("Pemilik");
        }
        idkuu = userList.get(position).getIdUser();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), EditUser.class);
                mIntent.putExtra("id_user", userList.get(position).getIdUser());
                mIntent.putExtra("nama_user", userList.get(position).getNamaUser());
                mIntent.putExtra("nohp_user", userList.get(position).getNohpUser());
                mIntent.putExtra("jabatan_user", userList.get(position).getJabatanUser());
                mIntent.putExtra("email_user", userList.get(position).getEmailUser());
                view.getContext().startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public String getIddelete(int position){
        return userList.get(position).getIdUser();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView iduser, namauser, nohpuser, jabatanuser, emailuser;
        public MyViewHolder(View itemView) {
            super(itemView);
            iduser = (TextView) itemView.findViewById(R.id.iduser);
            namauser = (TextView) itemView.findViewById(R.id.namauser);
            nohpuser = (TextView) itemView.findViewById(R.id.nohpuser);
            jabatanuser = (TextView) itemView.findViewById(R.id.jabatanuser);
            emailuser = (TextView) itemView.findViewById(R.id.emailuser);
        }
    }
}
