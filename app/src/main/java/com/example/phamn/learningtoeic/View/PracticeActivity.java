package com.example.phamn.learningtoeic.View;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaDataSource;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.R;

import java.util.Random;


public class PracticeActivity extends AppCompatActivity {
    SoundManager soundManager;
    ImageView imgStart,imgAns;
    EditText edtAns;
    TextView txtQues,txtTrans,txtLevel,txtCongra,txtScore,txtHighScore;
    Button btnConfirm,btnNext;
    ProgressBar progressQues;
    Random random;
    int i;
    int countClick;
    int score=0,highScore=0;
    SharedPreferences preferences;
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
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_sound);
        final Animation animCongra =AnimationUtils.loadAnimation(this,R.anim.scale_congratulation);
        final String[] quesData = {"Traffic is ____ freely on the motorway.","This photo ___ us a busy rush hour scene.",
                "Several people can be seen ___ to cross the road.","It looks as if it is ___ to rain.",
                "The ___ are all brightly coloured."};
        final  String[] quesData2 ={"The ___ on the left is riding a pedal cycle.",
                "All the people in the ___ are waving their arms"," The water is ___ green in colour.",
                "You can only see four ___ clearly.","You can see one man getting off the ___."};
        final String[] ansData = {"flowing","shows","trying","going","buildings"};
        final String[] ansData2 ={"cyclist","motorboats","deep","boats","tram"};
        final String[] fullansData={"Traffic is flowing freely on the motorway.","This photo shows us a busy rush hour scene."};
        final String[] transData={"Giao thông đang lưu thông tự do trên đường cao tốc.",
                "Bức ảnh này cho chúng ta thấy một khung cảnh giờ cao điểm bận rộn.",
                "Một số người có thể được nhìn thấy đang cố gắng băng qua đường",
                "Có vẻ như trời sắp mưa.","Các tòa nhà đều có màu sắc rực rỡ."};
        final String[] transData2 = {"Người đi xe đạp bên trái đang đạp xe đạp.","Tất cả những người trong xuồng máy đang vẫy tay.",
                "Nước có màu xanh đậm.","Bạn chỉ có thể nhìn thấy bốn chiếc thuyền rõ ràng.","Bạn có thể thấy một người đàn ông xuống xe điện."};
        soundplay();
        random=new Random();
        i=random.nextInt(5);

     /* final int songId = this.getIdSong(soundData[i]);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,songId);*/
        final MediaPlayer mp1 = MediaPlayer.create(this,R.raw.ques1);
        final MediaPlayer mp2 = MediaPlayer.create(this,R.raw.ques2);
        final MediaPlayer mp3 = MediaPlayer.create(this,R.raw.ques3);
        final MediaPlayer mp4 = MediaPlayer.create(this,R.raw.ques4);
        final MediaPlayer mp5 = MediaPlayer.create(this,R.raw.ques5);
        final MediaPlayer mp6 = MediaPlayer.create(this,R.raw.ques6);
        final MediaPlayer mp7= MediaPlayer.create(this,R.raw.ques7);
        final MediaPlayer mp8 = MediaPlayer.create(this,R.raw.ques8);
        final MediaPlayer mp9 = MediaPlayer.create(this,R.raw.ques9);
        final MediaPlayer mp10 = MediaPlayer.create(this,R.raw.ques10);
        final MediaPlayer mpAns1 = MediaPlayer.create(this,R.raw.ans1);
        final MediaPlayer mpAns2 = MediaPlayer.create(this,R.raw.ans2);
        final MediaPlayer mpAns3 = MediaPlayer.create(this,R.raw.ans3);
        final MediaPlayer mpAns4 = MediaPlayer.create(this,R.raw.ans4);
        final MediaPlayer mpAns5 = MediaPlayer.create(this,R.raw.ans5);
        final MediaPlayer mpAns6 = MediaPlayer.create(this,R.raw.ans6);
        final MediaPlayer mpAns7 = MediaPlayer.create(this,R.raw.ans7);
        final MediaPlayer mpAns8 = MediaPlayer.create(this,R.raw.ans8);
        final MediaPlayer mpAns9 = MediaPlayer.create(this,R.raw.ans9);
        final MediaPlayer mpAns10 = MediaPlayer.create(this,R.raw.ans10);
        int mediaArr[] = new int[10];
        mediaArr[1]=mp1.getAudioSessionId();
        if(txtLevel.getText().equals("Level 1")) {
            txtQues.setText(quesData[i]);
        }if(txtLevel.getText().equals("Level 2")){
            txtQues.setText(quesData2[i]);
        }
        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtLevel.getText().equals("Level 1")) {
                    if (i == 0) {
                        mp1.start();
                    }
                    if (i == 1) {
                        mp2.start();
                    }
                    if (i == 2) {
                        mp3.start();
                    }
                    if (i == 3) {
                        mp4.start();
                    }
                    if (i == 4) {
                        mp5.start();
                    }
                }
                if(txtLevel.getText().equals("Level 2"))
                {
                    if(i==0){mp6.start();}
                    if(i==1){mp7.start();}
                    if(i==2){mp8.start();}
                    if(i==3){mp9.start();}
                    if(i==4){mp10.start();}
                }
                v.startAnimation(animation);
                imgStart.setImageResource(R.drawable.icon_sound);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            //String ans = edtAns.getText().toString().trim();
            @Override
            public void onClick(View v) {
                String ans = edtAns.getText().toString().trim();
                countClick+=1;
                if (ans.equals("")) {
                    Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                }
                if(countClick==1)
                {
                    if(txtLevel.getText().equals("Level 1")) {
                        if (ans.equalsIgnoreCase(ansData[i])) {
                            txtTrans.setText(transData[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            txtCongra.setVisibility(View.VISIBLE);
                            txtCongra.startAnimation(animation);
                            score+=1;
                            saveScore();
                            Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                            edtAns.getText().clear();
                        }
                    }
                    if(txtLevel.getText().equals("Level 2"))
                    {
                        if (ans.equalsIgnoreCase(ansData2[i])) {
                            score+=1;
                            saveScore();
                            txtTrans.setText(transData2[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            txtCongra.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                            edtAns.getText().clear();
                        }
                    }
                }
                if(countClick==2)
                {
                    imgAns.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            v.startAnimation(animation);
                            if(txtLevel.getText().equals("Level 1"))
                            {
                                if(i==0){mpAns1.start();}
                                if(i==1){mpAns2.start();}
                                if(i==2){mpAns3.start();}
                                if(i==3){mpAns4.start();}
                                if(i==4){mpAns5.start();}

                            }
                            if(txtLevel.getText().equals("Level 2"))
                            {
                                if(i==0){mpAns6.start();}
                                if(i==1){mpAns7.start();}
                                if(i==2){mpAns8.start();}
                                if(i==3){mpAns9.start();}
                                if(i==4){mpAns10.start();}
                            }
                        }
                    });
                    if(txtLevel.getText().equals("Level 1"))
                    {
                        if(ans.equals(""))
                        {
                            imgAns.setVisibility(View.VISIBLE);
                            //Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                        }
                        else if(ans.equalsIgnoreCase(ansData[i]))
                        {
                            txtTrans.setText(transData[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            txtCongra.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            imgAns.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                            edtAns.getText().clear();
                        }
                    }
                    if(txtLevel.getText().equals("Level 2"))
                    {
                        if(ans.equals(""))
                        {
                            imgAns.setVisibility(View.VISIBLE);
                            //Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                        }
                        else if(ans.equalsIgnoreCase(ansData2[i]))
                        {
                            txtTrans.setText(transData2[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            txtCongra.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            imgAns.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                            edtAns.getText().clear();
                        }
                    }
                }
                if(countClick ==3)
                {

                    if(txtLevel.getText().equals("Level 1")) {

                        if (ans.equals("")) {
                            imgAns.setVisibility(View.VISIBLE);
                            txtTrans.setText(transData[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                        } else if (ans.equalsIgnoreCase(ansData[i])) {
                            txtTrans.setText(transData[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            txtCongra.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                        } else {
                            imgAns.setVisibility(View.VISIBLE);
                            txtTrans.setText(transData[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                            edtAns.getText().clear();
                        }
                    }
                    if(txtLevel.getText().equals("Level 2"))
                    {

                        if (ans.equals("")) {
                            imgAns.setVisibility(View.VISIBLE);
                            txtTrans.setText(transData2[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                        } else if (ans.equalsIgnoreCase(ansData2[i])) {
                            Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                            txtTrans.setText(transData2[i]);
                            txtCongra.setVisibility(View.VISIBLE);
                            txtTrans.setVisibility(View.VISIBLE);
                        } else {
                            imgAns.setVisibility(View.VISIBLE);
                            txtTrans.setText(transData2[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                            edtAns.getText().clear();
                        }
                    }
                }
                if(countClick>=4)
                {
                    if(txtLevel.getText().equals("Level 1")) {
                        if (ans.equals("")) {
                            txtTrans.setText(ansData[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            // Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                        } else if (ans.equalsIgnoreCase(ansData[i])) {
                            txtCongra.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                        } else {
                            txtTrans.setText(ansData[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                            edtAns.getText().clear();
                        }
                    }
                    if(txtLevel.getText().equals("Level 2"))
                    {

                        if (ans.equals("")) {
                            txtTrans.setText(ansData2[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            //Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                        } else if (ans.equalsIgnoreCase(ansData2[i])) {
                            txtCongra.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                        } else {
                            txtTrans.setText(ansData2[i]);
                            txtTrans.setVisibility(View.VISIBLE);
                            Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                            edtAns.getText().clear();
                        }
                    }
                }

                txtScore.setText("Score:"+score);
                //txtHighScore.setText(preferences.getInt("score",score));
            }

        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = progressQues.getProgress();
                countClick=0;
                i =random.nextInt(5);
                if(txtLevel.getText().equals("Level 1")&& current<progressQues.getMax())
                {
                    txtQues.setText(quesData[i]);
                    if(i==0){mp1.start();}
                    if(i==1){mp2.start();}
                    if(i==2){mp3.start();}
                    if(i==3){mp4.start();}
                    if(i==4){mp5.start();}

                }
                else if(current>=progressQues.getMax())
                {
                    current=0;
                    txtLevel.setText("Level 2");
                    txtQues.setText(quesData2[i]);
                    if(i==0){mp6.start();}
                    if(i==1){mp7.start();}
                    if(i==2){mp8.start();}
                    if(i==3){mp9.start();}
                    if(i==4){mp10.start();}
                }
                else
                {
                    txtQues.setText(quesData2[i]);
                    if(i==0){mp6.start();}
                    if(i==1){mp7.start();}
                    if(i==2){mp8.start();}
                    if(i==3){mp9.start();}
                    if(i==4){mp10.start();}
                }
                progressQues.setProgress(current+30);
                txtTrans.setVisibility(View.INVISIBLE);
                edtAns.getText().clear();
                txtCongra.setVisibility(View.INVISIBLE);
                imgAns.setVisibility(View.INVISIBLE);
            }
        });

    }
    /* public int getIdSong(String resName)
     {
         String packName = this.getPackageName();
         int resId = this.getResources().getIdentifier(resName,"raw",packName);
         return resId;
     }*/
    public void soundplay()
    {
        soundManager = new SoundManager();
        soundManager.initSounds(getBaseContext());
        soundManager.addSound(0,R.raw.ans1);
        soundManager.addSound(1,R.raw.ans2);
    }

    public void saveScore()
    {

        SharedPreferences.Editor editor = preferences.edit();
        //int score = Integer.parseInt(txtScore.getText().toString());
        editor.putInt("score",score);
        editor.commit();

    }
    public void init()
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

}
