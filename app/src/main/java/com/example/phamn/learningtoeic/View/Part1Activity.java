package com.example.phamn.learningtoeic.View;

import android.app.Activity;
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

import com.example.phamn.learningtoeic.Model.QuestionPart1;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.ViewModel.Part1ViewModel;
import com.example.phamn.learningtoeic.databinding.ActivityPart1Binding;
import com.example.phamn.learningtoeic.databinding.FragmentPart1Binding;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Part1Activity extends AppCompatActivity {
    Part1ViewModel part1ViewModel;
    @BindView(R.id.button_previous) Button buttonPrevious;
    @BindView(R.id.button_next) Button buttonNext;
    @BindView(R.id.radio_group) RadioGroup radioGroup;
    @BindView(R.id.radioButton_A) RadioButton radioButtonA;
    @BindView(R.id.radioButton_B) RadioButton radioButtonB;
    @BindView(R.id.radioButton_C) RadioButton radioButtonC;
    @BindView(R.id.radioButton_D) RadioButton radioButtonD;
    @BindView(R.id.image) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    // set fullscreen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // xóa tiêu đề
        setContentView(R.layout.activity_part1);
        ButterKnife.bind(this);
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
        liveDataListener();

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

    public void liveDataListener(){
        part1ViewModel.getQuestion().observe(this, new Observer<QuestionPart1>() {
            @Override
            public void onChanged(@Nullable QuestionPart1 questionPart1) {
                radioButtonA.setText(questionPart1.getAnswerA());
                radioButtonB.setText(questionPart1.getAnswerB());
                radioButtonC.setText(questionPart1.getAnswerC());
                radioButtonD.setText(questionPart1.getAnswerD());
                Picasso.with(getApplicationContext()).load(questionPart1.getImage())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error_image)
                        .into(imageView);
            }
        });
        part1ViewModel.getCurrentIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                //Toast.makeText(Part1Activity.this, ""+ integer, Toast.LENGTH_SHORT).show();
            }
        });
        part1ViewModel.getListQuestion().observe(this, new Observer<List<QuestionPart1>>() {
            @Override
            public void onChanged(@Nullable List<QuestionPart1> question_part1s) {

            }
        });
    }
}
