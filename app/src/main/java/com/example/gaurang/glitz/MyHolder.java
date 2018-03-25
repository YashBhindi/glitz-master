package com.example.gaurang.glitz;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by BHIMANI585 on 3/12/2018.
 */

public class MyHolder extends RecyclerView.ViewHolder {

    TextView name,email,contact,inquiry,title_,desc_,company,u_title,u_desc,c_name,c_email,c_website;

    TextView b_company,b_title,b_desc,a_name,a_reply,a_title,a_desc,a_cuser;

    public MyHolder(View itemView) {
        super(itemView);

        name=(TextView)itemView.findViewById(R.id.name1);
        email=(TextView)itemView.findViewById(R.id.email1);
        contact=(TextView)itemView.findViewById(R.id.contact1);
        inquiry=(TextView)itemView.findViewById(R.id.inquiry_type1);

        //subscribe data
            title_=(TextView)itemView.findViewById(R.id.s_title);
            desc_=(TextView)itemView.findViewById(R.id.s_des);

            //User_publish_messsage
        company=(TextView)itemView.findViewById(R.id.u_company);
        u_title=(TextView)itemView.findViewById(R.id.u_title);
        u_desc=(TextView)itemView.findViewById(R.id.u_desc);
        //company
        c_name=(TextView)itemView.findViewById(R.id.c_name);
        c_email=(TextView)itemView.findViewById(R.id.c_email);
        c_website=(TextView)itemView.findViewById(R.id.c_website);

        //blog
        b_company=(TextView)itemView.findViewById(R.id.b_company);
        b_title=(TextView)itemView.findViewById(R.id.b_title);
        b_desc=(TextView)itemView.findViewById(R.id.b_desc);

        //admin blog reply
        a_name=(TextView)itemView.findViewById(R.id.a_name);
        a_reply=(TextView)itemView.findViewById(R.id.a_reply);
        a_cuser = (TextView) itemView.findViewById(R.id.a_cuser);


        //admin tdata
        a_title=(TextView)itemView.findViewById(R.id.a_title);
        a_desc=(TextView)itemView.findViewById(R.id.a_desc);


    }
}
