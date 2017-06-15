package android.unity.myplugin;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class AlarmClock extends AppCompatActivity {
    public int alarmHour;
    public int alarmMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
        alarmHour = 0;
        alarmMinutes = 0;
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        datePicker.init(mYear, mMonth, mDay, null);
    }


    public void startAlarm(View view)
    {
        TimePicker alarmSet = (TimePicker) findViewById(R.id.timePicker);
        DatePicker dateSet = (DatePicker) findViewById(R.id.datePicker);

        long alarmDay = (long)dateSet.getDayOfMonth();
        long alarmMonth = (long)dateSet.getMonth();
        long alarmYear = (long)dateSet.getYear();
        alarmHour = alarmSet.getHour();
        alarmMinutes = alarmSet.getMinute();
        long sysTime = System.currentTimeMillis();

        Bundle extras = new Bundle();
        Calendar c = Calendar.getInstance();
        extras.putInt("WANTED_ALARM_HOUR", alarmHour);
        extras.putInt("WANTED_ALARM_MINUTE", alarmMinutes);
        extras.putLong("WANTED_DAY", alarmDay);
        extras.putLong("WANTED_MONTH", alarmMonth);
        extras.putLong("WANTED_YEAR", alarmYear);
        long hours;
        if (alarmDay > c.get(Calendar.DAY_OF_MONTH))
        {
            hours = (long)(((23 - c.get(Calendar.HOUR_OF_DAY))+ alarmHour + 1)*60*60*1000);
        }
        else {
            hours = (long) (alarmHour - c.get(Calendar.HOUR_OF_DAY)) * 60 * 60 * 1000;
        }
        long minutes = (long)(Math.abs(alarmMinutes - c.get(Calendar.MINUTE)))*60*1000;
        long time = sysTime + (hours + minutes - c.get(Calendar.SECOND)*1000);
        if (alarmMinutes < c.get(Calendar.MINUTE))
        {
            time = time - minutes*2;
        }
        extras.putLong("TIME_TO_ALARM", time);
        Intent intent = new Intent(this, AlarmWait.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}
