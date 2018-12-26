package com.example.phamn.learningtoeic.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Switch;

import com.example.phamn.learningtoeic.Adapter.TopicVocabularyAdapter;
import com.example.phamn.learningtoeic.Adapter.VocabularyAdapter;
import com.example.phamn.learningtoeic.Model.Vocabulary;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;

public class VocabularyActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    VocabularyAdapter adapter;
    List<Vocabulary> listVocab;
    List<List<Vocabulary>> listTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_vocabulary);

        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerHorizontal);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        listVocab = new ArrayList<>();
        listTopic = new ArrayList<>();
        initListTopic();
        Intent intent = getIntent();
        String topic = intent.getStringExtra("topic");
        getSupportActionBar().setTitle(topic);
        adapter = new VocabularyAdapter(getListVocab(topic));
        recyclerView.setAdapter(adapter);
    }

    public List<Vocabulary> getListVocab(String topic) {
        switch (topic){
            case "Contracts":
                return listTopic.get(0);
            case "Marketing":
                return listTopic.get(1);
            case "Warranties":
                return listTopic.get(2);
            case "Business Planning":
                return listTopic.get(3);
            case "Conferences":
                return listTopic.get(4);
            case "Computers & Internet":
                return listTopic.get(5);
            case "Office Technology":
                return listTopic.get(6);
            case "Office Procedures":
                return listTopic.get(7);
            case "Electronics":
                return listTopic.get(8);
            case "Correspondence":
                return listTopic.get(9);
            case "Job Ads & Recruitment":
                return listTopic.get(10);
            case "Apply & Interviewing":
                return listTopic.get(11);
            case "Hiring & Training":
                return listTopic.get(12);
            case "Salaries & Benefits":
                return listTopic.get(13);
            case "Promotions & Awards":
                return listTopic.get(14);
            case "Shopping":
                return listTopic.get(15);
            case "Order Supplies":
                return listTopic.get(16);
            case "Shipping":
                return listTopic.get(17);
            case "Invoices":
                return listTopic.get(18);
            case "Inventory":
                return listTopic.get(19);
            case "Banking":
                return listTopic.get(20);
            case "Accounting":
                return listTopic.get(21);
            case "Investments":
                return listTopic.get(22);
            case "Taxes":
                return listTopic.get(23);
            case "Financial Statement":
                return listTopic.get(24);
            case "Property & Departments":
                return listTopic.get(25);
            case "Board Meeting":
                return listTopic.get(26);
            case "Qualyty Control":
                return listTopic.get(27);
            case "Product Development":
                return listTopic.get(28);
            case "Renting & Leasing":
                return listTopic.get(29);
            case "Selecting A Restaurant":
                return listTopic.get(30);
            case "Eating Out":
                return listTopic.get(31);
            case "Ordering Lunch":
                return listTopic.get(32);
            case "Cooking As A Career":
                return listTopic.get(33);
            case "Events":
                return listTopic.get(34);
            case "General Travel":
                return listTopic.get(35);
            case "Airlines":
                return listTopic.get(36);
            case "Trains":
                return listTopic.get(37);
            case "Hotels":
                return listTopic.get(38);
            case "Car Rentals":
                return listTopic.get(39);
            case "Movies":
                return listTopic.get(40);
            case "Theater":
                return listTopic.get(41);
            case "Music":
                return listTopic.get(42);
            case "Museums":
                return listTopic.get(43);
            case "Media":
                return listTopic.get(44);
            case "Doctor's Office":
                return listTopic.get(45);
            case "Dentist's Office":
                return listTopic.get(46);
            case "Health":
                return listTopic.get(47);
            case "Hospitals":
                return listTopic.get(48);
            case "Pharmacy":
                return listTopic.get(49);
        }
        return listTopic.get(0);
    }

    public void initListTopic(){
        List<Vocabulary> list = new ArrayList<>();
        list.add(new Vocabulary("abide by", "/ə'baid/", "(v): tôn trọng, tuân theo, giữ (lời)", "The two parties agreed to abide by the judge's decision"));
        list.add(new Vocabulary("agreement", "/ə'gri:mənt/", "(n): hợp đồng, giao kèo, sự đồng ý/thỏa thuận với nhau", "According to the agreement, the caterer will also supply the flowers for the event"));
        list.add(new Vocabulary("assurance", "/ə'ʃuərəns/", "(n): sự cam đoan, bảo đảm, chắc chắn; sự tin chắc, tự tin", "The sales associate gave his assurance that the missing keyboard would be replaced the next day."));
        list.add(new Vocabulary("cancellation", "/,kænse'leiʃn/", "(n): sự bãi bỏ, hủy bỏ", "The cancelation of her flight caused her problems for the rest of the week"));
        list.add(new Vocabulary("determine", "/di'tə:min/", "(v): quyết định, xác định, định rõ; quyết tâm, kiên quyết", "After reading the contract, I was still unable to detemine if our company was liable for back wages."));
        list.add(new Vocabulary("engage", "/in'geidʤ/", "(v)Tham gia, cam kết, (n)sự hứa hẹn, hứa hôn", "He engaged us in a fascinating discussion about current business law"));
        list.add(new Vocabulary("establish", "/is'tæbliʃ/", "(v): thiết lập, thành lập; xác minh, chứng minh, củng cố", "The merger of the two company established a powerful new corporation"));
        list.add(new Vocabulary("obligateobligate", "/'ɔbligeit/", "(v): bắt buộc, ép buộc", "The contractor was obligated by the contract to work 40 hours a week."));
        list.add(new Vocabulary("party", "/'pɑ:ti/", "(n): đảng, phái, đội, nhóm; người tham dự/tham gia; buổi liên hoan, buổi tiệc", "The parties agreed to settlement in their contract dispute."));
        list.add(new Vocabulary("provision", "/provision/", "(n): sự dự liệu, dự trữ, dự phòng, cung cấp; điều khoản", "The father made provision for his children through his will."));
        list.add(new Vocabulary("resolve", "/ri'zɔlv/", "(v, n): (v) giải quyết, (n) sự kiên quyết / sự tin chắc", "The manager resolved to clean out all the files at the end of the week."));
        list.add(new Vocabulary("specific", "/spi'sifik/", "(adj): riêng biệt, cụ thể, đặc trưng; rõ ràng, rành mạch", "The customer's specific complaint was not addressed in his e-mail."));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("attract", "/ə'trækt/", "(v): hấp dẫn, lôi cuốn, thu hút", "The display attracted a number of people at the convention"));
        list.add(new Vocabulary("compare", "/kəm'peə/", "(v): so sánh, đối chiếu", "Once the customer compared the two products, her choice was easy."));
        list.add(new Vocabulary("competition", "/,kɔmpi'tiʃn/", "(v): ‹sự/cuộc› cạnh tranh, tranh giành, thi đấu", "In the competition for afternoon dinners, Hector's has come out on top"));
        list.add(new Vocabulary("consume", "/kən'sju:m/", "(v): tiêu thụ, tiêu dùng", "The printer consumes more toner than the downstairs printer"));
        list.add(new Vocabulary("convince", "/kən'vins/", "(v): Thuyết phục", "He convinced me that he was right."));
        list.add(new Vocabulary("currently", "/ˈkʌrəntli/", "(adv): hiện thời, hiện nay, lúc này", "Currently, customers are demanding big discounts for bulk orders"));
        list.add(new Vocabulary("fad", "/fæd/", "(n): mốt nhất thời, sự thích thú tạm thời; dở hơi, gàn dở", "The mini dress was a fad once thought to be finished, but now it is making a comeback."));
        list.add(new Vocabulary("inspiration", "/,inspə'reiʃn/", "(n): ‹sự/người/vật› truyền cảm hứng, gây cảm hứng", "His work is an inspiration to the marketing department."));
        list.add(new Vocabulary("market", "/'mɑ:kit/", "(v): thị trường, chợ, nơi mua bán sản phẩm...", "The market for brightly colored clothing was brisk last year, but it's moving sluggishly this year"));
        list.add(new Vocabulary("persuasion", "/pə'sweiʤn/", "(n): ‹sự› thuyết phục, làm cho tin (chú ý: persuade > convince)", "The seminar teaches techniques of persuasion to increase sales."));
        list.add(new Vocabulary("productive", "/prəˈdʌktɪv/", "(adj): sản xuất, sinh sản; sinh lợi nhiều, có hiệu quả", "The unproductive sales meeting brought many staff complaints"));
        list.add(new Vocabulary("satisfaction", "/,sætis'fækʃn/", "(n): sự làm thỏa mãn, sự hài lòng", "Your satisfaction is guaranteed or you'll get your money back."));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("characteristic", "/,kæriktə'ristik/", "(adj, n): (adj) đặc thù, (n) đặc trưng, đặc điểm, đặc thù, cá biệt", "One characteristic of the store is that it is slow in mailing refund checks."));
        list.add(new Vocabulary("consequence", "/'kɔnsikwəns/", "(n): kết quả, hậu quả, hệ quả; tầm quan trọng, tính trọng đại", "As a consequence of not having seen a dentist for several years, Lydia had several cavities."));
        list.add(new Vocabulary("consider", "/kən'sidə/", "(v): cân nhắc, suy xét, suy nghĩ (một cách cẩn thận); lưu ý, quan tâm", "After considering all the options, Della decided to buy a used car"));
        list.add(new Vocabulary("cover", "/'kʌvə/", "(v): che, phủ, trùm, bọc; bao gồm", "Will my medical insurance cover this surgery?"));
        list.add(new Vocabulary("expiration", "/,ekspaiə'reiʃn/", "(n): sự mãn hạn, sự hết hạn, sự kết thúc", "Have you checked the expriration date on this yogurt?"));
        list.add(new Vocabulary("frequently", "/'friːkwəntli/", "(adv): thường xuyên, một cách thường xuyên", "Appliances frequently come with a one-year warranty"));
        list.add(new Vocabulary("imply", "/im'plai/", "(v): ngụ ý, hàm ý, ẩn ý, ý nói", "The guarantee on the Walkman implied that all damages were covered under warranty for one year."));
        list.add(new Vocabulary("promise", "/promise/", "(v): (n) hứa hẹn, cam đoan, bảo đảm; (v) hứa", "The sales associate promised that our new mattress would arrive by noon on Saturday"));
        list.add(new Vocabulary("protect", "/protect/", "(v): bảo vệ, bảo hộ, che chở", "Consumer laws are designed to protect the public against unscrupulous vendors"));
        list.add(new Vocabulary("reputation", "/,repju:'teiʃn/", "(n): danh tiếng, thanh danh, tiếng (tốt của nhân vật)", "The company knew that the reputation of its products was the most important asset it had"));
        list.add(new Vocabulary("require", "/ri'kwaiə/", "(v): đòi hỏi, yêu cầu, cần phải", "The law requires that each item clearly display the warranty information"));
        list.add(new Vocabulary("variety", "/və'raiəti/", "(n): đa dạng, nhiều thứ/loại/vẻ khác nhau", "There's a variety of standard terms that you'll find in warranties"));
        listTopic.add(list);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; goto parent activity.
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
