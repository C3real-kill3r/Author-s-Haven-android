package com.example.kamran.logingreentheme.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kamran.logingreentheme.R;
import com.example.kamran.logingreentheme.fragments.AboutFragment;
import com.example.kamran.logingreentheme.fragments.DiscoverFragment;
import com.example.kamran.logingreentheme.fragments.EventsFragment;
import com.example.kamran.logingreentheme.fragments.HomeFragment;
import com.example.kamran.logingreentheme.fragments.ProfileFragment;
import com.pkmmte.view.CircularImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mToggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        CircularImageView circularImageView = headerView.findViewById(R.id.imgHeader);
        circularImageView.setBorderColor(getResources().getColor(R.color.grey));
        circularImageView.setBorderWidth(10);
        circularImageView.setSelectorColor(getResources().getColor(R.color.headings));
        circularImageView.setSelectorStrokeColor(getResources().getColor(R.color.colorPrimaryDark));
        circularImageView.setSelectorStrokeWidth(10);
        circularImageView.addShadow();
        TextView drawerUser = headerView.findViewById(R.id.usHeader);
        TextView drawerAccount = headerView.findViewById(R.id.emHeader);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String retrievedEmail  = preferences.getString("EMAIL",null);
        String retrievedUsername = preferences.getString("USERNAME", null);
        String retrievedImage = preferences.getString("profImage",null);
        drawerAccount.setText(retrievedEmail);
        drawerUser.setText(retrievedUsername);
        Picasso.get().load(retrievedImage).placeholder(R.drawable.prof).into(circularImageView);



        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.home);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;
            case R.id.about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutFragment()).commit();
                break;
            case R.id.settings:
                Toast.makeText(this, "Currently Undergoing Maintenance", Toast.LENGTH_SHORT).show();
                break;
            case R.id.event:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new EventsFragment()).commit();
                Toast.makeText(this, "Currently Undergoing Maintenance", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                final SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setTitle("Logout");
                alert.setMessage("Are you sure you want to logout?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        preferences.edit().putString("TOKEN","invalid").apply();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        });

                alert.show();
                break;
            case R.id.search:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new DiscoverFragment()).commit();
                break;
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
