package com.example.gaurang.glitz;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class User_publish_messsage_adapter extends RecyclerView.Adapter<MyHolder> {
    private Activity context;
    Context c;
    private ArrayList<User_publish_messsage> studentList;
    /* private Button b_up;*/
    public User_publish_messsage_adapter(Context c,ArrayList<User_publish_messsage> studentlist)
    {
        this.c=c;
        this.studentList=studentlist;

    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.user_inbox_list,parent,false);
        MyHolder holder=new MyHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.company.setText(studentList.get(position).getCompany());
        holder.u_title.setText(studentList.get(position).getTitle());
        holder.u_desc.setText(studentList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return studentList.size();

    }
}
