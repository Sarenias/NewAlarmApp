package android.unity.alarmlibrary;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Sarenias on 6/9/2017.
 */

public class CheckAlarmService extends Service {


    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        BackgroundAlarm alarm = new BackgroundAlarm();
        alarm.setAlarm(this, intent);
        return START_STICKY;
    }



    public IBinder onBind(Intent intent)
    {
        return null;
    }


}
