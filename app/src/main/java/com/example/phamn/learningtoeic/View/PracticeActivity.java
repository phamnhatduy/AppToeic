package com.example.phamn.learningtoeic.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.Random;


public class PracticeActivity extends AppCompatActivity {
    ImageView imgStart,imgAns;
    EditText edtAns;
    TextView txtQues,txtTrans,txtLevel,txtCongra,txtScore,txtHighScore;
    Button btnConfirm,btnNext;
    ProgressBar progressQues;
    Random random;
    int i,duplicate;
    int countClick;
    int score=0,highScore=0;
    SharedPreferences preferences;
    int[] number  = {0,1,2,3,4};
    ArrayList<Integer> listRan =new ArrayList<>();
    MediaPlayer mediaPlayer;
    final String[] quesData = {"Traffic is ____ freely on the motorway.","This photo ___ us a busy rush hour scene.",
            "Several people can be seen ___ to cross the road.","It looks as if it is ___ to rain.",
            "The ___ are all brightly coloured."};
    final  String[] quesData2 ={"The ___ on the left is riding a pedal cycle.",
            "All the people in the ___ are waving their arms"," The water is ___ green in colour.",
            "You can only see four ___ clearly.","You can see one man getting off the ___."};
    final String[] ansData = {"flowing","shows","trying","going","buildings"};
    final String[] ansData2 ={"cyclist","motorboats","deep","boats","tram"};
    final String[] transData={"Giao thông đang lưu thông tự do trên đường cao tốc.",
            "Bức ảnh này cho chúng ta thấy một khung cảnh giờ cao điểm bận rộn.",
            "Một số người có thể được nhìn thấy đang cố gắng băng qua đường",
            "Có vẻ như trời sắp mưa.","Các tòa nhà đều có màu sắc rực rỡ."};
    final String[] transData2 = {"Người đi xe đạp bên trái đang đạp xe đạp.","Tất cả những người trong xuồng máy đang vẫy tay.",
            "Nước có màu xanh đậm.","Bạn chỉ có thể nhìn thấy bốn chiếc thuyền rõ ràng.","Bạn có thể thấy một người đàn ông xuống xe điện."};
    final String[] quesData3 ={"The man is ___ miserable."," The man is wearing a plain yellow ___.",
            "The patient is about to ___ into tears","The shopper is ___ in black.","The operator is ___."};
    final String[] ansData3 ={"looking","shirt","burst","dressed","bareheaded"};
    final String[] transData3 ={"Người đàn ông trông thật đau khổ.","Người đàn ông mặc áo vàng trơn.","Bệnh nhân sắp bật khóc.",
            "Người mua sắm mặc đồ đen.","Người điểu khiển không đội nón."};
    final String[] quesData4={"The man is ___ the snow away from him.",
    "The doctor is ___ to some music.","The tiler is ___ on the floor.","You can see ___ in the tile adhesive.",
            " Everyone is wearing white ___."};
    final String[] ansData4={"scraping","listening","kneeling","grooves","uniforms"};
    final String[] transData4={"Người đàn ông đang cạo tuyết khỏi anh ta.","Bác sĩ đang nghe nhạc.","Người thợ lợp đang quỳ trên sàn nhà.",
    "Bạn có thể thấy các rãnh trong keo dán gạch.","Mọi người đều mặc đồng phục màu trắng."};
    final String[] quesData5={" One shopper is seen going up the ___.","The woman is ___.","Nobody is using the ___."
    ,"All the boxes are ___ in shape.","The car is a ___ drive model."};
    final String[] ansData5={"escalator","crying","stairs"," rectangular","righthand"};
    final String[] transData5={"Một người mua sắm được nhìn thấy đi lên thang cuốn","Người phụ nữ đang khóc.",
            "Tất cả các hộp có hình chữ nhật.","Chiếc xe là một mô hình tay lái bên phải."};
    Animation aniRepeat,animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Learning Toeic");
        init();
        preferences =  getSharedPreferences("my score",Context.MODE_PRIVATE);
        highScore = preferences.getInt("score",score);
        if(highScore>=score) {
            txtHighScore.setText("HighScore:" + highScore);
        }
        animation = AnimationUtils.loadAnimation(this,R.anim.scale_sound);
        aniRepeat = AnimationUtils.loadAnimation(this,R.anim.scale_sound_repeat);
        for(int j : number)
        {
            listRan.add(j);
        }
        random = new Random();
        duplicate = random.nextInt(listRan.size());
        i=listRan.get(duplicate);
        listRan.remove(duplicate);
        if(txtLevel.getText().equals("Level 1")) {
            txtQues.setText(quesData[i]);
        }else if(txtLevel.getText().equals("Level 2")){
            txtQues.setText(quesData2[i]);
        } else if(txtLevel.getText().equals("Level 3")){
            txtQues.setText(quesData3[i]);
        } else if(txtLevel.getText().equals("Level 4")){
            txtQues.setText(quesData4[i]);
        } else if(txtLevel.getText().equals("Level 5")){
            txtQues.setText(quesData5[i]);
        }
        //random=new Random();
        //i=random.nextInt(5);

