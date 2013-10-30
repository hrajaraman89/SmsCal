package com.apps.smscal.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;

public class PhoneNumberTranslator {
    public static String getName(ContentResolver resolver, String phoneNumber) {

        Cursor cursor = getPhoneLookupCursor(resolver, phoneNumber);

        String contactName = phoneNumber;

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                contactName = cursor.getString(cursor
                        .getColumnIndex(PhoneLookup.DISPLAY_NAME));
            }

            if (!cursor.isClosed()) {
                cursor.close();
            }
        }

        return contactName;
    }

    public static Cursor getPhoneLookupCursor(ContentResolver resolver, String phoneNumber) {
        Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(phoneNumber));

        Cursor cursor = resolver.query(uri,
                new String[] { PhoneLookup.DISPLAY_NAME }, null, null, null);
        return cursor;
    }

}
