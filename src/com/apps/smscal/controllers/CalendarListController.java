package com.apps.smscal.controllers;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.apps.smscal.R;
import com.apps.smscal.model.CalendarInfo;
import com.apps.smscal.services.CalendarListContentResolver;
import com.apps.smscal.services.UpdateListener;

public class CalendarListController {
    private ListView calendarListView;
    private CalendarInfo currentSelection;
    private Activity caller;
    private Cursor cursor;
    private String[] from;
    private final int selectedColor = Color.GREEN,
            unselectedColor = Color.WHITE;
    private UpdateListener updateListener;

    public CalendarListController(final Activity caller,
            final ListView calendarListView, UpdateListener listener) {
        this.updateListener = listener;
        this.currentSelection = new CalendarInfo();
        this.caller = caller;
        this.calendarListView = calendarListView;
    }

    public void start() {
        this.bindCalendarListToView();
        this.updateCalendarInfoFromListItem(0);
        addListenerToCalendarList(caller);
    }

    private void addListenerToCalendarList(final Activity caller) {
        this.calendarListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                    long arg3) {
                clearSelectedRow();
                updateCalendarInfoFromListItem(index);

                View row = calendarListView.getChildAt(index);
                row.setBackgroundColor(selectedColor);

                Toast.makeText(caller, "You have selected " + currentSelection,
                        Toast.LENGTH_SHORT).show();
            }

            private void clearSelectedRow() {
                int numRows = calendarListView.getCount();
                int index = 0;

                while (index < numRows) {
                    calendarListView.getChildAt(index).setBackgroundColor(
                            unselectedColor);
                    index++;
                }
            }

        });
    }

    private void updateCalendarInfoFromListItem(int position) {
        Cursor cursor = (Cursor) calendarListView.getItemAtPosition(position);
        currentSelection.setDisplayName(cursor.getString(0));
        currentSelection.setId(cursor.getInt(1));

        updateListener.onUpdate(getCurrentSelection());
    }

    private void bindCalendarListToView() {
        CalendarListContentResolver manager = new CalendarListContentResolver(
                this.caller.getContentResolver());

        cursor = manager.getCalendarList();
        from = manager.getCalendarListRows();

        ListAdapter calendarListAdapter = new SimpleCursorAdapter(caller,
                R.layout.calendar_row, cursor, from,
                new int[] { R.id.calendarName }, 0);

        this.calendarListView.setAdapter(calendarListAdapter);
    }

    public CalendarInfo getCurrentSelection() {
        return this.currentSelection;
    }

}
