package com.example.phamn.learningtoeic;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.phamn.learningtoeic.Model.MiniGame;

import java.util.ArrayList;
import java.util.List;

public class MiniGameActivity extends AppCompatActivity {
    ImageView ivPlay;
    ImageView ivCheck;
    EditText editText;
    TextView tvContent;
    TextView tvMean;
    Button btnSubmit;
    Button btnNext;
    List<MiniGame> miniGames;
    int number;
    int times;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_game);
        getSupportActionBar().hide();   // hide title bar
        mapping();
        times = 0;
        number = 0;
        miniGames = new ArrayList<>();
        miniGames.add(new MiniGame(
                "The _____ are leaning against a car",
                "Những đứa trẻ đang dựa vào chiếc xe.",
                "children",
                1));

        newQuestion(number);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivCheck.setVisibility(View.VISIBLE);
                times++;
                if (miniGames.get(number).getAnswer().equalsIgnoreCase(editText.getText().toString().trim())){
                    tvMean.setVisibility(View.VISIBLE);
                    ivCheck.setImageResource(R.drawable.correct);
                }
                else {
                    switch (times){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            tvMean.setVisibility(View.VISIBLE);
                            break;
                        default:
                            break;
                    }
                    ivCheck.setImageResource(R.drawable.incorrect);
                }
            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                times= 0;
                newQuestion(number);
            }
        });
    }

    private void mapping(){
        ivPlay = (ImageView) findViewById(R.id.iv_play);
        ivCheck = (ImageView) findViewById(R.id.iv_check);
        editText = (EditText) findViewById(R.id.edit_text_answer);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvMean = (TextView) findViewById(R.id.tv_mean);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        btnNext = (Button) findViewById(R.id.btn_next);
    }

    private void newQuestion(int index){
        ivCheck.setVisibility(View.INVISIBLE);
        tvContent.setText(miniGames.get(index).getContent());
        tvMean.setText(miniGames.get(index).getMean());
        tvMean.setVisibility(View.VISIBLE);
    }
}
