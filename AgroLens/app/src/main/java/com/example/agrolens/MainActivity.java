package com.example.agrolens;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.example.agrolens.LoginandRegister.Login;
import com.example.agrolens.LoginandRegister.Register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ImageView logoimg,teamlogoimg;
    private Button regbut,logbut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadLocale();
        logoimg=findViewById(R.id.logo);
        teamlogoimg=findViewById(R.id.teamlogo);
        regbut=findViewById(R.id.reg);
        logbut=findViewById(R.id.log);
        mAuth = FirebaseAuth.getInstance();


        final Animation vecl= (Animation) AnimationUtils.loadAnimation(MainActivity.this,R.anim.mytransition);
        final Animation vec2=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);
        logoimg.startAnimation(vecl);
        teamlogoimg.startAnimation(vecl);
        regbut.setAnimation(vecl);
        logbut.setAnimation(vecl);


        Thread timer =new Thread(){
            public void run(){
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {

                }

            }
        };
        timer.start();
        regbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
                finish();

            }
        });
        logbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
      //  FirebaseUser currentUser = mAuth.getCurrentUser();
        //if(currentUser != null){
          //  startActivity(new Intent(MainActivity.this, Dashboard.class));
            //finish();


        //}
    }

    public void languageselect(View view) {
        final String[] listItem={"English","हिन्दी","தமிழ்"};
        AlertDialog.Builder mBuilder=new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(i==0){
                    setLocale("en");
                    recreate();

                }
                else if(i==1){
                    setLocale("hi");
                    recreate();

                }
                else if(i==2){
                    setLocale("ta");
                    recreate();

                }
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog=mBuilder.create();
        dialog.show();
    }

    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }
    public void LoadLocale(){
        SharedPreferences preferences=getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language=preferences.getString("My_Lang","");
        setLocale(language);
    }
}