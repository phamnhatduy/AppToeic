package com.example.phamn.learningtoeic.View;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.phamn.learningtoeic.R;

public class MainActivity extends AppCompatActivity {
    Button buttonPart1, buttonTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);    // set fullscreen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // xóa tiêu đề
        setContentView(R.layout.activity_main);

        mapping();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMain fragmentMain = new FragmentMain();
        fragmentTransaction.add(R.id.frame_main, fragmentMain);
        fragmentTransaction.commit();


        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void mapping(){
        buttonPart1 = (Button)findViewById(R.id.button_part1);
        buttonTest = (Button) findViewById(R.id.button_test);
    }
}
