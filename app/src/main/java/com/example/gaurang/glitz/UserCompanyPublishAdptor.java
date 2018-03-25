package com.example.gaurang.glitz;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class UserCompanyPublishAdptor extends RecyclerView.Adapter<MyHolder> {

    Context c;
    ArrayList<User_publish_messsage> userlist;
    DatabaseReference databaseStudent,databaseStudent2,databaseStudent3;

    public UserCompanyPublishAdptor  (Context c,ArrayList<User_publish_messsage> userlist)
    {
        this.c=c;
        this.userlist=userlist;
        databaseStudent = FirebaseDatabase.getInstance().getReference("meet");
        databaseStudent2 = FirebaseDatabase.getInstance().getReference("Company");

    }



    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.user_publish_company_bloglist,parent,false);
        MyHolder holder=new MyHolder(v);

        return  holder;
    }


    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
                databaseStudent3 =databaseStudent2.child(userlist.get(position).getCompany().toString());


        databaseStudent3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                   /* Toast.makeText(getApplicationContext(),"getting the data...",Toast.LENGTH_SHORT).show();*/


                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    //databaseStudent ref = student_snapshot.getValue(DatabaseReference.class);
                    // for(DataSnapshot s2 : student_snapshot.child("Company_msg").getChildren()){

                    String s=student_snapshot.child("companyName").getValue(String.class);
                    holder.b_company.setText(userlist.get(position).getCompany().toString());


                    //}
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        holder.b_title.setText(userlist.get(position).getTitle());
        holder.b_desc.setText(userlist.get(position).getDesc());


    }


    @Override
    public int getItemCount() {
        return userlist.size();
    }


}