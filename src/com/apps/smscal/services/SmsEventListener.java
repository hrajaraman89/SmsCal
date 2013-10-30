package com.apps.smscal.services;

import android.content.ContentResolver;
import android.content.ContextWrapper;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;

import com.apps.smscal.model.CalendarInfo;
import com.apps.smscal.observers.SmsReceivedObserver;
import com.apps.smscal.observers.SmsSentObserver;

public class SmsEventListener implements UpdateListener {
    private static final String RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String SMS_OUT = "content://sms";

    private ContextWrapper wrapper;
    private CalendarEventAdder adder;

    public SmsEventListener(ContextWrapper wrapper, ContentResolver resolver) {
        this.setWrapper(wrapper);
        this.adder = new CalendarEventAdder(resolver);
        startObservers();
    }

    private void startObservers() {
        this.startReceiveObserver();
        this.startSendObservers();
    }

    private void startReceiveObserver() {
        wrapper.registerReceiver(new SmsReceivedObserver(adder),
                new IntentFilter(RECEIVED));
    }

    private void startSendObservers() {
        ContentResolver resolver = wrapper.getBaseContext()
                .getContentResolver();
        resolver.registerContentObserver(Uri.parse(SMS_OUT), true,
                new SmsSentObserver(new Handler(), resolver, adder));
    }

    public ContextWrapper getWrapper() {
        return wrapper;
    }

    private void setWrapper(ContextWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void onUpdate(CalendarInfo info) {
        adder.setCalendarInfo(info);
    }

}
