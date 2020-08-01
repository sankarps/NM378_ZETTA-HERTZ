package com.example.agrolens.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agrolens.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {
    LinearLayout crop_search,crop_find,crop_detect,crop_AR,crop_details,crop_live;
    TextView displayName;
    ImageView languagechange;
    DatabaseReference reference3,reference4;
    FirebaseDatabase database;
    FirebaseAuth auth;
    FirebaseUser user;
    String username;
    String uid;
    String langs="en";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        LoadLocale();
        auth= FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        uid=user.getUid();
        database=FirebaseDatabase.getInstance();
        reference4=database.getReference("Users");

        crop_search=view.findViewById(R.id.cropsearch);
        crop_find=view.findViewById(R.id.findlinear);
        languagechange=view.findViewById(R.id.change_language);
        crop_detect=view.findViewById(R.id.detectclick);
        crop_AR=view.findViewById(R.id.ar_scene);
        crop_details=view.findViewById(R.id.linearcropdeatils);
        displayName=view.findViewById(R.id.displayname);
        crop_live=view.findViewById(R.id.live_predict);
        crop_live.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("com.example.android.tflitecamerademo");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(getActivity(), "There is no package available in android", Toast.LENGTH_LONG).show();
                }

            }
        });
        crop_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        crop_AR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage("uk.co.appoly.sceneform_example");
                if (launchIntent != null) {
                    startActivity(launchIntent);
                } else {
                    Toast.makeText(getActivity(), "There is no package available in android", Toast.LENGTH_LONG).show();
                }
            }
        });
        crop_detect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        languagechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] listItem={"English","हिन्दी","தமிழ்"};
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Choose Language");
                mBuilder.setSingleChoiceItems(listItem, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(i==0){
                            setLocale("en");

                            getActivity().recreate();

                        }
                        else if(i==1){
                            setLocale("hi");

                            getActivity().recreate();

                        }
                        else if(i==2){
                            setLocale("ta");

                            getActivity().recreate();

                        }
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog=mBuilder.create();
                dialog.show();
            }
        });
        crop_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        crop_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Crop_Details.class);
                intent.putExtra("lang",langs);
                startActivity(intent);
            }
        });



        FirebaseDatabase database=FirebaseDatabase.getInstance();

        reference3=database.getReference("Users");

        reference3.child(uid).child("userdetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username=snapshot.child("name").getValue().toString();
                displayName.setText(username.toLowerCase());




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        langs=lang;


        Locale.setDefault(locale);
        Configuration configuration=new Configuration();
        configuration.locale=locale;
        getActivity().getBaseContext().getResources().updateConfiguration(configuration,getActivity().getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getActivity().getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang",lang);
        editor.apply();
    }


    public void LoadLocale(){
        SharedPreferences preferences=getActivity().getSharedPreferences("Settings", MODE_PRIVATE);
        String language=preferences.getString("My_Lang","");
        setLocale(language);
    }
}