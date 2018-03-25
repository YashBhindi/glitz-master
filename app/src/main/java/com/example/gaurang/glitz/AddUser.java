package com.example.gaurang.glitz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUser extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference newUser;

    EditText editName;
    EditText editemailId;
    EditText editcontact;

    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        newUser = FirebaseDatabase.getInstance().getReference("User");

        btnSubmit=(Button) findViewById(R.id.btn_submit);
        editName = (EditText) findViewById(R.id.txt_name);
        editemailId = (EditText) findViewById(R.id.txt_email);
        editcontact = (EditText) findViewById(R.id.txt_contact);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

    }
    private void addUser(){
        String userName=editName.getText().toString();
        String emailId=editemailId.getText().toString();
        String contact =editcontact.getText().toString();

        if(!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(emailId)  ){
            String id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                    UserRegister userRegister =new UserRegister(id,userName, emailId, contact);
            newUser.child(id).setValue(userRegister);
            Toast.makeText(this ,"The user is registered",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddUser.this, User_Inbox.class));
            finish();
        }
        else{
            Toast.makeText(this ,"All Fields are required..",Toast.LENGTH_SHORT).show();
        }


    }
}
