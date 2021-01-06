package com.example.finalproject.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.finalproject.R;
import com.example.finalproject.fragment.ChatFragment;
import com.example.finalproject.fragment.CustomeDialogeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    NavController navController;
    AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavigationView;
    FrameLayout continer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();


        appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.homeFragment, R.id.participantFragment)
                        .setDrawerLayout(drawerLayout)
                        .build();
        NavigationUI.setupActionBarWithNavController(MainActivity.this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

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
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment);
        bottomNavigationView=findViewById(R.id.bottomNavigation);
        continer=findViewById(R.id.continerr);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);

        else

            super.onBackPressed();
    }
}

