package com.apps.smscal.model;

import java.util.TimeZone;

public class EventInfo {
    public static final String DEFAULT_TIME_ZONE = TimeZone.getDefault()
            .getDisplayName();

    private long startTime, endTime;
    private String title, description;
    private String timeZone;

    private EventInfo() {
    }

    public static EventInfo makeInstance() {
        return new EventInfo();
    }

    public EventInfo setStartTime(long time) {
        this.startTime = time;
        return this;
    }

    public EventInfo setEndTime(long endTime) {
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

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
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
        return String.format("%s. %s to %s. %s", getTitle(), startTime,
                endTime, getDescription());
    }
}
