package com.example.gaurang.glitz;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public  void loginAdmin(View v){
        startActivity(new Intent(MainActivity.this, AdminLogin.class));
        finish();

    }
    public  void loginUser(View v){
        startActivity(new Intent(MainActivity.this, UserLogin.class));
        finish();
    }
    public  void loginEmp(View v){
        startActivity(new Intent(MainActivity.this, EmpLogin.class));
        finish();
    }

}
