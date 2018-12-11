package com.example.phamn.learningtoeic.View;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Adapter.TitleAdapter;
import com.example.phamn.learningtoeic.Model.Title;
import com.example.phamn.learningtoeic.Model.TitleOnPhone;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.ViewModel.MainViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainViewModel mainViewModel;
    Button btnPart1;
    Button btnPart2, btnPart3, btnPart4, btnHistory1, btnHistory2, btnHistory3, btnHistory4;
    TextView tvPartName;
    Dialog dialogLoading;
    ListView lvTitle;
    TitleAdapter titleAdapter;
    List<TitleOnPhone> listTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);    // set fullscreen
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // xóa tiêu đề
        setContentView(R.layout.activity_main);

        mapping();
        listTitle = new ArrayList<>();
        titleAdapter = new TitleAdapter(this, R.layout.item_title_listview, listTitle);
        lvTitle.setAdapter(titleAdapter);

        //showLoadingDialog(false);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        liveDataListener();

//        btnPart1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Part1Activity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//
//        btnPart2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Part2Activity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        btnPart3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Part3Activity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        btnPart4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Part4Activity.class);
//                startActivity(intent);
//                finish();
//            }
//        });
//        btnHistory1.setEnabled(false);
//        btnHistory2.setEnabled(false);
//        btnHistory3.setEnabled(false);
//        btnHistory4.setEnabled(false);


    }

    public void test(){
        TitleOnPhone title = new TitleOnPhone();
        title.setTitleName("đây là test");
        title.setTime1("1:00");
        title.setTime2("2:00");
        title.setTime3("3:00");
        title.setTime4("4:00");
        title.setNumberOfQuestions1(1);
        title.setNumberOfQuestions2(2);
        title.setNumberOfQuestions3(3);
        title.setNumberOfQuestions4(4);

        listTitle.add(title);
        listTitle.add(title);
        listTitle.add(title);
        listTitle.add(title);
        //}
        titleAdapter.notifyDataSetChanged();
    }

    public void mapping(){
        lvTitle = (ListView) findViewById(R.id.lv_title);
//        tvPartName = (TextView) findViewById(R.id.tv_TitleName);
//        btnPart1 = (Button)findViewById(R.id.button_part1);
//        btnPart2 = (Button)findViewById(R.id.button_part2);
//        btnPart3 = (Button)findViewById(R.id.button_part3);
//        btnPart4 = (Button)findViewById(R.id.button_part4);
//
//        btnHistory1 = (Button)findViewById(R.id.button_history1);
//        btnHistory2 = (Button)findViewById(R.id.button_history2);
//        btnHistory3 = (Button)findViewById(R.id.button_history3);
//        btnHistory4 = (Button)findViewById(R.id.button_history4);
    }

    public void liveDataListener(){
        mainViewModel.getListAllTitle().observe(this, new Observer<List<TitleOnPhone>>() {
            @Override
            public void onChanged(@Nullable List<TitleOnPhone> titles) {
//                if(!dialogLoading.isShowing())
//                    showLoadingDialog(true);
                for (int i = 0; i < titles.size(); i++) {
                    TitleOnPhone title = new TitleOnPhone(
                            titles.get(i).getTitleName(),
                            titles.get(i).getTime1(),
                            titles.get(i).getTime2(),
                            titles.get(i).getTime3(),
                            titles.get(i).getTime4(),
                            titles.get(i).getNumberOfQuestions1(),
                            titles.get(i).getNumberOfQuestions2(),
                            titles.get(i).getNumberOfQuestions3(),
                            titles.get(i).getNumberOfQuestions4());

//                    TitleOnPhone title = new TitleOnPhone();
//                    title.setTitleName(titles.get(i).getTitleName());
//                    title.setTime1(titles.get(i).getTime1());
//                    title.setTime2(titles.get(i).getTime2());
//                    title.setTime3(titles.get(i).getTime3());
//                    title.setTime4(titles.get(i).getTime4());
//                    title.setNumberOfQuestions1(titles.get(i).getNumberOfQuestions1());
//                    title.setNumberOfQuestions2(titles.get(i).getNumberOfQuestions2());
//                    title.setNumberOfQuestions3(titles.get(i).getNumberOfQuestions3());
//                    title.setNumberOfQuestions4(titles.get(i).getNumberOfQuestions4());

//                    TitleOnPhone title = new TitleOnPhone();
//                    title.setTitleName("đây là test");
//                    title.setTime1("1:00");
//                    title.setTime2("2:00");
//                    title.setTime3("3:00");
//                    title.setTime4("4:00");
//                    title.setNumberOfQuestions1(1);
//                    title.setNumberOfQuestions2(2);
//                    title.setNumberOfQuestions3(3);
//                    title.setNumberOfQuestions4(4);
//
//                    listTitle.add(title);
                    listTitle.add(title);
                }
                titleAdapter.notifyDataSetChanged();
            }
        });
    }

    public void showLoadingDialog(boolean successful){
        if(successful == false) { // đang tải
            dialogLoading = new Dialog(this, R.style.AppTheme);
            //dialogLoading.setTitle("LearningToeic");
            dialogLoading.setContentView(R.layout.loading_layout);
            dialogLoading.show();
            dialogLoading.setCanceledOnTouchOutside(false);
            Animation animRotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
            ImageView ivLoading = dialogLoading.findViewById(R.id.iv_loading);
            ivLoading.startAnimation(animRotate);
            Button btnStart = dialogLoading.findViewById(R.id.btn_start);
            btnStart.setVisibility(View.INVISIBLE);
        }
        else {  // tải thành công
            ImageView ivLoading = dialogLoading.findViewById(R.id.iv_loading);
            ivLoading.clearAnimation();
            ivLoading.setImageResource(R.drawable.success);
            TextView tvLoading = dialogLoading.findViewById(R.id.tv_loading);
            tvLoading.setText("Load Successfully!");
            Button btnStart = dialogLoading.findViewById(R.id.btn_start);
            btnStart.setVisibility(View.VISIBLE);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogLoading.hide();
                }
            });
        }
    }
}
