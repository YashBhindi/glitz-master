package com.example.gaurang.glitz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by yash on 25/3/18.
 */

public class UserAdpterMainBlog extends AppCompatActivity {

    FirebaseAuth auth;
    RecyclerView l_v_student;
    UserCompanyPublishAdptor adapter;
    ArrayList<User_publish_messsage> userlist;
    ListView l;
    DatabaseReference databaseStudent,databaseanother;
    ArrayList<User_publish_messsage> studentList;

    String key;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_company_publish_rv);
        auth=FirebaseAuth.getInstance();
        add_drawer();
        l_v_student = (RecyclerView) findViewById(R.id.b_recycler_view);
        l_v_student.setLayoutManager(new LinearLayoutManager(this));
        userlist = new ArrayList<>();

        auth= FirebaseAuth.getInstance();
        key=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
        databaseStudent = FirebaseDatabase.getInstance().getReference("meet");
        //databaseanother=FirebaseDatabase.getInstance().getReference("User").child();
        studentList = new ArrayList<>();
        //data
        //userlist=userListCollection.getDetails();
        //adapter
        adapter = new UserCompanyPublishAdptor(this, studentList);
        l_v_student.setAdapter(adapter);

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
        RecyclerView recyclerView = findViewById(R.id.c_recycler_view);
        recyclerView.addOnItemTouchListener(
                new OnClickCompanylist(context, new OnClickCompanylist.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        //Toast.makeText(Company_list.this,"list",Toast.LENGTH_SHORT).show();
                        User_publish_messsage s = studentList.get(position);

                        //Toast.makeText(Company_list.this,s.getC_email(),Toast.LENGTH_SHORT).show();

                    }
                })
        );

    }

    private void read_data() {
        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();

                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    //databaseStudent ref = student_snapshot.getValue(DatabaseReference.class);
                    // for(DataSnapshot s2 : student_snapshot.child("Company_msg").getChildren()){
                    User_publish_messsage s=student_snapshot.child("Comapny_msg").getValue(User_publish_messsage.class);
                    studentList.add(s);

                    //}

                }
                // adapter = new sub_list(SubScribe.this, studentList);

                l_v_student.setAdapter(adapter);
                l_v_student.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
