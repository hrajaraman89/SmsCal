package com.apps.smscal.observers;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

import com.apps.smscal.model.EventInfo;
import com.apps.smscal.services.EventAdder;
import com.apps.smscal.utils.PhoneNumberTranslator;

public class SmsSentObserver extends ContentObserver {
    private static final String TAG = SmsSentObserver.class.getSimpleName();

    private static final String TO = "SMS to ";

    private static final String CONTENT_SMS_URI = "content://sms/";
    private static final String BODY_KEY = "body";
    private static final String TYPE_KEY = "type";
    private static final String ADDRESS_KEY = "address";
    private static final String DATE_KEY = "date";

    private ContentResolver resolver;
    private EventAdder adder;

    public SmsSentObserver(Handler handler, ContentResolver resolver,
            EventAdder adder) {
        super(handler);
        this.setResolver(resolver);
        this.adder = adder;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);

        Uri uri = Uri.parse(CONTENT_SMS_URI);
        Cursor cur = this.getResolver().query(uri, null, null, null, null);
        cur.moveToNext();

        int type = cur.getInt(cur.getColumnIndex(TYPE_KEY));

        if (isSentMsg(type)) {

            String content = cur.getString(cur.getColumnIndex(BODY_KEY));
            String address = cur.getString(cur.getColumnIndex(ADDRESS_KEY));
            long date = Long.parseLong(cur.getString(cur
                    .getColumnIndex(DATE_KEY)));

            EventInfo info = EventInfo.makeInstance();

            String contactName = PhoneNumberTranslator.getName(getResolver(),
                    address);

            info.setDescription(content).setStartTime(date).setEndTime(date)
                    .setTimeZone(EventInfo.DEFAULT_TIME_ZONE)
                    .setTitle(TO + contactName);

            adder.add(info);

            Log.d(TAG, "Message sent " + info);
        }
    }

    public ContentResolver getResolver() {
        return resolver;
    }

    public void setResolver(ContentResolver resolver) {
        this.resolver = resolver;
    }

    private static boolean isSentMsg(int type) {
        return type == 2;
    }
}
