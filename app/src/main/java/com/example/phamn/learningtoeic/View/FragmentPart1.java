package com.example.phamn.learningtoeic.View;

import android.app.Fragment;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.Question_Part1;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.ViewModel.Part1ViewModel;
import com.example.phamn.learningtoeic.databinding.FragmentPart1Binding;

public class FragmentPart1 extends Fragment{
    Part1ViewModel part1ViewModel;
    ProgressBar progressBarTime;
    RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    int time = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_part1, container, false);
        mapping(view);
        updateProgressBarTime(5);

        radioButtonA.setText("abcasasdasd");
        return view;
    }

    private void initDatabinding(){
//        FragmentPart1Binding fragmentPart1Binding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_part1);
//        //Question_Part1 question_part1 = new Question_Part1();
//        part1ViewModel = ViewModelProviders.of(this).get(Part1ViewModel.class);
//        fragmentPart1Binding.setUser();
    }

    public void updateProgressBarTime(final int maxTime){
        progressBarTime.setMax(maxTime);
        progressBarTime.setProgress(0);
        CountDownTimer countDownTimer = new CountDownTimer(maxTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time += 1;
                progressBarTime.setProgress(time);
            }

            @Override
            public void onFinish() {
                time += 1;
                progressBarTime.setProgress(time);
                Toast.makeText(getActivity(), "finish", Toast.LENGTH_SHORT).show();
            }
        };
        countDownTimer.start();
    }

    public void mapping(View view){
        progressBarTime = (ProgressBar) view.findViewById(R.id.progressBar_Time);
        radioButtonA = (RadioButton )view.findViewById(R.id.radioButton_A);
        radioButtonB = (RadioButton )view.findViewById(R.id.radioButton_B);
        radioButtonC = (RadioButton )view.findViewById(R.id.radioButton_C);
        radioButtonD = (RadioButton )view.findViewById(R.id.radioButton_D);
    }

}
