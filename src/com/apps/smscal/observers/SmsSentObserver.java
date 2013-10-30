package com.apps.smscal.observers;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

public class SmsSentObserver extends ContentObserver {

    public SmsSentObserver(Handler handler) {
        super(handler);

    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);

        Log.d("SmsSentOberver", "Message received");

    }
}
