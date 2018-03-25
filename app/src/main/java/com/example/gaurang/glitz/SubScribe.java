package com.example.gaurang.glitz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
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
import java.util.List;
import java.util.Locale;

public class SubScribe extends AppCompatActivity {
    RecyclerView l_v_student;
    DatabaseReference databaseStudent;
    ArrayList<Publish_Msg> studentList;
    private ProgressBar progressBar;
    public EditText search;
    sub_list adapter;
    FirebaseAuth auth;

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
        studentList = new ArrayList<>();


        databaseStudent = FirebaseDatabase.getInstance().getReference("Admin_msg");


        studentList = new ArrayList<>();

     //   search = findViewById(R.id.);

        progressBar = findViewById(R.id.progressBar1);


        /*adapter = new List_item(Display.this, studentList);*/




    }
    public void add_drawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Inbox");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Publish");
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("User");
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("SubScribe");
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Logout");
        final PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("GYg");
        //create the drawer and remember the `Drawer` result object
        Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        item2,
                        item4,
                        new DividerDrawerItem(),
                        item1,
                        item3,
                        item6,
                        new DividerDrawerItem(),
                        item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem == item1) {
                            startActivity(new Intent(getApplicationContext(), User_msg.class));
                            finish();
                            return true;
                        }
                        if (drawerItem == item2) {
                            startActivity(new Intent(getApplicationContext(), Publish.class));
                            finish();
                            return true;
                        }
                        if (drawerItem == item3) {
                            startActivity(new Intent(getApplicationContext(), Users.class));
                            finish();
                            return true;
                        }
                        if (drawerItem == item4) {
                            startActivity(new Intent(getApplicationContext(), SubScribe.class));
                            finish();
                            return true;
                        }
                        if (drawerItem == item6) {
                            startActivity(new Intent(getApplicationContext(), Company_user_publish.class));
                            finish();
                            return true;
                        }
                        if (drawerItem == item5) {
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
                studentList.clear();
                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    Publish_Msg student = student_snapshot.getValue(Publish_Msg.class);
                    studentList.add(student);
                }
                adapter = new sub_list(SubScribe.this, studentList);
                progressBar.setVisibility(View.GONE);
                l_v_student.setAdapter(adapter);
                l_v_student.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
