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
//import android.util.Log;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import static android.Manifest.permission.RECEIVE_SMS;

//import java.util.regex.Pattern;

public class ConfirmActivity extends AppCompatActivity {

    final int PERMISSION_REQUEST_CODE=200;

    TextView textView;
    TextView editText;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

//        Log.d("tag","ttttttttttttt");
//        String str="123456789";
//        Log.d("tag1",str.substring(str.length()-1,str.length()));
//        Log.d("tag1",str.substring(5,6));

        setTitle(R.string.confActivTitle);
        textView = findViewById(R.id.receivedMessage);
        editText = findViewById(R.id.messageFromBroadcast);
        button = findViewById(R.id.confirmButton);

        MySMSReceiver.setSMSListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText) {
                if (compareMessage(messageText)){
                    editText.setText(messageText.substring(messageText.length()-6,messageText.length()));
                Log.d("tag",messageText);
                }

            }
        });
        if(Build.VERSION.SDK_INT>=23){
            ActivityCompat.requestPermissions(this, new String[]{RECEIVE_SMS}, PERMISSION_REQUEST_CODE);
        }



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

                        //do whatever u want to do
                        Toast.makeText(this, "permission allowed", Toast.LENGTH_LONG).show();

                    }

                }else
                {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    startActivity(new Intent(ConfirmActivity.this,Pop.class));

                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }

    }







//    private boolean compareCode(String str, EditText editText) {
//        if(compareChSeqs(str,editText.getText().toString())){
//            textView.setText("the confirmation is passed");
//            MySMSReceiver.receivedSMS="";
//            return true;
//        }
//        MySMSReceiver.receivedSMS="";
//
//        return false;
//    }
//



    private boolean compareMessage(String message) {
        String sub=message.substring(message.length()-6,message.length());
        String messageStr=message.substring(0,message.length()-6);

        Log.d("tag",messageStr);
        if (Pattern.matches("\\d\\d\\d\\d\\d\\d",sub) && messageStr.equals("This is code for confirmation: ")){
            Log.d("tag","Pattern matches && message is same");

            return true;
        }
        return false;
    }



}
