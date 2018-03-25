package com.example.gaurang.glitz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEmp extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference newEmp;


    EditText editemailId;
    EditText editcontactPersonName;
    EditText editcompanyDescription;
    EditText editservices;
    EditText editcontact;
    EditText editkey;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_emp);



        btnSubmit=(Button) findViewById(R.id.btn_submit);

        editemailId = (EditText) findViewById(R.id.txt_email);
        editcontactPersonName = (EditText) findViewById(R.id.txt_pname);
        editcompanyDescription = (EditText) findViewById(R.id.txt_cd);
        editservices = (EditText) findViewById(R.id.txt_service);
        editcontact = (EditText) findViewById(R.id.txt_pno);
        editkey = (EditText) findViewById(R.id.txt_key);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key=editkey.getText().toString();
                newEmp = FirebaseDatabase.getInstance().getReference("Company").child(key).child("dept");

                addEmp();
            }
        });

    }
    private void addEmp(){

        //String key=editkey.getText().toString();
        String emailId=editemailId.getText().toString();
        String contactPersonName=editcontactPersonName.getText().toString();
        String companyDescription=editcompanyDescription.getText().toString();
        String services=editservices.getText().toString();
        String contact =editcontact.getText().toString();

        if(!TextUtils.isEmpty(contact) && !TextUtils.isEmpty(emailId)  && !TextUtils.isEmpty(services) && !TextUtils.isEmpty(contactPersonName) && !TextUtils.isEmpty(companyDescription)){
            String id = newEmp.push().getKey();
            EmpRegister empRegister =new EmpRegister(id,  emailId, contactPersonName, companyDescription, services, contact,"");
            newEmp.child(id).setValue(empRegister);
            Toast.makeText(this ,"The department is registered",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddEmp.this, User_msg.class));
            finish();
        }
        else{
            Toast.makeText(this ,"All Fields are required..",Toast.LENGTH_SHORT).show();
        }


    }
}
