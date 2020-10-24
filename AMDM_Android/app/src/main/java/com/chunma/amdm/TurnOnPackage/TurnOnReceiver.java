package com.chunma.amdm.TurnOnPackage;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.chunma.amdm.PreferenceManager;

public class TurnOnReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(PreferenceManager.getInstance().getLockHistory(context)) {
            switch (action) {
                case Intent.ACTION_BOOT_COMPLETED:
                    Intent service_intent = new Intent(context, LockService.class);
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                        context.startForegroundService(service_intent);
                    }else{
                        context.startService(service_intent);
                    }
                    LockService.runningIntent = service_intent;
                case Intent.ACTION_SCREEN_OFF:
                    if (IsServiceRunning(context, LockService.class)) {
                        Intent activityIntent = new Intent(context, TurnOnActivity.class);
                        activityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(activityIntent);
                    }
/*
                if (km == null)
                    km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

                if (keyLock == null)
                    keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);

                disableKeyguard();

                Intent i = new Intent(context, TurnOnActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);*/
                    break;
            }
        }
    }

    private boolean IsServiceRunning(Context context, Class<?> serviceClass){
        ActivityManager manager = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)){
            if(serviceClass.getName().equals(service.service.getClassName())){
                return true;
            }
        }
        return false;
    }
}
