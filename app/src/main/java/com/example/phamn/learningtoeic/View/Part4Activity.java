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
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Model.Part4OnPhone;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.Repository.HistoryRepository;
import com.example.phamn.learningtoeic.ViewModel.Part4ViewModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Part4Activity extends AppCompatActivity {
    Part4ViewModel part4ViewModel;
    @BindView(R.id.scroll_view) ScrollView scrollView;
    @BindView(R.id.button_previous) Button btnPreviousTime;
    @BindView(R.id.button_next) Button btnNextTime;
    @BindView(R.id.button_previous_question) Button btnPreviousQuestion;
    @BindView(R.id.button_next_question) Button btnNextQuestion;
    @BindView(R.id.button_back) Button btnBack;
    @BindView(R.id.button_pause) Button btnPause;
    @BindView(R.id.button_submit) Button btnSubmit;
    @BindView(R.id.seekbar_time) SeekBar seekBar;
    @BindView(R.id.tv_current_time) TextView tvCurrentTime;
    @BindView(R.id.tv_total_time) TextView tvTotalTime;

    @BindView(R.id.tv_question_content_1) TextView tvQuestionContent1;
    @BindView(R.id.radio_group_1) RadioGroup radioGroup1;
    @BindView(R.id.radio_button_a_1) RadioButton radioButtonA1;
    @BindView(R.id.radio_button_b_1) RadioButton radioButtonB1;
    @BindView(R.id.radio_button_c_1) RadioButton radioButtonC1;
    @BindView(R.id.radio_button_d_1) RadioButton radioButtonD1;
    @BindView(R.id.tv_question_content_2) TextView tvQuestionContent2;
    @BindView(R.id.radio_group_2) RadioGroup radioGroup2;
    @BindView(R.id.radio_button_a_2) RadioButton radioButtonA2;
    @BindView(R.id.radio_button_b_2) RadioButton radioButtonB2;
    @BindView(R.id.radio_button_c_2) RadioButton radioButtonC2;
    @BindView(R.id.radio_button_d_2) RadioButton radioButtonD2;
    @BindView(R.id.tv_question_content_3) TextView tvQuestionContent3;
    @BindView(R.id.radio_group_3) RadioGroup radioGroup3;
    @BindView(R.id.radio_button_a_3) RadioButton radioButtonA3;
    @BindView(R.id.radio_button_b_3) RadioButton radioButtonB3;
    @BindView(R.id.radio_button_c_3) RadioButton radioButtonC3;
    @BindView(R.id.radio_button_d_3) RadioButton radioButtonD3;
    @BindView(R.id.tv_question_content_4) TextView tvQuestionContent4;
    @BindView(R.id.radio_group_4) RadioGroup radioGroup4;
    @BindView(R.id.radio_button_a_4) RadioButton radioButtonA4;
    @BindView(R.id.radio_button_b_4) RadioButton radioButtonB4;
    @BindView(R.id.radio_button_c_4) RadioButton radioButtonC4;
    @BindView(R.id.radio_button_d_4) RadioButton radioButtonD4;
    @BindView(R.id.tv_note) TextView tvNote;

    MediaPlayer mediaPlayer = new MediaPlayer();
    Dialog dialogLoading;
    boolean isTesting = true; // reviewing -> isTesting = false
    HistoryRepository historyRepository = new HistoryRepository(getApplication());
    String serial = "";
    String title = "";
    String audio = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();   // hide title bar

        setContentView(R.layout.activity_part4);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        title = intent.getStringExtra("titleName");
        serial = "Serial" + intent.getIntExtra("serialID", 1);
        audio = intent.getStringExtra("audio");

        part4ViewModel = ViewModelProviders.of(this).get(Part4ViewModel.class);
        part4ViewModel.setTitleName(serial, title);
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
                radioGroup1.clearCheck();
                radioGroup2.clearCheck();
                radioGroup3.clearCheck();
                radioGroup4.clearCheck();
                part4ViewModel.previousQuestion();
                scrollView.scrollTo(0,0);
            }
        });
        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioGroup1.clearCheck();
                radioGroup2.clearCheck();
                radioGroup3.clearCheck();
                radioGroup4.clearCheck();
                part4ViewModel.nextQuestion();
                scrollView.scrollTo(0,0);
            }
        });

        setOnCheckedChangeRadioGroup();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSubmitDialog();
            }
        });
    }

    public void setOnCheckedChangeRadioGroup(){
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup1.getCheckedRadioButtonId()){
                    case R.id.radio_button_a_1:
                        part4ViewModel.changeAnswer("A", 0);
                        break;
                    case R.id.radio_button_b_1:
                        part4ViewModel.changeAnswer("B", 0);
                        break;
                    case R.id.radio_button_c_1:
                        part4ViewModel.changeAnswer("C", 0);
                        break;
                    case R.id.radio_button_d_1:
                        part4ViewModel.changeAnswer("D", 0);
                        break;
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup2.getCheckedRadioButtonId()){
                    case R.id.radio_button_a_2:
                        part4ViewModel.changeAnswer("A", 1);
                        break;
                    case R.id.radio_button_b_2:
                        part4ViewModel.changeAnswer("B", 1);
                        break;
                    case R.id.radio_button_c_2:
                        part4ViewModel.changeAnswer("C", 1);
                        break;
                    case R.id.radio_button_d_2:
                        part4ViewModel.changeAnswer("D", 1);
                        break;
                }
            }
        });

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup3.getCheckedRadioButtonId()){
                    case R.id.radio_button_a_3:
                        part4ViewModel.changeAnswer("A", 2);
                        break;
                    case R.id.radio_button_b_3:
                        part4ViewModel.changeAnswer("B", 2);
                        break;
                    case R.id.radio_button_c_3:
                        part4ViewModel.changeAnswer("C", 2);
                        break;
                    case R.id.radio_button_d_3:
                        part4ViewModel.changeAnswer("D", 2);
                        break;
                }
            }
        });

        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup4.getCheckedRadioButtonId()){
                    case R.id.radio_button_a_4:
                        part4ViewModel.changeAnswer("A", 3);
                        break;
                    case R.id.radio_button_b_4:
                        part4ViewModel.changeAnswer("B", 3);
                        break;
                    case R.id.radio_button_c_4:
                        part4ViewModel.changeAnswer("C", 3);
                        break;
                    case R.id.radio_button_d_4:
                        part4ViewModel.changeAnswer("D", 3);
                        break;
                }
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
        String url = "https://myhost2018.000webhostapp.com/Serial1/" + title + "/Audio/" + title + "_Part4.m4a";
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
            String s = i + "";
            Button btn;
            btn = (Button)dialogNotice.findViewById(
                    dialogNotice.getContext().getResources().getIdentifier(
                            "button_" + String.valueOf(i).trim(),
                            "id",
                            getApplicationContext().getPackageName()));

            btn.setText("" + (60 + i));
            if(!part4ViewModel.getListAllQuestion().getValue().get(i - 11).getAnswerChosen().equals(""))
                btn.setBackgroundResource(R.drawable.question_chosen);
        }

        Button btnSubmit = (Button)dialogNotice.findViewById(R.id.button_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                btnPause.setBackgroundResource(R.drawable.ic_play_arrow);
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
                scrollView.scrollTo(0,0);
                btnSubmit.setVisibility(View.INVISIBLE);
                isTesting = false;
                part4ViewModel.updateQuestion(0);   // the first question
                startProgressBarTime();
                dialog.hide();
            }
        });
    }

    public void liveDataListener(){
        part4ViewModel.getListCurrentQuestion().observe(this, new Observer<List<Part4OnPhone>>() {
            @Override
            public void onChanged(@Nullable List<Part4OnPhone> listCurrentQuestion) {

                //tvQuestionContent1.setText("" + listCurrentQuestion.get(0).getQuestionNumber()+".");
                for (int i = 0; i < listCurrentQuestion.size(); i++){
                    if(listCurrentQuestion.get(0).getQuestionNumber() == 71)
                        btnPreviousQuestion.setEnabled(false);
                    else
                        btnPreviousQuestion.setEnabled(true);
                    if(listCurrentQuestion.get(listCurrentQuestion.size() - 1).getQuestionNumber() == 100)
                        btnNextQuestion.setEnabled(false);
                    else
                        btnNextQuestion.setEnabled(true);

                    getTvQuestionContent(i + 1).setVisibility(View.VISIBLE);    // hiện TvQuestionContent
                    getTvQuestionContent(i + 1).setText(
                            listCurrentQuestion.get(i).getQuestionNumber() + ". "
                                    + listCurrentQuestion.get(i).getQuestionContent());

                    getRadioGroup(i).setVisibility(View.VISIBLE);   // hiện RadioGroup
                    getRadioButton(i + 1 + "A").setText("A. " + listCurrentQuestion.get(i).getAnswerA());
                    getRadioButton(i + 1 + "B").setText("B. " + listCurrentQuestion.get(i).getAnswerB());
                    getRadioButton(i + 1 + "C").setText("C. " + listCurrentQuestion.get(i).getAnswerC());
                    getRadioButton(i + 1 + "D").setText("D. " + listCurrentQuestion.get(i).getAnswerD());

                    // chọn vào đáp án đã chọn trước đó
                    if(!listCurrentQuestion.get(i).getAnswerChosen().equals(""))
                        getRadioButton(i + 1 + listCurrentQuestion.get(i).getAnswerChosen())
                                .setChecked(true); //check vào đáp án đã chọn trước đó

                    if(!isTesting){ // đang xem lại bài test
                        getRadioButton(i + 1 + "A").setTextColor(Color.parseColor("#000000"));
                        getRadioButton(i + 1 + "A").setEnabled(false);
                        getRadioButton(i + 1 + "B").setTextColor(Color.parseColor("#000000"));
                        getRadioButton(i + 1 + "B").setEnabled(false);
                        getRadioButton(i + 1 + "C").setTextColor(Color.parseColor("#000000"));
                        getRadioButton(i + 1 + "C").setEnabled(false);
                        getRadioButton(i + 1 + "D").setTextColor(Color.parseColor("#000000"));
                        getRadioButton(i + 1 + "D").setEnabled(false);
                        if (!listCurrentQuestion.get(i).getAnswerChosen().trim().equals("")){   // đáp án không bỏ trống
                            // đáp án chọn là đúng
                            if (listCurrentQuestion.get(i).getAnswerChosen().equals(listCurrentQuestion.get(i).getCorrectAnswer())) {
                                getRadioButton(i + 1 + listCurrentQuestion.get(i).getAnswerChosen())
                                        .setTextColor(Color.parseColor("#FF01D71A"));
                            }
                            // đáp án chọn là sai
                            else {
                                if (!listCurrentQuestion.get(i).getAnswerChosen().equals(listCurrentQuestion.get(i).getCorrectAnswer())) {
                                    getRadioButton(i + 1 + listCurrentQuestion.get(i).getAnswerChosen())
                                            .setTextColor(Color.parseColor("#FFF90000"));
                                    getRadioButton(i + 1 + listCurrentQuestion.get(i).getCorrectAnswer())
                                            .setTextColor(Color.parseColor("#FF01D71A"));
                                }
                            }
                        }
                        else
                            getRadioButton(i + 1 + listCurrentQuestion.get(i).getCorrectAnswer())
                                    .setTextColor(Color.parseColor("#FF01D71A"));

                        tvNote.setText(listCurrentQuestion.get(0).getNote());
                    }
                }
                if(listCurrentQuestion.size() < 4) {
                    for (int j = listCurrentQuestion.size(); j <= 4; j++) {
                        getTvQuestionContent(j + 1).setVisibility(View.GONE);
                        getRadioGroup(j + 1).setVisibility(View.GONE);
                    }
                }

            }

        });

        part4ViewModel.getListAllQuestion().observe(this, new Observer<List<Part4OnPhone>>() {
            @Override
            public void onChanged(@Nullable List<Part4OnPhone> question_part4s) {

            }
        });

        part4ViewModel.getCurrentIndex().observe(this, new Observer<Integer>() {
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
    }

    public String getResult(){
        int score = 0;
        for (int i = 0; i < part4ViewModel.getListAllQuestion().getValue().size(); i++){
            if(part4ViewModel.getListAllQuestion().getValue().get(i).getAnswerChosen().trim().equals(
                    part4ViewModel.getListAllQuestion().getValue().get(i).getCorrectAnswer().trim())) {
                score += 1;

            }
        }
        return score + "/" + part4ViewModel.getListAllQuestion().getValue().size();
    }

    public TextView getTvQuestionContent(int number){
        switch (number){
            case 1: return tvQuestionContent1;
            case 2: return tvQuestionContent2;
            case 3: return tvQuestionContent3;
        }
        return tvQuestionContent4;
    }

    public RadioGroup getRadioGroup(int number){
        switch (number){
            case 1: return radioGroup1;
            case 2: return radioGroup2;
            case 3: return radioGroup3;
        }
        return radioGroup4;
    }

    public RadioButton getRadioButton(String anwser){
        if (anwser.equals("1A"))
            return radioButtonA1;
        if (anwser.equals("1B"))
            return radioButtonB1;
        if (anwser.equals("1C"))
            return radioButtonC1;
        if (anwser.equals("1D"))
            return radioButtonD1;

        if (anwser.equals("2A"))
            return radioButtonA2;
        if (anwser.equals("2B"))
            return radioButtonB2;
        if (anwser.equals("2C"))
            return radioButtonC2;
        if (anwser.equals("2D"))
            return radioButtonD2;

        if (anwser.equals("3A"))
            return radioButtonA3;
        if (anwser.equals("3B"))
            return radioButtonB3;
        if (anwser.equals("3C"))
            return radioButtonC3;
        if (anwser.equals("3D"))
            return radioButtonD3;

        if (anwser.equals("4A"))
            return radioButtonA4;
        if (anwser.equals("4B"))
            return radioButtonB4;
        if (anwser.equals("4C"))
            return radioButtonC4;
        return radioButtonD4;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        mediaPlayer.pause();
        btnPause.setBackgroundResource(R.drawable.ic_play_arrow);
        showNoticeDialog("Bạn có muốn thoát?");
    }
}
