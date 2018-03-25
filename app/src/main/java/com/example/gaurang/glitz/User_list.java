package com.example.gaurang.glitz;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class User_list extends ArrayAdapter<User_Data> {
    private Activity context;
    private List<User_Data> studentList;
    private ArrayList<User_Data> arraylist;
   /* private Button b_up;*/

    public User_list(Activity context, List<User_Data> studentList) {
        super(context, R.layout.activity_user_list, studentList);
        this.context = context;
        this.studentList = studentList;
        this.arraylist = new ArrayList<User_Data>();
        this.arraylist.addAll(studentList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.activity_user_list, null, true);

        TextView t_v_name = listViewItem.findViewById(R.id.u_name);
        TextView t_v_mob = listViewItem.findViewById(R.id.u_mob);
        TextView t_v_email = listViewItem.findViewById(R.id.u_email);


        final User_Data student = studentList.get(position);
        t_v_name.setText("Name: " + student.getName());
        t_v_mob.setText("Mobile No.: " + student.getMobile_no());
        t_v_email.setText("Email : " + student.getEmail());
        return listViewItem;
    }

        public void filter(String charText) {
            charText = charText.toLowerCase(Locale.getDefault());
            studentList.clear();
            if (charText.length() == 0) {
                studentList.addAll(arraylist);
            } else {
                for (User_Data wp : arraylist) {
                    if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText) || wp.getMobile_no().toLowerCase(Locale.getDefault()).contains(charText) || wp.getEmail().contains(charText)) {
                        studentList.add(wp);
                    }
                }
            }
            notifyDataSetChanged();
        }




}