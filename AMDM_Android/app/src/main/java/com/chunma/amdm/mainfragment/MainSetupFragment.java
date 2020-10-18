package com.chunma.amdm.mainfragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chunma.amdm.R;
import com.chunma.amdm.TurnOnPackage.LockService;
import com.chunma.amdm.TurnOnPackage.TurnOnActivity;


public class MainSetupFragment extends Fragment {

    public MainSetupFragment() {
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
        return inflater.inflate(R.layout.fragment_main_setup, container, false);
    }

    public void OnclickLock(View v){

        //락이 아닌지 확인해야함

        //락이 시작되었을때

        //이미지 변경


        //서비스 시작
        Intent intent = new Intent(this.getActivity(), LockService.class);
        this.getActivity().startService(intent);

        intent = new Intent(this.getActivity(), TurnOnActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.getActivity().startActivity(intent);




    }
}