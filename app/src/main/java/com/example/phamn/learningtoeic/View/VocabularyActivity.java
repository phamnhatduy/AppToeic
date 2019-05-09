package com.example.phamn.learningtoeic.View;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

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
    //
    SoundManager soundManager;
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
        //
        adapter = new VocabularyAdapter(this,getListVocab(topic));
        recyclerView.setAdapter(adapter);
        soundPlay();
        //
        adapter.setOnItemClickedListener(new VocabularyAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(String pos) {
                Toast.makeText(VocabularyActivity.this, pos, Toast.LENGTH_SHORT).show();
                if(pos.equals("1. abide by")) {
                    soundManager.playSound(1);
                }
                if(pos.equals("2. agreement")) {
                    soundManager.playSound(2);
                }
                if(pos.equals("3. assurance")) {
                    soundManager.playSound(3);
                }
                if(pos.equals("4. cancellation")) {
                    soundManager.playSound(4);
                }
                if(pos.equals("5. determine")) {
                    soundManager.playSound(5);
                }
                if(pos.equals("6. engage")) {
                    soundManager.playSound(6);
                }
                if(pos.equals("7. establish")) {
                    soundManager.playSound(7);
                }
                if(pos.equals("8. obligate")) {
                    soundManager.playSound(8);
                }
                if(pos.equals("9. party")) {
                    soundManager.playSound(9);
                }
                if(pos.equals("10. provision")) {
                    soundManager.playSound(10);
                }
                if(pos.equals("11. resolve")) {
                    soundManager.playSound(11);
                }
                if(pos.equals("12. specific")) {
                    soundManager.playSound(12);
                }
            }
        });
    }
    public void PlayMedia()
    {
        MediaPlayer mediaPlayer;
        mediaPlayer = MediaPlayer.create(this,R.raw.abide);
        mediaPlayer.start();
    }
    public void soundPlay()
    {
        soundManager = new SoundManager();
        soundManager.initSounds(getBaseContext());
        soundManager.addSound(1,R.raw.abide);
        soundManager.addSound(2,R.raw.agreement);
        soundManager.addSound(3,R.raw.assurance);
        soundManager.addSound(4,R.raw.cancellation);
        soundManager.addSound(5,R.raw.determine);
        soundManager.addSound(6,R.raw.engage);
        soundManager.addSound(7,R.raw.establish);
        soundManager.addSound(8,R.raw.obligate);
        soundManager.addSound(9,R.raw.party);
        soundManager.addSound(10,R.raw.provision);
        soundManager.addSound(11,R.raw.resolve);
        soundManager.addSound(12,R.raw.specific);

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
        list.add(new Vocabulary("obligate", "/'ɔbligeit/", "(v): bắt buộc, ép buộc", "The contractor was obligated by the contract to work 40 hours a week."));
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

        list = new ArrayList<>();
        list.add(new Vocabulary("address", "/ə'dres/", "(n,v): (n) địa chỉ, diễn văn, bài nói chuyện, tác phong nói chuyện, sự khôn khéo; (v) trình bày", "Marco's business plan addresses the needs of small business owners"));
        list.add(new Vocabulary("avoid", "/ə'vɔid/", "(v): tránh, tránh khỏi; hủy bỏ, bác bỏ", "To avoid going out of business, owners should prepare a proper business plan"));
        list.add(new Vocabulary("demonstrate", "/'demənstreit/", "(v): bày tỏ, biểu lộ, cho thấy; chứng minh, giải thích", "The professor demonstrated through a case study that a business plan can impress a lender"));
        list.add(new Vocabulary("develop", "/di'veləp/", "(v): phát triển, tiến triển, triển khai, mở rộng", "Lily developed her ideas into a business plan by taking a class at the community college"));
        list.add(new Vocabulary("evaluate", "/i'væljueit/", "(v): đánh giá, định giá; ước lượng", "It's important to evaluate your competition when making a business plan"));
        list.add(new Vocabulary("gather", "/'gæðə/", "(v): tập hợp, tụ thập, thu thập; kết luận, suy ra", "We gathered information for our plan from many sources"));
        list.add(new Vocabulary("offer", "/'ɔfə/", "(n,v): (n) đề xuất, đề nghị, chào mời, chào hàng, dạm, hỏi, ướm; (v) đề nghị", "Devon accepted our offer to write the business plan"));
        list.add(new Vocabulary("primarily", "/'praimərili/", "(adv): trước hết, đầu tiên; chính, chủ yếu, quan trọng nhất", "The developers are thinking primarily of how to enter the South American market"));
        list.add(new Vocabulary("risk", "/rɪsk/", "(n): nguy cơ, sự nguy hiểm, sự rủi ro", "The primary risk for most start-up businesses is insufficient capital"));
        list.add(new Vocabulary("strategy", "/ˈstrætədʒi/", "(n): chiến lược, sự vạch kế hoạch hành động", "A business plan is a strategy for running a business and avoiding problems"));
        list.add(new Vocabulary("strong", "/strɔɳ/", "(adj): khỏe, mạnh, tốt, bền, kiên cố; đanh thép, kiên quyết; sôi nổi, nhiệt tình...", "The professor made a strong argument for the value of a good business plan"));
        list.add(new Vocabulary("substitution", "/,sʌbsti'tju:ʃn/", "(n): sự đổi, sự thay thế", "Your substitution of fake names for real ones makes the document seem insincere"));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("accommodate", "/ə'kɔmədeit/", "(v): điều tiết, điều chỉnh, thu xếp, làm cho phù hợp", "The meeting room was large enough to accommodate the various needs of the groups using it"));
        list.add(new Vocabulary("arrangement", "/ə'reindʤmənt/", "(n): ‹sự› thu xếp, dàn xếp, sắp xếp, sắp đặt", "The travel arrangements were taken care of by Sara, Mr. Billing's capable assistant"));
        list.add(new Vocabulary("association", "/ə,sousi'eiʃn/", "(n): hội, hội liên hiệp, đoàn thể; ‹sự› kết hợp, liên kết, liên hợp", "Local telephone companies formed an association to serve common goals, meet their common needs, and improve efficiency"));
        list.add(new Vocabulary("attend", "/ə'tend/", "(v): tham dự, có mặt; chăm sóc, phục vụ; đi theo, đi kèm, hộ tống", "We expect more than 100 members to attend the annual meeting"));
        list.add(new Vocabulary("get in touch", "N/A", "(v): liên lạc với, tiếp xúc với, giữ quan hệ với, có dính líu đến", "As soon as we arrive at the hotel, we will get in touch with the manager about the unexpected guests"));
        list.add(new Vocabulary("hold", "/hould/", "(v): cầm, nắm, giữ; chứa, đựng; tổ chức, tiến hành", "This meeting room holds at least 80 people comfortably"));
        list.add(new Vocabulary("location", "/lou'keiʃn/", "(n): vị trí, khu đất, hiện trường", "The location of the meeting was changed from the Red Room to the Green Room"));
        list.add(new Vocabulary("overcrowded", "/əʊvəˈkraʊdɪd/", "(adj): chật ních, đông nghịt", "Too many poor people are living in overcrowded conditions"));
        list.add(new Vocabulary("register", "/'redʤistə/", "(n, v): (n) danh sách, sổ, sổ sách; (v) đăng ký", "According to the register, more than 250 people attended the afternoon seminar"));
        list.add(new Vocabulary("select", "/si'lekt/", "(v): chọn lựa, chọn lọc, tuyển chọn", "The winners were a select group"));
        list.add(new Vocabulary("session", "/'seʃn/", "(n): phiên, kỳ, buổi (họp, học)", "The morning sessions tend to fill up first, so sign up early"));
        list.add(new Vocabulary("take part in", "N/A", "(v): tham dự, tham gia", "We could not get enough people to take part in the meeting, so we canceled it"));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("access", "/ˈækses/", "(n, v): (n) lối vào, đường vào, sự/quyền truy cập; (v) truy cập", "You can't gain access to the files unless you know the password"));
        list.add(new Vocabulary("allocate", "/ˈæləkeɪt/", "(v): cấp cho, phân phối, phân phát, chia phần; chỉ định, định rõ vị trí", "The office manager did not allocate enough money to purchase software"));
        list.add(new Vocabulary("compatible", "/kəm'pætəbl/", "(adj): tương thích, tương hợp, hợp nhau, có thể dùng được với nhau", "This operating system is not compatible with this model computer"));
        list.add(new Vocabulary("delete", "/di'li:t/", "(v): xóa đi, bỏ đi, gạch đi (to remove, to erase)", "The technicians deleted all the data on the disk accidentally"));
        list.add(new Vocabulary("display", "/dis'plei/", "(v) hiển thị, biểu lộ, phô bày, trình bày, trưng bày; (n) sự trưng bày", "The accounting program displays a current balance when opened."));
        list.add(new Vocabulary("duplicate", "/'dju:plikit/", "(v): sao lại, làm thành 2 bản, gấp đôi, nhân đôi", "I think the new word processing program will duplicate the success of the one introduced last year"));
        list.add(new Vocabulary("failure", "/'feiljə/", "(n): hỏng, thiếu, yếu, trượt, thất bại, bất thành", "Your failure to inform us about the changed password cost the company a day's work"));
        list.add(new Vocabulary("figure out", "/ˈfɪɡə/ /aʊt/", "(v): tìm hiểu, đoán ra, tính toán ra, giải ra", "By examining all of the errors, the technicians figured out how to fix the problem"));
        list.add(new Vocabulary("ignore", "/ig'nɔ:/", "(v): bỏ qua, phớt lờ, không để ý tới", "He ignored all the 'No Smoking' signs and lit up a cigarette"));
        list.add(new Vocabulary("search", "/sə:tʃ/", "(n, v): (n) tìm kiếm, tìm hiểu; điều tra, thăm dò; (v) tìm", "Our search of the database produced very little information"));
        list.add(new Vocabulary("shut down", "/ʃʌt/ /daʊn/", "(v): đóng lại, ngừng lại; tắt máy, ngừng hoạt động, chấm dứt", "Please shut down the computer before you leave"));
        list.add(new Vocabulary("warning", "/'wɔ:niɳ/", "(n): ‹sự/lời› cảnh báo, báo trước (có nguy hiểm hoặc gặp vấn đề rắc rối)", "The red flashing light gives a warning to users that the battery is low"));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("affordable", "/əˈfɔːd/", "(adj): có đủ khả năng, có đủ điều kiện (sức lực/thời gian/tiền bạc)", "The company's first priority was to find an affordable phone system"));
        list.add(new Vocabulary("as needed", "N/A", "(adv): khi cần, lúc cần, cần thiết", "The service contract states that repairs will be made on an as-needed basis"));
        list.add(new Vocabulary("be in charge of", "N/A", "(v): chịu trách nhiệm về; đang điều khiển, đang chỉ huy", "He appointed someone to be in charge of maintaining a supply of paper in the fax machine"));
        list.add(new Vocabulary("capacity", "/kə'pæsiti/", "(n): sức chứa, dung tích, khả năng chứa đựng; khả năng, năng lực", "The new conference room is much larger and has a capacity of one hundred people"));
        list.add(new Vocabulary("durable", "/ˈdjʊərəbl/", "(adj): bền, lâu, lâu bền", "These chairs are more durable than the first ones we looked at"));
        list.add(new Vocabulary("initiative", "/i'niʃiətiv/", "(n): bắt đầu, khởi đầu, khởi xướng", "Employees are encouraged to take the initiative and share their ideas with management."));
        list.add(new Vocabulary("physically", "/ˈfɪzɪkli/", "(adv): về thân thể/cơ thể/thể chất; theo quy luật tự nhiên, một cách vật lý", "The computer screen is making her physically sick."));
        list.add(new Vocabulary("provider", "/provider/", "(n): người cung cấp, nhà cung cấp (supplier)", "The deparment was extremely pleased with the service they received from the phone provide."));
        list.add(new Vocabulary("recur", "/ri'kə:/", "(v): lặp lại, diễn lại, tái diễn, tái phát; lặp đi lặp lại", "The managers did not want that particular error to recur"));
        list.add(new Vocabulary("reduction", "/ri'dʌkʃn/", "(n): ‹sự› giảm, hạ, thu nhỏ, hạ thấp (lessening, decrease)", "The outlet store gave a 20 percent reduction in the price of the shelves and bookcases"));
        list.add(new Vocabulary("stay on top of", "N/A", "(v): nắm bắt tình hình (đang xảy ra), hiểu biết thông tin mới nhất", "In this industry, you must stay on top of current developments"));
        list.add(new Vocabulary("stock", "/stɒk/", "(v, n): kho/hàng dự trữ; vốn, cổ phần", "The employees stocked the shelves on a weekly basis"));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("appreciation", "/ə,pri:ʃi'eiʃn/", "(n): ‹sự› đánh giá, nhận thức (đúng/cao/sâu sắc); sự cảm kích", "In appreciation of your hard work on the Castcon project, the department will hold a casual lunch party on November third"));
        list.add(new Vocabulary("be made of", "N/A", "(v): làm bằng (cái gì), gồm có (cái gì)", "This job will really test what you are made of"));
        list.add(new Vocabulary("bring in", "N/A", "(v): Thuê, mướn, tuyển dụng, dẫn tới, mang tới", "The company brought in new team of project planners"));
        list.add(new Vocabulary("casually", "/ˈkæʒuəli/", "(adv): bình thường, tự nhiên, không trịnh trọng, thân mật (informally)", "On Fridays, most employees dress casually"));
        list.add(new Vocabulary("code", "/koud/", "(n): quy định, quy tắc, luật lệ, đạo lý", "Even the most traditional companies are changing their dress code to something less formal"));
        list.add(new Vocabulary("expose", "/iks'pouz/", "(v): phơi bày, bộc lộ, phô ra, trưng bày", "He did not want to expose his fears and insecurity to anyone."));
        list.add(new Vocabulary("glimpse", "/glimps/", "(n): nhìn lướt qua, thoáng qua", "The secretary caught a glimpse of her new boss as she was leaving the office."));
        list.add(new Vocabulary("out of", "N/A", "(adj): hết, mất, không còn", "The presenter ran out of time before he reached his conclusion"));
        list.add(new Vocabulary("outdated", "/aut'deitid/", "(adj): hết hạn; lỗi thời, lạc hậu, cổ, hiện nay không còn dùng (obsolete)", "Before you do a mailing, make sure that none of the addresses is outdated."));
        list.add(new Vocabulary("practice", "/'præktis/", "(n, v): (n) sự thực hành; (v) thực hành, rèn luyện, tập luyện; thói quen, thủ tục", "Bill practiced answering the telephone until he was satisfied"));
        list.add(new Vocabulary("reinforce", "/,ri:in'fɔ:s/", "(v): tăng cường, củng cố, gia cố", "Employees reinforced their learning with practice in the workplace"));
        list.add(new Vocabulary("verbally", "/'və:bəli/", "(adv): bằng miệng, bằng lời nói", "The guarantee was made only verbally"));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("disk", "/disk/", "(n): đĩa (vi tính, thể thao, đĩa hát...)", "Rewritable compact disks are more expensive than read-only CDs"));
        list.add(new Vocabulary("facilitate", "/fə'siliteit/", "(v): làm cho dễ dàng, làm cho thuận tiện", "The computer program facilitated the scheduling of appointments"));
        list.add(new Vocabulary("network", "/'netwə:k/", "(v, n): (v) kết nối, liên kết; (n) mạng lưới, hệ thống", "We set up a new network in my office to share files"));
        list.add(new Vocabulary("popularity", "/,pɔpju'læriti/", "(n): ‹tính/sự› đại chúng, phổ biến, nổi tiếng, được yêu mến", "This brand of computers is extremely popular among college students"));
        list.add(new Vocabulary("process", "/ˈprəʊses/", "(v, n): (v) xử lý; (n) quá trình, sự tiến triển, sự tiến hành; phương pháp, cách thức, quy trình", "There is a process for determining why your computer is malfunctioning"));
        list.add(new Vocabulary("replace", "/ri'pleis/", "(v): thay thế; đặt vào lại chỗ cũ", "I've replaced the hard drive that was malfunctioning"));
        list.add(new Vocabulary("revolution", "/,revə'lu:ʃn/", "(n): vòng, tua, sự xoay vòng; cuộc cách mạng", "We see a revolution in the computer field almost every day"));
        list.add(new Vocabulary("sharp", "/ʃɑ:p/", "(adj): sắc, bén, rõ rệt, sắc nét; thông minh, láu lỉnh; thình lình, đột ngột", "The new employee proved how sharp she was when she mastered the new program in a few days"));
        list.add(new Vocabulary("skill", "/skil/", "(n): kỹ năng, kỹ xảo; sự khéo léo, sự tinh xảo", "The software developer has excellent technical skills and would be an asset to our software programming team"));
        list.add(new Vocabulary("software", "/ˈsɒftweə(r)/", "(n): phần mềm, chương trình máy tính", "Many computers come pre-loaded with software"));
        list.add(new Vocabulary("store", "/stɔ:/", "(v): cửa hàng, cửa hiệu, kho hàng", "You can store more data on a zip drive"));
        list.add(new Vocabulary("technically", "/ˈteknɪkli/", "(adv): nói đến/nói về mặt kỹ thuật; một cách chuyên môn/nghiêm túc", "Technically speaking, the virus infected only script files"));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("assemble", "/ə'sembl/", "(v): thu thập, lắp ráp, tập hợp", "All the students were asked to assemble in the main hall"));
        list.add(new Vocabulary("beforehand", "/bi'fɔ:hænd/", "(adv): sẵn, có sẵn, trước, sớm", "To speed up the mailing, we should prepare the labels beforehand"));
        list.add(new Vocabulary("complication", "/ˌkɑːmplɪˈkeɪʃn/", "(n): sự phức tạp, sự rắc rối", "She will have to spend two more days in the hospital due to complications during the surgery."));
        list.add(new Vocabulary("courier", "/'kuriə/", "(n): người đưa tin, người đưa thư, người chuyển phát", "We hired a courier to deliver the package"));
        list.add(new Vocabulary("express", "/iks'pres/", "(adj): nhanh, hỏa tốc, tốc hành.", "It's important that this document be there tomorrow, so please send it express mail."));
        list.add(new Vocabulary("fold", "/fould/", "(v): nếp gấp, gấp lại", "Fold the letter into three parts before stuffing it into the envelope"));
        list.add(new Vocabulary("layout", "/ˈleɪaʊt/", "(n): sự bổ trí trang giấy", "There is no single correct layout for business letters"));
        list.add(new Vocabulary("mention", "/'menʃn/", "(n, v): (n) sự đề cập; (v) nói đến, đề cập đếm, đề xuất", "You should mention in the letter that we can arrange for mailing the brochures as well as printing them"));
        list.add(new Vocabulary("petition", "/pi'tiʃn/", "(n, v): (n) ‹sự/đơn› cầu xin, đơn kiến nghị, đơn thỉnh cầu; (v) cầu xin, kiến nghị", "The petition was photocopied and distributed to workers who will collect the neccessary signatures"));
        list.add(new Vocabulary("proof", "/proof/", "(n): bằng chứng, chứng cớ", "This letter was not proofed very carefully; it is full of typing mistakes"));
        list.add(new Vocabulary("register", "/'redʤistə/", "(v): đăng ký, sổ, sổ sách, công- tơ", "You can register this mail for an additional $2.2"));
        list.add(new Vocabulary("revise", "/ri'vaiz/", "(v): đọc lại, xem lại, duyệt lại, sửa lại (bản in thử, đạo luật...)", "The brochure was revised several times before it was sent to the printer"));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        listTopic.add(list);

        list = new ArrayList<>();
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
        list.add(new Vocabulary("", "", "", ""));
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
