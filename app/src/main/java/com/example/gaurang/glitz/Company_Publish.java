package com.example.gaurang.glitz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class Company_Publish extends AppCompatActivity {
    CompanyData u;
    ListView l_v_student;

    private EditText e_t_sid, e_t_name, e_t_title,e_t_desc;
    private Button btn_ok;
    DatabaseReference databaseReference_student;
    FirebaseAuth auth;
    List<User_publish_messsage> studentList;
    User_publish_messsage_adapter adapter;
    final long[] count = new long[1];
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__publish);
        e_t_sid = findViewById(R.id.c__id);
        e_t_name = findViewById(R.id.c__name);
        e_t_title = findViewById(R.id.c_title_publish);
        e_t_desc = findViewById(R.id.c_desc_publish);
        btn_ok = findViewById(R.id.ok);
        auth= FirebaseAuth.getInstance();
key=FirebaseAuth.getInstance().getCurrentUser().getUid().toString();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("value");
        u = (CompanyData) bundle.getSerializable("Student_obj");
        databaseReference_student = FirebaseDatabase.getInstance().getReference("Company").child(u.getId()).child("Inbox");
        e_t_sid.setText(key);//firebase auth
        e_t_name.setText(u.getC_name());
        e_t_sid.setEnabled(false);      //id number is already fetched .. no need to edit after this throughout whole
        e_t_name.setEnabled(false);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publish_messege(key,e_t_title.getText().toString().trim(),e_t_desc.getText().toString().trim());
                Toast.makeText(getApplicationContext(),"Messege Published",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), User_Inbox.class));
                finish();

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference_student.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                  //    studentList.clear();
                for (DataSnapshot student_snapshot : dataSnapshot.getChildren()) {
                    User_publish_messsage student = student_snapshot.getValue(User_publish_messsage.class);
                    count[0] =dataSnapshot.getChildrenCount();
                     //studentList.add(student);
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


}
