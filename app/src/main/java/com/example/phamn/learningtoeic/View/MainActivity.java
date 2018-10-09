package com.example.phamn.learningtoeic.View;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.phamn.learningtoeic.JSON.JSON;
import com.example.phamn.learningtoeic.Model.QuestionResponse;
import com.example.phamn.learningtoeic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buttonPart1, buttonTest;
    TextView tvTest;
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

        //QuestionResponse questionResponse = new QuestionResponse();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new JSON().execute("https://myhost2018.000webhostapp.com/Test1/Part1/json.php");
            }
        });
    }

    public void mapping(){
        buttonPart1 = (Button)findViewById(R.id.button_part1);
        buttonTest = (Button) findViewById(R.id.button_test);
        tvTest = (TextView) findViewById(R.id.textView_test);
    }
}
