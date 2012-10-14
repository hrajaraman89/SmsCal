package com.apps.smscal.services;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.CalendarContract.Calendars;

public class CalendarListManager {

    private ContentResolver contentResolver;

    private static final String[] EVENT_PROJECTION = new String[] {
            Calendars.CALENDAR_DISPLAY_NAME, Calendars._ID };

    public CalendarListManager(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public Cursor getCalendarList() {
        return contentResolver.query(Calendars.CONTENT_URI, EVENT_PROJECTION,
                null, null, null);
    }

    public String[] getCalendarListRows() {
        return EVENT_PROJECTION;
    }
}
