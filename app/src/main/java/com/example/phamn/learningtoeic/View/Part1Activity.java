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
import com.example.phamn.learningtoeic.Model.Part1OnPhone;
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

        Intent intent = getIntent();
        String titleName = intent.getStringExtra("titleName");

        //dùng livedata
        part1ViewModel = ViewModelProviders.of(this).get(Part1ViewModel.class);
        part1ViewModel.setTitleName(titleName);
        liveDataListener();

        // show dialog Loading
        dialogLoading = new Dialog(this, R.style.AppTheme);
        //dialogLoading.setTitle("LearningToeic");
        dialogLoading.setContentView(R.layout.loading_layout);
        dialogLoading.setCanceledOnTouchOutside(false);
        showLoadingDialog(false);

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

    public void showLoadingDialog(boolean successful){
        TextView tvNumberOfQuestion = dialogLoading.findViewById(R.id.tv_question);
        TextView tvTime = dialogLoading.findViewById(R.id.tv_time);
        ImageView ivLoading = dialogLoading.findViewById(R.id.iv_loading);
        Button btnStart = dialogLoading.findViewById(R.id.btn_start);

        if(successful == false) { // đang tải
            Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            ivLoading.startAnimation(animRotate);
            btnStart.setVisibility(View.INVISIBLE);
            tvNumberOfQuestion.setVisibility(View.INVISIBLE);
            tvTime.setVisibility(View.INVISIBLE);
            dialogLoading.show();
        }
        else {  // tải thành công
            ivLoading.clearAnimation();
            ivLoading.setImageResource(R.drawable.success);
            TextView tvLoading = dialogLoading.findViewById(R.id.tv_loading);
            tvLoading.setText("Load Successfully!");
            btnStart.setVisibility(View.VISIBLE);
            tvNumberOfQuestion.setVisibility(View.VISIBLE);
            tvTime.setVisibility(View.VISIBLE);

            tvNumberOfQuestion.setText("Number of question: "+ getIntent().getIntExtra("numberOfQuestion", 10));
            tvTime.setText("Time: " + getIntent().getStringExtra("time"));

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
        String url = "https://myhost2018.000webhostapp.com/Test1/Audio/Test1_Part1.m4a";
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
                            if(isTesting)
                                showSubmitDialog();
                            else
                                showScoreDialog();
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
                btnPause.setText("Continue");
                dialogNotice.hide();
                showScoreDialog();
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
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
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
                part1ViewModel.updateQuestion(0);   // the first question
                startProgressBarTime();
                dialog.hide();
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
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
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

    public void liveDataListener(){
        part1ViewModel.getQuestion().observe(this, new Observer<Part1OnPhone>() {
            @Override
            public void onChanged(@Nullable Part1OnPhone questionPart1) {
                tvNumber.setText("" + questionPart1.getQuestionNumber());
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
                    radioButtonA.setText("A. " + questionPart1.getAnswerA());
                    radioButtonB.setText("B. " + questionPart1.getAnswerB());
                    radioButtonC.setText("C. " + questionPart1.getAnswerC());
                    radioButtonD.setText("D. " + questionPart1.getAnswerD());

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
                            getRadioButton(questionPart1.getAnswerChosen()).setTextColor(Color.parseColor("#FF01D71A"));
                        } else {
                            if (!questionPart1.getAnswerChosen().equals(questionPart1.getCorrectAnswer())) {
                                getRadioButton(questionPart1.getAnswerChosen()).setTextColor(Color.parseColor("#FFF90000"));
                                getRadioButton(questionPart1.getCorrectAnswer()).setTextColor(Color.parseColor("#FF01D71A"));
                            }
                        }
                    }
                    else
                        getRadioButton(questionPart1.getCorrectAnswer()).setTextColor(Color.parseColor("#FF01D71A"));
                }
            }
        });

        part1ViewModel.getListQuestion().observe(this, new Observer<List<Part1OnPhone>>() {
            @Override
            public void onChanged(@Nullable List<Part1OnPhone> question_part1s) {

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
    }

    public String getResult(){
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
