package com.apps.smscal.observers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.apps.smscal.model.EventInfo;
import com.apps.smscal.model.EventInfoCreator;

public class SmsReceivedObserver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int result = getResultCode();

        if (result == Activity.RESULT_OK) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                EventInfo info = EventInfoCreator.makeEventInfoFromBundle(
                        bundle, context);

                Toast.makeText(context, info.toString(), Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}
