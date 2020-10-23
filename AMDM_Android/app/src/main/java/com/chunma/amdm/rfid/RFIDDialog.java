package com.chunma.amdm.rfid;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.chunma.amdm.R;
import com.chunma.amdm.TurnOnPackage.TurnOnActivity;

import java.nio.charset.Charset;


public class RFIDDialog extends Dialog {
    static public int DIALOG_CANCEL=0;
    static public int DIALOG_CONFIRM=1;
    static public int DIALOG_ERROR=2;

    static public int dialog_text;

    public interface CustomDialogClickListener {
        void onPositiveClick();
        void onNegativeClick();
    }

    private Context context;
    private CustomDialogClickListener customDialogClickListener;
    private TextView tvTitle, tvNegative, tvPositive;

    public RFIDDialog(@NonNull Context context ,CustomDialogClickListener customDialogClickListener) {
        super(context);
        this.context = context;
        this.customDialogClickListener = customDialogClickListener;
    }

    Thread RFIDthread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);

        Button cancelButton = (Button)findViewById(R.id.rfid_dialog_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_text=DIALOG_CANCEL;
                RFIDthread.interrupt();
                customDialogClickListener.onNegativeClick();
                dismiss();
            }
        });

        final Button confirmButton = (Button)findViewById(R.id.rfid_dialog_confirm);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_text=DIALOG_CONFIRM;
                customDialogClickListener.onPositiveClick();
                dismiss();
            }
        });

        final TextView readytextView = (TextView)findViewById(R.id.ready_rfid);
        final TextView tagtextView = (TextView)findViewById(R.id.tag_rfid);
        final TextView confirmtextView = (TextView)findViewById(R.id.confirm_rfid);

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
                getOwnerActivity().runOnUiThread(new Runnable() {
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
                getOwnerActivity().runOnUiThread(new Runnable() {
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

    Intent intent;

    public void createRFIDTag() {
/*
        intent =new Intent(activity,TurnOnActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(activity);

        if (mNfcAdapter == null) {
            // NFC 미지원단말
            Toast.makeText(activity.getApplicationContext(), "NFC를 지원하지 않는 단말기입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        Tag myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

        Ndef ndefTag = Ndef.get(myTag);

        // 태그 크기
        int size = ndefTag.getMaxSize();
        // 쓰기 가능 여부
        boolean writable = ndefTag.isWritable();
        // 태그 타입
        String type = ndefTag.getType();
        // 태그 ID
        String id = byteArrayToHexString(myTag.getId());*/

    }

    public static String byteArrayToHexString(byte[] b) {
        int len = b.length;
        String data = new String();

        for (int i = 0; i < len; i++){
            data += Integer.toHexString((b[i] >> 4) & 0xf);
            data += Integer.toHexString(b[i] & 0xf);
        }
        return data;
    }

}
