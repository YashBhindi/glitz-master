package com.example.gaurang.glitz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by yash on 25/3/18.
 */

public class Admin_blog_tadapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<Admin_blog_tbean> userlist;
    DatabaseReference databaseStudent;

    public Admin_blog_tadapter (Context c,ArrayList<Admin_blog_tbean> userlist)
    {
        this.c=c;
        this.userlist=userlist;
        databaseStudent = FirebaseDatabase.getInstance().getReference("meet");

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.admin_blog_tdata,parent,false);
        MyHolder holder=new MyHolder(v);

        return  holder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.a_title.setText(userlist.get(position).getA_title());
        holder.a_desc.setText(userlist.get(position).getA_desc());


    }


    @Override
    public int getItemCount() {
        return userlist.size();
    }


}