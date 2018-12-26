package com.example.phamn.learningtoeic.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.phamn.learningtoeic.Adapter.TopicVocabularyAdapter;
import com.example.phamn.learningtoeic.Model.TopicVocabulary;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;

public class TopicVocabularyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TopicVocabularyAdapter adapter;
    List<TopicVocabulary> listTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_vocabulary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vocabulary");

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_topic_vocabulary);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerHorizontal = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerHorizontal);

        listTopic = new ArrayList<>();
        listTopic.add(new TopicVocabulary(R.drawable.contracts, "Contracts"));
        listTopic.add(new TopicVocabulary(R.drawable.marketing, "Marketing"));
        listTopic.add(new TopicVocabulary(R.drawable.warranties, "Warranties"));
        listTopic.add(new TopicVocabulary(R.drawable.business_planning, "Business Planning"));
        listTopic.add(new TopicVocabulary(R.drawable.conferences, "Conferences"));
        listTopic.add(new TopicVocabulary(R.drawable.computers_and_the_internet, "Computers & Internet"));
        listTopic.add(new TopicVocabulary(R.drawable.office_technology, "Office Technology"));
        listTopic.add(new TopicVocabulary(R.drawable.office_procedures, "Office Procedures"));
        listTopic.add(new TopicVocabulary(R.drawable.electronics, "Electronics"));
        listTopic.add(new TopicVocabulary(R.drawable.correspondence, "Correspondence"));
        listTopic.add(new TopicVocabulary(R.drawable.job_ads_and_recruitment, "Job Ads & Recruitment"));
        listTopic.add(new TopicVocabulary(R.drawable.apply_and_interviewing, "Apply & Interviewing"));
        listTopic.add(new TopicVocabulary(R.drawable.hiring_and_training, "Hiring & Training"));
        listTopic.add(new TopicVocabulary(R.drawable.salaries_and_benefits, "Salaries & Benefits"));
        listTopic.add(new TopicVocabulary(R.drawable.promotion, "Promotions & Awards"));
        listTopic.add(new TopicVocabulary(R.drawable.shopping, "Shopping"));
        listTopic.add(new TopicVocabulary(R.drawable.ordering_supplies, "Order Supplies"));
        listTopic.add(new TopicVocabulary(R.drawable.shipping, "Shipping"));
        listTopic.add(new TopicVocabulary(R.drawable.invoices, "Invoices"));
        listTopic.add(new TopicVocabulary(R.drawable.inventory, "Inventory"));
        listTopic.add(new TopicVocabulary(R.drawable.banking, "Banking"));
        listTopic.add(new TopicVocabulary(R.drawable.accounting, "Accounting"));
        listTopic.add(new TopicVocabulary(R.drawable.investments, "Investments"));
        listTopic.add(new TopicVocabulary(R.drawable.taxes, "Taxes"));
        listTopic.add(new TopicVocabulary(R.drawable.financial_statements, "Financial Statement"));
        listTopic.add(new TopicVocabulary(R.drawable.property_and_departments, "Property & Departments"));
        listTopic.add(new TopicVocabulary(R.drawable.board_meeting, "Board Meeting"));
        listTopic.add(new TopicVocabulary(R.drawable.quality_control, "Qualyty Control"));
        listTopic.add(new TopicVocabulary(R.drawable.product_development, "Product Development"));
        listTopic.add(new TopicVocabulary(R.drawable.renting_and_leasing, "Renting & Leasing"));
        listTopic.add(new TopicVocabulary(R.drawable.selecting_a_restaurant, "Selecting A Restaurant"));
        listTopic.add(new TopicVocabulary(R.drawable.eating_out, "Eating Out"));
        listTopic.add(new TopicVocabulary(R.drawable.ordering_lunch, "Ordering Lunch"));
        listTopic.add(new TopicVocabulary(R.drawable.cook, "Cooking As A Career"));
        listTopic.add(new TopicVocabulary(R.drawable.events, "Events"));
        listTopic.add(new TopicVocabulary(R.drawable.general_travel, "General Travel"));
        listTopic.add(new TopicVocabulary(R.drawable.airlines, "Airlines"));
        listTopic.add(new TopicVocabulary(R.drawable.trains, "Trains"));
        listTopic.add(new TopicVocabulary(R.drawable.hotels, "Hotels"));
        listTopic.add(new TopicVocabulary(R.drawable.car_rentals, "Car Rentals"));
        listTopic.add(new TopicVocabulary(R.drawable.movies, "Movies"));
        listTopic.add(new TopicVocabulary(R.drawable.theater, "Theater"));
        listTopic.add(new TopicVocabulary(R.drawable.music, "Music"));
        listTopic.add(new TopicVocabulary(R.drawable.museums, "Museums"));
        listTopic.add(new TopicVocabulary(R.drawable.media, "Media"));
        listTopic.add(new TopicVocabulary(R.drawable.doctors_office, "Doctor's Office"));
        listTopic.add(new TopicVocabulary(R.drawable.dentists_office, "Dentist's Office"));
        listTopic.add(new TopicVocabulary(R.drawable.health, "Health"));
        listTopic.add(new TopicVocabulary(R.drawable.hospitals, "Hospitals"));
        listTopic.add(new TopicVocabulary(R.drawable.pharmacy, "Pharmacy"));

        adapter = new TopicVocabularyAdapter(this, listTopic);
        recyclerView.setAdapter(adapter);
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

