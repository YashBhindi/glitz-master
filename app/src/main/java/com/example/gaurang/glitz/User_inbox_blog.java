package com.example.gaurang.glitz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.ArrayList;

public class User_inbox_blog extends AppCompatActivity {
        RecyclerView l_v_student;
        DatabaseReference databaseStudent;
        ArrayList<User_publish_messsage> studentList;
        private ProgressBar progressBar;
        public EditText search;
        User_publish_messsage_adapter adapter;
        FirebaseAuth auth;
        String temp;
        ArrayList<String> keyp;
        Context context;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sub_scribe);
            //  b_search = findViewById(R.id.search);
            //drawer d=new drawer();
            auth=FirebaseAuth.getInstance();
            add_drawer();
            l_v_student = (RecyclerView) findViewById(R.id.sub_list);
            l_v_student.setLayoutManager(new LinearLayoutManager(this));
            studentList = new ArrayList<User_publish_messsage>();
            keyp = new ArrayList<String>();


            databaseStudent = FirebaseDatabase.getInstance().getReference("meet");


            studentList = new ArrayList<User_publish_messsage>();

            //   search = findViewById(R.id.);

            progressBar = findViewById(R.id.progressBar1);


        /*adapter = new List_item(Display.this, studentList);*/




        }
    public  void add_drawer(){

//if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Inbox");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Company's");

        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("Logout");


//create the drawer and remember the `Drawer` result object
        FragmentManager fragmentManager = getSupportFragmentManager();

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        item2,
                        new DividerDrawerItem(),
                        item1,
                        new DividerDrawerItem(),
                        item3

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        if (drawerItem == item1) {
                            startActivity(new Intent(getApplicationContext(), User_Inbox.class));
                            finish();
                            return true;
                        }
                        if(drawerItem==item2) {
                            //Toast.makeText(getApplicationContext(),"item 3 selected",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Company_list.class));
                            finish();
                            return true;
                        }
                        if(drawerItem==item3){
                            auth.signOut();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        }

                        return true;
                    }
                })
                .build();
    }
    protected void onStart() {
            super.onStart();
            progressBar.setVisibility(View.VISIBLE);
            databaseStudent.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                   /* Toast.makeText(getApplicationContext(),"getting the data...",Toast.LENGTH_SHORT).show();*/
keyp.clear();
                    studentList.clear();

                    for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                        //databaseStudent ref = student_snapshot.getValue(DatabaseReference.class);
                           // for(DataSnapshot s2 : student_snapshot.child("Company_msg").getChildren()){
                                User_publish_messsage s=student_snapshot.child("Comapny_msg").getValue(User_publish_messsage.class);
                                studentList.add(s);
                                keyp.add(student_snapshot.getKey().toString());

//Toast.makeText(getApplicationContext(),student_snapshot.getKey().toString(),Toast.LENGTH_SHORT).show();

                            //}
                    }
                   adapter = new User_publish_messsage_adapter(User_inbox_blog.this, studentList);
                    progressBar.setVisibility(View.GONE);
                    l_v_student.setAdapter(adapter);
                    l_v_student.setVisibility(View.VISIBLE);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        RecyclerView recyclerView = findViewById(R.id.sub_list);
        recyclerView.addOnItemTouchListener(
                new OnClickCompanylist(context, new OnClickCompanylist.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        //Toast.makeText(Company_list.this,"list",Toast.LENGTH_SHORT).show();
                        User_publish_messsage s = studentList.get(position);
                        String key= keyp.get(position);
                        String cuser=FirebaseAuth.getInstance().getCurrentUser().getUid();
                        Bundle b=new Bundle();
                        b.putSerializable("u_p_m",s);
                        b.putString("key",key);
                        b.putString("cuser",cuser);
                        Intent i=new Intent(User_inbox_blog.this,reply.class);
                        i.putExtra("bundle",b);
                        startActivity(i);
                        Toast.makeText(User_inbox_blog.this,"o3n4",Toast.LENGTH_SHORT).show();

                    }
                })
        );

    }

    }

