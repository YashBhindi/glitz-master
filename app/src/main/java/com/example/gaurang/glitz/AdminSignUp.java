package com.example.gaurang.glitz;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class AdminSignUp extends AppCompatActivity {

    private EditText e_email, e_pass;
    private Button b_sign_in;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    private  Button b_next;
    TextView textView_Confirm;
    private static  FirebaseUser user;

    String mail_id,passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        b_sign_in = findViewById(R.id.btn_sign_in);
        e_email = findViewById(R.id.email);
        e_pass = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        b_next = findViewById(R.id.btn_next);
        auth=FirebaseAuth.getInstance();


        b_sign_in.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(AdminSignUp.this, AdminLogin.class));
                finish();
            }
        });




        b_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mail_id = e_email.getText().toString().trim();
                passwd = e_pass.getText().toString().trim();

                if (TextUtils.isEmpty(mail_id)) {
                    Toast.makeText(getApplicationContext(), "Please, Enter your email address.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwd)) {
                    Toast.makeText(getApplicationContext(), "Please, Enter your password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (passwd.length() < 6) {
                    Toast.makeText(getApplicationContext(), "password length must graer than 6 characters.", Toast.LENGTH_SHORT).show();
                    return;
                }

                //to hide keyboard
                InputMethodManager inputMgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                EditText editText = (EditText) findViewById(R.id.password);
                inputMgr.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                //end code of hide key board

                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(mail_id, passwd).addOnCompleteListener(AdminSignUp.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(), "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(AdminSignUp.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_LONG).show();
                        } else {
                            startActivity(new Intent(AdminSignUp.this, AdminEmailVerify.class));
                            finish();
                        }
                    }

                });

            }

        });

    }

    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}