        final ArrayList<Integer> playQues1= new ArrayList<>();
        playQues1.add(R.raw.ques1);
        playQues1.add(R.raw.ques2);
        playQues1.add(R.raw.ques3);
        playQues1.add(R.raw.ques4);
        playQues1.add(R.raw.ques5);
        final ArrayList<Integer> playQues2 = new ArrayList<>();
        playQues2.add(R.raw.ques6);
        playQues2.add(R.raw.ques7);
        playQues2.add(R.raw.ques8);
        playQues2.add(R.raw.ques9);
        playQues2.add(R.raw.ques10);
        final ArrayList<Integer> playAns1 = new ArrayList<>();
        playAns1.add(R.raw.ans1);
        playAns1.add(R.raw.ans2);
        playAns1.add(R.raw.ans3);
        playAns1.add(R.raw.ans4);
        playAns1.add(R.raw.ans5);
        final ArrayList<Integer> playAns2 = new ArrayList<>();
        playAns2.add(R.raw.ans6);
        playAns2.add(R.raw.ans7);
        playAns2.add(R.raw.ans8);
        playAns2.add(R.raw.ans9);
        playAns2.add(R.raw.ans10);
        final ArrayList<Integer> playQues3 = new ArrayList<>();
        playQues3.add(R.raw.ques11);
        playQues3.add(R.raw.ques12);
        playQues3.add(R.raw.ques13);
        playQues3.add(R.raw.ques14);
        playQues3.add(R.raw.ques15);
        final ArrayList<Integer> ansQues3 = new ArrayList<>();
        ansQues3.add(R.raw.ans11);
        ansQues3.add(R.raw.ans12);
        ansQues3.add(R.raw.ans13);
        ansQues3.add(R.raw.ans14);
        ansQues3.add(R.raw.ans15);
        final ArrayList<Integer> playQues4 = new ArrayList<>();
        playQues4.add(R.raw.ques16);
        playQues4.add(R.raw.ques17);
        playQues4.add(R.raw.ques18);
        playQues4.add(R.raw.ques19);
        playQues4.add(R.raw.ques20);
        final ArrayList<Integer> playAns4 = new ArrayList<>();
        playAns4.add(R.raw.ans16);
        playAns4.add(R.raw.ans17);
        playAns4.add(R.raw.ans18);
        playAns4.add(R.raw.ans19);
        playAns4.add(R.raw.ans20);
        final ArrayList<Integer> playQues5 = new ArrayList<>();
        playQues5.add(R.raw.ques21);
        playQues5.add(R.raw.ques22);
        playQues5.add(R.raw.ques23);
        playQues5.add(R.raw.ques24);
        playQues5.add(R.raw.ques25);
        final ArrayList<Integer> playAns5 = new ArrayList<>();
        playAns5.add(R.raw.ans21);
        playAns5.add(R.raw.ans22);
        playAns5.add(R.raw.ans23);
        playAns5.add(R.raw.ans24);
        playAns5.add(R.raw.ans25);

        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtLevel.getText().equals("Level 1")) {
                    mediaPlayer = MediaPlayer.create(PracticeActivity.this, playQues1.get(i));
                }
                else if(txtLevel.getText().equals("Level 2"))
                {
                    mediaPlayer=MediaPlayer.create(PracticeActivity.this,playQues2.get(i));
                }
                else if(txtLevel.getText().equals("Level 3"))
                {
                    mediaPlayer=MediaPlayer.create(PracticeActivity.this,playQues3.get(i));
                }
                else if(txtLevel.getText().equals("Level 4"))
                {
                    mediaPlayer=MediaPlayer.create(PracticeActivity.this,playQues4.get(i));
                }
                else if(txtLevel.getText().equals("Level 5"))
                {
                    mediaPlayer=MediaPlayer.create(PracticeActivity.this,playQues5.get(i));
                }
                mediaPlayer.start();
                v.startAnimation(animation);
                imgStart.setImageResource(R.drawable.icon_sound);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = edtAns.getText().toString().trim();
                final String level = txtLevel.getText().toString().trim();
                countClick+=1;
                if (ans.equals(" ")) {
                    toastShow(v);
                }
                if(countClick == 1)
                {
                    switch (level)
                    {
                        case "Level 1":
                            if (ans.equalsIgnoreCase(ansData[i])) {
                                txtTrans.setText(transData[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                //txtCongra.startAnimation(animation);
                                score+=1;
                                saveScore();
                            } else {
                                toastShowWrong(v);
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 2":
                            if (ans.equalsIgnoreCase(ansData2[i])) {
                                score+=1;
                                saveScore();
                                txtTrans.setText(transData2[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                               // txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 3":
                            if (ans.equalsIgnoreCase(ansData3[i])) {
                                score+=1;
                                saveScore();
                                txtTrans.setText(transData3[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 4":
                            if (ans.equalsIgnoreCase(ansData4[i])) {
                                score+=1;
                                saveScore();
                                txtTrans.setText(transData4[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 5":
                            if (ans.equalsIgnoreCase(ansData5[i])) {
                                score+=1;
                                saveScore();
                                txtTrans.setText(transData5[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                               // txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                    }
                }
                else if(countClick == 2)
                {
                    imgAns.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.startAnimation(animation);
                            switch (level)
                            {
                                case "Level 1":
                                    mediaPlayer = MediaPlayer.create(PracticeActivity.this,playAns1.get(i));
                                    break;
                                case "Level 2":
                                    mediaPlayer = MediaPlayer.create(PracticeActivity.this,playAns2.get(i));
                                    break;
                                case "Level 3":
                                    mediaPlayer=MediaPlayer.create(PracticeActivity.this,ansQues3.get(i));
                                    break;
                                case "Level 4":
                                    mediaPlayer=MediaPlayer.create(PracticeActivity.this,playAns4.get(i));
                                    break;
                                case "Level 5":
                                    mediaPlayer=MediaPlayer.create(PracticeActivity.this,playAns5.get(i));
                                    break;
                            }
                            mediaPlayer.start();
                        }
                    });
                    switch (level)
                    {
                        case "Level 1":
                            if(ans.equals(""))
                            {
                                imgAns.setVisibility(View.VISIBLE);
                            }
                            else if(ans.equalsIgnoreCase(ansData[i]))
                            {
                                txtTrans.setText(transData[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 2":
                            if(ans.equals(""))
                            {
                                imgAns.setVisibility(View.VISIBLE);
                            }
                            else if(ans.equalsIgnoreCase(ansData2[i]))
                            {
                                txtTrans.setText(transData2[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                               // txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 3":
                            if(ans.equals(""))
                            {
                                imgAns.setVisibility(View.VISIBLE);
                            }
                            else if(ans.equalsIgnoreCase(ansData3[i]))
                            {
                                txtTrans.setText(transData3[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 4":
                            if(ans.equals(""))
                            {
                                imgAns.setVisibility(View.VISIBLE);
                            }
                            else if(ans.equalsIgnoreCase(ansData4[i]))
                            {
                                txtTrans.setText(transData4[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                               // txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 5":
                            if(ans.equals(""))
                            {
                                imgAns.setVisibility(View.VISIBLE);
                            }
                            else if(ans.equalsIgnoreCase(ansData5[i]))
                            {
                                txtTrans.setText(transData5[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(edtAns.getText().toString());
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;

                    }
                }
                else if(countClick ==3)
                {
                    switch (level)
                    {
                        case "Level 1":
                            if (ans.equals("")) {
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData[i])) {
                                txtTrans.setText(transData[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 2":
                            if (ans.equals("")) {
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData2[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData2[i])) {
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                                txtTrans.setText(transData2[i]);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                txtTrans.setVisibility(View.VISIBLE);
                            } else {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData2[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 3":
                            if (ans.equals("")) {
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData3[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData3[i])) {
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                                txtTrans.setText(transData3[i]);
                                toastShowTrue(v);
                               // txtCongra.setVisibility(View.VISIBLE);
                                txtTrans.setVisibility(View.VISIBLE);
                            } else {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData3[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 4":
                            if (ans.equals("")) {
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData4[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData4[i])) {
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                                txtTrans.setText(transData4[i]);
                                toastShowTrue(v);
                               // txtCongra.setVisibility(View.VISIBLE);
                                txtTrans.setVisibility(View.VISIBLE);
                            } else {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData4[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 5":
                            if (ans.equals("")) {
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData5[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData5[i])) {
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                                txtTrans.setText(transData5[i]);
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                txtTrans.setVisibility(View.VISIBLE);
                            } else {
                                toastShowWrong(v);
                                imgAns.setVisibility(View.VISIBLE);
                                txtTrans.setText(transData5[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;


                    }
                }
                else if(countClick>=4)
                {
                    switch (level)
                    {
                        case "Level 1":
                            if (ans.equals("")) {
                                txtTrans.setText(ansData[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData[i])) {
                                toastShowTrue(v);
                                //txtCongra.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                txtTrans.setText(ansData[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                // Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 2":
                            if (ans.equals("")) {
                                txtTrans.setText(ansData2[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData2[i])) {
                                //txtCongra.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                txtTrans.setText(ansData2[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 3":
                            if (ans.equals("")) {
                                txtTrans.setText(ansData3[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData3[i])) {
                                //txtCongra.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                txtTrans.setText(ansData3[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 4":
                            if (ans.equals("")) {
                                txtTrans.setText(ansData4[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData4[i])) {
                                //txtCongra.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                txtTrans.setText(ansData4[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                        case "Level 5":
                            if (ans.equals("")) {
                                txtTrans.setText(ansData5[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                //Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                            } else if (ans.equalsIgnoreCase(ansData5[i])) {
                                //txtCongra.setVisibility(View.VISIBLE);
                                toastShowTrue(v);
                                // Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            } else {
                                toastShowWrong(v);
                                txtTrans.setText(ansData5[i]);
                                txtTrans.setVisibility(View.VISIBLE);
                                edtAns.getText().clear();
                            }
                            break;
                    }
                }

                txtScore.setText("Score:"+score);
            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = progressQues.getProgress();
                countClick=0;
                checkDuplicate();
                duplicate=random.nextInt(listRan.size());
                i=listRan.get(duplicate);
                listRan.remove(duplicate);
                String level = txtLevel.getText().toString();
                switch (level)
                {
                    case "Level 1":
                        if(current<progressQues.getMax())
                            txtQues.setText(quesData[i]);
                        else {
                            current = 0;
                            txtLevel.setText("Level 2");
                            txtQues.setText(quesData2[i]);
                        }
                        break;
                    case "Level 2":
                        if(current<progressQues.getMax())
                        {
                            txtQues.setText(quesData2[i]);
                        }
                        else {
                            current = 0;
                            txtLevel.setText("Level 3");
                            txtQues.setText(quesData3[i]);
                        }
                        break;
                    case "Level 3":
                        if(current<progressQues.getMax())
                        {
                            txtQues.setText(quesData3[i]);
                        }
                        else {
                            current = 0;
                            txtLevel.setText("Level 4");
                            txtQues.setText(quesData4[i]);
                        }
                        break;
                    case "Level 4":
                        if(current<progressQues.getMax())
                        {
                            txtQues.setText(quesData4[i]);
                        }
                        else {
                            current = 0;
                            txtLevel.setText("Level 5");
                            txtQues.setText(quesData5[i]);
                        }
                        break;
                    case "Level 5":
                        if(current<progressQues.getMax())
                        {
                            txtQues.setText(quesData5[i]);
                        }
                        else {
                            showAlertDialog();
                        }
                        break;

                }
                progressQues.setProgress(current+20);
                txtTrans.setVisibility(View.INVISIBLE);
                edtAns.getText().clear();
                txtCongra.setVisibility(View.INVISIBLE);
                imgAns.setVisibility(View.INVISIBLE);
            }
        });

    }
    private void toastShow(View view)
    {
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.toast_layout,null);

        TextView text = view.findViewById(R.id.txt_toast);
        text.setText("Please insert your answer!");
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
    private void toastShowWrong(View view)
    {
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.toast_layout,null);

        TextView text = view.findViewById(R.id.txt_toast);
        text.setText("Wrong");
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
    private void toastShowTrue(View view)
    {
        LayoutInflater inflater = getLayoutInflater();
        view = inflater.inflate(R.layout.toast_layout,null);

        TextView text = view.findViewById(R.id.txt_toast);
        text.setText("Correctly");
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
    private  void checkDuplicate()
    {
        if(listRan.size()==0) {
            for(int j:number)
            {
                listRan.add(j);
            }
        }

    }
    private void saveScore()
    {

        SharedPreferences.Editor editor = preferences.edit();
        //int score = Integer.parseInt(txtScore.getText().toString());
        editor.putInt("score",score);
        editor.commit();

    }
    private void init()
    {
        imgStart=findViewById(R.id.img_play);
        imgAns=findViewById(R.id.img_ans);
        edtAns=findViewById(R.id.edt_ans);
        txtQues=findViewById(R.id.edt_question);
        txtTrans=findViewById(R.id.txt_translate);
        txtCongra=findViewById(R.id.txt_congra);
        txtScore=findViewById(R.id.txt_score);
        txtHighScore=findViewById(R.id.txt_highscore);
        btnConfirm=findViewById(R.id.btn_confirm);
        btnNext=findViewById(R.id.btn_next);
        progressQues=findViewById(R.id.progress_ques);
        txtLevel=findViewById(R.id.txt_level);
    }
    public void showAlertDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Congratulation. You have completed this session.");
        //builder.setTitle("Your result");
        builder.setCancelable(false);
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //dialogInterface.dismiss();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStart() {
        imgStart.setAnimation(aniRepeat);
        super.onStart();
    }
}
