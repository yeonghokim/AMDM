package com.chunma.amdm.TurnOnPackage;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class TurnOnReceiver extends BroadcastReceiver {

    private KeyguardManager km = null;
    private KeyguardManager.KeyguardLock keyLock = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        switch (action){
            case Intent.ACTION_BOOT_COMPLETED:
            case Intent.ACTION_SCREEN_OFF:
                String str = "브로드캐스트가 실행되었습니다.";
                Toast t1 = Toast.makeText(context,str,Toast.LENGTH_SHORT);
                t1.show();

                if (km == null)
                    km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);

                if (keyLock == null)
                    keyLock = km.newKeyguardLock(Context.KEYGUARD_SERVICE);

                disableKeyguard();

                Intent i = new Intent(context, TurnOnActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                break;
        }
    }

    //잠금화면 다시 나타나게 하기
    public void reenableKeyguard() {
        keyLock.reenableKeyguard();
    }

    //잠금화면 없애기
    public void disableKeyguard() {
        keyLock.disableKeyguard();
    }

}
