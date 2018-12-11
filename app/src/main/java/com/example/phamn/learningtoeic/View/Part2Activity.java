package com.example.phamn.learningtoeic.View;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.phamn.learningtoeic.Model.Part2OnPhone;
import com.example.phamn.learningtoeic.Model.Part1OnPhone;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.View.MainActivity;
import com.example.phamn.learningtoeic.ViewModel.Part2ViewModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Part2Activity extends AppCompatActivity {
    Part2ViewModel part2ViewModel;
    @BindView(R.id.button_previous) Button btnPreviousTime;
    @BindView(R.id.button_next) Button btnNextTime;
    @BindView(R.id.button_previous_question) Button btnPreviousQuestion;
    @BindView(R.id.button_next_question) Button btnNextQuestion;
    @BindView(R.id.button_back) Button btnBack;
    @BindView(R.id.button_pause) Button btnPause;
    @BindView(R.id.button_submit) Button btnSubmit;
    @BindView(R.id.tv_question_content) TextView tvQuestionContent;
    @BindView(R.id.radio_group) RadioGroup radioGroup;
    @BindView(R.id.radioButton_A) RadioButton radioButtonA;
    @BindView(R.id.radioButton_B) RadioButton radioButtonB;
    @BindView(R.id.radioButton_C) RadioButton radioButtonC;
    @BindView(R.id.seekbar_time) SeekBar seekBar;
    @BindView(R.id.tv_current_time) TextView tvCurrentTime;
    @BindView(R.id.tv_total_time) TextView tvTotalTime;

    MediaPlayer mediaPlayer = new MediaPlayer();
    Dialog dialogLoading;
    boolean isTesting = true; // reviewing -> isTesting = false

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String titleName = intent.getStringExtra("titleName");

        part2ViewModel = ViewModelProviders.of(this).get(Part2ViewModel.class);
        part2ViewModel.setTitleName(titleName);
        liveDataListener();

        // show dialog Loading
        showLoadingDialog(false);
        //
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
                mediaPlayer.pause();
                btnPause.setText("Continue");
                showNoticeDialog("Are you sure want to back?");
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

        btnPreviousTime.setText("<<");
        btnNextTime.setText(">>");

        btnPreviousTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.getCurrentPosition() > 5000)
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 5000);
                else
                    mediaPlayer.seekTo(0);
            }
        });
        btnNextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.getCurrentPosition() < (mediaPlayer.getDuration() - 5000))
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 5000);
                else
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
            }
        });

        btnPreviousQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                part2ViewModel.previousQuestion();
            }
        });
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup.clearCheck();
                part2ViewModel.nextQuestion();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radioButton_A:
                        part2ViewModel.changeAnswer("A");
                        //Toast.makeText(Part1Activity.this, ""+ R.id.radioButton_A, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton_B:
                        part2ViewModel.changeAnswer("B");
                        break;
                    case R.id.radioButton_C:
                        part2ViewModel.changeAnswer("C");
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

    public void showLoadingDialog(boolean successful){
        if(successful == false) { // đang tải
            dialogLoading = new Dialog(this, R.style.AppTheme);
            //dialogLoading.setTitle("LearningToeic");
            dialogLoading.setContentView(R.layout.loading_layout);
            dialogLoading.show();
            dialogLoading.setCanceledOnTouchOutside(false);
            Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            ImageView ivLoading = dialogLoading.findViewById(R.id.iv_loading);
            ivLoading.startAnimation(animRotate);
            Button btnStart = dialogLoading.findViewById(R.id.btn_start);
            btnStart.setVisibility(View.INVISIBLE);
        }
        else {  // tải thành công
            ImageView ivLoading = dialogLoading.findViewById(R.id.iv_loading);
            ivLoading.clearAnimation();
            ivLoading.setImageResource(R.drawable.success);
            TextView tvLoading = dialogLoading.findViewById(R.id.tv_loading);
            tvLoading.setText("Load Successfully!");
            Button btnStart = dialogLoading.findViewById(R.id.btn_start);
            btnStart.setVisibility(View.VISIBLE);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startProgressBarTime();
                    dialogLoading.hide();
                }
            });
        }
    }

    public void initAudio(){
        String url = "https://myhost2018.000webhostapp.com/Test1/Audio/Test1_Part2.m4a";
//       String url = "https://myhost2018.000webhostapp.com/Test1/Audio/TestAudio.mp3";
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
                seekBar.setMax(mediaPlayer.getDuration());
                showLoadingDialog(true);
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
        btnPause.setText("Pause");
        mediaPlayer.start();
        mediaPlayer.seekTo(0);
        seekBar.setProgress(0);

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
        dialogNotice.setContentView(R.layout.end_part2_layout);
        dialogNotice.show();
        dialogNotice.setCanceledOnTouchOutside(true);
        Button btn11 = (Button)dialogNotice.findViewById(R.id.button_11);
        if(!part2ViewModel.getListQuestion().getValue().get(0).getAnswerChosen().equals(""))
            btn11.setBackgroundResource(R.drawable.question_chosen);
        Button btn12 = (Button)dialogNotice.findViewById(R.id.button_12);
        if(!part2ViewModel.getListQuestion().getValue().get(1).getAnswerChosen().equals(""))
            btn12.setBackgroundResource(R.drawable.question_chosen);
        Button btn13 = (Button)dialogNotice.findViewById(R.id.button_13);
        if(!part2ViewModel.getListQuestion().getValue().get(2).getAnswerChosen().equals(""))
            btn13.setBackgroundResource(R.drawable.question_chosen);
        Button btn14 = (Button)dialogNotice.findViewById(R.id.button_14);
        if(!part2ViewModel.getListQuestion().getValue().get(3).getAnswerChosen().equals(""))
            btn14.setBackgroundResource(R.drawable.question_chosen);
        Button btn15 = (Button)dialogNotice.findViewById(R.id.button_15);
        if(!part2ViewModel.getListQuestion().getValue().get(4).getAnswerChosen().equals(""))
            btn15.setBackgroundResource(R.drawable.question_chosen);
        Button btn16 = (Button)dialogNotice.findViewById(R.id.button_16);
        if(!part2ViewModel.getListQuestion().getValue().get(5).getAnswerChosen().equals(""))
            btn16.setBackgroundResource(R.drawable.question_chosen);
        Button btn17 = (Button)dialogNotice.findViewById(R.id.button_17);
        if(!part2ViewModel.getListQuestion().getValue().get(6).getAnswerChosen().equals(""))
            btn17.setBackgroundResource(R.drawable.question_chosen);
        Button btn8 = (Button)dialogNotice.findViewById(R.id.button_18);
        if(!part2ViewModel.getListQuestion().getValue().get(7).getAnswerChosen().equals(""))
            btn8.setBackgroundResource(R.drawable.question_chosen);
        Button btn19 = (Button)dialogNotice.findViewById(R.id.button_19);
        if(!part2ViewModel.getListQuestion().getValue().get(8).getAnswerChosen().equals(""))
            btn19.setBackgroundResource(R.drawable.question_chosen);
        Button btn20 = (Button)dialogNotice.findViewById(R.id.button_20);
        if(!part2ViewModel.getListQuestion().getValue().get(9).getAnswerChosen().equals(""))
            btn20.setBackgroundResource(R.drawable.question_chosen);

        Button btn21 = (Button)dialogNotice.findViewById(R.id.button_21);
        if(!part2ViewModel.getListQuestion().getValue().get(10).getAnswerChosen().equals(""))
            btn21.setBackgroundResource(R.drawable.question_chosen);
        Button btn22 = (Button)dialogNotice.findViewById(R.id.button_22);
        if(!part2ViewModel.getListQuestion().getValue().get(11).getAnswerChosen().equals(""))
            btn22.setBackgroundResource(R.drawable.question_chosen);
        Button btn23 = (Button)dialogNotice.findViewById(R.id.button_23);
        if(!part2ViewModel.getListQuestion().getValue().get(12).getAnswerChosen().equals(""))
            btn23.setBackgroundResource(R.drawable.question_chosen);
        Button btn24 = (Button)dialogNotice.findViewById(R.id.button_24);
        if(!part2ViewModel.getListQuestion().getValue().get(13).getAnswerChosen().equals(""))
            btn24.setBackgroundResource(R.drawable.question_chosen);
        Button btn25 = (Button)dialogNotice.findViewById(R.id.button_25);
        if(!part2ViewModel.getListQuestion().getValue().get(14).getAnswerChosen().equals(""))
            btn25.setBackgroundResource(R.drawable.question_chosen);
        Button btn26 = (Button)dialogNotice.findViewById(R.id.button_26);
        if(!part2ViewModel.getListQuestion().getValue().get(15).getAnswerChosen().equals(""))
            btn26.setBackgroundResource(R.drawable.question_chosen);
        Button btn27 = (Button)dialogNotice.findViewById(R.id.button_27);
        if(!part2ViewModel.getListQuestion().getValue().get(16).getAnswerChosen().equals(""))
            btn27.setBackgroundResource(R.drawable.question_chosen);
        Button btn28 = (Button)dialogNotice.findViewById(R.id.button_28);
        if(!part2ViewModel.getListQuestion().getValue().get(17).getAnswerChosen().equals(""))
            btn28.setBackgroundResource(R.drawable.question_chosen);
        Button btn29 = (Button)dialogNotice.findViewById(R.id.button_29);
        if(!part2ViewModel.getListQuestion().getValue().get(18).getAnswerChosen().equals(""))
            btn29.setBackgroundResource(R.drawable.question_chosen);
        Button btn30 = (Button)dialogNotice.findViewById(R.id.button_30);
        if(!part2ViewModel.getListQuestion().getValue().get(19).getAnswerChosen().equals(""))
            btn30.setBackgroundResource(R.drawable.question_chosen);

        Button btn31 = (Button)dialogNotice.findViewById(R.id.button_31);
        if(!part2ViewModel.getListQuestion().getValue().get(20).getAnswerChosen().equals(""))
            btn31.setBackgroundResource(R.drawable.question_chosen);
        Button btn32 = (Button)dialogNotice.findViewById(R.id.button_32);
        if(!part2ViewModel.getListQuestion().getValue().get(21).getAnswerChosen().equals(""))
            btn32.setBackgroundResource(R.drawable.question_chosen);
        Button btn33 = (Button)dialogNotice.findViewById(R.id.button_33);
        if(!part2ViewModel.getListQuestion().getValue().get(22).getAnswerChosen().equals(""))
            btn33.setBackgroundResource(R.drawable.question_chosen);
        Button btn34 = (Button)dialogNotice.findViewById(R.id.button_34);
        if(!part2ViewModel.getListQuestion().getValue().get(23).getAnswerChosen().equals(""))
            btn34.setBackgroundResource(R.drawable.question_chosen);
        Button btn35 = (Button)dialogNotice.findViewById(R.id.button_35);
        if(!part2ViewModel.getListQuestion().getValue().get(24).getAnswerChosen().equals(""))
            btn35.setBackgroundResource(R.drawable.question_chosen);
        Button btn36 = (Button)dialogNotice.findViewById(R.id.button_36);
        if(!part2ViewModel.getListQuestion().getValue().get(25).getAnswerChosen().equals(""))
            btn36.setBackgroundResource(R.drawable.question_chosen);
        Button btn37 = (Button)dialogNotice.findViewById(R.id.button_37);
        if(!part2ViewModel.getListQuestion().getValue().get(26).getAnswerChosen().equals(""))
            btn37.setBackgroundResource(R.drawable.question_chosen);
        Button btn38 = (Button)dialogNotice.findViewById(R.id.button_38);
        if(!part2ViewModel.getListQuestion().getValue().get(27).getAnswerChosen().equals(""))
            btn38.setBackgroundResource(R.drawable.question_chosen);
        Button btn39 = (Button)dialogNotice.findViewById(R.id.button_39);
        if(!part2ViewModel.getListQuestion().getValue().get(28).getAnswerChosen().equals(""))
            btn39.setBackgroundResource(R.drawable.question_chosen);
        Button btn40 = (Button)dialogNotice.findViewById(R.id.button_40);
        if(!part2ViewModel.getListQuestion().getValue().get(29).getAnswerChosen().equals(""))
            btn40.setBackgroundResource(R.drawable.question_chosen);

        Button btnSubmit = (Button)dialogNotice.findViewById(R.id.button_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                btnPause.setText("Continue");
                dialogNotice.hide();
                showScoreDialog();
            }
        });
    }

    public void showNoticeDialog(String content) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.notice_layout);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        TextView tvNotice = (TextView)dialog.findViewById(R.id.tv_notice);
        tvNotice.setText(content);
        Button btnYes = (Button)dialog.findViewById(R.id.btn_yes);
        Button btnNo = (Button)dialog.findViewById(R.id.btn_no);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.hide();
            }
        });
    }

    public void showScoreDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.score_layout);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        TextView tvScore = (TextView)dialog.findViewById(R.id.textview_score);
        tvScore.setText(getResult());
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
                btnSubmit.setVisibility(View.INVISIBLE);
                isTesting = false;
                part2ViewModel.updateQuestion(0);   // the first question
                startProgressBarTime();
                dialog.hide();
            }
        });
    }

    public void liveDataListener(){
        part2ViewModel.getQuestion().observe(this, new Observer<Part2OnPhone>() {
            @Override
            public void onChanged(@Nullable Part2OnPhone questionPart2) {
                tvQuestionContent.setText("" + questionPart2.getQuestionNumber()+".");
                if (!questionPart2.getAnswerChosen().equals("")) {
                    switch (questionPart2.getAnswerChosen()) {
                        case "A":
                            radioButtonA.setChecked(true);
                            break;
                        case "B":
                            radioButtonB.setChecked(true);
                            break;
                        case "C":
                            radioButtonC.setChecked(true);
                            break;
                    }
                }

                if (!isTesting) {    // is reviewing
                    tvQuestionContent.setText("" + questionPart2.getQuestionNumber() + ". " + questionPart2.getQuestionContent());
                    radioButtonA.setText("A. " + questionPart2.getAnswerA());
                    radioButtonB.setText("B. " + questionPart2.getAnswerB());
                    radioButtonC.setText("C. " + questionPart2.getAnswerC());
                    radioButtonA.setTextColor(Color.parseColor("#000000"));
                    radioButtonA.setEnabled(false);
                    radioButtonB.setTextColor(Color.parseColor("#000000"));
                    radioButtonB.setEnabled(false);
                    radioButtonC.setTextColor(Color.parseColor("#000000"));
                    radioButtonC.setEnabled(false);
                    if (!questionPart2.getAnswerChosen().trim().equals("")){
                        if (questionPart2.getAnswerChosen().equals(questionPart2.getCorrectAnswer())) {
                            getRadioButton(questionPart2.getAnswerChosen()).setTextColor(Color.parseColor("#00EE00"));
                        } else {
                            if (!questionPart2.getAnswerChosen().equals(questionPart2.getCorrectAnswer())) {
                                getRadioButton(questionPart2.getAnswerChosen()).setTextColor(Color.parseColor("#FF0000"));
                                getRadioButton(questionPart2.getCorrectAnswer()).setTextColor(Color.parseColor("#00FF00"));
                            }
                        }
                    }
                    else
                        getRadioButton(questionPart2.getCorrectAnswer()).setTextColor(Color.parseColor("#00FF00"));
                }
            }
        });

        part2ViewModel.getListQuestion().observe(this, new Observer<List<Part2OnPhone>>() {
            @Override
            public void onChanged(@Nullable List<Part2OnPhone> question_part2s) {

            }
        });

        part2ViewModel.getCurrentIndex().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if(integer == 0)
                    btnPreviousQuestion.setEnabled(false);
                else
                    btnPreviousQuestion.setEnabled(true);
                if (integer == 30 - 1)
                    btnNextQuestion.setEnabled(false);
                else
                    btnNextQuestion.setEnabled(true);
            }
        });

        part2ViewModel.getListAnswerChosen().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(@Nullable List<String> strings) {

            }
        });
    }

    public String getResult(){
        int score = 0;
        for (int i = 0; i < part2ViewModel.getListQuestion().getValue().size(); i++){
            if(part2ViewModel.getListQuestion().getValue().get(i).getAnswerChosen().trim().equals(
                    part2ViewModel.getListQuestion().getValue().get(i).getCorrectAnswer().trim())) {
                score += 1;

            }
        }
        return score + "/" + part2ViewModel.getListQuestion().getValue().size();
    }

    public RadioButton getRadioButton(String anwser){
        if (anwser.equals("A"))
            return radioButtonA;
        if (anwser.equals("B"))
            return radioButtonB;
        return radioButtonC;
    }
}
