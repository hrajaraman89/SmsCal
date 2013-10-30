package com.apps.smscal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.apps.smscal.controllers.CalendarListController;
import com.apps.smscal.services.SmsEventListener;

public class MainActivity extends Activity {

    public static final String CALENDAR_INFO = "CalendarInfo";
    private CalendarListController controller;
    private SmsEventListener listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startSmsListeners();
        startController();
    }

    public void startController() {
        ListView calendarListView = (ListView) findViewById(R.id.calendarListView);

        controller = new CalendarListController(this, calendarListView,
                listener);
        controller.start();
    }

    private void startSmsListeners() {
        this.listener = new SmsEventListener(this, getContentResolver());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
