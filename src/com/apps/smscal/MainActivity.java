package com.apps.smscal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.apps.smscal.controllers.CalendarListController;
import com.apps.smscal.services.SmsEventListener;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startSmsListeners();

        ListView calendarListView = (ListView) findViewById(R.id.calendarListView);

        new CalendarListController(this, calendarListView)
                .bindCalendarListToView();
    }

    private void startSmsListeners() {
        Intent serviceIntent = new Intent(this, SmsEventListener.class);
        startService(serviceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
