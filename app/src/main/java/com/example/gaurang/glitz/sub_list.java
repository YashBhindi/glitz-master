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

public class sub_list extends RecyclerView.Adapter<MyHolder> {
    private Activity context;
    Context c;
    private ArrayList<Publish_Msg> studentList;
   /* private Button b_up;*/
   public sub_list(Context c,ArrayList<Publish_Msg> studentlist)
   {
       this.c=c;
       this.studentList=studentlist;

   }


















    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_sub_list,parent,false);
        MyHolder holder=new MyHolder(v);
        return  holder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.title_.setText(studentList.get(position).getTitle());
        holder.desc_.setText(studentList.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return studentList.size();

    }
}
