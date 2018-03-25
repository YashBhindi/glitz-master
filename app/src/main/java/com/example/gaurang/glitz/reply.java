package com.example.gaurang.glitz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class reply extends AppCompatActivity {
Button ok;
EditText id,name,reply;
String r_id,r_name,r_reply;
FirebaseAuth auth;
DatabaseReference databaseReference_student;
String key,cuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        ok=findViewById(R.id.r_ok);
        id=findViewById(R.id.r_id);
        name=findViewById(R.id.r_name);
        reply=findViewById(R.id.r_replay);
        r_id=id.getText().toString().trim();
        r_name=name.getText().toString().trim();
        r_reply=reply.getText().toString().trim();
User_publish_messsage u;
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        u = (User_publish_messsage) bundle.getSerializable("u_p_m");
        key= (String) bundle.get("key");
        cuser= (String) bundle.get("cuser");
        databaseReference_student = FirebaseDatabase.getInstance().getReference("meet").child(key).child("reply");
        //add_drawer();
        id.setText(cuser);
        name.setText(u.getCompany());
        id.setEnabled(false);      //id number is already fetched .. no need to edit after this throughout whole
        name.setEnabled(false);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r_reply=reply.getText().toString().trim();
                                publish_messege(r_reply,cuser,cuser);
                Toast.makeText(getApplicationContext(),"Messege Published",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), User_inbox_blog.class));
                finish();

            }
        });

    }

    public void publish_messege(String id, String cuser, String reply) {
//            long k=count[0]+1;
  //          key="Title"+ Long.toString(k);
            User_msg_by_cpy s1 = new User_msg_by_cpy(reply,id, cuser);
            databaseReference_student.child(key).setValue(s1);


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


}
