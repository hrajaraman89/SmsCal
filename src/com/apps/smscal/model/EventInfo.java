package com.apps.smscal.model;

import java.util.Calendar;

public class EventInfo {
    private Calendar startTime, endTime;
    private String title, description;
    private String timeZone;

    public static EventInfo makeInstance() {
        return new EventInfo();
    }

    public EventInfo setStartTime(Calendar time) {
        this.startTime = time;
        return this;
    }

    public EventInfo setEndTime(Calendar endTime) {
        this.endTime = endTime;
        return this;
    }

    public EventInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public EventInfo setDescription(String description) {
        this.description = description;
        return this;
    }

    public EventInfo setTimeZone(String timeZone) {
        this.timeZone = timeZone;
        return this;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTimeZone() {
        return timeZone;
    }

    @Override
    public String toString() {
        return String.format("%s. from %s to %s. %s", getTitle(),
                getStartTime().toString(), getEndTime().toString(),
                getDescription());
    }

}
