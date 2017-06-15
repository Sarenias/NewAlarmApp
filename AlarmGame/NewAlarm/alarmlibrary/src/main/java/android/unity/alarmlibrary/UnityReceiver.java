package android.unity.alarmlibrary;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Sarenias on 6/14/2017.
 */

public class UnityReceiver extends BroadcastReceiver {
    private static UnityReceiver instance;
    private static boolean alarmDone = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        changeAlarm();
        Toast.makeText(context, "Broadcast received", Toast.LENGTH_LONG).show();

    }

    private static void changeAlarm()
    {
        alarmDone = true;
    }
    private static boolean getStatus()
    {
        return alarmDone;
    }

    public static void createInstance() {
        if (instance == null) {
            instance = new UnityReceiver();
        }
    }

}
