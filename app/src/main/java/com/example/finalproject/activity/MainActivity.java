package com.example.finalproject.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.finalproject.R;
import com.example.finalproject.fragment.ChatFragment;
import com.example.finalproject.fragment.CustomeDialogeFragment;

import com.example.finalproject.fragment.HomeFragment;
import com.example.finalproject.fragment.ParticipantFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
//    NavController navController;
//    AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initViews();

        changeFragment(new HomeFragment());

        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setDisplayHomeAsUpEnabled(true);

//        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navigationView, navController);
//        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }




    public void showCustomDialog() {

        Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.fragment_custome_dialoge);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialoge_corner));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        getSupportFragmentManager()
                .beginTransaction()
                .add(new CustomeDialogeFragment(), "CustomDialog")
                .commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_end, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.end) {

            showCustomDialog();
        } else if (id == android.R.id.home) {
            drawerLayout.open();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        frameLayout=findViewById(R.id.continer);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
//        appBarConfiguration =
//                new AppBarConfiguration.Builder(R.id.homeFragment, R.id.participantFragment)
//                        .setDrawerLayout(drawerLayout)
//                        .build();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id==R.id.chat){
                changeFragment(new ChatFragment());
            }

            return false;
        });

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id==R.id.participantFragment){
                changeFragment(new ParticipantFragment());
            }else if(id==R.id.homeFragment){
                changeFragment(new HomeFragment());
            }


            drawerLayout.close();
            drawerLayout.closeDrawers();
            return false;
        });
    }

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.continer, fragment)
                .commit();
    }


//    @Override
//    public boolean onSupportNavigateUp() {
//        return NavigationUI.navigateUp(navController, appBarConfiguration);
//    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);

        else

            super.onBackPressed();
    }








}
