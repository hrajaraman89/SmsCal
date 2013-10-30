package com.apps.smscal.services;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.provider.CalendarContract.Events;

import com.apps.smscal.model.CalendarInfo;
import com.apps.smscal.model.EventInfo;

public class CalendarEventAdder implements EventAdder {

    private CalendarInfo calendarInfo;
    private ContentResolver contentResolver;

    public CalendarEventAdder(ContentResolver resolver) {
        setContentResolver(resolver);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.apps.smscal.services.EventAdder#add(com.apps.smscal.model.EventInfo)
     */
    @Override
    public void add(EventInfo info) {
        ContentValues values = new ContentValues();
        values.put(Events.DTSTART, info.getStartTime());
        values.put(Events.DTEND, info.getEndTime());
        values.put(Events.TITLE, info.getTitle());
        values.put(Events.DESCRIPTION, info.getDescription());
        values.put(Events.CALENDAR_ID, this.getCalendarInfo().getId());
        values.put(Events.EVENT_TIMEZONE, info.getTimeZone());
        values.put(Events.STATUS, Events.STATUS_CONFIRMED);
        values.put(Events.HAS_ALARM, 0);

        this.getContentResolver().insert(Events.CONTENT_URI, values);
    }

    private CalendarInfo getCalendarInfo() {
        return calendarInfo;
    }

    void setCalendarInfo(CalendarInfo calendarInfo) {
        this.calendarInfo = calendarInfo;
    }

    private ContentResolver getContentResolver() {
        return contentResolver;
    }

    void setContentResolver(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }
}
