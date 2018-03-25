package com.example.gaurang.glitz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserEmailVerify extends AppCompatActivity {

    private FirebaseUser user;
    private Button b_send, b_continue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_email_verify);
        b_send = (Button) findViewById(R.id.btn_send);
        b_continue = (Button) findViewById(R.id.btn_continue);
        user = FirebaseAuth.getInstance().getCurrentUser();


        b_send.setOnClickListener(new View.OnClickListener() {
            //implement the onClick method of interface
            @Override
            public void onClick(View v) {
                //code for  send the verification mail to the user
                b_send.setEnabled(false);
                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        b_send.setEnabled(true);
                        if (task.isSuccessful()) {
                            //if mail sent
                            Toast.makeText(UserEmailVerify.this, "Verification mail sent to the " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(UserEmailVerify.this, "Failed to sent the verification mail", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        b_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //code to check the mail is verified
                user.reload().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (user.isEmailVerified()) {
                            //if verification is done then trasfer to the pass activity
                            startActivity(new Intent(UserEmailVerify.this, AddUser.class));
                            finish();
                            return;
                        }
                        else{
                            Toast.makeText(UserEmailVerify.this, "Please verify your email address", Toast.LENGTH_SHORT).show();
                        }

                    }

                });
            }

        });



    }
}
