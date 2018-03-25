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


public class Admin_blog_adapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Admin_blog_bean> userlist;
    DatabaseReference databaseStudent;

    public Admin_blog_adapter (Context c,ArrayList<Admin_blog_bean> userlist)
    {
        this.c=c;
        this.userlist=userlist;
        databaseStudent = FirebaseDatabase.getInstance().getReference("meet");

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_blog_data,parent,false);
        MyHolder holder=new MyHolder(v);

        return  holder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.a_name.setText(userlist.get(position).getA_name());
        holder.a_reply.setText(userlist.get(position).getA_reply());
        holder.a_cuser.setText(userlist.get(position).getA_cuser());


    }


    @Override
    public int getItemCount() {
        return userlist.size();
    }


}