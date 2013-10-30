package com.apps.smscal.observers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.apps.smscal.model.EventInfo;
import com.apps.smscal.model.EventInfoCreator;
import com.apps.smscal.services.EventAdder;

public class SmsReceivedObserver extends BroadcastReceiver {
    private static final String TAG = SmsReceivedObserver.class.getSimpleName();

    private EventAdder adder;

    public SmsReceivedObserver(EventAdder adder) {
        this.adder = adder;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        int result = getResultCode();

        if (result == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                EventInfo info = EventInfoCreator.makeEventInfoFromBundle(
                        bundle, context);

                Log.d(TAG, "Message received: " + info);

                adder.add(info);
            }
        }
    }
}
