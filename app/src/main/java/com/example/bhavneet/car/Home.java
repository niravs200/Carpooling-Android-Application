package com.example.bhavneet.car;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar=null;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button btnUser = findViewById(R.id.btnUser);
        Button btnDriver = findViewById(R.id.btnDriver);
        Intent in = getIntent();
        name = in.getStringExtra("username");

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent myintent = new Intent(Home.this, UserActivity1.class);
                    myintent.putExtra("username", name);
                    startActivity(myintent);

                } catch (Exception e) {
                    Toast.makeText(Home.this, "Check", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent myintent = new Intent(Home.this, DriverActivity1.class);
                    myintent.putExtra("username", name);
                    startActivity(myintent);

                } catch (Exception e) {
                    Toast.makeText(Home.this, "Check", Toast.LENGTH_SHORT).show();
                }
            }
        });

        drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //@SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.

        int id=item.getItemId();
        item.setChecked(true);
        switch (id){


            case R.id.nav_userhome:
                Intent i= new Intent(Home.this,UserActivity1.class);
                startActivity(i);
                break;
            case R.id.nav_driverhome:
                Intent g= new Intent(Home.this,DriverActivity1.class);
                startActivity(g);
                break;
            case R.id.nav_help:
                Intent s= new Intent(Home.this,Help.class);
                startActivity(s);
                break;
            case R.id.nav_tools:
                Intent t= new Intent(Home.this,Login.class);
                Toast.makeText(Home.this, "You are being logged out. Kindly Login again!", Toast.LENGTH_SHORT).show();

                startActivity(t);
                break;


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
