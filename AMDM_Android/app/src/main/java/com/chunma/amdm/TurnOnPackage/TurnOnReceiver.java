package com.chunma.amdm.TurnOnPackage;

import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.chunma.amdm.MainActivity;
import com.chunma.amdm.PreferenceManager;
import com.chunma.amdm.SplashActivity;

public class TurnOnReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        switch (action){
            case Intent.ACTION_SCREEN_OFF:

                String str = "브로드캐스트가 실행되었습니다.";
                Toast t1 = Toast.makeText(context,str,Toast.LENGTH_LONG);
                t1.show();
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

            case Intent.ACTION_BOOT_COMPLETED:
            default:
                //락이 실행되어있는지 DB에서 가져오기
                if(!IsServiceRunning(context,LockService.class)
                                &&
                        PreferenceManager.getInstance().getLockHistory(context)) {

                    Intent service_intent = new Intent(context, LockService.class);
                    service_intent.setFlags(Intent.FLAG_FROM_BACKGROUND);
                    context.startService(service_intent);
                }
                break;
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
