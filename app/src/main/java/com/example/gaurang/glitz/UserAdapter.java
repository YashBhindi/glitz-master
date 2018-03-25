package com.example.gaurang.glitz;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<UserData> userlist;
    DatabaseReference databaseStudent;

    public UserAdapter(Context c,ArrayList<UserData> userlist)
    {
        this.c=c;
        this.userlist=userlist;
        databaseStudent = FirebaseDatabase.getInstance().getReference("user_table");

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list,parent,false);
        MyHolder holder=new MyHolder(v);
        return  holder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.name.setText(userlist.get(position).getName());
        holder.email.setText(userlist.get(position).getEmail());
        holder.contact.setText(userlist.get(position).getContact());
        holder.inquiry.setText(userlist.get(position).getInquiry_type());

    }


    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public void remove(int pos)
    {
        userlist.remove(pos);
        this.notifyItemRemoved(pos);
        String id=userlist.get(pos).getId();
        databaseStudent.child(id).removeValue();

    }
}