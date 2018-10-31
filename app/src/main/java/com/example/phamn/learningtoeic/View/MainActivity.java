package com.example.phamn.learningtoeic.View;

import android.app.Activity;
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

import com.example.phamn.learningtoeic.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends Activity {
    Button buttonPart1;
    TextView tvTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);    // set fullscreen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // xóa tiêu đề
        setContentView(R.layout.activity_main);

        mapping();

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMain fragmentMain = new FragmentMain();
        fragmentTransaction.add(R.id.frame_main, fragmentMain);
        fragmentTransaction.commit();

    }

    public void mapping(){
        buttonPart1 = (Button)findViewById(R.id.button_part1);
        tvTest = (TextView) findViewById(R.id.textView_test);
    }
}
