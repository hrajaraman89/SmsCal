package com.apps.smscal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.apps.smscal.controllers.CalendarListController;
import com.apps.smscal.services.SmsEventListener;

public class MainActivity extends Activity {

    public static final String CALENDAR_INFO = "CalendarInfo";
    private CalendarListController controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView calendarListView = (ListView) findViewById(R.id.calendarListView);

        controller = new CalendarListController(this, calendarListView);
        startSmsListeners();
    }

    private void startSmsListeners() {
        Intent serviceIntent = new Intent(this, SmsEventListener.class);
        serviceIntent.putExtra(CALENDAR_INFO,
                this.controller.getCurrentSelection());
        startService(serviceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
