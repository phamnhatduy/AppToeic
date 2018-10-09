package com.example.phamn.learningtoeic.View;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.phamn.learningtoeic.JSON.JSON;
import com.example.phamn.learningtoeic.Model.Question_Part1;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.ViewModel.Part1ViewModel;
import com.example.phamn.learningtoeic.databinding.ActivityPart1Binding;
import com.example.phamn.learningtoeic.databinding.FragmentPart1Binding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Part1Activity extends AppCompatActivity {
    Part1ViewModel part1ViewModel;
    Button buttonPrevious, buttonNext;
    RadioGroup radioGroup;
    RadioButton radioButtonA,radioButtonB,radioButtonC,radioButtonD;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    // set fullscreen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // xóa tiêu đề
        setContentView(R.layout.activity_part1);
        mapping();
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        FragmentPart1 fragmentPart1 = new FragmentPart1();
//        fragmentTransaction.add(R.id.frame_part1, fragmentPart1);
//        fragmentTransaction.commit();
//        ActivityPart1Binding activityPart1Binding = DataBindingUtil.setContentView(this, R.layout.activity_part1);
        //Question_Part1 question_part1 = new Question_Part1();
//        part1ViewModel = ViewModelProviders.of(this).get(Part1ViewModel.class);
//        part1ViewModel.init();
//        activityPart1Binding.setViewModel(part1ViewModel);
        //part1ViewModel.getQuestionPart1();

        //dùng livedata
        part1ViewModel = ViewModelProviders.of(this).get(Part1ViewModel.class);
        registerLiveDataListener();
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                part1ViewModel.previousQuestion();
            }
        });
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                part1ViewModel.nextQuestion();
            }
        });
    }

    private void mapping() {
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioButtonA = (RadioButton) findViewById(R.id.radioButton_A);
        radioButtonB = (RadioButton) findViewById(R.id.radioButton_B);
        radioButtonC = (RadioButton) findViewById(R.id.radioButton_C);
        radioButtonD = (RadioButton) findViewById(R.id.radioButton_D);
        buttonPrevious = (Button) findViewById(R.id.button_previous);
        buttonNext = (Button)findViewById(R.id.button_next);
        imageView = (ImageView) findViewById(R.id.image);
    }

    public void getLiveData(){

    }

    public void registerLiveDataListener(){
        part1ViewModel.getQuestion().observe(this, new Observer<Question_Part1>() {
            @Override
            public void onChanged(@Nullable Question_Part1 question_part1) {
                updateImage();
                radioButtonA.setText(part1ViewModel.getQuestion().getValue().getAnswerA());
                radioButtonB.setText(part1ViewModel.getQuestion().getValue().getAnswerB());
                radioButtonC.setText(part1ViewModel.getQuestion().getValue().getAnswerC());
                radioButtonD.setText(part1ViewModel.getQuestion().getValue().getAnswerD());
            }
        });
        part1ViewModel.getCurrentIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                Toast.makeText(Part1Activity.this, "alo", Toast.LENGTH_SHORT).show();
            }
        });
        part1ViewModel.getListQuestion().observe(this, new Observer<List<Question_Part1>>() {
            @Override
            public void onChanged(@Nullable List<Question_Part1> question_part1s) {

            }
        });
    }

    public void updateImage(){
        Picasso.with(this).load("https://lh6.googleusercontent.com/E5EYoD7Fl8rqmd4wMNdln5jfTJaQNO5NxoHMxeexgeNX9uBG4L1AzowkpkM7x_YgrrO7KIBtMIL61IQ7kag=w1366-h657")
                .placeholder(R.drawable.loading)
                .error(R.drawable.error_image)
                .into(imageView);
    }

}
