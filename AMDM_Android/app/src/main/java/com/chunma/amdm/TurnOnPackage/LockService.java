package com.chunma.amdm.TurnOnPackage;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.chunma.amdm.R;

public class LockService extends Service {

    public static Intent runningIntent=null;

    private TurnOnReceiver mReceiver = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationManager manager  = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            NotificationChannel channel = new NotificationChannel("amdm","amdmService", NotificationManager.IMPORTANCE_HIGH);
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.enableVibration(true);
            manager.createNotificationChannel(channel);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"amdm");
            builder.setSmallIcon(R.drawable.logo_lock);
            builder.setContentTitle("AMDM");
            builder.setContentText("락이 진행되고 있습니다");
            builder.setAutoCancel(true);

            Notification notification =builder.build();
            startForeground(10,notification);
        }

        if(intent != null){
            if(intent.getAction()==null){
                if(mReceiver==null){
                    createReceiver();
                }
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            stopForeground(STOP_FOREGROUND_REMOVE);
            NotificationManager manager  = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            manager.cancel(10);
        }

        if(mReceiver != null){
            unregisterReceiver(mReceiver);
        }
    }

    public void createReceiver(){
        mReceiver = new TurnOnReceiver();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        registerReceiver(mReceiver, filter);

        Intent intent = new Intent(this,TurnOnActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}