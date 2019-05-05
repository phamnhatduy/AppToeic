package com.example.phamn.learningtoeic.View;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.Adapter.TitleAdapter;
import com.example.phamn.learningtoeic.Model.History;
import com.example.phamn.learningtoeic.Model.Serial;
import com.example.phamn.learningtoeic.Model.TitleOnPhone;
import com.example.phamn.learningtoeic.R;
import com.example.phamn.learningtoeic.Repository.HistoryRepository;
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
    Dialog dialogLoading, dialogStarting;
    ListView lvTitle;
    TitleAdapter titleAdapter;
    List<TitleOnPhone> listTitle;
    List<History> listHistory;
    int serialID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        showStartDialog();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mapping();


        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        liveDataListener();

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
        switch (id){
            case R.id.nav_series_1:
                serialID = 1;
                break;
            case R.id.nav_vacabulary:
                Intent intent = new Intent(this, TopicVocabularyActivity.class);
                this.startActivity(intent);
                break;
            case R.id.nav_tips:
                Intent intent1 = new Intent(this, TopicTipsActivity.class);
                this.startActivity(intent1);
                break;
            case R.id.nav_idiom:
                Intent intent2 = new Intent(this, IdiomActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.nav_clear_history:
                HistoryRepository repo = new HistoryRepository(getApplication());
                repo.deleteAllHistory();
                break;
            case R.id.nav_support:
                final Dialog dialogHelp = new Dialog(this);
                dialogHelp.setContentView(R.layout.support_layout);
                dialogHelp.setCanceledOnTouchOutside(false);
                dialogHelp.show();
                Button btnClose = (Button) dialogHelp.findViewById(R.id.button_close);
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogHelp.cancel();
                    }
                });
                break;
            case R.id.nav_about:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.about_layout);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                Button btnClose2 = (Button) dialog.findViewById(R.id.button_close);
                btnClose2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void mapping(){
        lvTitle = (ListView) findViewById(R.id.lv_title);
    }

    public void liveDataListener(){
        mainViewModel.getListAllSerial().observe(this, new Observer<List<Serial>>() {
            @Override
            public void onChanged(@Nullable List<Serial> serials) {

            }
        });
        mainViewModel.getListTitleOfSerial().observe(this, new Observer<List<TitleOnPhone>>() {
            @Override
            public void onChanged(@Nullable List<TitleOnPhone> titles) {

            }
        });
        mainViewModel.getListAllTitle().observe(this, new Observer<List<TitleOnPhone>>() {
            @Override
            public void onChanged(@Nullable List<TitleOnPhone> titles) {
                if (listTitle == null) {
                    listTitle = new ArrayList<>();
                    titleAdapter = new TitleAdapter(getApplication(), R.layout.item_title_listview, listTitle);
                    lvTitle.setAdapter(titleAdapter);
                }

                for (int i = 0; i < titles.size(); i++) {
                    TitleOnPhone title = new TitleOnPhone();
                    title.setSerialID(titles.get(i).getSerialID());
                    title.setTitleName(titles.get(i).getTitleName());
                    title.setPart1Audio(titles.get(i).getPart1Audio());
                    title.setPart2Audio(titles.get(i).getPart2Audio());
                    title.setPart3Audio(titles.get(i).getPart3Audio());
                    title.setPart4Audio(titles.get(i).getPart4Audio());
                    title.setPart1ID(titles.get(i).getPart1ID());
                    title.setPart2ID(titles.get(i).getPart2ID());
                    title.setPart3ID(titles.get(i).getPart3ID());
                    title.setPart4ID(titles.get(i).getPart4ID());
                    title.setTime1(titles.get(i).getTime1());
                    title.setTime2(titles.get(i).getTime2());
                    title.setTime3(titles.get(i).getTime3());
                    title.setTime4(titles.get(i).getTime4());
                    title.setNumberOfQuestions1(titles.get(i).getNumberOfQuestions1());
                    title.setNumberOfQuestions2(titles.get(i).getNumberOfQuestions2());
                    title.setNumberOfQuestions3(titles.get(i).getNumberOfQuestions3());
                    title.setNumberOfQuestions4(titles.get(i).getNumberOfQuestions4());
                    title.setHistory1(new History(title.getPart1ID(), "--/--", "--/--"));
                    title.setHistory2(new History(title.getPart2ID(), "--/--", "--/--"));
                    title.setHistory3(new History(title.getPart3ID(), "--/--", "--/--"));
                    title.setHistory4(new History(title.getPart4ID(), "--/--", "--/--"));
                    if (listHistory != null && listHistory.size() > 0) {
                        for (int j = 0; j < listHistory.size(); j++) {
                            if (listHistory.get(j).getPartID() == titles.get(i).getPart1ID()) {
                                title.setHistory1(listHistory.get(j));
                                continue;
                            }
                            if (listHistory.get(j).getPartID() == titles.get(i).getPart2ID()) {
                                title.setHistory2(listHistory.get(j));
                                continue;
                            }
                            if (listHistory.get(j).getPartID() == titles.get(i).getPart3ID()) {
                                title.setHistory3(listHistory.get(j));
                                continue;
                            }
                            if (listHistory.get(j).getPartID() == titles.get(i).getPart4ID()) {
                                title.setHistory4(listHistory.get(j));
                                continue;
                            }
                        }
                    }

                    listTitle.add(title);

                }
                titleAdapter.notifyDataSetChanged();

                if(dialogStarting.isShowing())
                    dialogStarting.cancel();
            }
        });

        mainViewModel.getListHistory().observe(this, new Observer<List<History>>() {
            @Override
            public void onChanged(@Nullable List<History> histories) {
                listHistory = histories;
                List<TitleOnPhone> list = mainViewModel.getListAllTitle().getValue();
                if (list != null) {
                    listTitle.clear();
                    for (int i = 0; i < list.size(); i++) {
                        TitleOnPhone title = new TitleOnPhone();
                        title.setTitleName(list.get(i).getTitleName());
                        title.setPart1ID(list.get(i).getPart1ID());
                        title.setPart2ID(list.get(i).getPart2ID());
                        title.setPart3ID(list.get(i).getPart3ID());
                        title.setPart4ID(list.get(i).getPart4ID());
                        title.setTime1(list.get(i).getTime1());
                        title.setTime2(list.get(i).getTime2());
                        title.setTime3(list.get(i).getTime3());
                        title.setTime4(list.get(i).getTime4());
                        title.setNumberOfQuestions1(list.get(i).getNumberOfQuestions1());
                        title.setNumberOfQuestions2(list.get(i).getNumberOfQuestions2());
                        title.setNumberOfQuestions3(list.get(i).getNumberOfQuestions3());
                        title.setNumberOfQuestions4(list.get(i).getNumberOfQuestions4());
                        title.setHistory1(new History(title.getPart1ID(), "--/--", "--/--"));
                        title.setHistory2(new History(title.getPart2ID(), "--/--", "--/--"));
                        title.setHistory3(new History(title.getPart3ID(), "--/--", "--/--"));
                        title.setHistory4(new History(title.getPart4ID(), "--/--", "--/--"));
                        if (listHistory != null && listHistory.size() > 0) {
                            for (int j = 0; j < listHistory.size(); j++) {
                                if (listHistory.get(j).getPartID() == list.get(i).getPart1ID()) {
                                    title.setHistory1(listHistory.get(j));
                                    continue;
                                }
                                if (listHistory.get(j).getPartID() == list.get(i).getPart2ID()) {
                                    title.setHistory2(listHistory.get(j));
                                    continue;
                                }
                                if (listHistory.get(j).getPartID() == list.get(i).getPart3ID()) {
                                    title.setHistory3(listHistory.get(j));
                                    continue;
                                }
                                if (listHistory.get(j).getPartID() == list.get(i).getPart4ID()) {
                                    title.setHistory4(listHistory.get(j));
                                    continue;
                                }
                            }
                        }
                        listTitle.add(title);
                    }
                    titleAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void showStartDialog(){
        dialogStarting = new Dialog(this, R.style.AppTheme);
        dialogStarting.getWindow().getAttributes().windowAnimations = R.style.DialogTheme;
        dialogStarting.setContentView(R.layout.start_layout);
        dialogStarting.show();
        dialogStarting.setCanceledOnTouchOutside(false);
        if(isOnline()){
//            Toast.makeText(this, "có internet", Toast.LENGTH_SHORT).show();
        }
        else {
//            Toast.makeText(this, "không có internet", Toast.LENGTH_SHORT).show();
            showInternetDialog();
        }
    }

    public void showInternetDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.internet_layout);
        dialog.show();
        dialog.setCanceledOnTouchOutside(true);
        Button btn = (Button) dialog.findViewById(R.id.button_try_again);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        return false;
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
                    dialogLoading.cancel();
                }
            });
        }
    }
}
