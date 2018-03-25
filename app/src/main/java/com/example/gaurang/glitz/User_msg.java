package com.example.gaurang.glitz;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;

public class User_msg extends AppCompatActivity {

    RecyclerView l_v_student;
    UserAdapter adapter;
    ArrayList<UserData> userlist;
    ListView l;
    DatabaseReference databaseStudent;
    ArrayList<UserData> studentList;
    ItemTouchHelper helper;
    ItemTouchHelper.Callback callback;
FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        add_drawer();
        l_v_student = (RecyclerView) findViewById(R.id.recycler_view);
        l_v_student.setLayoutManager(new LinearLayoutManager(this));
        userlist = new ArrayList<>();

        auth= FirebaseAuth.getInstance();
        databaseStudent = FirebaseDatabase.getInstance().getReference("user_table1");


        studentList = new ArrayList<>();


        //data

        //userlist=userListCollection.getDetails();

        //adapter
        adapter = new UserAdapter(this, studentList);
        l_v_student.setAdapter(adapter);
     //   read_data();

       // helper.attachToRecyclerView(l_v_student);


//add_data("yash","6546464","general","A@hfgds");





       /* userlist=new ArrayList<>();
        UserData u=new UserData();
        u.setContact("sdasd");
        u.setEmail("adasd");
        u.setInquiry_type("asdsad");
        u.setName("dad");
        userlist.add(u);
        UserAdapter adapter = new UserAdapter(User_msg.this,userlist);
        ListView listView=(ListView)findViewById(R.id.listview1);
        listView.setAdapter(adapter); */
    }
    protected void onStart() {
        super.onStart();
       read_data();
    }
    public void add_drawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Inbox");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Publish");
        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("User");
        final PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("SubScribe");
        final PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Logout");
        final PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName("Fire Query");
        final PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName("Query Reply");

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
                        item7,
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
                        if (drawerItem == item7) {
                            startActivity(new Intent(getApplicationContext(), Admin_blog.class));
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
    private void add_data(String name_ip, String mobile_no_ip, String email_ip) {
        String id = databaseStudent.push().getKey();
        User_Data s1 = new User_Data(id, name_ip, mobile_no_ip, email_ip);
        databaseStudent.child(id).setValue(s1);

    }

    private void read_data() {
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    UserData student = student_snapshot.getValue(UserData.class);
                    studentList.add(student);
                }


                adapter = new UserAdapter(User_msg.this, studentList);
                l_v_student.setAdapter(adapter);
                l_v_student.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void add_data(String name_ip, String contact_no_ip,String inquiry_type_ip, String email_ip) {
        String id = databaseStudent.push().getKey();
        UserData s1 = new UserData(id, name_ip,email_ip,inquiry_type_ip,contact_no_ip);
        databaseStudent.child(id).setValue(s1);
        Toast.makeText(getApplicationContext(),"data added",Toast.LENGTH_SHORT).show();

    }


}
