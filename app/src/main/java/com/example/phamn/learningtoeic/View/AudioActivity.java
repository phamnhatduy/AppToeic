package com.example.phamn.learningtoeic.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Model.TopicVocabulary;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AudioActivity extends AppCompatActivity {

    ImageView imgSpreaker;
    TextView txtNo,txtCorrect,txtIncorrect,txtCongra,txtShows;
    RadioGroup radioGroup;
    RadioButton radioButtonA,radioButtonB,radioButtonC,radioButtonD;
    SoundManager soundContracts,soundMarket;
    Random random;
    int no=1,corr=0,incorr=0;
    int pos,duplicate;

    final String[] wordAnswer = {"abide","accommodate","address","agreement","arrangement","association",
            "assurance","attend","attract","avoid","cancellation","characteristic","compare","competition","consequence",
            "consider","consume","convince","cover","currently","demonstrate","determine","develop","engage","establish",
            "evaluate","expiration","fad","frequently","gather","hold","imply","inspiration","location","market","obligate",
            "offer","overcrowded","party","persuasion","primarily","productive","promise","protect","provision","register",
            "reputation","require","resolve","risk","satisfaction","select","session","specific","strategy","strong","substitution",
            "variety"};
    final String[] wordTranslate = {"tôn trọng,tuân theo","điều tiết, điều chỉnh","địa chỉ",
            "hợp đồng","sắp xếp","liên kết","bảo đảm,chắc chắn",
            "tham dự","hấp dẫn,thu hút","tránh","sự hủy bỏ","đặc thù,đặc trưng",
            "so sánh","tranh giành,thi đấu","kết quả","cân nhắc,suy xét",
            "tiêu thụ","thuyết phục","che,phủ","hiện nay","bày tỏ",
            "quyết định,xác định","phát triển","sự hứa hẹn","thiết lập,thành lập,",
            "đánh giá, ước lượng","sự hết hạn","sự nhất thời","thường xuyên",
            "tập hợp","giữ","ẩn ý","truyền cảm hứng","vị trí",
            "thị trường,chợ","bắt buộc","đề xuất","chật ních","đảng,buổi tiệc",
            "sự thuyết phục","trước hết","sản xuất","lời hứa","bảo vệ",
            "sự dự trữ","đăng ký","danh tiếng","yêu cầu","giải quyết",
            "rủi ro","sự hài lòng","chọn lựa","phiên,kỳ","riêng biệt",
            "chiến lược","khoẻ,mạnh","sự thay thế","đa dạng"
    };
    Animation animation;
    final String[] wordContract = {"abide","agreement", "assurance","cancellation","determine","engage","establish","obligate",
            "party","provision","resolve","specific"};
    final String[] meanContract = {"tôn trọng,tuân theo","đồng ý,hợp đồng","bảo đảm,chắc chắn","sự hủy bỏ","quyết định,xác định","sự hứa hẹn",
            "thiết lập,thành lập","bắt buộc","đảng,buổi tiệc","sự dự trữ","giải quyết","riêng biệt"};
    final String[] wordMarket={"attract","compare","competition","consume","convince","currently","fad","inspiration","market",
            "persuasion","productive","satisfaction"};
    final String[] meanMarket={"hấp dẫn,thu hút","so sánh","tranh giành,thi đấu","tiêu thụ","thuyết phục","hiện nay","sự nhất thời",
            "truyền cảm hứng","thị trường,chợ","sự thuyết phục","sản xuất","sự hài lòng"};
    String top;
    ArrayList<String> wrongListContr,wrongListMar;
    ArrayList<String> wrongListMeanContr,wrongListMeanMar;
    ArrayList<Integer> listRan =new ArrayList<>();
    int[] number = {0,1,2,3,4,5,6,7,8,9,10,11};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Learning Toeic");
        init();
        animation = AnimationUtils.loadAnimation(this,R.anim.scale_sound);
        //random = new Random();

        wrongListContr = new ArrayList<String>();
        wrongListMeanContr=new ArrayList<String>();
        wrongListMar=new ArrayList<>();
        wrongListMeanMar=new ArrayList<>();

        for(int i : number)
        {
            listRan.add(i);
        }
        no=listRan.size();
        random = new Random();
        duplicate = random.nextInt(listRan.size());
        pos=listRan.get(duplicate);
        listRan.remove(duplicate);

        Intent intent = getIntent();
        top = intent.getStringExtra("topic");

        pos = random.nextInt(12);
        soundContract();
        soundMarketing();

        imgSpreaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                imgSpreaker.setImageResource(R.drawable.icon_sound);
                if(top.equals("Contracts")) {
                    soundContracts.playSound(pos);
                    Toast.makeText(AudioActivity.this, wordContract[pos], Toast.LENGTH_SHORT).show();
                }
                else if(top.equals("Marketing"))
                {
                    soundMarket.playSound(pos);
                    Toast.makeText(AudioActivity.this, wordMarket[pos], Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(top.equals("Contracts")) {
            checkRandom();
        }
        else if(top.equals("Marketing"))
        {
            checkRandom();
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //countDown();
                switch (radioGroup.getCheckedRadioButtonId())
                {

                    case R.id.radio_a:
                        if(radioButtonA.isChecked()) {
                            CompareAns();
                            delay();
                        }

                    case R.id.radio_b:
                        if(radioButtonB.isChecked() ) {
                            CompareAns();
                            delay();

                        }
                    case R.id.radio_c:
                        if(radioButtonC.isChecked()) {
                            CompareAns();
                            delay();

                        }
                    case R.id.radio_d:
                        if(radioButtonD.isChecked() ) {
                            CompareAns();
                            delay();

                        }
                }
                txtNo.setText("No:"+no);
                txtCorrect.setText("Correct:"+corr);
                txtIncorrect.setText("Incorrect:"+incorr);
            }

        });

    }
    public void checkRandom()
    {
        if(top.equals("Contracts")) {
            if (pos <= 8) {
                radioButtonA.setText(meanContract[pos]);
                radioButtonB.setText(meanContract[pos + 1]);
                radioButtonC.setText(meanContract[pos + 2]);
                radioButtonD.setText(meanContract[pos + 3]);
            } else {
                radioButtonA.setText(meanContract[pos - 1]);
                radioButtonB.setText(meanContract[pos]);
                radioButtonC.setText(meanContract[pos - 2]);
                radioButtonD.setText(meanContract[pos - 3]);
            }
        }else if(top.equals("Marketing"))
        {
            if (pos <= 8) {
                radioButtonA.setText(meanMarket[pos]);
                radioButtonB.setText(meanMarket[pos + 1]);
                radioButtonC.setText(meanMarket[pos + 2]);
                radioButtonD.setText(meanMarket[pos + 3]);
            } else {
                radioButtonA.setText(meanMarket[pos - 1]);
                radioButtonB.setText(meanMarket[pos]);
                radioButtonC.setText(meanMarket[pos - 2]);
                radioButtonD.setText(meanMarket[pos - 3]);
            }
        }
    }
    private void soundContract()
    {
        soundContracts = new SoundManager();
        soundContracts.initSounds(getBaseContext());
        soundContracts.addSound(0, R.raw.abide);
        soundContracts.addSound(1,R.raw.agreement);
        soundContracts.addSound(2,R.raw.assurance);
        soundContracts.addSound(3,R.raw.cancellation);
        soundContracts.addSound(4,R.raw.determine);
        soundContracts.addSound(5,R.raw.engage);
        soundContracts.addSound(6,R.raw.establish);
        soundContracts.addSound(7,R.raw.obligate);
        soundContracts.addSound(8,R.raw.party);
        soundContracts.addSound(9,R.raw.provision);
        soundContracts.addSound(10,R.raw.resolve);
        soundContracts.addSound(11,R.raw.specific);
    }
    private void soundMarketing()
    {
        soundMarket = new SoundManager();
        soundMarket.initSounds(getBaseContext());
        soundMarket.addSound(0,R.raw.attract);
        soundMarket.addSound(1,R.raw.compare);
        soundMarket.addSound(2,R.raw.competition);
        soundMarket.addSound(3,R.raw.consume);
        soundMarket.addSound(4,R.raw.convince);
        soundMarket.addSound(5,R.raw.currently);
        soundMarket.addSound(6,R.raw.fad);
        soundMarket.addSound(7,R.raw.inspiration);
        soundMarket.addSound(8,R.raw.market);
        soundMarket.addSound(9,R.raw.persuasion);
        soundMarket.addSound(10,R.raw.productive);
        soundMarket.addSound(11,R.raw.satisfaction);

    }
    /*
    public void soundPlay()
    {
        soundManager = new SoundManager();
        soundManager.initSounds(getBaseContext());
        soundManager.addSound(0, R.raw.abide);
        soundManager.addSound(1,R.raw.accommodate);
        soundManager.addSound(2,R.raw.address);
        soundManager.addSound(3,R.raw.agreement);
        soundManager.addSound(4,R.raw.arrangement);
        soundManager.addSound(5,R.raw.association);
        soundManager.addSound(6,R.raw.assurance);
        soundManager.addSound(7,R.raw.attend);
        soundManager.addSound(8,R.raw.attract);
        soundManager.addSound(9,R.raw.avoid);
        soundManager.addSound(10,R.raw.cancellation);
        soundManager.addSound(11,R.raw.characteristic);
        soundManager.addSound(12,R.raw.compare);
        soundManager.addSound(13,R.raw.competition);
        soundManager.addSound(14,R.raw.consequence);
        soundManager.addSound(15,R.raw.consider);
        soundManager.addSound(16,R.raw.consume);
        soundManager.addSound(17,R.raw.convince);
        soundManager.addSound(18,R.raw.cover);
        soundManager.addSound(19,R.raw.currently);
        soundManager.addSound(20,R.raw.demonstrate);
        soundManager.addSound(21,R.raw.determine);
        soundManager.addSound(22,R.raw.develop);
        soundManager.addSound(23,R.raw.engage);
        soundManager.addSound(24,R.raw.establish);
        soundManager.addSound(25,R.raw.evaluate);
        soundManager.addSound(26,R.raw.expiration);
        soundManager.addSound(27,R.raw.fad);
        soundManager.addSound(28,R.raw.frequently);
        soundManager.addSound(29,R.raw.gather);
        soundManager.addSound(30,R.raw.hold);
        soundManager.addSound(31,R.raw.imply);
        soundManager.addSound(32,R.raw.inspiration);
        soundManager.addSound(33,R.raw.location);
        soundManager.addSound(34,R.raw.market);
        soundManager.addSound(35,R.raw.obligate);
        soundManager.addSound(36,R.raw.offer);
        soundManager.addSound(37,R.raw.overcrowded);
        soundManager.addSound(38,R.raw.party);
        soundManager.addSound(39,R.raw.persuasion);
        soundManager.addSound(40,R.raw.primarily);
        soundManager.addSound(41,R.raw.productive);
        soundManager.addSound(42,R.raw.promise);
        soundManager.addSound(43,R.raw.protect);
        soundManager.addSound(44,R.raw.provision);
        soundManager.addSound(45,R.raw.register);
        soundManager.addSound(46,R.raw.reputation);
        soundManager.addSound(47,R.raw.require);
        soundManager.addSound(48,R.raw.resolve);
        soundManager.addSound(49,R.raw.risk);
        soundManager.addSound(50,R.raw.satisfaction);
        soundManager.addSound(51,R.raw.select);
        soundManager.addSound(52,R.raw.session);
        soundManager.addSound(53,R.raw.specific);
        soundManager.addSound(54,R.raw.strategy);
        soundManager.addSound(55,R.raw.strong);
        soundManager.addSound(56,R.raw.substitution);
        soundManager.addSound(57,R.raw.variety);

    }*/
    public void init()
    {
        imgSpreaker = findViewById(R.id.image_speaker);
        txtCorrect=findViewById(R.id.txt_correct);
        txtNo=findViewById(R.id.txt_no);
        txtCongra=findViewById(R.id.txt_congra_review);
        txtCorrect=findViewById(R.id.txt_correct);
        txtIncorrect=findViewById(R.id.txt_incorrect);
        radioGroup=findViewById(R.id.radio_group_ans1);
        radioButtonA=findViewById(R.id.radio_a);
        radioButtonB=findViewById(R.id.radio_b);
        radioButtonC=findViewById(R.id.radio_c);
        radioButtonD=findViewById(R.id.radio_d);
        txtShows=findViewById(R.id.txt_shows);
    }
    public void CompareAns()
    {
        if(top.equals("Contracts")) {
            if (radioButtonA.isChecked()) {
                if (radioButtonA.getText().equals(meanContract[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundContracts.playSound(pos);
                    corr += 1;
                    txtShows.setText(wordContract[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                } else {
                    txtShows.setText(wordContract[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                    wrongListContr.add(wordContract[pos]);
                    wrongListMeanContr.add(meanContract[pos]);
                    radioButtonA.setChecked(true);
                    radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    soundContracts.playSound(pos);
                    txtCongra.setText("Wrong !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            } else if (radioButtonB.isChecked()) {
                if (radioButtonB.getText().equals(meanContract[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundContracts.playSound(pos);
                    corr += 1;
                    txtShows.setText(wordContract[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                } else {
                    txtShows.setText(wordContract[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                    radioButtonB.setChecked(true);
                    radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                   // showAnswer();
                    wrongListContr.add(wordContract[pos]);
                    wrongListMeanContr.add(meanContract[pos]);
                    soundContracts.playSound(pos);
                    txtCongra.setText("Wrong !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonC.isChecked()) {
                if (radioButtonC.getText().equals(meanContract[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundContracts.playSound(pos);
                    corr += 1;
                    txtShows.setText(wordContract[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                } else {
                    txtShows.setText(wordContract[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                    radioButtonC.setChecked(true);
                    radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListContr.add(wordContract[pos]);
                    //wrongList.add(wordTranslate[pos]);
                    wrongListMeanContr.add(meanContract[pos]);
                    //showAnswer();
                    soundContracts.playSound(pos);
                    txtCongra.setText("Wrong !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonD.isChecked()) {
                if (radioButtonD.getText().equals(meanContract[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundContracts.playSound(pos);
                    corr += 1;
                    txtShows.setText(wordContract[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                } else {
                    txtShows.setText(wordContract[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                    radioButtonD.setChecked(true);
                    radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListContr.add(wordContract[pos]);
                    //wrongList.add(wordTranslate[pos]);
                    wrongListMeanContr.add(meanContract[pos]);
                    //showAnswer();
                    soundContracts.playSound(pos);
                    txtCongra.setText("Wrong !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            }
        }
        else if(top.equals("Marketing"))
        {
            if (radioButtonA.isChecked()) {
                if (radioButtonA.getText().equals(meanMarket[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundMarket.playSound(pos);
                    corr += 1;
                    txtShows.setText(wordMarket[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                } else {
                    txtShows.setText(wordMarket[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                    wrongListMar.add(wordMarket[pos]);
                    wrongListMeanMar.add(meanMarket[pos]);
                    radioButtonA.setChecked(true);
                    radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                   // showAnswer();
                    soundMarket.playSound(pos);
                    txtCongra.setText("Wrong !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            } else if (radioButtonB.isChecked()) {
                if (radioButtonB.getText().equals(meanMarket[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundMarket.playSound(pos);
                    corr += 1;
                    txtShows.setText(wordMarket[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                } else {
                    txtShows.setText(wordMarket[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                    radioButtonB.setChecked(true);
                    radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    wrongListMar.add(wordMarket[pos]);
                    wrongListMeanMar.add(meanMarket[pos]);
                    soundMarket.playSound(pos);
                    txtCongra.setText("Wrong !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                   // corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonC.isChecked()) {
                if (radioButtonC.getText().equals(meanMarket[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundMarket.playSound(pos);
                    corr += 1;
                    txtShows.setText(wordMarket[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                } else {
                    txtShows.setText(wordMarket[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                    radioButtonC.setChecked(true);
                    radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListMar.add(wordMarket[pos]);
                    //wrongList.add(wordTranslate[pos]);
                    wrongListMeanMar.add(meanMarket[pos]);
                   // showAnswer();
                    soundMarket.playSound(pos);
                    txtCongra.setText("Wrong !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonD.isChecked()) {
                if (radioButtonD.getText().equals(meanMarket[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundMarket.playSound(pos);
                    corr += 1;
                    txtShows.setText(wordMarket[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                } else {
                    txtShows.setText(wordMarket[pos]);
                    txtShows.setVisibility(View.VISIBLE);
                    radioButtonD.setChecked(true);
                    radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListMar.add(wordMarket[pos]);
                    //wrongList.add(wordTranslate[pos]);
                    wrongListMeanMar.add(meanMarket[pos]);
                   // showAnswer();
                    soundMarket.playSound(pos);
                    txtCongra.setText("Wrong !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            }
        }


    }
    public void showAnswer()
    {
        if(top.equals("Contracts")) {
            if (radioButtonA.getText().equals(meanContract[pos])) {
                radioButtonA.setChecked(true);
                radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_right);
            } else if (radioButtonB.getText().equals(meanContract[pos])) {
                radioButtonB.setChecked(true);
                radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_right);
            } else if (radioButtonC.getText().equals(meanContract[pos])) {
                radioButtonC.setChecked(true);
                radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_right);
            } else if (radioButtonD.getText().equals(meanContract[pos])) {
                radioButtonD.setChecked(true);
                radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_right);
            }
        }
        else if(top.equals("Marketing"))
        {
            if (radioButtonA.getText().equals(meanMarket[pos])) {
                radioButtonA.setChecked(true);
                radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_right);
            } else if (radioButtonB.getText().equals(meanMarket[pos])) {
                radioButtonB.setChecked(true);
                radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_right);
            } else if (radioButtonC.getText().equals(meanMarket[pos])) {
                radioButtonC.setChecked(true);
                radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_right);
            } else if (radioButtonD.getText().equals(meanMarket[pos])) {
                radioButtonD.setChecked(true);
                radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_right);
            }
        }
    }
    public void delay()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(listRan.size()>0) {
                    ChangeQues();
                }
                else {
                    showFinishAlertDialog();
                }
            }
        }, 2000);
    }
    public void ChangeQues()
    {
       // pos = random.nextInt(12);
        duplicate = random.nextInt(listRan.size());
        pos=listRan.get(duplicate);
        listRan.remove(duplicate);
        no-=1;
        int butArray[] = new int[4];
        butArray[0] = radioButtonA.getId();
        butArray[1]= radioButtonB.getId();
        butArray[2]=radioButtonC.getId();
        butArray[3]=radioButtonD.getId();
        int ra = random.nextInt(butArray.length);
        if (top.equals("Contracts")) {
            if (ra == 0) {
                if (pos <= 8) {
                    radioButtonA.setText(meanContract[pos]);
                    radioButtonB.setText(meanContract[pos + 1]);
                    radioButtonC.setText(meanContract[pos + 2]);
                    radioButtonD.setText(meanContract[pos + 3]);
                } else {
                    radioButtonA.setText(meanContract[pos]);
                    radioButtonB.setText(meanContract[pos - 1]);
                    radioButtonC.setText(meanContract[pos - 2]);
                    radioButtonD.setText(meanContract[pos - 3]);
                }
            } else if (ra == 1) {
                if (pos <= 8) {
                    radioButtonA.setText(meanContract[pos + 1]);
                    radioButtonB.setText(meanContract[pos]);
                    radioButtonC.setText(meanContract[pos + 2]);
                    radioButtonD.setText(meanContract[pos + 3]);
                } else {
                    radioButtonA.setText(meanContract[pos - 1]);
                    radioButtonB.setText(meanContract[pos]);
                    radioButtonC.setText(meanContract[pos - 2]);
                    radioButtonD.setText(meanContract[pos - 3]);
                }
            } else if (ra == 2) {
                if (pos <= 8) {
                    radioButtonA.setText(meanContract[pos + 1]);
                    radioButtonB.setText(meanContract[pos + 2]);
                    radioButtonC.setText(meanContract[pos]);
                    radioButtonD.setText(meanContract[pos + 3]);
                } else {
                    radioButtonA.setText(meanContract[pos - 1]);
                    radioButtonB.setText(meanContract[pos - 2]);
                    radioButtonC.setText(meanContract[pos]);
                    radioButtonD.setText(meanContract[pos - 3]);
                }
            } else if (ra == 3) {
                if (pos <= 8) {
                    radioButtonA.setText(meanContract[pos + 3]);
                    radioButtonB.setText(meanContract[pos + 1]);
                    radioButtonC.setText(meanContract[pos + 2]);
                    radioButtonD.setText(meanContract[pos]);
                } else {
                    radioButtonA.setText(meanContract[pos - 1]);
                    radioButtonB.setText(meanContract[pos - 3]);
                    radioButtonC.setText(meanContract[pos - 2]);
                    radioButtonD.setText(meanContract[pos]);
                }

            }
        }
        else if(top.equals("Marketing"))
        {
            if (ra == 0) {
                if (pos <= 8) {
                    radioButtonA.setText(meanMarket[pos]);
                    radioButtonB.setText(meanMarket[pos + 1]);
                    radioButtonC.setText(meanMarket[pos + 2]);
                    radioButtonD.setText(meanMarket[pos + 3]);
                } else {
                    radioButtonA.setText(meanMarket[pos]);
                    radioButtonB.setText(meanMarket[pos - 1]);
                    radioButtonC.setText(meanMarket[pos - 2]);
                    radioButtonD.setText(meanMarket[pos - 3]);
                }
            } else if (ra == 1) {
                if (pos <= 8) {
                    radioButtonA.setText(meanMarket[pos + 1]);
                    radioButtonB.setText(meanMarket[pos]);
                    radioButtonC.setText(meanMarket[pos + 2]);
                    radioButtonD.setText(meanMarket[pos + 3]);
                } else {
                    radioButtonA.setText(meanMarket[pos - 1]);
                    radioButtonB.setText(meanMarket[pos]);
                    radioButtonC.setText(meanMarket[pos - 2]);
                    radioButtonD.setText(meanMarket[pos - 3]);
                }
            } else if (ra == 2) {
                if (pos <= 8) {
                    radioButtonA.setText(meanMarket[pos + 1]);
                    radioButtonB.setText(meanMarket[pos + 2]);
                    radioButtonC.setText(meanMarket[pos]);
                    radioButtonD.setText(meanMarket[pos + 3]);
                } else {
                    radioButtonA.setText(meanMarket[pos - 1]);
                    radioButtonB.setText(meanMarket[pos - 2]);
                    radioButtonC.setText(meanMarket[pos]);
                    radioButtonD.setText(meanMarket[pos - 3]);
                }
            } else if (ra == 3) {
                if (pos <= 8) {
                    radioButtonA.setText(meanMarket[pos + 3]);
                    radioButtonB.setText(meanMarket[pos + 1]);
                    radioButtonC.setText(meanMarket[pos + 2]);
                    radioButtonD.setText(meanMarket[pos]);
                } else {
                    radioButtonA.setText(meanMarket[pos - 1]);
                    radioButtonB.setText(meanMarket[pos - 3]);
                    radioButtonC.setText(meanMarket[pos - 2]);
                    radioButtonD.setText(meanMarket[pos]);
                }
            }
        }
        radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector);
        imgSpreaker.setImageResource(R.drawable.play_sound_1);
        radioGroup.clearCheck();
        txtCongra.setVisibility(View.INVISIBLE);
        txtShows.setVisibility(View.INVISIBLE);
        //no+=1;

    }
    public void showFinishAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Congratulation.You have completed this topic.");
        //builder.setTitle("Your result");
        builder.setCancelable(false);
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //dialogInterface.dismiss();
                //onRestart();
                // onResume();
                finish();

            }
        });
        builder.setPositiveButton("Result", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(AudioActivity.this,ResultRememberActivity.class);
                if(top.equals("Contracts")) {
                    intent.putStringArrayListExtra("id", wrongListContr);
                    intent.putStringArrayListExtra("name", wrongListMeanContr);
                }
                else if(top.equals("Marketing"))
                {
                    intent.putStringArrayListExtra("id", wrongListMar);
                    intent.putStringArrayListExtra("name", wrongListMeanMar);
                }
                startActivity(intent);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to finish ?");
        //builder.setTitle("Your result");
        builder.setCancelable(false);
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //dialogInterface.dismiss();
                finish();
            }
        });
        builder.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(AudioActivity.this,ResultRememberActivity.class);
                if(top.equals("Contracts")) {
                    intent.putStringArrayListExtra("id", wrongListContr);
                    intent.putStringArrayListExtra("name", wrongListMeanContr);
                }
                else if(top.equals("Marketing"))
                {
                    intent.putStringArrayListExtra("id", wrongListMar);
                    intent.putStringArrayListExtra("name", wrongListMeanMar);
                }
                startActivity(intent);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showAlertDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
