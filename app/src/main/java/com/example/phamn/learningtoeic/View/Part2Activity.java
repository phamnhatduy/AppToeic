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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Model.Part2OnPhone;
import com.example.phamn.learningtoeic.Model.Part1OnPhone;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.Repository.HistoryRepository;
import com.example.phamn.learningtoeic.View.MainActivity;
import com.example.phamn.learningtoeic.ViewModel.Part2ViewModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    HistoryRepository historyRepository = new HistoryRepository(getApplication());
    String serial = "Serial1";
    String title = "Test1";
    String audio = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();   // hide title bar

        setContentView(R.layout.activity_part2);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        title = intent.getStringExtra("titleName");
        serial = "Serial" + intent.getIntExtra("serialID", 1);
        audio = intent.getStringExtra("audio");

        part2ViewModel = ViewModelProviders.of(this).get(Part2ViewModel.class);
        part2ViewModel.setTitleName(serial, title);
        liveDataListener();

        // show dialog Loading
        dialogLoading = new Dialog(this, R.style.AppTheme);
        dialogLoading.setContentView(R.layout.loading_layout);
        dialogLoading.setCanceledOnTouchOutside(false);
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
                btnPause.setBackgroundResource(R.drawable.ic_play_arrow);
                showNoticeDialog("Bạn có muốn thoát?");
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    btnPause.setBackgroundResource(R.drawable.ic_play_arrow);
                }
                else {
                    mediaPlayer.start();
                    btnPause.setBackgroundResource(R.drawable.ic_pause);
                }
            }
        });

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
            tvLoading.setText("Lấy dữ liệu hoàn tất!");
            btnStart.setVisibility(View.VISIBLE);
            tvNumberOfQuestion.setVisibility(View.VISIBLE);
            tvTime.setVisibility(View.VISIBLE);

            tvNumberOfQuestion.setText("Số câu hỏi: "+ getIntent().getIntExtra("numberOfQuestion", 10));
            tvTime.setText("Thời gian: " + getIntent().getStringExtra("time"));

            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startProgressBarTime();
                    dialogLoading.cancel();
                }
            });
        }
    }

    public void initAudio(){
        String url = "https://myhost2018.000webhostapp.com/Serial1/" + title + "/Audio/" + title + "_Part2.m4a";
        //String url = "https://myhost2018.000webhostapp.com/Serial1/Test1/Audio/Test1_Part2.m4a";
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
        btnPause.setBackgroundResource(R.drawable.ic_pause);
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

        for (int i = 11; i <= 40; i++) {
            Button btn;
            btn = (Button)dialogNotice.findViewById(
                    dialogNotice.getContext().getResources().getIdentifier(
                            "button_" + String.valueOf(i).trim(),
                            "id",
                            getApplicationContext().getPackageName()));

            if(!part2ViewModel.getListQuestion().getValue().get(i - 11).getAnswerChosen().equals(""))
                btn.setBackgroundResource(R.drawable.question_chosen);
            final int x = i - 11;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioGroup.clearCheck();
                    part2ViewModel.updateQuestion(x);
                    dialogNotice.dismiss();
                }
            });
        }

        Button btnSubmit = (Button)dialogNotice.findViewById(R.id.button_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                btnPause.setBackgroundResource(R.drawable.ic_play_arrow);
                dialogNotice.cancel();
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
                finish();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
    }

    public void showScoreDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.score_layout);
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        TextView tvScore = (TextView)dialog.findViewById(R.id.textview_score);

        String result = getResult();
        tvScore.setText(result);
        Intent intent = getIntent();
        int partID = intent.getIntExtra("partID", 0);
        historyRepository.deleteHistory(partID);
        historyRepository.insert(new History(partID,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1),
                result)); // add to history
        tvScore.setText(result);

        Button btnHome = (Button)dialog.findViewById(R.id.button_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Button btnReview = (Button)dialog.findViewById(R.id.button_review);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPause.setBackgroundResource(R.drawable.ic_pause);
                btnSubmit.setVisibility(View.INVISIBLE);
                isTesting = false;
                part2ViewModel.updateQuestion(0);   // the first question
                startProgressBarTime();
                dialog.cancel();
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
                            getRadioButton(questionPart2.getAnswerChosen()).setTextColor(Color.parseColor("#FF01D71A"));
                        } else {
                            if (!questionPart2.getAnswerChosen().equals(questionPart2.getCorrectAnswer())) {
                                getRadioButton(questionPart2.getAnswerChosen()).setTextColor(Color.parseColor("#FFF90000"));
                                getRadioButton(questionPart2.getCorrectAnswer()).setTextColor(Color.parseColor("#FF01D71A"));
                            }
                        }
                    }
                    else
                        getRadioButton(questionPart2.getCorrectAnswer()).setTextColor(Color.parseColor("#FF01D71A"));
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        mediaPlayer.pause();
        btnPause.setBackgroundResource(R.drawable.ic_play_arrow);
        showNoticeDialog("Bạn có muốn thoát?");
    }
}
