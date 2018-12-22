package com.example.phamn.learningtoeic.View;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.phamn.learningtoeic.Adapter.TitleAdapter;
import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Model.TitleOnPhone;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.ViewModel.HistoryViewModel;
import com.example.phamn.learningtoeic.ViewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    MainViewModel mainViewModel;
    HistoryViewModel historyViewModel;
    Button btnPart1;
    Button btnPart2, btnPart3, btnPart4, btnHistory1, btnHistory2, btnHistory3, btnHistory4;
    TextView tvPartName;
    Dialog dialogLoading;
    ListView lvTitle, lvHistory;
    TitleAdapter titleAdapter;
    List<TitleOnPhone> listTitle;
    List<History> listHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //////////////////////////////////////////////////////////////////////////////////////////////
        mapping();


        //showLoadingDialog(false);
//        Intent intent = getIntent();
//        int partID = intent.getIntExtra("partID", 1);

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        liveDataListener();
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        historyViewModel.getListAllHistory().observe(this, new Observer<List<History>>() {
            @Override
            public void onChanged(@Nullable List<History> histories) {
                for(int i = 0; i < histories.size(); i++){

                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reload) {
            finish();
            startActivity(getIntent());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_series_1) {

        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_support) {

        } else if (id == R.id.nav_about) {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.about_layout);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            Button btnClose = (Button) dialog.findViewById(R.id.button_close);
            btnClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.hide();
                }
            });
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mapping(){
        lvTitle = (ListView) findViewById(R.id.lv_title);
        lvHistory = (ListView) findViewById(R.id.lv_history);
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
                if(listTitle == null) {
                    listTitle = new ArrayList<>();
                    titleAdapter = new TitleAdapter(getApplication(), R.layout.item_title_listview, listTitle);
                    lvTitle.setAdapter(titleAdapter);
                }
//                if(!dialogLoading.isShowing())
//                    showLoadingDialog(true);
                for (int i = 0; i < titles.size(); i++) {
                    TitleOnPhone title = new TitleOnPhone(
                            titles.get(i).getTitleName(),
                            titles.get(i).getPartID(),
                            titles.get(i).getTime1(),
                            titles.get(i).getTime2(),
                            titles.get(i).getTime3(),
                            titles.get(i).getTime4(),
                            titles.get(i).getNumberOfQuestions1(),
                            titles.get(i).getNumberOfQuestions2(),
                            titles.get(i).getNumberOfQuestions3(),
                            titles.get(i).getNumberOfQuestions4(),
                            titles.get(i).getListHistory());
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

        mainViewModel.getListHistory().observe(this, new Observer<List<History>>() {
            @Override
            public void onChanged(@Nullable List<History> histories) {

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
