package android.unity.alarmlibrary;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AlarmWait extends Activity {
    int hour;
    int minute;
    String ampm;
    String displayHour;
    String displayMinute;
    BroadcastReceiver broadcast_receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_wait);
        Intent serviceIntent = new Intent(this, CheckAlarmService.class);
        serviceIntent.putExtras(this.getIntent().getExtras());
        startService(serviceIntent);
        Bundle timeBundle = this.getIntent().getExtras();
        displayHour = Integer.toString(timeBundle.getInt("WANTED_ALARM_HOUR"));
        displayMinute = Integer.toString(timeBundle.getInt("WANTED_ALARM_MINUTE"));
        if (timeBundle.getInt("WANTED_ALARM_MINUTE") < 10)
        {
            displayMinute = "0" + displayMinute;
        }
        hour = timeBundle.getInt("WANTED_ALARM_HOUR");
        minute = timeBundle.getInt("WANTED_ALARM_MINUTE");
        if (hour>12)
        {
            ampm = " PM";
            displayHour = Integer.toString(hour - 12);
        }
        else
        {
            ampm = " AM";
        }
        TextView displayAlarm = (TextView) findViewById(R.id.alarmTime);
        displayAlarm.setText(displayHour + ":" + displayMinute + ampm);
        broadcast_receiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent intent) {
                String action = intent.getAction();
                if (action.equals("finish_activity")) {
                    finish();
                }
            }
        };
        registerReceiver(broadcast_receiver, new IntentFilter("finish_activity"));
    }
    public void resetAlarm(View view)
    {
        AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent alarmCancel = new Intent(this, BackgroundAlarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, alarmCancel, PendingIntent.FLAG_UPDATE_CURRENT);
        stopService(alarmCancel);
        am.cancel(pi);
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void onDestroy()
    {
        unregisterReceiver(broadcast_receiver);
        super.onDestroy();
    }

}
