package com.apps.smscal.services;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import com.apps.smscal.observers.SmsReceivedObserver;
import com.apps.smscal.observers.SmsSentObserver;

public class SmsEventListener extends Service {
    private static final String RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String SMS_OUT = "content://sms";

    @Override
    public void onCreate() {
        Toast.makeText(getBaseContext(), "Creating service", Toast.LENGTH_SHORT)
                .show();
        startObservers();
    }

    private void startObservers() {
        this.startReceiveObserver();
        this.startSendObservers();
    }

    private void startReceiveObserver() {
        Toast.makeText(getBaseContext(), "Starting sms_receive",
                Toast.LENGTH_SHORT).show();
        registerReceiver(new SmsReceivedObserver(), new IntentFilter(RECEIVED));
    }

    private void startSendObservers() {
        ContentResolver resolver = getBaseContext().getContentResolver();
        resolver.registerContentObserver(Uri.parse(SMS_OUT), true,
                new SmsSentObserver(new Handler(), this));
        Toast.makeText(getBaseContext(), "Starting sms_send",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
