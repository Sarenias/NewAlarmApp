package android.unity.unitytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.unity3d.player.UnityPlayerActivity;

public class GameActivity extends UnityPlayerActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        // TODO Auto-generated method stub
        super.onCreate(arg0);
        Log.d("Unity", "Unity started");
    }
}
