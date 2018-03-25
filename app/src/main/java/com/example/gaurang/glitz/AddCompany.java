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

public class AddCompany extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference newCompany;

    EditText editCIN;
    EditText editcompanyName;
    EditText editcompanyWebsite;
    EditText editemailId;
    EditText editcontactPersonName;
    EditText editcompanyDescription;
    EditText editservices;
    EditText editcontact;
    FirebaseAuth auth;

    Button btnSubmit;
    String cuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);
        cuser=FirebaseAuth.getInstance().getCurrentUser().getEmail();
        newCompany = FirebaseDatabase.getInstance().getReference("Company");
        btnSubmit=(Button) findViewById(R.id.btn_submit);
        editCIN = (EditText) findViewById(R.id.txt_cin);
        editcompanyName = (EditText) findViewById(R.id.txt_cname);
        editcompanyWebsite = (EditText) findViewById(R.id.txt_cwebsite);
        editemailId = (EditText) findViewById(R.id.txt_email);
        editcontactPersonName = (EditText) findViewById(R.id.txt_pname);
        editcompanyDescription = (EditText) findViewById(R.id.txt_cd);
        editservices = (EditText) findViewById(R.id.txt_service);
        editcontact = (EditText) findViewById(R.id.txt_pno);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCompany();
            }
        });

    }


    private void addCompany(){
        String CIN=editCIN.getText().toString();
        String companyName=editcompanyName.getText().toString();
        String companyWebsite=editcompanyWebsite.getText().toString();
        String emailId=editemailId.getText().toString();
        String contactPersonName=editcontactPersonName.getText().toString();
        String companyDescription=editcompanyDescription.getText().toString();
        String services=editservices.getText().toString();
        String contact =editcontact.getText().toString();
        //String cuser=auth.getCurrentUser().toString().trim();

        if(!TextUtils.isEmpty(CIN) && !TextUtils.isEmpty(companyName) && !TextUtils.isEmpty(companyWebsite)&& !TextUtils.isEmpty(contact) && !TextUtils.isEmpty(emailId)  && !TextUtils.isEmpty(services) && !TextUtils.isEmpty(contactPersonName) && !TextUtils.isEmpty(companyDescription)){
            String id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
            CompanyRegister companyRegister =new CompanyRegister(id, CIN, companyName, companyWebsite, emailId, contactPersonName, companyDescription, services, contact,cuser,"12","12");
            newCompany.child(id).setValue(companyRegister);
            Toast.makeText(this ,"The company is registered",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddCompany.this, User_msg.class));
            finish();
        }
        else{
            Toast.makeText(this ,"All Fields are required..",Toast.LENGTH_SHORT).show();
        }


    }
}
