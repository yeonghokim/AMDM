package com.chunma.amdm.mainfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chunma.amdm.MainActivity;
import com.chunma.amdm.R;
import com.chunma.amdm.TCPconnect.TCPconnecter;
import com.chunma.amdm.TurnOnPackage.LockService;
import com.chunma.amdm.TurnOnPackage.TurnOnActivity;

public class MainLockFragment extends Fragment {

    public MainLockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final ViewGroup rootview = (ViewGroup)inflater.inflate(R.layout.fragment_main_lock, container, false);
        Button lockbutton = (Button)rootview.findViewById(R.id.mainlockbutton);
        lockbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),"helloAMDM",Toast.LENGTH_LONG).show();
                TCPconnecter connecter = new TCPconnecter(){
                    @Override
                    public void run(){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((MainActivity)getActivity()).loadingLayout.setAlpha(1.0f);
                            }
                        });
                        super.run();
                        //TurnOnService 실행/*
                        Intent intent = new Intent(activity, LockService.class);
                        intent.setFlags(Intent.FLAG_FROM_BACKGROUND);
                        activity.startService(intent);
                    }
                };

                connecter.activity= MainActivity.mainActivity;
                connecter.SetTcpSocket("127.0.0.1",12345);
                connecter.setRequestString("Lock");
                connecter.start();
                //통신 끝나고 응답 받으면 바로 락 실행
            }
        });
        return rootview;
    }
}