package com.apps.smscal.model;

public class CalendarInfo {
    private String displayName;
    private int id;

    public CalendarInfo(String dString, int id) {
        this.setDisplayName(dString);
        this.setId(id);
    }

    public CalendarInfo() {
        this("", 0);
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.getDisplayName() + " " + this.getId();
    }
}
