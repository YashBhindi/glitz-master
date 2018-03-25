package com.example.gaurang.glitz;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class Publish extends AppCompatActivity {

    ListView listView;
    List<UserData> userlist;
    DatabaseReference databaseStudent;
    String cuser;
    List<CompanyRegister> studentList;
    SharedPreferences sharedPreferences;

    FirebaseAuth auth;

    String key,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        final EditText titlevalue=(EditText)findViewById(R.id.title_value);
        final EditText desvalue=(EditText)findViewById(R.id.des_value);
        Button send=(Button)findViewById(R.id.send);
        cuser= FirebaseAuth.getInstance().getCurrentUser().getEmail();
         Toast.makeText(getApplicationContext(),key,Toast.LENGTH_LONG).show();
        databaseStudent = FirebaseDatabase.getInstance().getReference("Admin_msg");
        add_drawer();
        send.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                String title=titlevalue.getText().toString().trim();

                String desc=desvalue.getText().toString().trim();
                add_data(title,desc);
                startActivity(new Intent(getApplicationContext(), SubScribe.class));
                finish();
            }
        });

    }

  //  @TargetApi(Build.VERSION_CODES.N)
   // @RequiresApi(api = Build.VERSION_CODES.N)
    /*private String find(String cuser) {
        String id = "hello";
        /*List<CompanyRegister> result = studentList.stream()
                .filter(a -> Objects.equals(a.getCuser(), cuser))
                .collect(Collectors.toList());
*/
        /*for (CompanyRegister s : studentList) {
            if (s.getCuser().contains(cuser)) {
                return s.getId();
            }
        }
        return "hello";

    }*/

   /* @Override
    protected void onStart() {
        super.onStart();

        databaseStudent.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                studentList.clear();
                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    CompanyRegister student = student_snapshot.getValue(CompanyRegister.class);
                    if (cuser.equals(student.getCuser())) {
                        //studentList.add(student);
                        sharedPreferences = getApplicationContext().getSharedPreferences("name", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", string1);
                        editor.apply();
                    }
                    for (CompanyRegister s : studentList) {
                        if (s.getCuser().contains(cuser)) {
                            key=s.getId();
                        }
                    }

                    //studentList.add(student);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/


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
    private void add_data(String title_ip, String desc_ip) {
        String id = databaseStudent.push().getKey();
        Publish_Msg s1 = new Publish_Msg(title_ip,desc_ip);
        databaseStudent.child(id).setValue(s1);
        // Log.i("MainActivity", "id: " + s_id_ip + " mob. no.: " + mobile_no_ip + " fees: " + fees_ip + " pickup_point: " + pickup_point_ip + ".");

        //  Toast.makeText(getApplicationContext(), "Student added sucessfully.", Toast.LENGTH_SHORT).show();

    }
}
