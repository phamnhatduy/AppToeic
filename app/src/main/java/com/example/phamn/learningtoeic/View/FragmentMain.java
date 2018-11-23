package com.example.phamn.learningtoeic.View;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.ViewModel.Part2Activity;

public class FragmentMain extends Fragment{
    Button btnPart1;
    Button btnPart2, btnPart3, btnPart4, btnHistory1, btnHistory2, btnHistory3, btnHistory4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mapping(view);

        btnPart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Part1Activity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnPart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.phamn.learningtoeic.View.Part2Activity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        btnPart3.setEnabled(false);
        btnPart4.setEnabled(false);
        btnHistory1.setEnabled(false);
        btnHistory2.setEnabled(false);
        btnHistory3.setEnabled(false);
        btnHistory4.setEnabled(false);
        return view;
    }

    public void mapping(View view){
        btnPart1 = (Button)view.findViewById(R.id.button_part1);
        btnPart2 = (Button)view.findViewById(R.id.button_part2);
        btnPart3 = (Button)view.findViewById(R.id.button_part3);
        btnPart4 = (Button)view.findViewById(R.id.button_part4);

        btnHistory1 = (Button)view.findViewById(R.id.button_history1);
        btnHistory2 = (Button)view.findViewById(R.id.button_history2);
        btnHistory3 = (Button)view.findViewById(R.id.button_history3);
        btnHistory4 = (Button)view.findViewById(R.id.button_history4);
    }
}
