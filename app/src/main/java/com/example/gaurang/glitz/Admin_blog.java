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
import android.widget.ListView;
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

public class Admin_blog extends AppCompatActivity {
    FirebaseAuth auth;
    RecyclerView l_v_student;
    Admin_blog_adapter adapter;
    ArrayList<Admin_blog_bean> userlist;
    ListView l;
    DatabaseReference databaseStudent, databaseanother;
    ArrayList<Admin_blog_bean> studentList;
    String key;
    Context context;
    ArrayList<String> keyp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_blog);
        auth = FirebaseAuth.getInstance();
        add_drawer();
        l_v_student = (RecyclerView) findViewById(R.id.blog_rv_reply);
        l_v_student.setLayoutManager(new LinearLayoutManager(this));
        userlist = new ArrayList<>();
        keyp = new ArrayList<String>();

        auth = FirebaseAuth.getInstance();
        key = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        databaseStudent = FirebaseDatabase.getInstance().getReference("meet");
        //databaseanother=FirebaseDatabase.getInstance().getReference("User").child();
        studentList = new ArrayList<>();
        //data
        //userlist=userListCollection.getDetails();
        //adapter
        adapter = new Admin_blog_adapter(this, studentList);
        l_v_student.setAdapter(adapter);

    }

    public void add_drawer() {

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
                        Fragment fragment = null;
                        Intent i = null;

                        if (drawerItem == item1) {
                            startActivity(new Intent(getApplicationContext(), User_Inbox.class));
                            finish();
                            return true;
                        }
                        if (drawerItem == item2) {
                            //Toast.makeText(getApplicationContext(),"item 3 selected",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Company_list.class));
                            finish();
                            return true;
                        }
                        if (drawerItem == item3) {
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
        read_data();

    }

    private void read_data() {
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();
                keyp.clear();

                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    //Admin_blog_bean student = student_snapshot.getValue(Admin_blog_bean.class);

                   // Object o=student_snapshot.child("Company_msg").child("company").getValue();
                    //Toast.makeText(getApplicationContext(),String.valueOf(o),Toast.LENGTH_SHORT).show();
                    /*for (DataSnapshot student_snapshot2 : student_snapshot.getChildren()) {
                        Admin_blog_bean s = student_snapshot2.child("reply").getValue(Admin_blog_bean.class);
                        keyp.add(student_snapshot.getKey().toString());

                        studentList.add(s);
                    }*/
                }


                adapter = new Admin_blog_adapter(Admin_blog.this, studentList);
                l_v_student.setAdapter(adapter);
                l_v_student.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
