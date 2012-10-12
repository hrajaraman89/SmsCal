package com.apps.smscal.observers;

import android.content.ContextWrapper;
import android.database.ContentObserver;
import android.os.Handler;
import android.widget.Toast;

public class SmsSentObserver extends ContentObserver {
    private ContextWrapper wrapper;

    public SmsSentObserver(Handler handler) {
        super(handler);
    }

    public SmsSentObserver(Handler handler, ContextWrapper wrapper) {
        super(handler);

        this.wrapper = wrapper;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Toast.makeText(wrapper.getBaseContext(), "SMS sent!",
                Toast.LENGTH_SHORT).show();
    }
}
