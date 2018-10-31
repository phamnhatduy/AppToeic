package com.example.phamn.learningtoeic.View;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.phamn.learningtoeic.Model.QuestionPart1;
import com.example.phamn.learningtoeic.Model.Question_Part1;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.ViewModel.Part1ViewModel;
import com.example.phamn.learningtoeic.databinding.ActivityPart1Binding;
import com.example.phamn.learningtoeic.databinding.FragmentPart1Binding;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Part1Activity extends AppCompatActivity {
    Part1ViewModel part1ViewModel;
    @BindView(R.id.button_previous) Button btnPreviousTime;
    @BindView(R.id.button_next) Button btnNextTime;
    @BindView(R.id.button_previous_question) Button btnPreviousQuestion;
    @BindView(R.id.button_next_question) Button btnNextQuestion;
    @BindView(R.id.button_back) Button btnBack;
    @BindView(R.id.button_pause) Button btnPause;
    @BindView(R.id.button_submit) Button btnSubmit;
    @BindView(R.id.radio_group) RadioGroup radioGroup;
    @BindView(R.id.radioButton_A) RadioButton radioButtonA;
    @BindView(R.id.radioButton_B) RadioButton radioButtonB;
    @BindView(R.id.radioButton_C) RadioButton radioButtonC;
    @BindView(R.id.radioButton_D) RadioButton radioButtonD;
    @BindView(R.id.image) ImageView imageView;
    @BindView(R.id.tv_number) TextView tvNumber;
    @BindView(R.id.seekbar_time) SeekBar seekBar;
    @BindView(R.id.tv_current_time) TextView tvCurrentTime;
    @BindView(R.id.tv_total_time) TextView tvTotalTime;

    MediaPlayer mediaPlayer = new MediaPlayer();
    Dialog dialogLoading;
    boolean isTesting = true; // reviewing -> isTesting = false
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();   // hide title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);    // set fullscreen
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

        // show dialog Loading
        showDialogLoading();

        initAudio();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvCurrentTime.setText(new SimpleDateFormat("mm:ss").format(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPause.setText("Continue");
                }
                else {
                    mediaPlayer.start();
                    btnPause.setText("Pause");
                }
            }
        });

        btnPreviousTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
            }
        });

        btnPreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                part1ViewModel.previousQuestion();
            }
        });
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                part1ViewModel.nextQuestion();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButton_A:
                        part1ViewModel.changeAnswer("A");
                        //Toast.makeText(Part1Activity.this, ""+ R.id.radioButton_A, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton_B:
                        part1ViewModel.changeAnswer("B");
                        break;
                    case R.id.radioButton_C:
                        part1ViewModel.changeAnswer("C");
                        break;
                    case R.id.radioButton_D:
                        part1ViewModel.changeAnswer("D");
                        break;
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSubmitDialog();
            }
        });
    }

    public void showDialogLoading(){
        dialogLoading = new Dialog(this);
        dialogLoading.setContentView(R.layout.loading_layout);
        dialogLoading.show();
        dialogLoading.setCanceledOnTouchOutside(false);
        Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        ImageView ivLoading = dialogLoading.findViewById(R.id.iv_loading);
        ivLoading.startAnimation(animRotate);
    }

    public void initAudio(){
        String url = "https://myhost2018.000webhostapp.com/Test1/Part1/Audio/Test1_Part1.m4a";
//        String url = "http://myhost2018.byethost31.com/Toeic/Test1/Part1/Audio/Test1_Part1.m4a";
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //Toast.makeText(Part1Activity.this, "" + mediaPlayer.getDuration(), Toast.LENGTH_SHORT).show();
                //mp.start();
                tvTotalTime.setText(new SimpleDateFormat("mm:ss").format(mediaPlayer.getDuration()));
                startProgressBarTime();
                if(dialogLoading.isShowing())
                    dialogLoading.hide();
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }

    public void startProgressBarTime(){
        tvCurrentTime.setText("00:00");
        mediaPlayer.start();
        mediaPlayer.seekTo(0);
        seekBar.setProgress(0);
        seekBar.setMax(mediaPlayer.getDuration());

        updateTime();
    }

    public void updateTime(){

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer.isPlaying()) {
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    tvCurrentTime.setText(new SimpleDateFormat("mm:ss").format(mediaPlayer.getCurrentPosition()));
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            showSubmitDialog();
                            //finish();
                        }
                    });
                }
                handler.postDelayed(this, 500);
            }
        }, 100);
    }

    public void showSubmitDialog() {
        final Dialog dialogNotice = new Dialog(this);
        dialogNotice.setContentView(R.layout.end_part1_layout);
        dialogNotice.show();
        dialogNotice.setCanceledOnTouchOutside(true);
        Button btn1 = (Button)dialogNotice.findViewById(R.id.button_1);
        if(!part1ViewModel.getListQuestion().getValue().get(0).getAnswerChosen().equals(""))
            btn1.setBackgroundResource(R.drawable.question_chosen);
        Button btn2 = (Button)dialogNotice.findViewById(R.id.button_2);
        if(!part1ViewModel.getListQuestion().getValue().get(1).getAnswerChosen().equals(""))
            btn2.setBackgroundResource(R.drawable.question_chosen);
        Button btn3 = (Button)dialogNotice.findViewById(R.id.button_3);
        if(!part1ViewModel.getListQuestion().getValue().get(2).getAnswerChosen().equals(""))
            btn3.setBackgroundResource(R.drawable.question_chosen);
        Button btn4 = (Button)dialogNotice.findViewById(R.id.button_4);
        if(!part1ViewModel.getListQuestion().getValue().get(3).getAnswerChosen().equals(""))
            btn4.setBackgroundResource(R.drawable.question_chosen);
        Button btn5 = (Button)dialogNotice.findViewById(R.id.button_5);
        if(!part1ViewModel.getListQuestion().getValue().get(4).getAnswerChosen().equals(""))
            btn5.setBackgroundResource(R.drawable.question_chosen);
        Button btn6 = (Button)dialogNotice.findViewById(R.id.button_6);
        if(!part1ViewModel.getListQuestion().getValue().get(5).getAnswerChosen().equals(""))
            btn6.setBackgroundResource(R.drawable.question_chosen);
        Button btn7 = (Button)dialogNotice.findViewById(R.id.button_7);
        if(!part1ViewModel.getListQuestion().getValue().get(6).getAnswerChosen().equals(""))
            btn7.setBackgroundResource(R.drawable.question_chosen);
        Button btn8 = (Button)dialogNotice.findViewById(R.id.button_8);
        if(!part1ViewModel.getListQuestion().getValue().get(7).getAnswerChosen().equals(""))
            btn8.setBackgroundResource(R.drawable.question_chosen);
        Button btn9 = (Button)dialogNotice.findViewById(R.id.button_9);
        if(!part1ViewModel.getListQuestion().getValue().get(8).getAnswerChosen().equals(""))
            btn9.setBackgroundResource(R.drawable.question_chosen);
        Button btn10 = (Button)dialogNotice.findViewById(R.id.button_10);
        if(!part1ViewModel.getListQuestion().getValue().get(9).getAnswerChosen().equals(""))
            btn10.setBackgroundResource(R.drawable.question_chosen);

        Button btnSubmit = (Button)dialogNotice.findViewById(R.id.button_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                dialogNotice.hide();
                showScoreDialog();
            }
        });
    }

    public void showScoreDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.score_layout);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        TextView tvScore = (TextView)dialog.findViewById(R.id.textview_score);
        tvScore.setText(result());
        Button btnHome = (Button)dialog.findViewById(R.id.button_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Button btnReview = (Button)dialog.findViewById(R.id.button_review);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTesting = false;
                part1ViewModel.updateQuestion(0);   // the first question
                startProgressBarTime();
                dialog.hide();
            }
        });
    }

    public void liveDataListener(){
        part1ViewModel.getQuestion().observe(this, new Observer<Question_Part1>() {
            @Override
            public void onChanged(@Nullable Question_Part1 questionPart1) {
                tvNumber.setText("" + questionPart1.getQuestionNumber());
                radioButtonA.setText("A. " + questionPart1.getAnswerA());
                radioButtonB.setText("B. " + questionPart1.getAnswerB());
                radioButtonC.setText("C. " + questionPart1.getAnswerC());
                radioButtonD.setText("D. " + questionPart1.getAnswerD());
//                Picasso.with(getApplicationContext()).load(questionPart1.getImage())
//                        .placeholder(R.drawable.loading)
//                        .error(R.drawable.error_image)
//                        .into(imageView);
                Glide.with(getApplicationContext())
                        .load(questionPart1.getImage())
                        //.load("https://myhost2018.000webhostapp.com/Test1/Part1/image/1.jpg")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);

                    if (!questionPart1.getAnswerChosen().equals("")) {
                        //Toast.makeText(Part1Activity.this, "" + questionPart1.getAnswerChosen(), Toast.LENGTH_SHORT).show();
                        switch (questionPart1.getAnswerChosen()) {
                            case "A":
                                radioButtonA.setChecked(true);
                                break;
                            case "B":
                                radioButtonB.setChecked(true);
                                break;
                            case "C":
                                radioButtonC.setChecked(true);
                                break;
                            case "D":
                                radioButtonD.setChecked(true);
                                break;
                        }
                    } else {
                        //Toast.makeText(Part1Activity.this, "chưa chọn", Toast.LENGTH_SHORT).show();
                    }

                if (!isTesting) {    // is reviewing
                    radioButtonA.setTextColor(Color.parseColor("#000000"));
                    radioButtonA.setEnabled(false);
                    radioButtonB.setTextColor(Color.parseColor("#000000"));
                    radioButtonB.setEnabled(false);
                    radioButtonC.setTextColor(Color.parseColor("#000000"));
                    radioButtonC.setEnabled(false);
                    radioButtonD.setTextColor(Color.parseColor("#000000"));
                    radioButtonD.setEnabled(false);
                    if (!questionPart1.getAnswerChosen().trim().equals("")){
                        if (questionPart1.getAnswerChosen().equals(questionPart1.getCorrectAnswer())) {
                            getRadioButton(questionPart1.getAnswerChosen()).setTextColor(Color.parseColor("#00EE00"));
                        } else {
                            if (!questionPart1.getAnswerChosen().equals(questionPart1.getCorrectAnswer())) {
                                getRadioButton(questionPart1.getAnswerChosen()).setTextColor(Color.parseColor("#FF0000"));
                                getRadioButton(questionPart1.getCorrectAnswer()).setTextColor(Color.parseColor("#00FF00"));
                            }
                        }
                    }
                    else
                        getRadioButton(questionPart1.getCorrectAnswer()).setTextColor(Color.parseColor("#00FF00"));
                }
            }
        });

        part1ViewModel.getListQuestion().observe(this, new Observer<List<Question_Part1>>() {
            @Override
            public void onChanged(@Nullable List<Question_Part1> question_part1s) {

            }
        });

        part1ViewModel.getCurrentIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if(integer == 0)
                    btnPreviousQuestion.setEnabled(false);
                else
                    btnPreviousQuestion.setEnabled(true);
                if (integer == 10 - 1)
                    btnNextQuestion.setEnabled(false);
                else
                    btnNextQuestion.setEnabled(true);
            }
        });

        part1ViewModel.getListAnswerChosen().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {

            }
        });
    }

    public String result(){
        int score = 0;
        for (int i = 0; i < part1ViewModel.getListQuestion().getValue().size(); i++){
            if(part1ViewModel.getListQuestion().getValue().get(i).getAnswerChosen().trim().equals(
                    part1ViewModel.getListQuestion().getValue().get(i).getCorrectAnswer().trim())) {
                score += 1;

            }
        }
        return score + "/" + part1ViewModel.getListQuestion().getValue().size();
    }

    public RadioButton getRadioButton(String anwser){
        if (anwser.equals("A"))
            return radioButtonA;
        if (anwser.equals("B"))
            return radioButtonB;
        if (anwser.equals("C"))
            return radioButtonC;
        return radioButtonD;
    }
}
