package com.chunma.amdm.rfid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.chunma.amdm.R;


public class RFIDDialog {

    static public int DIALOG_CANCEL=0;
    static public int DIALOG_CONFIRM=1;
    static public int DIALOG_ERROR=2;


    static public int dialog_text;

    private Context context;

    Activity activity;

    public RFIDDialog(Context context,Activity activity) {
        this.context = context;
        this.activity=activity;
    }

    Thread RFIDthread;

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        // 커스텀 다이얼로그를 정의하기위해 Dialog클래스를 생성한다.
        final Dialog dlg = new Dialog(context);

        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_dialog);

        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        final TextView readytextView = (TextView)dlg.findViewById(R.id.ready_rfid);
        final TextView tagtextView = (TextView)dlg.findViewById(R.id.tag_rfid);
        final TextView confirmtextView = (TextView)dlg.findViewById(R.id.confirm_rfid);

        final Button confirmButton = (Button)dlg.findViewById(R.id.rfid_dialog_confirm);
        final Button cancelButton = (Button)dlg.findViewById(R.id.rfid_dialog_cancel);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_text=DIALOG_CONFIRM;
                dlg.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_text=DIALOG_CANCEL;
                RFIDthread.interrupt();
                dlg.dismiss();
            }
        });

        RFIDthread = new Thread(){
            @Override
            public void run() {
                super.run();
                dialog_text=DIALOG_ERROR;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        readytextView.setAlpha(0.3f);
                        tagtextView.setAlpha(1.0f);
                    }
                });
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tagtextView.setAlpha(0.3f);
                        confirmtextView.setAlpha(1.0f);
                        confirmButton.setClickable(true);
                    }
                });
            }
        };
        RFIDthread.start();

    }



}
