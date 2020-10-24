package com.chunma.amdm.mainfragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
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

                ((MainActivity) getActivity()).goLock();
            }
        });
        return rootview;
    }
}