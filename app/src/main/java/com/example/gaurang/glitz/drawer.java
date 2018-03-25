package com.example.gaurang.glitz;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class drawer extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        add_drawer();
        auth=FirebaseAuth.getInstance();

    }

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

}
