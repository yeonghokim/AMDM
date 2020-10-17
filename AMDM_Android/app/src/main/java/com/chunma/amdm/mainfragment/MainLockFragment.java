package com.chunma.amdm.mainfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chunma.amdm.R;

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
        Button button = (Button)getActivity().findViewById(R.id.mainlockbutton);

        final Context context =getContext();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"make toast", Toast.LENGTH_LONG);

            }
        });
        return inflater.inflate(R.layout.fragment_main_lock, container, false);
    }
}