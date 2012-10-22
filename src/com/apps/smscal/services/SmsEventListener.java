package com.apps.smscal.services;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;

import com.apps.smscal.MainActivity;
import com.apps.smscal.model.CalendarInfo;
import com.apps.smscal.observers.SmsReceivedObserver;
import com.apps.smscal.observers.SmsSentObserver;

public class SmsEventListener extends Service {
    private static final String RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String SMS_OUT = "content://sms";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        CalendarInfo info = (CalendarInfo) intent
                .getSerializableExtra(MainActivity.CALENDAR_INFO);

        EventAdder adder = new CalendarEventAdder(info, getContentResolver());
        startObservers(adder);
        return startId;
    }

    private void startObservers(EventAdder adder) {
        this.startReceiveObserver(adder);
        this.startSendObservers();
    }

    private void startReceiveObserver(EventAdder adder) {
        registerReceiver(new SmsReceivedObserver(adder), new IntentFilter(
                RECEIVED));
    }

    private void startSendObservers() {
        ContentResolver resolver = getBaseContext().getContentResolver();
        resolver.registerContentObserver(Uri.parse(SMS_OUT), true,
                new SmsSentObserver(new Handler(), this));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
