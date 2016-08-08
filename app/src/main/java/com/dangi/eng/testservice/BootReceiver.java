package com.dangi.eng.testservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by lee on 2016. 8. 8..
 */
public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent i = new Intent("arabiannight.tistory.com.service");
            context.startService(i);
        }
        if (intent.getAction().equals("ACTION.RESTART.PersistentService")) {
            Intent i = new Intent("arabiannight.tistory.com.service");
            context.startService(i);
        }
    }
}
