package com.chunma.amdm.TurnOnPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chunma.amdm.PreferenceManager;
import com.chunma.amdm.R;

public class TurnOnActivity extends AppCompatActivity{

    LinearLayout rfidLayout;

    Button rfid_cancelButton;
    Button rfid_confirmButton;

    TextView readytextView;
    TextView tagtextView;
    TextView confirmtextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_on);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        rfidLayout =(LinearLayout)findViewById(R.id.rfid_layout);

        rfid_cancelButton=(Button)findViewById(R.id.rfid_dialog_cancel);
        rfid_confirmButton=(Button)findViewById(R.id.rfid_dialog_confirm);


        readytextView = (TextView)findViewById(R.id.ready_rfid);
        tagtextView = (TextView)findViewById(R.id.tag_rfid);
        confirmtextView = (TextView)findViewById(R.id.confirm_rfid);

        rfid_cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rfidLayout.setAlpha(0.0f);
            }
        });
        rfid_confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferenceManager.getInstance().setLockHistory(TurnOnActivity.this,false);
                stopService(LockService.runningIntent);
                LockService.runningIntent=null;
                finish();
            }
        });

        //비행기 모드 온
        /*if(!isAirModeOn()) {
            Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            startActivity(intent);
        }*/

    }
    public void onClickRFID(View v){
        //RFID버튼이 눌렸을떄
        //커스텀 다이얼로그 실행
        rfidLayout.setAlpha(1.0f);
        Thread RFIDthread = new Thread(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        readytextView.setAlpha(1.0f);
                        tagtextView.setAlpha(0.3f);
                        confirmtextView.setAlpha(0.3f);
                        rfid_confirmButton.setClickable(false);
                    }
                });
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tagtextView.setAlpha(0.3f);
                        confirmtextView.setAlpha(1.0f);
                        rfid_confirmButton.setClickable(true);
                    }
                });
            }
        };


        RFIDthread.start();
        /*
        RFIDDialog RFIDdialog = new RFIDDialog(this, new RFIDDialog.CustomDialogClickListener() {
            @Override
            public void onPositiveClick() {
                Toast.makeText(getApplicationContext(),"Positive",Toast.LENGTH_LONG);
            }

            @Override
            public void onNegativeClick() {
                Toast.makeText(getApplicationContext(),"Positive",Toast.LENGTH_SHORT);
            }
        });
        //RFIDdialog.setCanceledOnTouchOutside(true);
        RFIDdialog.setCancelable(true);
        //RFIDdialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        RFIDdialog.show();*/
    }

    private Boolean isAirModeOn() {
        return Settings.Global.getInt(getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK ||keyCode==KeyEvent.KEYCODE_HOME) {
            //나중에 차단하기
            Toast.makeText(this,"락이 진행되고 있습니다!",Toast.LENGTH_LONG).show();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}