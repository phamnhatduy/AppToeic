package com.example.phamn.learningtoeic.View;

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

public class ReviewActivity extends AppCompatActivity {
    ImageView imageView;
    EditText edtAnswer;
    Button btnAnswer,btnChange;
    SoundManager soundManager;
    Random random;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        soundPlay();
        setContentView(R.layout.activity_review);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Learning Toeic");
        //init
        imageView = findViewById(R.id.image_speaker);
        edtAnswer = findViewById(R.id.edt_answer);
        btnAnswer=findViewById(R.id.btn_play);
        btnChange=findViewById(R.id.btn_change);
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_sound);
        final String[] wordAnswer = {"abide","accommodate","address","agreement","arrangement","association",
                "assurance","attend","attract","avoid","cancellation","characteristic","compare","competition","consequence",
                "consider","consume","convince","cover","currently","demonstrate","determine","develop","engage","establish",
                "evaluate","expiration","fad","frequently","gather","hold","imply","inspiration","location","market","obligate",
                "offer","overcrowded","party","persuasion","primarily","productive","promise","protect","provision","register",
                "reputation","require","resolve","risk","satisfaction","select","session","specific","strategy","strong","substitution",
                "variety"};

        random = new Random();
        pos = random.nextInt(58);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                imageView.setImageResource(R.drawable.sound_icon);
                // pos = random.nextInt(58);
                soundManager.playSound(pos);
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // imageView.setEnabled(false);
                pos = random.nextInt(58);
                soundManager.playSound(pos);
                Toast.makeText(ReviewActivity.this, wordAnswer[pos], Toast.LENGTH_SHORT).show();
                edtAnswer.getText().clear();
            }
        });
        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ans = edtAnswer.getText().toString().trim();
                if(ans.equals(""))
                {
                    Toast.makeText(ReviewActivity.this, "Please insert your answer ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(ans.equalsIgnoreCase(wordAnswer[pos]))
                    {
                        Toast.makeText(ReviewActivity.this, "That's correct!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ReviewActivity.this, "That's wrong !", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
