package com.example.phamn.learningtoeic.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.phamn.learningtoeic.Adapter.IdiomAdapter;
import com.example.phamn.learningtoeic.Model.Idiom;
import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;

public class IdiomActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    IdiomAdapter idiomAdapter;
    List<Idiom> listIdiom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiom);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Idiom");

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view_idiom);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerHorizontal = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerHorizontal);

        listIdiom = new ArrayList<>();
        listIdiom.add(new Idiom("You scratch my back and I’ll scratch yours", "Có qua có lại mới toại lòng nhau"));
        listIdiom.add(new Idiom("New one in, old one out", "Có mới nới cũ"));
        listIdiom.add(new Idiom("It’ too late to lock the stable when the horse is stolen", "Mất bò mới lo làm chuồng"));
        listIdiom.add(new Idiom("With age comes wisdom", "Gừng càng già càng cay"));
        listIdiom.add(new Idiom("Nothing is more precious than independence and freedom", "Không có gì quý hơn độc lập tự do"));
        listIdiom.add(new Idiom("Handsome is as handsome does", "Tốt gỗ hơn tốt nước sơn"));
        listIdiom.add(new Idiom("Never offer to teach fish to swim","Múa rìu qua mắt thợ"));
        listIdiom.add(new Idiom("To try to run before the one can walk","Chưa học bò chớ lo học chạy"));
        listIdiom.add(new Idiom("Nobody has ever shed tears without seeing a coffin","Chưa thấy quan tài chưa đổ lệ"));
        listIdiom.add(new Idiom("You get what you pay for","Tiền nào của nấy"));
        listIdiom.add(new Idiom("As strong as a horse","Khỏe như trâu"));
        listIdiom.add(new Idiom("All roads lead to Rome","Đường nào cũng về La Mã"));
        listIdiom.add(new Idiom("Good wine needs no bush","Hữu xạ tự nhiên hương"));
        listIdiom.add(new Idiom("Diamond cuts diamond","Vỏ quýt dày có móng tay nhọn"));
        listIdiom.add(new Idiom("Spare the rod and spoil the child","Thương cho roi cho vọt"));
        listIdiom.add(new Idiom("Speak one way and act another","Nói một đường làm một nẻo"));
        listIdiom.add(new Idiom("Don’t judge a book by its cover","Đừng đánh giá con người qua bề ngoài"));
        listIdiom.add(new Idiom("It’s no use beating around the bush","Nói gần nói xa chẳng qua nói thật"));
        listIdiom.add(new Idiom("Man proposes God deposes","Mưu sự tại nhân thành sự tại thiên"));
        listIdiom.add(new Idiom("Out of sight out of mind","Xa mặt cách lòng"));
        listIdiom.add(new Idiom("East or West home is best","Dù trong dù đục ao nhà vẫn hơn"));
        listIdiom.add(new Idiom("So many men, so many minds","Chín người mười ý"));
        listIdiom.add(new Idiom("Every man has his mistakes","Không ai hoàn hảo cả"));
        listIdiom.add(new Idiom("Love me love my dog","Yêu ai yêu cả đường đi, ghét ai ghét cả tông chi họ hàng"));
        listIdiom.add(new Idiom("What will be will be","Cái gì đến cũng đến"));
        listIdiom.add(new Idiom("Every day is not Sunday","Sông có khúc người có lúc"));
        listIdiom.add(new Idiom("When in Rome do as the Romans do","Nhập gia tùy tục"));
        listIdiom.add(new Idiom("He laughs best who laughs last","Cười người hôm trước hôm sau người cười"));
        listIdiom.add(new Idiom("Slow but sure","Chậm mà chắc"));
        listIdiom.add(new Idiom("Beauty is only skin deep","Cái nết đánh chết cái đẹp"));
        listIdiom.add(new Idiom("Jack of all trades and master of none","Nghề nào cũng biết nhưng chẳng tinh nghề nào"));
        listIdiom.add(new Idiom("Every Jack has his Jill","Nồi nào úp vung nấy"));
        listIdiom.add(new Idiom("A friend in need is a friend indeed","Hoạn nạn mới biết bạn hiền"));
        listIdiom.add(new Idiom("Curses come home to roost","Ác giả ác báo"));
        listIdiom.add(new Idiom("No pains no gains","Tay làm hàm nhai"));
        listIdiom.add(new Idiom("Grasp all lose all","Tham thì thâm"));
        listIdiom.add(new Idiom("Easier said than done","Nói thì dễ làm thì khó"));
        listIdiom.add(new Idiom("Easy come easy go","Dễ được thì cũng dễ mất"));
        listIdiom.add(new Idiom("Nothing venture nothing gains","Phi thương bất phú"));
        listIdiom.add(new Idiom("Other times other ways","Mỗi thời mỗi cách"));
        listIdiom.add(new Idiom("While there’s life, there’s hope","Còn nước còn tát"));
        listIdiom.add(new Idiom("The empty vessel makes greatest sound","Thùng rỗng kêu to"));
        listIdiom.add(new Idiom("He who excuses himself, accuses himself","Có tật giật mình"));
        listIdiom.add(new Idiom("Beauty is in the eye of the beholder","Yêu nên tốt, ghét nên xấu"));
        listIdiom.add(new Idiom("Blood is thicker than water","Một giọt máu đào hơn ao nước lã"));
        listIdiom.add(new Idiom("Good watch prevents misfortune","Cẩn tắc vô ưu"));
        listIdiom.add(new Idiom("Great minds think alike","Ý tưởng lớn gặp nhau"));
        listIdiom.add(new Idiom("He that knows nothing doubts nothing","Điếc không sợ súng"));
        listIdiom.add(new Idiom("His eyes are bigger than his belly","No bụng đói con mắt"));
        listIdiom.add(new Idiom("It’s the first step that counts","Vạn sự khởi đầu nan"));
        listIdiom.add(new Idiom("Like father like son","Cha nào con nấy"));
        listIdiom.add(new Idiom("Tit for tat","Ăn miếng trả miếng"));
        listIdiom.add(new Idiom("The more the merrier","Càng đông càng vui"));
        listIdiom.add(new Idiom("When the cat is away, the mice will play","Vắng chủ nhà gà mọc đuôi tôm"));
        listIdiom.add(new Idiom("Who drinks will drink again","Chứng nào tật nấy"));
        listIdiom.add(new Idiom("Don’t count your chickens before they hatch","Nói trước bước không qua"));
        listIdiom.add(new Idiom("To carry coals to Newcastle","Chở củi về rừng"));
        listIdiom.add(new Idiom("Haste makes waste","Dục tốc bất đạt"));
        listIdiom.add(new Idiom("If you sell your cow, you will sell her milk too","Cùi không sợ lở"));
        listIdiom.add(new Idiom("Neck or nothing","Không vào hang cọp sao bắt được cọp con"));
        listIdiom.add(new Idiom("A good turn deserves another","Ở hiền gặp lành"));
        listIdiom.add(new Idiom("A miss is as good as a mile","Sai một ly đi một dặm"));
        listIdiom.add(new Idiom("Losers are always in the wrong","Thắng làm vua thua làm giặc"));
        listIdiom.add(new Idiom("Laughing is the best medicine","Một nụ cười bằng mười than thuốc bổ"));
        listIdiom.add(new Idiom("If you can’t bite, never show your teeth","Miệng hùm gan sứa"));
        listIdiom.add(new Idiom("Love is blind","Tình yêu là mù quáng"));
        listIdiom.add(new Idiom("Where there’s smoke, there’s fire","Không có lửa sao có khói"));
        listIdiom.add(new Idiom("Let bygones be bygones","Việc gì qua rồi hãy cho qua"));
        listIdiom.add(new Idiom("We reap what we sow","Gieo gió ắt gặp bảo"));
        listIdiom.add(new Idiom("To kill two birds with one stone","Nhất cửa lưỡng tiện"));
        listIdiom.add(new Idiom("Bitter pills may have blessed effects","Thuốc đắng dã tật"));
        listIdiom.add(new Idiom("Better die on your feet than live on your knees","Chết vinh còn hơn sống nhục"));
        listIdiom.add(new Idiom("United we stand, divided we fall","Đoàn kết là sống, chia rẽ là chết"));
        listIdiom.add(new Idiom("Birds have the same feather stick together","Đồng thanh tương ứng, đồng khí tương cầu"));
        listIdiom.add(new Idiom("Practice makes perfect","Có công mài sắt có ngày nên kim"));
        listIdiom.add(new Idiom("Never say die up man try","Đừng bao giờ bỏ cuộc"));
        listIdiom.add(new Idiom("When you eat a fruit, think of the man who planted the tree","Uống nước nhớ nguồn"));
        listIdiom.add(new Idiom("All that glitters is not gold","Chớ thấy sáng loáng mà tưởng là vàng"));
        listIdiom.add(new Idiom("Never put off tomorrow what you can do today","Việc gì làm được hôm nay chớ để ngày mai"));
        listIdiom.add(new Idiom("To set a sprat to catch a mackerel","Thả con tép bắt con tôm"));
        listIdiom.add(new Idiom("Better late than never","Thà trễ còn hơn không"));
        listIdiom.add(new Idiom("Travel broadens the mind","Đi một ngày đàng học một sàng khôn"));
        listIdiom.add(new Idiom("No more no less","Không hơn không kém"));
        listIdiom.add(new Idiom("Sink or swim","Được ăn cả ngã về không"));
        listIdiom.add(new Idiom("To live from hand to mouth","Được đồng nào hay đồng đó"));
        listIdiom.add(new Idiom("To give him an inch, he will take a yard","Được voi đòi tiên"));
        listIdiom.add(new Idiom("You can’t have it both ways","Được cái này thì mất cái kia"));
        listIdiom.add(new Idiom("A good wife makes a good husband","Trai khôn vì vợ, gái ngoan vì chồng"));
        listIdiom.add(new Idiom("A man is known by the company he keeps","Nhìn việc biết người"));
        listIdiom.add(new Idiom("A good name is sooner lost than won","Mua danh ba vạn bánh danh ba đồng"));
        listIdiom.add(new Idiom("A good name is better than riches","Tốt danh hơn tốt áo"));
        listIdiom.add(new Idiom("A good face is a letter of recommendation","Nhân hiền tại mạo"));
        listIdiom.add(new Idiom("A good beginning makes a good ending","Đầu xuôi đuôi lọt"));
        listIdiom.add(new Idiom("A clean hand needs no washing","Vàng thật không sợ lửa"));
        listIdiom.add(new Idiom("The failure is the mother of success","Thất bại là mẹ thành công"));
        listIdiom.add(new Idiom("The die is cast","Chạy trời không khỏi nắng"));
        listIdiom.add(new Idiom("Death pays all debts","Chết là hết"));
        listIdiom.add(new Idiom("A black hen lays a white egg","Xanh vỏ đỏ lòng"));
        listIdiom.add(new Idiom("Time cure all pains","Thời gian sẽ làm lành mọi vết thương"));
        listIdiom.add(new Idiom("Money talks","Có tiền mua tiên cũng được"));

        idiomAdapter = new IdiomAdapter(listIdiom);
        recyclerView.setAdapter(idiomAdapter);
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
