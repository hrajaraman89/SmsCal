package com.apps.smscal.model;

import java.util.Calendar;
import java.util.TimeZone;

import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class EventInfoCreator {
    private static final String FROM = "from";

    public static EventInfo makeEventInfoFromBundle(Bundle bundle,
            Context context) {

        Object[] pdus = (Object[]) bundle.get("pdus");
        return makeEventInfoFromPdu((byte[]) pdus[0]);
    }

    private static EventInfo makeEventInfoFromPdu(byte[] pdu) {
        SmsMessage message = SmsMessage.createFromPdu(pdu);

        String description = message.getMessageBody();
        String title = FROM + ": " + message.getOriginatingAddress();

        long startTimeMillis = message.getTimestampMillis();
        Calendar startTime = Calendar.getInstance();
        startTime.setTimeInMillis(startTimeMillis);
        Calendar endTime = startTime;

        EventInfo info = EventInfo.makeInstance().setDescription(description)
                .setTitle(title).setStartTime(startTime).setEndTime(endTime)
                .setTimeZone(TimeZone.getDefault().getDisplayName());

        return info;
    }

}