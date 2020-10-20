package com.chunma.amdm.TurnOnPackage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.chunma.amdm.R;
import com.chunma.amdm.rfid.RFIDDialog;

public class TurnOnActivity extends AppCompatActivity implements DialogInterface.OnDismissListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turn_on);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        //비행기 모드 온
        /*if(!isAirModeOn()) {
            Intent intent = new Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS);
            startActivity(intent);
        }*/
    }
    public void onClickRFID(View v){
        //RFID버튼이 눌렸을떄
        //커스텀 다이얼로그 실행
        Toast.makeText(getApplicationContext(),"helloAMDM",Toast.LENGTH_LONG).show();
        RFIDDialog customDialog = new RFIDDialog(TurnOnActivity.this,this);
        customDialog.callFunction();
    }

    private Boolean isAirModeOn() {
        return Settings.Global.getInt(getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK ||keyCode==KeyEvent.KEYCODE_HOME) {
            //나중에 차단하기
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        //락 해제
        if(RFIDDialog.dialog_text==RFIDDialog.DIALOG_CONFIRM)
            finish();
    }
}