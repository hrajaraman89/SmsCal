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
import com.apps.smscal.services.CalendarListManager;

public class CalendarListController {
    private ListView calendarListView;
    private String currentSelection;
    private Activity caller;
    private Cursor cursor;
    private String[] from;
    private final int selectedColor = Color.GREEN,
            unselectedColor = Color.WHITE;

    public CalendarListController(final Activity caller,
            final ListView calListView) {
        this.caller = caller;
        this.calendarListView = calListView;

        this.currentSelection = (String) this.calendarListView
                .getItemAtPosition(0);

        this.calendarListView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int index,
                    long arg3) {
                clearSelectedRow();
                currentSelection = getStringFromListItem(index);

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

            private String getStringFromListItem(int position) {
                Cursor cursor = (Cursor) calendarListView
                        .getItemAtPosition(position);
                return cursor.getString(0);
            }
        });
    }

    public void bindCalendarListToView() {
        CalendarListManager manager = new CalendarListManager(
                this.caller.getContentResolver());

        cursor = manager.getCalendarList();
        from = manager.getCalendarListRows();

        ListAdapter calendarListAdapter = new SimpleCursorAdapter(caller,
                R.layout.calendar_row, cursor, from,
                new int[] { R.id.calendarName }, 0);

        this.calendarListView.setAdapter(calendarListAdapter);
    }

    public String getCurrentSelection() {
        return this.currentSelection;
    }

}
