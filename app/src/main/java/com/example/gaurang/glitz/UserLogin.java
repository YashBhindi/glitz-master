package com.example.gaurang.glitz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserLogin extends AppCompatActivity {

    private EditText e_email, e_pass;
    private FirebaseAuth auth;
    private FirebaseUser user;
    private ProgressBar progressBar;
    private Button b_sign_up, b_login, b_forget_pass;

   /* @Override
    public void onBackPressed() {
        finish();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        auth = FirebaseAuth.getInstance();

        /*
        if (auth.getCurrentUser() != null) {
            this.startActivity(new Intent(this, UserDrawer.class));
            finish();
        }
        */

        setContentView(R.layout.activity_user_login);

        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(t oolbar);*/

        e_email = findViewById(R.id.email);
        e_pass = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);
        b_sign_up = findViewById(R.id.btn_signup);
        b_login = findViewById(R.id.btn_login);
        b_forget_pass = findViewById(R.id.btn_reset_password);

        //
        auth = FirebaseAuth.getInstance();

        b_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, UserSignUp.class));
                finish();
            }
        });
        b_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserLogin.this, UserResetPassword.class));
                finish();
            }
        });
        b_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail_id = e_email.getText().toString().trim();
                final String pass = e_pass.getText().toString().trim();

                if (TextUtils.isEmpty(mail_id)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(mail_id, pass).addOnCompleteListener(UserLogin.this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()) {

                            Toast.makeText(UserLogin.this, "Login Failed", Toast.LENGTH_LONG).show();
                        } else {
                            user=FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()) {
                                Intent intent = new Intent(UserLogin.this, User_Inbox.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(UserLogin.this, "Verify the email to continue", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
            }
        });
    }
}
