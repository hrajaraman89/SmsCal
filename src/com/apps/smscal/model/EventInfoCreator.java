package com.apps.smscal.model;

import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsMessage;

import com.apps.smscal.utils.PhoneNumberTranslator;

public class EventInfoCreator {
    private static final String FROM = "SMS from ";

    public static EventInfo makeEventInfoFromBundle(Bundle bundle,
            Context context) {

        Object[] pdus = (Object[]) bundle.get("pdus");
        return makeEventInfoFromPdu((byte[]) pdus[0],
                context.getContentResolver());
    }

    private static EventInfo makeEventInfoFromPdu(byte[] pdu,
            ContentResolver resolver) {
        SmsMessage message = SmsMessage.createFromPdu(pdu);

        String description = message.getMessageBody();

        String phoneNumber = message.getOriginatingAddress();
        String title = FROM
                + PhoneNumberTranslator.getName(resolver, phoneNumber);

        long startTime = message.getTimestampMillis();
        long endTime = startTime;

        EventInfo info = EventInfo.makeInstance().setDescription(description)
                .setTitle(title).setStartTime(startTime).setEndTime(endTime)
                .setTimeZone(EventInfo.DEFAULT_TIME_ZONE);

        return info;
    }

}
