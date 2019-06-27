package com.example.phamn.learningtoeic.View;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Model.Part1OnPhone;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.Repository.HistoryRepository;
import com.example.phamn.learningtoeic.ViewModel.Part1ViewModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Part1Activity extends AppCompatActivity {
    Part1ViewModel part1ViewModel;
    @BindView(R.id.scroll_view) ScrollView scrollView;
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
    //@BindView(R.id.btn_answerA) Button btnAnswerA;
    //test
    MediaPlayer mediaPlayer = new MediaPlayer();
    Dialog dialogLoading;
    boolean isTesting = true; // reviewing -> isTesting = false
    String serial = "";
    String serialName = "";
    String audio = "";
    String title = "";
    String result = "";
    Matrix matrix = new Matrix();
    Float scale = 1f;
    ScaleGestureDetector sgd;
    ImageView ivZoom;
    PinchZoomPan pZP;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();   // hide title bar
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);    // set fullscreen
        setContentView(R.layout.activity_part1);
        ButterKnife.bind(this); // databinding

        dialog = new Dialog(this,android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.setContentView(R.layout.zoom_image);
        dialog.setCanceledOnTouchOutside(false);
        pZP = (PinchZoomPan) dialog.findViewById(R.id.ivZoom);

        //sgd = new ScaleGestureDetector(this, new ScaleListener());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showZoomImage();
            }
        });
        scrollView.setOnTouchListener(new OnSwipeTouchListener(Part1Activity.this) {
            public void onSwipeTop() {
                Toast.makeText(Part1Activity.this, "top", Toast.LENGTH_SHORT).show();
            }
            public void onSwipeRight() {
                radioGroup.clearCheck();
                part1ViewModel.previousQuestion();
            }
            public void onSwipeLeft() {
                radioGroup.clearCheck();
                part1ViewModel.nextQuestion();
            }
            public void onSwipeBottom() {
                Toast.makeText(Part1Activity.this, "bottom", Toast.LENGTH_SHORT).show();
            }

        });

        Intent intent = getIntent();
        serial = intent.getStringExtra("serialName");
        title = intent.getStringExtra("titleName");
        audio = intent.getStringExtra("audio");
        //serial = "Serial1";
        //dùng livedata
        part1ViewModel = ViewModelProviders.of(this).get(Part1ViewModel.class);
        part1ViewModel.setTitleName(serial, title);
        liveDataListener();

        // show dialog Loading
        dialogLoading = new Dialog(this, R.style.AppTheme);
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

    public void showZoomImage(){
        dialog.show();

        Button btnClose = (Button)dialog.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

//    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener{
//        @Override
//        public boolean onScale(ScaleGestureDetector detector) {
//            scale = scale * detector.getScaleFactor();
//            scale = Math.max(0.1f, Math.min(scale, 5f));
//            matrix.setScale(scale,scale);
//            ivZoom.setImageMatrix(matrix);
//            return true;
//        }
//    }
    public void initAudio(){
        //String url = "https://myhost2018.000webhostapp.com/" + serial + "/Test1/Audio/Test1_Part1.m4a";
        String url = "https://myhost2018.000webhostapp.com/Serial1/" + title + "/Audio/"+ title +"_Part1.m4a";
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
        dialogNotice.setContentView(R.layout.end_part1_layout);
        dialogNotice.show();
        dialogNotice.setCanceledOnTouchOutside(true);

        for (int i = 1; i <= 10; i++) {
            Button btn;
            btn = (Button)dialogNotice.findViewById(
                    dialogNotice.getContext().getResources().getIdentifier(
                            "button_" + String.valueOf(i).trim(),
                            "id",
                            getApplicationContext().getPackageName()));

            if(!part1ViewModel.getListQuestion().getValue().get(i - 1).getAnswerChosen().equals(""))
                btn.setBackgroundResource(R.drawable.question_chosen);
            final int x = i - 1;
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    radioGroup.clearCheck();
                    part1ViewModel.updateQuestion(x);
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
                dialogNotice.dismiss();
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

        result = getResult();
        tvScore.setText(result);
        final Intent intent = getIntent();
        int partID = intent.getIntExtra("partID", 0);
        HistoryRepository historyRepository = new HistoryRepository(getApplication());
        historyRepository.deleteHistory(partID);
        historyRepository.insert(new History(partID,
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1),
                result)); // add to history

        Button btnHome = (Button)dialog.findViewById(R.id.button_home);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent2 = new Intent();//(Part1Activity.this, NavigationActivity.class);
                intent2.putExtra(Intent.EXTRA_INTENT, 1);
                setResult(99, intent2);
//                startActivityForResult(intent2, Activity.RESULT_OK);
                finish();
                //getApplicationContext().startActivity(intent);
            }
        });
        Button btnReview = (Button)dialog.findViewById(R.id.button_review);
        btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSubmit.setVisibility(View.INVISIBLE);
                btnPause.setBackgroundResource(R.drawable.ic_pause);
                isTesting = false;
                part1ViewModel.updateQuestion(0);   // the first question
                startProgressBarTime();
                dialog.dismiss();
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

        final Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
        final int partID = intent.getIntExtra("partID", 0);
        intent.putExtra("history", "" + partID + ","+ result);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NavigationActivity.class);
                intent.putExtra("back","Co");
                //startActivity(intent);
                dialog.dismiss();
                finish();
                //getApplicationContext().startActivity(intent);

            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void liveDataListener(){
        part1ViewModel.getQuestion().observe(this, new Observer<Part1OnPhone>() {
            @Override
            public void onChanged(@Nullable Part1OnPhone questionPart1) {
                tvNumber.setText("" + questionPart1.getQuestionNumber());
                Glide.with(getApplicationContext())
                        .load(questionPart1.getImage())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(imageView);
                Glide.with(getApplicationContext())
                        .load(questionPart1.getImage())
                        .asBitmap()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                pZP.loadImageOnCanvas(resource);
                            }
                        });
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

                    radioButtonA.setTextColor(getResources().getColor(R.color.colorTextRadioButton));
                    radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector);
                    radioButtonA.setEnabled(false);
                    radioButtonB.setTextColor(getResources().getColor(R.color.colorTextRadioButton));
                    radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector);
                    radioButtonB.setEnabled(false);
                    radioButtonC.setTextColor(getResources().getColor(R.color.colorTextRadioButton));
                    radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector);
                    radioButtonC.setEnabled(false);
                    radioButtonD.setTextColor(getResources().getColor(R.color.colorTextRadioButton));
                    radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector);
                    radioButtonD.setEnabled(false);
                    if (!questionPart1.getAnswerChosen().trim().equals("")){
                        if (questionPart1.getAnswerChosen().equals(questionPart1.getCorrectAnswer())) {
//                            getRadioButton(questionPart1.getAnswerChosen()).setTextColor(Color.parseColor("#FF01D71A"));
                            getRadioButton(questionPart1.getAnswerChosen()).setBackgroundResource(R.drawable.radio_flat_selector_right);
                        } else {
                            if (!questionPart1.getAnswerChosen().equals(questionPart1.getCorrectAnswer())) {
//                                getRadioButton(questionPart1.getAnswerChosen()).setTextColor(Color.parseColor("#FFF90000"));
                                getRadioButton(questionPart1.getAnswerChosen()).setBackgroundResource(R.drawable.radio_flat_selector_wrong);
//                                getRadioButton(questionPart1.getCorrectAnswer()).setTextColor(Color.parseColor("#FF01D71A"));
                                getRadioButton(questionPart1.getCorrectAnswer()).setBackgroundResource(R.drawable.radio_flat_selector_right);
                            }
                        }
                    }
                    else {
                        getRadioButton(questionPart1.getCorrectAnswer()).setBackgroundResource(R.drawable.radio_flat_selector_right);
//                        getRadioButton(questionPart1.getCorrectAnswer()).setTextColor(Color.parseColor("#FF01D71A"));
                    }
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

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        mediaPlayer.pause();
        btnPause.setBackgroundResource(R.drawable.ic_play_arrow);
        showNoticeDialog("Bạn có muốn thoát?");
    }
}
