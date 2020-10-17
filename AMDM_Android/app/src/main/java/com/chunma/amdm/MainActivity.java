package com.chunma.amdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chunma.amdm.mainfragment.MainLockFragment;
import com.chunma.amdm.mainfragment.MainSetupFragment;
import com.chunma.amdm.mainfragment.MainStaticsFragment;

public class MainActivity extends AppCompatActivity {

    MainLockFragment mainLockFragment = new MainLockFragment();
    MainSetupFragment mainSetupFragment = new MainSetupFragment();
    MainStaticsFragment mainStaticsFragment = new MainStaticsFragment();

    protected FragmentManager fragmentManager;
    protected FragmentTransaction transaction;

    int nowPage=2;//main
    int changePage=0;

    ImageButton staticsbutton;
    ImageButton mainbutton;
    ImageButton setupbutton;

    TextView staticsText;
    TextView mainText;
    TextView setupText;

    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity=this;
        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainLockFragment);
        transaction.commit();

        mainbutton=(ImageButton)findViewById(R.id.mainbutton);
        mainText=(TextView)findViewById(R.id.mainbuttontext);

        staticsbutton=(ImageButton)findViewById(R.id.staticsbutton);
        staticsText=(TextView)findViewById(R.id.staticsbuttontext);

        setupbutton=(ImageButton)findViewById(R.id.setupbutton);
        setupText=(TextView)findViewById(R.id.setupbuttontext);

        mainText.setTextColor(ContextCompat.getColor(this.getApplicationContext(), R.color.mainNavigaionButtonTextSelect));
        mainbutton.setImageResource(R.drawable.night_mode_select);

        //mainbutton.setTranslationY(-10.0f);

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


}