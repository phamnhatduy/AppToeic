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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.phamn.learningtoeic.Model.Vocabulary;
import com.example.phamn.learningtoeic.R;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class EngToVnActivity extends AppCompatActivity {

    TextView txtQues;
    TextView txtCongra;
    TextView txtNo,txtCorr,txtIncorr,txtTitle;
    RadioGroup radioGroup1;
    RadioButton radioButtonA,radioButtonB,radioButtonC,radioButtonD;
    int pos,duplicate;
    int no=1,corr=0,incorr=0;
    SoundManager soundManager;
    SoundManager soundContracts,soundMarket,soundWarranties,soundBusiness,soundConfere;
    Random random;

    final String[] wordContract = {"abide","agreement", "assurance","cancellation","determine","engage","establish","obligate",
            "party","provision","resolve","specific"};
    final String[] meanContract = {"tôn trọng,tuân theo","đồng ý,hợp đồng","bảo đảm,chắc chắn","sự hủy bỏ","quyết định,xác định","sự hứa hẹn",
            "thiết lập,thành lập","bắt buộc","đảng,buổi tiệc","sự dự trữ","giải quyết","riêng biệt"};
    final String[] wordMarket={"attract","compare","competition","consume","convince","currently","fad","inspiration","market",
            "persuasion","productive","satisfaction"};
    final String[] meanMarket={"hấp dẫn,thu hút","so sánh","tranh giành,thi đấu","tiêu thụ","thuyết phục","hiện nay","sự nhất thời",
            "truyền cảm hứng","thị trường,chợ","sự thuyết phục","sản xuất","sự hài lòng"};
    final String[] wordWarranties={"characteristic","consequence","consider","cover","expiration","frequently","imply","promise","protect",
            "reputation","require","variety"};
    final String[] meanWarranties={"đặc thù,đặc trưng","kết quả","cân nhắc,suy xét","che,phủ","sự hết hạn","sự nhất thời","thường xuyên","ẩn ý",
            "lời hứa","bảo vệ", "danh tiếng","yêu, cầu","đa dạng"};
    final String[] wordBussiness ={"address","avoid","demonstrate","develop","evaluate","gather","offer","primarily","risk","strategy","strong",
            "substitution"};
    final String[] meanBussiness ={"địa chỉ","tránh","bày tỏ","phát triển","đánh giá, ước lượng","tập hợp","đề xuất","trước hết","rủi ro","chiến lược",
            "khoẻ,mạnh","sự thay thế"};
    final String[] wordConfere={"accommodate","arrangement","association","attend","get in touch","hold","location","overcrowded","register","select",
            "session","take part in"};
    final String[] meanConfere={"điều tiết,điều chỉnh","sắp xếp","liên kết","tham dự","giữ liên lạc","giữ","vị trí","chật ních","đăng ký","chọn lựa",
            "phiên,kỳ","tham dự"};

    String top;
    ArrayList<String> wrongListContr,wrongListMar,wrongListWarran,wrongListBusiness,wrongListConfere;
    ArrayList<String> wrongListMeanContr,wrongListMeanMar,wrongListMeanWarr,wrongListMeanBusiness,wrongListMeanConfere;
    Animation animation;
    ArrayList<Integer> listRan =new ArrayList<>();
    int[] number = {0,1,2,3,4,5,6,7,8,9,10,11};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vn_to_eng);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vocabulary Quizzes");
        animation = AnimationUtils.loadAnimation(this,R.anim.scale_sound);
        //anh xa
        txtQues=findViewById(R.id.txt_question);
        txtCongra=findViewById(R.id.txt_congra_remem);
        radioGroup1=findViewById(R.id.radio_group_ans1);
        txtNo=findViewById(R.id.txt_no);
        txtCorr=findViewById(R.id.txt_correct);
        txtIncorr=findViewById(R.id.txt_incorrect);
        txtTitle=findViewById(R.id.txt_title);
        radioButtonA=findViewById(R.id.radio_a);
        radioButtonB=findViewById(R.id.radio_b);
        radioButtonC=findViewById(R.id.radio_c);
        radioButtonD=findViewById(R.id.radio_d);
        //tao am thanh
        soundContract();
        soundMarketing();
        soundWarranties();
        soundBusiness();
        soundConfere();
        //tao mang
        wrongListContr = new ArrayList<String>();
        wrongListMeanContr=new ArrayList<String>();
        wrongListMar=new ArrayList<>();
        wrongListMeanMar=new ArrayList<>();
        wrongListWarran=new ArrayList<>();
        wrongListMeanWarr=new ArrayList<>();
        wrongListBusiness=new ArrayList<>();
        wrongListMeanBusiness=new ArrayList<>();
        wrongListConfere=new ArrayList<>();
        wrongListMeanConfere=new ArrayList<>();
        //check random
        for(int i : number)
        {
            listRan.add(i);
        }
        no=listRan.size();
        random = new Random();
        duplicate = random.nextInt(listRan.size());
        pos=listRan.get(duplicate);
        listRan.remove(duplicate);
        if(listRan.isEmpty())
        {
            for(int i:number){listRan.add(i);}
        }
        // pos = random.nextInt(12);

        //txtQues.setText(wordTranslate[pos]);
        //nhan intent
        Intent intent=getIntent();
        top=intent.getStringExtra("topic");
        if(top.equals("Contracts")) {
            //txtQues.setText(contract[pos]);
            txtQues.setText(wordContract[pos]);
            checkRandom();
        }
        else if(top.equals("Marketing"))
        {
            txtQues.setText(wordMarket[pos]);
            checkRandom();
        }
        else if(top.equals("Warranties"))
        {
            txtQues.setText(wordWarranties[pos]);
            checkRandom();
        }
        else if(top.equals("Business Planning"))
        {
            txtQues.setText(wordBussiness[pos]);
            checkRandom();
        }
        else if(top.equals("Conferences"))
        {
            txtQues.setText(wordConfere[pos]);
            checkRandom();
        }

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                txtTitle.setVisibility(View.INVISIBLE);
                switch (radioGroup1.getCheckedRadioButtonId())
                {

                    case R.id.radio_a:
                        if(radioButtonA.isChecked()) {
                            CompareAns();
                            delay();
                        }
                        break;

                    case R.id.radio_b:
                        if(radioButtonB.isChecked()) {
                            CompareAns();
                            delay();
                        }
                        break;

                    case R.id.radio_c:
                        if(radioButtonC.isChecked()) {
                            CompareAns();
                            delay();
                        }
                        break;

                    case R.id.radio_d:
                        if(radioButtonD.isChecked()) {
                            CompareAns();
                            delay();
                        }
                        break;

                }

                txtNo.setText("No:"+no);
                txtCorr.setText("Correct:"+corr);
                txtIncorr.setText("Incorrect:"+incorr);

            }
        });
    }
    private void checkRandom()
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
        }else if(top.equals("Warranties"))
        {
            if (pos <= 8) {
                radioButtonA.setText(meanWarranties[pos]);
                radioButtonB.setText(meanWarranties[pos + 1]);
                radioButtonC.setText(meanWarranties[pos + 2]);
                radioButtonD.setText(meanWarranties[pos + 3]);
            } else {
                radioButtonA.setText(meanWarranties[pos - 1]);
                radioButtonB.setText(meanWarranties[pos]);
                radioButtonC.setText(meanWarranties[pos - 2]);
                radioButtonD.setText(meanWarranties[pos - 3]);
            }
        }
        else if(top.equals("Business Planning"))
        {
            if (pos <= 8) {
                radioButtonA.setText(meanBussiness[pos]);
                radioButtonB.setText(meanBussiness[pos + 1]);
                radioButtonC.setText(meanBussiness[pos + 2]);
                radioButtonD.setText(meanBussiness[pos + 3]);
            } else {
                radioButtonA.setText(meanBussiness[pos - 1]);
                radioButtonB.setText(meanBussiness[pos]);
                radioButtonC.setText(meanBussiness[pos - 2]);
                radioButtonD.setText(meanBussiness[pos - 3]);
            }
        }
        else if(top.equals("Conferences"))
        {
            if (pos <= 8) {
                radioButtonA.setText(meanConfere[pos]);
                radioButtonB.setText(meanConfere[pos + 1]);
                radioButtonC.setText(meanConfere[pos + 2]);
                radioButtonD.setText(meanConfere[pos + 3]);
            } else {
                radioButtonA.setText(meanConfere[pos - 1]);
                radioButtonB.setText(meanConfere[pos]);
                radioButtonC.setText(meanConfere[pos - 2]);
                radioButtonD.setText(meanConfere[pos - 3]);
            }
        }

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
                } else {
                    wrongListContr.add(wordContract[pos]);
                    wrongListMeanContr.add(meanContract[pos]);
                    radioButtonA.setChecked(true);
                    radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    soundContracts.playSound(pos);
                    txtCongra.setText(meanContract[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    // corr -= 1;
                    incorr += 1;
                }
            } else if (radioButtonB.isChecked()) {
                if (radioButtonB.getText().equals(meanContract[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundContracts.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonB.setChecked(true);
                    radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    // showAnswer();
                    wrongListContr.add(wordContract[pos]);
                    wrongListMeanContr.add(meanContract[pos]);
                    soundContracts.playSound(pos);
                    txtCongra.setText(meanContract[pos]);
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
                } else {
                    radioButtonC.setChecked(true);
                    radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListContr.add(wordContract[pos]);
                    //wrongList.add(wordTranslate[pos]);
                    wrongListMeanContr.add(meanContract[pos]);
                    //showAnswer();
                    soundContracts.playSound(pos);
                    txtCongra.setText(meanContract[pos]);
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
                } else {
                    radioButtonD.setChecked(true);
                    radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListContr.add(wordContract[pos]);
                    //wrongList.add(wordTranslate[pos]);
                    wrongListMeanContr.add(meanContract[pos]);
                    //showAnswer();
                    soundContracts.playSound(pos);
                    txtCongra.setText(meanContract[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            }
            else {
                // showAnswer();
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
                } else {
                    wrongListMar.add(wordMarket[pos]);
                    wrongListMeanMar.add(meanMarket[pos]);
                    radioButtonA.setChecked(true);
                    radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    soundMarket.playSound(pos);
                    txtCongra.setText(meanMarket[pos]);
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
                } else {
                    radioButtonB.setChecked(true);
                    radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    wrongListMar.add(wordMarket[pos]);
                    wrongListMeanMar.add(meanMarket[pos]);
                    soundMarket.playSound(pos);
                    txtCongra.setText(meanMarket[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
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
                } else {
                    radioButtonC.setChecked(true);
                    radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListMar.add(wordMarket[pos]);
                    //wrongList.add(wordTranslate[pos]);
                    wrongListMeanMar.add(meanMarket[pos]);
                    //showAnswer();
                    soundMarket.playSound(pos);
                    txtCongra.setText(meanMarket[pos]);
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
                } else {
                    radioButtonD.setChecked(true);
                    radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListMar.add(wordMarket[pos]);
                    //wrongList.add(wordTranslate[pos]);
                    wrongListMeanMar.add(meanMarket[pos]);
                    //showAnswer();
                    soundMarket.playSound(pos);
                    txtCongra.setText(meanMarket[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            }
        }
        else if(top.equals("Warranties"))
        {
            if (radioButtonA.isChecked()) {
                if (radioButtonA.getText().equals(meanWarranties[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundWarranties.playSound(pos);
                    corr += 1;
                } else {
                    wrongListWarran.add(wordWarranties[pos]);
                    wrongListMeanWarr.add(meanWarranties[pos]);
                    radioButtonA.setChecked(true);
                    radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    soundWarranties.playSound(pos);
                    txtCongra.setText(meanWarranties[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            } else if (radioButtonB.isChecked()) {
                if (radioButtonB.getText().equals(meanWarranties[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundWarranties.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonB.setChecked(true);
                    radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    wrongListWarran.add(wordWarranties[pos]);
                    wrongListMeanWarr.add(meanWarranties[pos]);
                    soundWarranties.playSound(pos);
                    txtCongra.setText(meanWarranties[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonC.isChecked()) {
                if (radioButtonC.getText().equals(meanWarranties[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundWarranties.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonC.setChecked(true);
                    radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListWarran.add(wordWarranties[pos]);
                    wrongListMeanWarr.add(meanWarranties[pos]);
                    //showAnswer();
                    soundWarranties.playSound(pos);
                    txtCongra.setText(meanWarranties[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonD.isChecked()) {
                if (radioButtonD.getText().equals(meanWarranties[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundWarranties.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonD.setChecked(true);
                    radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListWarran.add(wordWarranties[pos]);
                    wrongListMeanWarr.add(meanWarranties[pos]);
                    //showAnswer();
                    soundWarranties.playSound(pos);
                    txtCongra.setText(meanWarranties[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            }
        }
        else if(top.equals("Business Planning"))
        {
            if (radioButtonA.isChecked()) {
                if (radioButtonA.getText().equals(meanBussiness[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundBusiness.playSound(pos);
                    corr += 1;
                } else {
                    wrongListBusiness.add(wordBussiness[pos]);
                    wrongListMeanBusiness.add(meanBussiness[pos]);
                    radioButtonA.setChecked(true);
                    radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    soundBusiness.playSound(pos);
                    txtCongra.setText(meanBussiness[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            } else if (radioButtonB.isChecked()) {
                if (radioButtonB.getText().equals(meanBussiness[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundBusiness.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonB.setChecked(true);
                    radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    wrongListBusiness.add(wordBussiness[pos]);
                    wrongListMeanBusiness.add(meanBussiness[pos]);
                    soundBusiness.playSound(pos);
                    txtCongra.setText(meanBussiness[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonC.isChecked()) {
                if (radioButtonC.getText().equals(meanBussiness[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundBusiness.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonC.setChecked(true);
                    radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListBusiness.add(wordBussiness[pos]);
                    wrongListMeanBusiness.add(meanBussiness[pos]);
                    //showAnswer();
                    soundBusiness.playSound(pos);
                    txtCongra.setText(meanBussiness[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonD.isChecked()) {
                if (radioButtonD.getText().equals(meanBussiness[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundBusiness.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonD.setChecked(true);
                    radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListBusiness.add(wordBussiness[pos]);
                    wrongListMeanBusiness.add(meanBussiness[pos]);
                    //showAnswer();
                    soundBusiness.playSound(pos);
                    txtCongra.setText(meanBussiness[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            }
        }
        else if(top.equals("Conferences"))
        {
            if (radioButtonA.isChecked()) {
                if (radioButtonA.getText().equals(meanConfere[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundConfere.playSound(pos);
                    corr += 1;
                } else {
                    wrongListConfere.add(wordConfere[pos]);
                    wrongListMeanConfere.add(meanConfere[pos]);
                    radioButtonA.setChecked(true);
                    radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    soundConfere.playSound(pos);
                    txtCongra.setText(meanConfere[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }
            } else if (radioButtonB.isChecked()) {
                if (radioButtonB.getText().equals(meanConfere[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    soundConfere.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonB.setChecked(true);
                    radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    //showAnswer();
                    wrongListConfere.add(wordConfere[pos]);
                    wrongListMeanConfere.add(meanConfere[pos]);
                    soundConfere.playSound(pos);
                    txtCongra.setText(meanConfere[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonC.isChecked()) {
                if (radioButtonC.getText().equals(meanConfere[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.setVisibility(View.VISIBLE);
                    txtCongra.startAnimation(animation);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundConfere.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonC.setChecked(true);
                    radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListConfere.add(wordConfere[pos]);
                    wrongListMeanConfere.add(meanConfere[pos]);
                    //showAnswer();
                    soundConfere.playSound(pos);
                    txtCongra.setText(meanConfere[pos]);
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //corr -= 1;
                    incorr += 1;
                }

            } else if (radioButtonD.isChecked()) {
                if (radioButtonD.getText().equals(meanConfere[pos])) {
                    txtCongra.setText("Congratulation !");
                    txtCongra.startAnimation(animation);
                    txtCongra.setVisibility(View.VISIBLE);
                    //Toast.makeText(RememberActivity.this, "true", Toast.LENGTH_SHORT).show();
                    soundConfere.playSound(pos);
                    corr += 1;
                } else {
                    radioButtonD.setChecked(true);
                    radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector_wrong);
                    wrongListConfere.add(wordConfere[pos]);
                    wrongListMeanConfere.add(meanConfere[pos]);
                    //showAnswer();
                    soundConfere.playSound(pos);
                    txtCongra.setText(meanConfere[pos]);
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
        //random = new Random();
        duplicate = random.nextInt(listRan.size());
        pos=listRan.get(duplicate);
        listRan.remove(duplicate);
        no-=1;
        // txtQues.setText(wordTranslate[pos]);
        int butArray[] = new int[4];
        butArray[0] = radioButtonA.getId();
        butArray[1]= radioButtonB.getId();
        butArray[2]=radioButtonC.getId();
        butArray[3]=radioButtonD.getId();
        int ra = random.nextInt(butArray.length);
        if (top.equals("Contracts")) {
            txtQues.setText(wordContract[pos]);
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
            txtQues.setText(wordMarket[pos]);
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
        else if(top.equals("Warranties"))
        {
            txtQues.setText(wordWarranties[pos]);
            if (ra == 0) {
                if (pos <= 8) {
                    radioButtonA.setText(meanWarranties[pos]);
                    radioButtonB.setText(meanWarranties[pos + 1]);
                    radioButtonC.setText(meanWarranties[pos + 2]);
                    radioButtonD.setText(meanWarranties[pos + 3]);
                } else {
                    radioButtonA.setText(meanWarranties[pos]);
                    radioButtonB.setText(meanWarranties[pos - 1]);
                    radioButtonC.setText(meanWarranties[pos - 2]);
                    radioButtonD.setText(meanWarranties[pos - 3]);
                }
            } else if (ra == 1) {
                if (pos <= 8) {
                    radioButtonA.setText(meanWarranties[pos + 1]);
                    radioButtonB.setText(meanWarranties[pos]);
                    radioButtonC.setText(meanWarranties[pos + 2]);
                    radioButtonD.setText(meanWarranties[pos + 3]);
                } else {
                    radioButtonA.setText(meanWarranties[pos - 1]);
                    radioButtonB.setText(meanWarranties[pos]);
                    radioButtonC.setText(meanWarranties[pos - 2]);
                    radioButtonD.setText(meanWarranties[pos - 3]);
                }
            } else if (ra == 2) {
                if (pos <= 8) {
                    radioButtonA.setText(meanWarranties[pos + 1]);
                    radioButtonB.setText(meanWarranties[pos + 2]);
                    radioButtonC.setText(meanWarranties[pos]);
                    radioButtonD.setText(meanWarranties[pos + 3]);
                } else {
                    radioButtonA.setText(meanWarranties[pos - 1]);
                    radioButtonB.setText(meanWarranties[pos - 2]);
                    radioButtonC.setText(meanWarranties[pos]);
                    radioButtonD.setText(meanWarranties[pos - 3]);
                }
            } else if (ra == 3) {
                if (pos <= 8) {
                    radioButtonA.setText(meanWarranties[pos + 3]);
                    radioButtonB.setText(meanWarranties[pos + 1]);
                    radioButtonC.setText(meanWarranties[pos + 2]);
                    radioButtonD.setText(meanWarranties[pos]);
                } else {
                    radioButtonA.setText(meanWarranties[pos - 1]);
                    radioButtonB.setText(meanWarranties[pos - 3]);
                    radioButtonC.setText(meanWarranties[pos - 2]);
                    radioButtonD.setText(meanWarranties[pos]);
                }
            }
        }
        else if(top.equals("Business Planning"))
        {
            txtQues.setText(wordBussiness[pos]);
            if (ra == 0) {
                if (pos <= 8) {
                    radioButtonA.setText(meanBussiness[pos]);
                    radioButtonB.setText(meanBussiness[pos + 1]);
                    radioButtonC.setText(meanBussiness[pos + 2]);
                    radioButtonD.setText(meanBussiness[pos + 3]);
                } else {
                    radioButtonA.setText(meanBussiness[pos]);
                    radioButtonB.setText(meanBussiness[pos - 1]);
                    radioButtonC.setText(meanBussiness[pos - 2]);
                    radioButtonD.setText(meanBussiness[pos - 3]);
                }
            } else if (ra == 1) {
                if (pos <= 8) {
                    radioButtonA.setText(meanBussiness[pos + 1]);
                    radioButtonB.setText(meanBussiness[pos]);
                    radioButtonC.setText(meanBussiness[pos + 2]);
                    radioButtonD.setText(meanBussiness[pos + 3]);
                } else {
                    radioButtonA.setText(meanBussiness[pos - 1]);
                    radioButtonB.setText(meanBussiness[pos]);
                    radioButtonC.setText(meanBussiness[pos - 2]);
                    radioButtonD.setText(meanBussiness[pos - 3]);
                }
            } else if (ra == 2) {
                if (pos <= 8) {
                    radioButtonA.setText(meanBussiness[pos + 1]);
                    radioButtonB.setText(meanBussiness[pos + 2]);
                    radioButtonC.setText(meanBussiness[pos]);
                    radioButtonD.setText(meanBussiness[pos + 3]);
                } else {
                    radioButtonA.setText(meanBussiness[pos - 1]);
                    radioButtonB.setText(meanBussiness[pos - 2]);
                    radioButtonC.setText(meanBussiness[pos]);
                    radioButtonD.setText(meanBussiness[pos - 3]);
                }
            } else if (ra == 3) {
                if (pos <= 8) {
                    radioButtonA.setText(meanBussiness[pos + 3]);
                    radioButtonB.setText(meanBussiness[pos + 1]);
                    radioButtonC.setText(meanBussiness[pos + 2]);
                    radioButtonD.setText(meanBussiness[pos]);
                } else {
                    radioButtonA.setText(meanBussiness[pos - 1]);
                    radioButtonB.setText(meanBussiness[pos - 3]);
                    radioButtonC.setText(meanBussiness[pos - 2]);
                    radioButtonD.setText(meanBussiness[pos]);
                }
            }
        }
        else if(top.equals("Conferences"))
        {
            txtQues.setText(wordConfere[pos]);
            if (ra == 0) {
                if (pos <= 8) {
                    radioButtonA.setText(meanConfere[pos]);
                    radioButtonB.setText(meanConfere[pos + 1]);
                    radioButtonC.setText(meanConfere[pos + 2]);
                    radioButtonD.setText(meanConfere[pos + 3]);
                } else {
                    radioButtonA.setText(meanConfere[pos]);
                    radioButtonB.setText(meanConfere[pos - 1]);
                    radioButtonC.setText(meanConfere[pos - 2]);
                    radioButtonD.setText(meanConfere[pos - 3]);
                }
            } else if (ra == 1) {
                if (pos <= 8) {
                    radioButtonA.setText(meanConfere[pos + 1]);
                    radioButtonB.setText(meanConfere[pos]);
                    radioButtonC.setText(meanConfere[pos + 2]);
                    radioButtonD.setText(meanConfere[pos + 3]);
                } else {
                    radioButtonA.setText(meanConfere[pos - 1]);
                    radioButtonB.setText(meanConfere[pos]);
                    radioButtonC.setText(meanConfere[pos - 2]);
                    radioButtonD.setText(meanConfere[pos - 3]);
                }
            } else if (ra == 2) {
                if (pos <= 8) {
                    radioButtonA.setText(meanConfere[pos + 1]);
                    radioButtonB.setText(meanConfere[pos + 2]);
                    radioButtonC.setText(meanConfere[pos]);
                    radioButtonD.setText(meanConfere[pos + 3]);
                } else {
                    radioButtonA.setText(meanConfere[pos - 1]);
                    radioButtonB.setText(meanConfere[pos - 2]);
                    radioButtonC.setText(meanConfere[pos]);
                    radioButtonD.setText(meanConfere[pos - 3]);
                }
            } else if (ra == 3) {
                if (pos <= 8) {
                    radioButtonA.setText(meanConfere[pos + 3]);
                    radioButtonB.setText(meanConfere[pos + 1]);
                    radioButtonC.setText(meanConfere[pos + 2]);
                    radioButtonD.setText(meanConfere[pos]);
                } else {
                    radioButtonA.setText(meanConfere[pos - 1]);
                    radioButtonB.setText(meanBussiness[pos - 3]);
                    radioButtonC.setText(meanConfere[pos - 2]);
                    radioButtonD.setText(meanBussiness[pos]);
                }
            }
        }

        radioButtonA.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonB.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonC.setBackgroundResource(R.drawable.radio_flat_selector);
        radioButtonD.setBackgroundResource(R.drawable.radio_flat_selector);

        radioGroup1.clearCheck();
        txtCongra.setVisibility(View.INVISIBLE);
        // no+=1;
        if(no==12)
        {
            //Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }

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

                Intent intent = new Intent(EngToVnActivity.this,ResultRememberActivity.class);
                intent.putExtra("code","eng");
                if(top.equals("Contracts")) {
                    intent.putStringArrayListExtra("id", wrongListContr);
                    intent.putStringArrayListExtra("name", wrongListMeanContr);
                }
                else if(top.equals("Marketing"))
                {
                    intent.putStringArrayListExtra("id", wrongListMar);
                    intent.putStringArrayListExtra("name", wrongListMeanMar);
                }
                else if(top.equals("Warranties"))
                {
                    intent.putStringArrayListExtra("id", wrongListWarran);
                    intent.putStringArrayListExtra("name", wrongListMeanWarr);
                }
                else if(top.equals("Business Planning"))
                {
                    intent.putStringArrayListExtra("id", wrongListBusiness);
                    intent.putStringArrayListExtra("name", wrongListMeanBusiness);
                }
                else if(top.equals("Conferences"))
                {
                    intent.putStringArrayListExtra("id", wrongListConfere);
                    intent.putStringArrayListExtra("name", wrongListMeanConfere);
                }
                startActivity(intent);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void showAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
                Intent intent = new Intent(EngToVnActivity.this,ResultRememberActivity.class);
                if(top.equals("Contracts")) {
                    intent.putStringArrayListExtra("id", wrongListContr);
                    intent.putStringArrayListExtra("name", wrongListMeanContr);
                }
                else if(top.equals("Marketing"))
                {
                    intent.putStringArrayListExtra("id", wrongListMar);
                    intent.putStringArrayListExtra("name", wrongListMeanMar);
                }
                else if(top.equals("Warranties"))
                {
                    intent.putStringArrayListExtra("id", wrongListWarran);
                    intent.putStringArrayListExtra("name", wrongListMeanWarr);
                }
                else if(top.equals("Business Planning"))
                {
                    intent.putStringArrayListExtra("id", wrongListBusiness);
                    intent.putStringArrayListExtra("name", wrongListMeanBusiness);
                }
                else if(top.equals("Conferences"))
                {
                    intent.putStringArrayListExtra("id", wrongListConfere);
                    intent.putStringArrayListExtra("name", wrongListMeanConfere);
                }
                startActivity(intent);
                //builder.setCancelable(true);

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
    private void soundWarranties()
    {
        soundWarranties= new SoundManager();
        soundWarranties.initSounds(getBaseContext());
        soundWarranties.addSound(0,R.raw.characteristic);
        soundWarranties.addSound(1,R.raw.consequence);
        soundWarranties.addSound(2,R.raw.consider);
        soundWarranties.addSound(3,R.raw.cover);
        soundWarranties.addSound(4,R.raw.expiration);
        soundWarranties.addSound(5,R.raw.frequently);
        soundWarranties.addSound(6,R.raw.imply);
        soundWarranties.addSound(7,R.raw.promise);
        soundWarranties.addSound(8,R.raw.protect);
        soundWarranties.addSound(9,R.raw.reputation);
        soundWarranties.addSound(10,R.raw.require);
        soundWarranties.addSound(11,R.raw.variety);
    }
    private void soundBusiness()
    {
        soundBusiness = new SoundManager();
        soundBusiness.initSounds(getBaseContext());
        soundBusiness.addSound(0,R.raw.address);
        soundBusiness.addSound(1,R.raw.avoid);
        soundBusiness.addSound(2,R.raw.demonstrate);
        soundBusiness.addSound(3,R.raw.develop);
        soundBusiness.addSound(4,R.raw.evaluate);
        soundBusiness.addSound(5,R.raw.gather);
        soundBusiness.addSound(6,R.raw.offer);
        soundBusiness.addSound(7,R.raw.primarily);
        soundBusiness.addSound(8,R.raw.risk);
        soundBusiness.addSound(9,R.raw.strategy);
        soundBusiness.addSound(10,R.raw.strong);
        soundBusiness.addSound(11,R.raw.substitution);
    }
    private void soundConfere()
    {
        soundConfere= new SoundManager();
        soundConfere.initSounds(getBaseContext());
        soundConfere.addSound(0,R.raw.accommodate);
        soundConfere.addSound(1,R.raw.arrangement);
        soundConfere.addSound(2,R.raw.association);
        soundConfere.addSound(3,R.raw.attend);
        soundConfere.addSound(4,R.raw.intouch);
        soundConfere.addSound(5,R.raw.hold);
        soundConfere.addSound(6,R.raw.location);
        soundConfere.addSound(7,R.raw.overcrowded);
        soundConfere.addSound(8,R.raw.register);
        soundConfere.addSound(9,R.raw.select);
        soundConfere.addSound(10,R.raw.session);
        soundConfere.addSound(11,R.raw.takepart);

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
