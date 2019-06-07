package com.example.phamn.learningtoeic.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RememberActivity<List> extends AppCompatActivity {
    TextView txtQues;
    TextView txtCongra;
    TextView txtNo,txtCorr,txtIncorr;
    RadioGroup radioGroup1;
    RadioButton radioButtonA,radioButtonB,radioButtonC,radioButtonD;
    int pos;
    int no=1,corr=0,incorr=0;
    SoundManager soundManager;
    Random random;
    ProgressBar progressBar;
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
            "sự dự trữ","đăng ký,danh sách","danh tiếng","yêu cầu","giải quyết",
            "rủi ro","sự hài lòng","chọn lựa","phiên,kỳ","riêng biệt",
            "chiến lược","khoẻ,mạnh","sự thay thế","đa dạng"
    };
     ArrayList<String> wrongList;
     ArrayList<String> wrongListMean=new ArrayList<>();
    public static final int MY_REQUEST_CODE = 100;
     Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vocabulary Quizzes");
        animation = AnimationUtils.loadAnimation(this,R.anim.scale_sound);
        txtQues=findViewById(R.id.txt_question);
        txtCongra=findViewById(R.id.txt_congra_remem);
        radioGroup1=findViewById(R.id.radio_group_ans1);
        progressBar=findViewById(R.id.progress_time);
        txtNo=findViewById(R.id.txt_no);
        txtCorr=findViewById(R.id.txt_correct);
        txtIncorr=findViewById(R.id.txt_incorrect);

        radioButtonA=findViewById(R.id.radio_a);
        radioButtonB=findViewById(R.id.radio_b);
        radioButtonC=findViewById(R.id.radio_c);
        radioButtonD=findViewById(R.id.radio_d);
        soundPlay();
        wrongList = new ArrayList<String>();
        random = new Random();
        pos = random.nextInt(10);
        txtQues.setText(wordTranslate[pos]);

        radioButtonA.setText(wordAnswer[pos]);
        radioButtonB.setText(wordAnswer[pos+1]);
        radioButtonC.setText(wordAnswer[pos+2]);
        radioButtonD.setText(wordAnswer[pos+3]);




        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //countDown();
                switch (radioGroup1.getCheckedRadioButtonId())
                {
                    case R.id.radio_a:
                        if(radioButtonA.isChecked()) {
                            CompareAns();
                            delay();
                           // no+=1;
                        }

                   case R.id.radio_b:
                       if(radioButtonB.isChecked() ) {
                           CompareAns();
                           delay();
                           //no+=1;
                       }
                    case R.id.radio_c:
                        if(radioButtonC.isChecked()) {
                            CompareAns();
                            delay();
                           // no+=1;
                        }
                    case R.id.radio_d:
                        if(radioButtonD.isChecked() ) {
                            CompareAns();
                           delay();
                            //no+=1;
                        }
                }

                txtNo.setText(no+"/50");
                txtCorr.setText("Correct:"+corr);
                txtIncorr.setText("Incorrect:"+incorr);
                if(no==10)
                {
                    Toast.makeText(RememberActivity.this, "Stopppp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void CompareAns()
    {
        if( radioButtonA.isChecked())
        {
            if(radioButtonA.getText().equals(wordAnswer[pos])) {
                txtCongra.setText("Congratulation !");
                txtCongra.setVisibility(View.VISIBLE);
                txtCongra.startAnimation(animation);
                //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                soundManager.playSound(pos);
                corr+=1;
                // ChangeQues();
            }
            else {
                showAnswer();
                wrongList.add(wordAnswer[pos]);
                wrongListMean.add(wordTranslate[pos]);
                radioButtonA.setChecked(true);
                radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                soundManager.playSound(pos);
                txtCongra.setText("Quite bad !");
                txtCongra.startAnimation(animation);
                txtCongra.setVisibility(View.VISIBLE);
                incorr+=1;}


        }
        if( radioButtonB.isChecked())
        {
             if(radioButtonB.getText().equals(wordAnswer[pos]))
            {
            txtCongra.setText("Congratulation !");
            txtCongra.setVisibility(View.VISIBLE);
            txtCongra.startAnimation(animation);
            //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
            soundManager.playSound(pos);
            corr+=1;
            //incorr+=1;
            //ChangeQues();
            }
            else{
                 /*if(radioButtonA.getText().equals(wordAnswer[pos]) || radioButtonC.getText().equals(wordAnswer[pos])
                 || radioButtonD.getText().equals(wordAnswer[pos]))
                ;     radioButtonA.setChecked(true);*/
                 radioButtonB.setChecked(true);
                 radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                 showAnswer();
                 wrongList.add(wordAnswer[pos]);
                 wrongListMean.add(wordTranslate[pos]);
                 soundManager.playSound(pos);
                 txtCongra.setText("Wrong !");
                 txtCongra.startAnimation(animation);
                 txtCongra.setVisibility(View.VISIBLE);
                incorr+=1;}

        }
        if(radioButtonC.isChecked())
        {
            if(radioButtonC.getText().equals(wordAnswer[pos])) {
                txtCongra.setText("Congratulation !");
                txtCongra.setVisibility(View.VISIBLE);
                //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                soundManager.playSound(pos);
                corr+=1;
                //incorr += 1;
                //ChangeQues();
            }else{
                /*
                if(radioButtonA.getText().equals(wordAnswer[pos]) || radioButtonB.getText().equals(wordAnswer[pos])
                || radioButtonD.getText().equals(wordAnswer[pos]))
                    radioButtonA.setChecked(true);*/
                radioButtonC.setChecked(true);
               radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                wrongList.add(wordAnswer[pos]);
                wrongListMean.add(wordTranslate[pos]);
                showAnswer();
                soundManager.playSound(pos);
                txtCongra.setText("Wrong !");
                txtCongra.startAnimation(animation);
                txtCongra.setVisibility(View.VISIBLE);
                incorr+=1;}

        }
        if(radioButtonD.isChecked())
        {
            if(radioButtonD.getText().equals(wordAnswer[pos]) ) {
                txtCongra.setText("Congratulation !");
                txtCongra.startAnimation(animation);
                txtCongra.setVisibility(View.VISIBLE);
                //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                soundManager.playSound(pos);
                corr+=1;
                //incorr += 1;
            }else {
               /* if(radioButtonA.getText().equals(wordAnswer[pos]))
                    radioButtonA.setChecked(true);*/
                radioButtonD.setChecked(true);
                radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                wrongList.add(wordAnswer[pos]);
                wrongListMean.add(wordTranslate[pos]);
               showAnswer();
                soundManager.playSound(pos);
                txtCongra.setText("Wrong !");
                txtCongra.startAnimation(animation);
                txtCongra.setVisibility(View.VISIBLE);
                incorr+=1;}
            //ChangeQues();

        }

        else {
                // txtCongra.setText("Quite bad !");
                //soundManager.playSound(pos);
               // incorr += 1;
                //radioButtonA.setChecked(true);
                //ChangeQues();
                //txtCongra.startAnimation(animation);
                //txtCongra.setVisibility(View.VISIBLE);
                //Toast.makeText(RememberActivity.this, "false", Toast.LENGTH_SHORT).show();

        }

    }
    public void showAnswer()
    {
        if(radioButtonA.getText().equals(wordAnswer[pos]))
        {
            radioButtonA.setChecked(true);
            radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_right);
        }
        else if(radioButtonB.getText().equals(wordAnswer[pos]))
        {
            radioButtonB.setChecked(true);
            radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_right);
        }
        else if(radioButtonC.getText().equals(wordAnswer[pos]))
        {
            radioButtonC.setChecked(true);
            radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_right);
        }
        else if(radioButtonD.getText().equals(wordAnswer[pos]))
        {
            radioButtonD.setChecked(true);
            radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_right);
        }
    }
    public void delay()
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ChangeQues();
            }
        }, 2000);
    }
    public void ChangeQues()
    {
        random = new Random();
        pos = random.nextInt(50);
        txtQues.setText(wordTranslate[pos]);
        radioButtonA.setText(wordAnswer[pos+random.nextInt(3)]);
        radioButtonB.setText(wordAnswer[pos+random.nextInt(4)]);
        radioButtonC.setText(wordAnswer[pos+random.nextInt(5)]);
        radioButtonD.setText(wordAnswer[pos+random.nextInt(1)]);
        radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector);
        radioGroup1.clearCheck();
        txtCongra.setVisibility(View.INVISIBLE);
        no+=1;

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
               Toast.makeText(RememberActivity.this, wrongList.toString(), Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(RememberActivity.this,ResultRememberActivity.class);
               //String s = wrongList.get(pos);
               //intent.putStringArrayListExtra("wrongWord",(ArrayList<String>) wrongList);
               startActivity(intent);
               //finish();
           }
       });
       // builder.show();
       AlertDialog alertDialog = builder.create();
       alertDialog.show();
    }
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

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                showAlertDialog();
                //this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
