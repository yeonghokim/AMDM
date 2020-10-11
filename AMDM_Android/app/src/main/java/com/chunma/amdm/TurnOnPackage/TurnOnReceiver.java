package com.chunma.amdm.TurnOnPackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TurnOnReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        switch (action){
            case "android.intent.action.BOOT_COMPLETED":
                String str = "브로드캐스트가 실행되었습니다.";
                Toast t1 = Toast.makeText(context,str,Toast.LENGTH_SHORT);
                t1.show();
                break;
        }



    }
}
