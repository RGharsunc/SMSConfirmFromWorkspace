package com.example.vardan.smsconfirm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;



public class MySMSReceiver extends BroadcastReceiver {

    private static SmsListener mListener;
    String receivedSMS="This is code for confirmation: ";


    @Override
    public void onReceive(Context context, Intent intent) {


        try {
            if (intent.getExtras() != null) {
                final Object[] pdus = (Object[]) intent.getExtras().get("pdus");
                SmsMessage sms = SmsMessage.createFromPdu((byte[]) pdus[0]);

                for (int i = 0; i < pdus.length; ++i) {
                    receivedSMS += sms.getDisplayMessageBody();
                }
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(context, receivedSMS, duration);
                toast.show();
                toast.show();

                mListener.messageReceived(receivedSMS);


            }
        } catch (Exception e) {
            receivedSMS = "exception found";
        }
    }


    public static void setSMSListener(SmsListener smsListener) {
        mListener = smsListener;
    }

}