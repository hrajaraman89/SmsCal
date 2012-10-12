package com.apps.smscal.observers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SmsReceivedObserver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int result = getResultCode();

        if (result == Activity.RESULT_OK) {
            Toast.makeText(context, "SMS received!", Toast.LENGTH_SHORT).show();
        }
    }

}
