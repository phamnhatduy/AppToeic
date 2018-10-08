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

public class FragmentMain extends Fragment{
    Button buttonPart1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        buttonPart1 = (Button)view.findViewById(R.id.button_part1);
        buttonPart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Part1Activity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
