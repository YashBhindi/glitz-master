package com.example.gaurang.glitz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.internal.zzbdd;
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

public class User_Publish extends AppCompatActivity {
User_Data u;
    ListView l_v_student;

    private EditText e_t_sid, e_t_name, e_t_title,e_t_desc;
    private Button btn_ok;
    DatabaseReference databaseReference_student;
    FirebaseAuth auth;
    String key;
    ArrayList<User_publish_messsage> studentList;
    User_publish_messsage_adapter adapter;
    final long[] count = new long[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__publish);
        e_t_sid = findViewById(R.id.s_id);
        e_t_name = findViewById(R.id.s_name);
        e_t_title = findViewById(R.id.title_publish);
        e_t_desc = findViewById(R.id.desc_publish);
        btn_ok = findViewById(R.id.ok);
        auth= FirebaseAuth.getInstance();


        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("value");
        u = (User_Data) bundle.getSerializable("Student_obj");
        databaseReference_student = FirebaseDatabase.getInstance().getReference("User").child(u.getId()).child("Inbox");
add_drawer();
        e_t_sid.setText(u.getId());
        e_t_name.setText(u.getName());
        e_t_sid.setEnabled(false);      //id number is already fetched .. no need to edit after this throughout whole
        e_t_name.setEnabled(false);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publish_messege(u.getId(),e_t_title.getText().toString().trim(),e_t_desc.getText().toString().trim());
                Toast.makeText(getApplicationContext(),"Messege Published",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), User_msg.class));
                finish();

            }
        });

    }
    public  void add_drawer(){

//if you want to update the items at a later time it is recommended to keep it in a variable
        final PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("Inbox");
        final PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("Publish");

        final PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("User");
        final  PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("SubScribe");
        final  PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("Logout");


//create the drawer and remember the `Drawer` result object
        FragmentManager fragmentManager = getSupportFragmentManager();

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
                        new DividerDrawerItem(),
                        item5
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        Fragment fragment = null;
                        Intent i=null;

                        if (drawerItem == item1) {
                            startActivity(new Intent(getApplicationContext(), User_msg.class));
                            finish();
                            return true;
                        }
                        if(drawerItem==item2){
                            startActivity(new Intent(getApplicationContext(),Publish.class));
                            finish();
                            return true;


                        }
                        if(drawerItem==item3){
                            // Toast.makeText(getApplicationContext(),"item 3 selected",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Users.class));
                            finish();
                            return true;


                        }
                        if(drawerItem==item4){
                            // Toast.makeText(getApplicationContext(),"item 3 selected",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),SubScribe.class));
                            finish();
                            return true;


                        }
                        if(drawerItem==item5){

                            auth.signOut();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();

                        }

                        return true;
                    }
                })
                .build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference_student.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
          //      studentList.clear();
                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    User_publish_messsage student = student_snapshot.getValue(User_publish_messsage.class);
                    count[0] =dataSnapshot.getChildrenCount();
                  //  studentList.add(student);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void publish_messege(String id, String e_t_title, String e_t_desc) {
        long k=count[0]+1;
        key="Title"+ Long.toString(k);
            User_msg_by_cpy s1 = new User_msg_by_cpy(id, e_t_title, e_t_desc);
            databaseReference_student.child(key).setValue(s1);

        }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, User_msg.class));
    }



}
