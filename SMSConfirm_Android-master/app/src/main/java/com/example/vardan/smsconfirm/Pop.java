package com.example.vardan.smsconfirm;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.RECEIVE_SMS;


public class Pop extends Activity{

    final int PERMISSION_REQUEST_CODE=200;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_activity);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int) (width*.8),(int) (height*.4));

        Button confButton=findViewById(R.id.popupConfitmButton);
        confButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(Pop.this, new String[]{RECEIVE_SMS}, PERMISSION_REQUEST_CODE);}


        });



    }

    @Override

    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        switch (permsRequestCode){
            case PERMISSION_REQUEST_CODE:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.RECEIVE_SMS)
                            == PackageManager.PERMISSION_GRANTED) {

                        Intent i = new Intent(getApplicationContext(), IntentActivity.class);
                        startActivity(i);
                        //do whatever u want to do
                    }

                }{

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
//                    startActivity(new Intent(IntentActivity.this,Pop.class));


                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(), IntentActivity.class);
                    startActivity(i);

                }
                return;
            }
        }


    }}
