package com.example.gaurang.glitz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Users extends AppCompatActivity implements Serializable {

    ListView l_v_student;
    DatabaseReference databaseStudent;
    List<User_Data> studentList;
    private ProgressBar progressBar;
    public EditText search;
    private User_list adapter;
FirebaseAuth auth;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(this, drawer.class);// main activity in second
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(i);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        //  b_search = findViewById(R.id.search);
           //drawer d=new drawer();
        auth= FirebaseAuth.getInstance();
           add_drawer();
        l_v_student = findViewById(R.id.user_list);

        databaseStudent = FirebaseDatabase.getInstance().getReference("User");


        studentList = new ArrayList<>();

        search = findViewById(R.id.search);

        progressBar = findViewById(R.id.progressBar);


        /*adapter = new List_item(Display.this, studentList);*/


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
              /*  Display.this.adapter.getFilter().filter(s.toString());
                adapter.notifyDataSetChanged();*/
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = search.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);

            }
        });
        l_v_student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User_Data s = studentList.get(position);
                Intent intent = new Intent(getApplicationContext(), User_Publish.class);
                /*intent.putExtra("Student_obj",s);*/
              /*  intent.putExtra("ID", s.getEntry_id());
                intent.putExtra("prev_fees",s.getPaid_fees());*/
                Bundle b = new Bundle();
                b.putSerializable("Student_obj", (Serializable) s);
                intent.putExtra("value", b);
                startActivity(intent);
            }
        });




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
    } private void add_data(String name_ip, String mobile_no_ip, String email_ip) {
        String id = databaseStudent.push().getKey();
        User_Data s1 = new User_Data(id, name_ip, mobile_no_ip, email_ip);
        databaseStudent.child(id).setValue(s1);

    }

    @Override
    protected void onStart() {
        super.onStart();
        progressBar.setVisibility(View.VISIBLE);
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    studentList.clear();
                    for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                        User_Data student = student_snapshot.getValue(User_Data.class);
                        studentList.add(student);
                    }


                    adapter = new User_list(Users.this, studentList);
                    progressBar.setVisibility(View.GONE);
                    l_v_student.setTextFilterEnabled(true);
                    l_v_student.setAdapter(adapter);
                    l_v_student.setVisibility(View.VISIBLE);
                }

                @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}


