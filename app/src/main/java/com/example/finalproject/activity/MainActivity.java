package com.example.finalproject.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.fragment.app.Fragment;

import com.example.finalproject.R;
import com.example.finalproject.fragment.mainFragment.ChatFragment;
import com.example.finalproject.fragment.mainFragment.CustomeDialogeFragment;

import com.example.finalproject.fragment.mainFragment.HomeFragment;
import com.example.finalproject.fragment.mainFragment.ParticipantFragment;
import com.example.finalproject.fragment.signFragment.SignInFragment;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    //    NavController navController;
//    AppBarConfiguration appBarConfiguration;
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    boolean pressMute =true;
    boolean pressVideo=true;
    String Nickname;
    private Socket socket;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // mSocket.connect();
        setContentView(R.layout.activity_main);

        Nickname= (String)getIntent().getExtras().getString(UserActivity.NICKNAME);


        try {
            socket = IO.socket("https://flannel-poutine-04705.herokuapp.com");
            socket.connect();
            socket.emit("join", Nickname);
        } catch (URISyntaxException e) {
            e.printStackTrace();

        }
        initViews();

        //implementing socket listeners
        socket.on("userjoinedthechat", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];

                        Toast.makeText(MainActivity.this,data,Toast.LENGTH_SHORT).show();
                        Log.i(TAG, "run: "+data);

                    }
                });
            }
        });
        //to start with home fragment
        changeFragment(new HomeFragment());

        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionbar.setDisplayHomeAsUpEnabled(true);
        toggelBottomNavigationFragment();
        toggelDrawerLayout();
       // bottomNavigationView.setItemTextColor(ColorStateList.valueOf(Color.RED));

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

        addFragment(new CustomeDialogeFragment());
        //getSupportFragmentManager()
       //         .beginTransaction()
         //       .add(new CustomeDialogeFragment(), "CustomDialog")
         //       .commit();

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




    }

    private void toggelBottomNavigationFragment(){

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id==R.id.chat){
                changeFragment(new ChatFragment());
             //   startActivity(new Intent(getApplicationContext(),ChatActivity.class));

            }else if(id==R.id.unmute&& pressMute){
                item.setIcon(R.drawable.ic_mute);
                item.setTitle("Unmute");
                pressMute =false;

            }else if(id==R.id.unmute&&!pressMute){
                item.setIcon(R.drawable.ic_unmute);
                item.setTitle("Mute");
                pressMute =true;
            }
            else if(id==R.id.stop_video&&pressVideo){
                item.setIcon(R.drawable.ic_video_stop);
                item.setTitle("Start Video");
                pressVideo=false;
            }else if(id==R.id.stop_video&&!pressVideo){
                item.setIcon(R.drawable.ic_video_start);
                item.setTitle("Stop Video");
                pressVideo=true;
            }else if (id==R.id.hand){
                    try {
                        socket = IO.socket("https://flannel-poutine-04705.herokuapp.com");
                        socket.connect();
                        Nickname= getIntent().getExtras().getString(SignInFragment.NICKNAME);

                        socket.emit("join", Nickname);
                        socket.emit("messagedetection",Nickname,"\u270B\uFE0F");

                    } catch (URISyntaxException e) {
                        e.printStackTrace();

                    }
                    Toast.makeText(getApplicationContext(),"Raise Hand",Toast.LENGTH_LONG).show();




            }

            return false;

        });

    }

    private void toggelDrawerLayout(){
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

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(fragment,"")
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


