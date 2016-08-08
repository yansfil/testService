package com.dangi.eng.testservice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by lee on 2016. 8. 8..
 */
public class ServiceClass extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAGs", "실행되었습니다");
        Log.d("TAGs", String.valueOf(flags));
        unregisterRestartAlarm();
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        registerRestartAlarm();
        Log.d("TAGs" , "종료되었습니다");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // support persistent of Service
    public void registerRestartAlarm() {//좀비 앱 !! alarmManager이용
        Log.d("PersistentService", "registerRestartAlarm");
        Intent intent = new Intent(ServiceClass.this, BootReceiver.class);
        intent.setAction("ACTION.RESTART.PersistentService");
        PendingIntent sender = PendingIntent.getBroadcast(ServiceClass.this, 0, intent, 0);
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += 10*1000;                                               // 10초 후에 알람이벤트 발생
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime, 10*1000, sender);
    }
    public void unregisterRestartAlarm() { //좀비 앱 !! alarmManager이용
        Log.d("PersistentService", "unregisterRestartAlarm");
        Intent intent = new Intent(ServiceClass.this, BootReceiver.class);
        intent.setAction("ACTION.RESTART.PersistentService");
        PendingIntent sender = PendingIntent.getBroadcast(ServiceClass.this, 0, intent, 0);
        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);
        am.cancel(sender);
    }

}
