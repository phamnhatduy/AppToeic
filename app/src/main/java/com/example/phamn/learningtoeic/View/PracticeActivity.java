package com.example.phamn.learningtoeic.View;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.R;

import java.util.Random;


public class PracticeActivity extends AppCompatActivity {
    ImageView imgStart;
    EditText edtAns;
    TextView txtQues,txtTrans;
    Button btnConfirm,btnNext;
    Random random;
    int i;
    int countClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Learning Toeic");

        imgStart=findViewById(R.id.img_play);
        edtAns=findViewById(R.id.edt_ans);
        txtQues=findViewById(R.id.edt_question);
        txtTrans=findViewById(R.id.txt_translate);
        btnConfirm=findViewById(R.id.btn_confirm);
        btnNext=findViewById(R.id.btn_next);
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_sound);
        final String[] quesData = {"Traffic is ____ freely on the motorway.","This photo shows us a busy rush hour scene"};
        final String[] ansData = {"flowing","shows"};
        final String[] fullansData={"Traffic is flowing freely on the motorway.","This photo shows us a busy rush hour scene."};
        final String[] transData={"Giao thông đang lưu thông tự do trên đường cao tốc.",
                "Bức ảnh này cho chúng ta thấy một khung cảnh giờ cao điểm bận rộn."};
        random=new Random();
        i=random.nextInt(2);

     /*   final int songId = this.getIdSong(soundData[i]);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,songId);*/
        final MediaPlayer mp1 = MediaPlayer.create(this,R.raw.ques1);
        final MediaPlayer mp2=MediaPlayer.create(this,R.raw.ques2);

        txtQues.setText(quesData[i]);
        imgStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(i==0)
                {
                    mp1.start();
                }
                if(i==1)
                {
                    mp2.start();
                }
                v.startAnimation(animation);
                imgStart.setImageResource(R.drawable.sound_icon);
            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countClick+=1;
                String ans = edtAns.getText().toString().trim();
                if(countClick==1)
                {
                    if(ans.equals(""))
                    {
                        Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                    }
                    else if(ans.equalsIgnoreCase(ansData[i]))
                    {
                        Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                        edtAns.getText().clear();
                    }
                }
                if(countClick==2)
                {
                    txtTrans.setText(transData[i]);
                    txtTrans.setVisibility(View.VISIBLE);
                    if(ans.equals(""))
                    {
                        Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                    }
                    else if(ans.equalsIgnoreCase(ansData[i]))
                    {
                        Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                        edtAns.getText().clear();
                    }
                }
                if(countClick>=3)
                {
                    txtTrans.setText(ansData[i]);
                    txtTrans.setVisibility(View.VISIBLE);
                    if(ans.equals(""))
                    {
                        Toast.makeText(PracticeActivity.this, "Please insert", Toast.LENGTH_SHORT).show();
                    }
                    else if(ans.equalsIgnoreCase(ansData[i]))
                    {
                        Toast.makeText(PracticeActivity.this, "true", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(PracticeActivity.this, "false", Toast.LENGTH_SHORT).show();
                        edtAns.getText().clear();
                    }
                }

            }

        });
        btnNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                countClick=0;
                i =random.nextInt(2);
                txtQues.setText(quesData[i]);
                if(i==0)
                {
                    mp1.start();
                }
                if(i==1)
                {
                    mp2.start();
                }
                txtTrans.setVisibility(View.INVISIBLE);
                edtAns.getText().clear();
            }
        });

    }
   /* public int getIdSong(String resName)
    {
        String packName = this.getPackageName();
        int resId = this.getResources().getIdentifier(resName,"raw",packName);
        return resId;
    }*/
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
