package com.fastcnt.fpad;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    public static final String TAG = "MyService";

    @Override
    public void onCreate() {
        Log.d("MyService", "MyService thread id is " + Thread.currentThread().getId());
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
        Notification notification = new Notification(R.drawable.ic_launcher,
                "有通知到来", System.currentTimeMillis());
        Intent notificationIntent = new Intent(this, FullActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);
        notification.setLatestEventInfo(this, "这是通知的标题", "这是通知的内容",
                pendingIntent);
        startForeground(1, notification);
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() executed");
        return super.onStartCommand(intent, flags, startId);
    }

    public MyService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private MyBinder mBinder = new MyBinder();

    class MyBinder extends Binder {

        public void startDownload() {
            Log.d(TAG, "startDownload() executed");
            int i = 1;
            while (i <= 5) {
                Log.d(TAG, "new time ====" + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }

    }

}

