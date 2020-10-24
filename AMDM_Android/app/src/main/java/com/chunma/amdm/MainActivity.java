package com.chunma.amdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chunma.amdm.TCPconnect.TCPconnecter;
import com.chunma.amdm.TurnOnPackage.LockService;
import com.chunma.amdm.mainfragment.MainLockFragment;
import com.chunma.amdm.mainfragment.MainSetupFragment;
import com.chunma.amdm.mainfragment.MainStaticsFragment;

public class MainActivity extends AppCompatActivity {


    MainLockFragment mainLockFragment = new MainLockFragment();
    MainSetupFragment mainSetupFragment = new MainSetupFragment();
    MainStaticsFragment mainStaticsFragment = new MainStaticsFragment();

    protected FragmentManager fragmentManager;
    protected FragmentTransaction transaction;

    public LinearLayout loading_layout;

    int nowPage=2;//main
    int changePage=0;

    ImageButton staticsbutton;
    ImageButton mainbutton;
    ImageButton setupbutton;

    TextView staticsText;
    TextView mainText;
    TextView setupText;

    static public MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity=this;
        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainLockFragment);
        transaction.commit();

        FrameLayout mainLayout = (FrameLayout) findViewById(R.id.mainlayout);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.loading_dialog, mainLayout, true);

        loading_layout=(LinearLayout)findViewById(R.id.loadinglayout);

        mainbutton=(ImageButton)findViewById(R.id.mainbutton);
        mainText=(TextView)findViewById(R.id.mainbuttontext);

        staticsbutton=(ImageButton)findViewById(R.id.staticsbutton);
        staticsText=(TextView)findViewById(R.id.staticsbuttontext);

        setupbutton=(ImageButton)findViewById(R.id.setupbutton);
        setupText=(TextView)findViewById(R.id.setupbuttontext);

        mainText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
        mainbutton.setImageResource(R.drawable.night_mode_select);

        mainbutton.setTranslationY(-10.0f);

    }

    public void onClickStatics(View v){

        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragmentmanager,mainStaticsFragment);
        transaction.commit();
        changeImage(1);
    }
    public void onClickLock(View v){

        fragmentManager =getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainLockFragment);
        transaction.commit();
        changeImage(2);

    }
    public void onClickSetup(View v){
        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainSetupFragment);
        transaction.commit();
        changeImage(3);
    }
    public void changeImage(int changepage){
        if(changepage==nowPage) return;
        changePage=changepage;


        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        ImageButton downImage=null;
                        switch (nowPage){
                            case 1:
                                staticsText.setTextColor(ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.mainNavigaionButtonText));
                                staticsbutton.setImageResource(R.drawable.graph);
                                downImage = staticsbutton;
                                break;
                            case 2:
                                mainText.setTextColor(ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.mainNavigaionButtonText));
                                mainbutton.setImageResource(R.drawable.night_mode);
                                downImage = mainbutton;
                                break;
                            case 3:
                                setupText.setTextColor(ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.mainNavigaionButtonText));
                                setupbutton.setImageResource(R.drawable.settings);
                                downImage = setupbutton;
                                break;
                        }
                        Animation anim = AnimationUtils.loadAnimation(mainActivity.getApplicationContext(),R.anim.translate_down);   // 에니메이션 설정 파일
                        /*final ImageButton finalDownImage = downImage;
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            public void onAnimationStart(Animation animation) {}
                            public void onAnimationRepeat(Animation animation) {}
                            public void onAnimationEnd(Animation animation) {
                                finalDownImage.setTranslationY(0.0f);
                            }
                        });
                        if(downImage!=null)
                            downImage.startAnimation(anim);*/
                        downImage.setTranslationY(0.0f);

                        ImageButton upImage=null;
                        switch (changePage){
                            case 1:
                                staticsText.setTextColor(ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
                                staticsbutton.setImageResource(R.drawable.graph_select);
                                upImage = staticsbutton;
                                break;
                            case 2:
                                mainText.setTextColor(ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
                                mainbutton.setImageResource(R.drawable.night_mode_select);
                                upImage =mainbutton;
                                break;
                            case 3:
                                setupText.setTextColor(ContextCompat.getColor(mainActivity.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
                                setupbutton.setImageResource(R.drawable.settings_select);
                                upImage = setupbutton;
                                break;
                        }
                        anim = AnimationUtils.loadAnimation(mainActivity.getApplicationContext(),R.anim.translate_up);   // 에니메이션 설정 파일
                        /*final ImageButton finalUpImage = upImage;
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            public void onAnimationStart(Animation animation) {}
                            public void onAnimationRepeat(Animation animation) {}
                            public void onAnimationEnd(Animation animation) {
                                finalUpImage.setTranslationY(-10.0f);
                            }
                        });

                        if(upImage!=null)
                           upImage.startAnimation(anim);*/

                        upImage.setTranslationY(-10.0f);

                        nowPage=changePage;
                    }
                });
            }
        }).start();
    }

    public void goLock(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("락 진행");
        alertDialogBuilder.setMessage("락을 진행하시겠습니까?")
                .setCancelable(true)
                .setPositiveButton("진행",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Lockconnecter connecter = new Lockconnecter();
                        connecter.setRequestString("Lock Time ID");
                        connecter.start();
                    }
                })
                .setNegativeButton("취소",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog =alertDialogBuilder.create();
        alertDialog.show();
    }

    class Lockconnecter extends TCPconnecter{
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading_layout.setAlpha(1.0f);
                }
            });
            final ImageView imgFrame = (ImageView) loading_layout.findViewById(R.id.iv_frame_loading);
            final AnimationDrawable frameAnimation = (AnimationDrawable) imgFrame.getBackground();
            imgFrame.post(new Runnable() {
                @Override
                public void run() {
                    frameAnimation.start();
                }
            });
            super.run();
            frameAnimation.stop();

            Intent intent = new Intent(MainActivity.this, LockService.class);
            LockService.runningIntent = intent;
            //intent.setFlags(Intent.FLAG_FROM_BACKGROUND);
            finishAffinity();
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        PreferenceManager.getInstance().setLockHistory(MainActivity.this,true);
                    }
                });
                startForegroundService(intent);
            }else
                startService(intent);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    loading_layout.setAlpha(0.0f);
                }
            });
        }
    }


}