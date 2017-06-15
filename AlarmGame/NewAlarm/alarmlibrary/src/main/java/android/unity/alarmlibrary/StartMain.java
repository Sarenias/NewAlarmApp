package android.unity.alarmlibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Sarenias on 6/13/2017.
 */

public class StartMain {

    public StartMain(Context ctx)
    {
        Activity act;
        Intent i = new Intent(ctx, MainActivity.class);
        ctx.startActivity(i);
    }
}
