package com.example.SosLocationApp;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class MyActivity extends Activity {
    private AlarmManager alarmManager;
    private PendingIntent notifyIntent;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarms();
            }
        });
    }

    private void setAlarms() {
        Intent myIntent = new Intent(this,
                SendLocationService.class);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        notifyIntent = PendingIntent.getService(this, 0,
                myIntent, 0);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        Log.v("TAG", "time for alarm trigger:" + calendar.getTime().toString());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 5 * 60 * 1000, notifyIntent);
    }


}
