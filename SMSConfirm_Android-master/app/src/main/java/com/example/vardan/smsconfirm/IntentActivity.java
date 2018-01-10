package com.example.vardan.smsconfirm;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.RECEIVE_SMS;

public class IntentActivity extends AppCompatActivity {

    final int PERMISSION_REQUEST_CODE=200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        setTitle(R.string.titleIntentActivity);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                String sharedText = handleSendText(intent);
               TextView textView = findViewById(R.id.sharedText);
                textView.setText(sharedText);
            }
        }

        Button confirmButton = findViewById(R.id.toConfirmation);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ConfirmActivity.class);
                startActivity(i);
            }
        });


    }


    String handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            return sharedText;
        }
        return "text is not received";

    }





}
