package com.chunma.amdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.chunma.amdm.mainfragment.MainLockFragment;
import com.chunma.amdm.mainfragment.MainSetupFragment;
import com.chunma.amdm.mainfragment.MainStaticsFragment;

public class MainActivity extends AppCompatActivity {

    MainLockFragment mainLockFragment = new MainLockFragment();
    MainSetupFragment mainSetupFragment = new MainSetupFragment();
    MainStaticsFragment mainStaticsFragment = new MainStaticsFragment();

    protected FragmentManager fragmentManager;
    protected FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragmentmanager,mainLockFragment);
        transaction.commit();
    }

    public void onClickSetup(View v){

        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainSetupFragment);
        transaction.commit();
    }
    public void onClickLock(View v){

        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentmanager,mainLockFragment);
        transaction.commit();
    }
    public void onClickStatics(View v){

        fragmentManager =getSupportFragmentManager();

        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.fragmentmanager,mainStaticsFragment);
        transaction.commit();
    }
}