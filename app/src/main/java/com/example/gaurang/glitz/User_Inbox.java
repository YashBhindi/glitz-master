package com.example.gaurang.glitz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

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

public class User_Inbox extends AppCompatActivity {
    FirebaseAuth auth;
    RecyclerView l_v_student;
    User_publish_messsage_adapter adapter;
    ArrayList<User_publish_messsage> userlist;
    ListView l;
    DatabaseReference databaseStudent,databaseanother;
    ArrayList<User_publish_messsage> studentList;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__inbox);
        auth=FirebaseAuth.getInstance();
        add_drawer();
        l_v_student = (RecyclerView) findViewById(R.id.u_recycler_view);
        l_v_student.setLayoutManager(new LinearLayoutManager(this));
        userlist = new ArrayList<>();

        auth= FirebaseAuth.getInstance();
        key=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        databaseStudent = FirebaseDatabase.getInstance().getReference("User").child(key).child("Inbox");
//databaseanother=FirebaseDatabase.getInstance().getReference("User").child();

        studentList = new ArrayList<>();


        //data

        //userlist=userListCollection.getDetails();

        //adapter
        adapter = new User_publish_messsage_adapter(this, studentList);
        l_v_student.setAdapter(adapter);

    }
    public  void add_drawer(){

//if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Inbox");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Company's");
        final PrimaryDrawerItem item4= new PrimaryDrawerItem().withIdentifier(4).withName("inbox blog");

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
                        item4,
                        new DividerDrawerItem(),
                        item3

                        )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Fragment fragment = null;
                        Intent i=null;

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
                        if(drawerItem==item4) {
                            //Toast.makeText(getApplicationContext(),"item 3 selected",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), User_inbox_blog.class));
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
        read_data();
    }
    private void read_data() {
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    User_publish_messsage student = student_snapshot.getValue(User_publish_messsage.class);
                    studentList.add(student);
                }


                adapter = new User_publish_messsage_adapter(User_Inbox.this, studentList);
                l_v_student.setAdapter(adapter);
                l_v_student.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



}
