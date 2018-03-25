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


public class CompanyAdapter extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<CompanyData> userlist;
    DatabaseReference databaseStudent;

    public CompanyAdapter (Context c,ArrayList<CompanyData> userlist)
    {
        this.c=c;
        this.userlist=userlist;
        databaseStudent = FirebaseDatabase.getInstance().getReference("Company");

    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_company_list,parent,false);
        MyHolder holder=new MyHolder(v);

        return  holder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.c_name.setText(userlist.get(position).getC_name());
        holder.c_email.setText(userlist.get(position).getC_email());
        holder.c_website.setText(userlist.get(position).getC_website());


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