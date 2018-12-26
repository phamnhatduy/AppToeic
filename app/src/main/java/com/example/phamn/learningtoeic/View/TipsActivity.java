package com.example.phamn.learningtoeic.View;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.phamn.learningtoeic.R;

import java.util.ArrayList;
import java.util.List;

public class TipsActivity extends AppCompatActivity {
    ListView listView;
    LinearLayout tipsLayout;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tips");
        tipsLayout = (LinearLayout) findViewById(R.id.tips_layout);
        scrollView = (ScrollView) findViewById(R.id.scroll_view_tips);
        Intent intent = getIntent();
        int number = intent.getIntExtra("number", 0);
        switch (number){
            case 0: showTip1();
                break;
            case 1: showTipPart1();
                break;
            case 2: showTipPart2();
                break;
            case 3: showTipPart3();
                break;
            case 4: showTipPart4();
                break;
        }
    }

    public void showTip1(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView1 = new TextView(this);
        textView1.setTextSize(22);
        textView1.setTextColor(Color.parseColor("#0000EE"));
        textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView1.setText("1. Tập quen dần với việc nghe cường độ cao");
        layout.addView(textView1);

        TextView textView2 = new TextView(this);
        textView2.setTextSize(20);
        textView2.setTextColor(Color.parseColor("#000033"));
        textView2.setText("  Tôi biết cảm giác đó: đâu đầu, đau tai, mệt mỏi, chóng mặt, buồn ngủ, trầm cảm, lo lắng. Không phải cảm sốt hay tâm thần gì đâu, đó là cảm giác khi nghe tiếng Anh liên tục trên 15’ đối với những bạn chưa quen, phải không nào?\n" +
                "\n" +
                "  Bốn năm trước khi lần đầu nghe tin tức trên CNN, BBC tôi có cảm giác y chang. Chẳng hiểu nó lãi nhãi cái quái gì, nghe tiếng được tiếng không, nghe 1 hồi là nhức đầu, chóng mặt, cảm giác như bị đi lạc vô chỗ quái quỷ nào đó.\n" +
                "\n" +
                "  Bình thường thôi, những nguyên nhân chính khiến bạn cảm thấy như vậy là:");
        layout.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setTextSize(20);
        textView3.setTextColor(Color.parseColor("#000033"));
        textView3.setTypeface(textView3.getTypeface(), Typeface.BOLD);
        textView3.setText(" - Ít nghe tiếng Anh thường xuyên\n" +
                " - Chủ yếu bạn nghe những nội dung ngắn\n" +
                " - Cố gắng dịch từng câu chữ sang tiếng Việt\n");
        layout.addView(textView3);

        TextView textView4 = new TextView(this);
        textView4.setTextSize(22);
        textView4.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView4.setTextColor(Color.parseColor("#0000EE"));
        textView4.setText("Vậy bây giờ làm sao?");
        layout.addView(textView4);

        TextView textView5 = new TextView(this);
        textView5.setTextSize(20);
        textView5.setTextColor(Color.parseColor("#000033"));
        textView5.setText(" - Nghe nhiều lên! Nghe hằng ngày, mọi lúc mọi nơi.\n" +
                " - Nghe nội dung dài lên. Nghe những đoạn hội thoại, bài phát biểu, chương trình thời sự (CNN, BBC, VOA) dài ít nhất 30’.\n" +
                " - Tập tư duy bằng tiếng Anh.\n" +
                " - Cách khác là nghe thụ động. Ví dụ như bật BBC radio, nghe khoảng 2 tiếng 1 ngày, không cần hiểu, vừa nghe vừa làm việc khác, nghe đến mức không bị nhức đầu, phân biệt được các từ, quen với các âm, và vẫn tập trung làm được việc khác là đạt!");
        layout.addView(textView5);

        ImageView iv1 = new ImageView(this);
        iv1.setLayoutParams(new FrameLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        iv1.setImageResource(R.drawable.bbc_radio);
        layout.addView(iv1);

        TextView textView6 = new TextView(this);
        textView6.setTextSize(22);
        textView6.setTextColor(Color.parseColor("#0000EE"));
        textView6.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView6.setText("2. Phớt lờ những quấy rầy khi tập trung");
        layout.addView(textView6);

        TextView textView7 = new TextView(this);
        textView7.setTextSize(20);
        textView7.setTextColor(Color.parseColor("#000033"));
        textView7.setText("  Mỗi nội dung trong bài nghe TOEIC kéo dài từ 30 giây đến tầm 2 phút. Một người lớn khoẻ mạnh bình thường thì thời gian tập trung trung bình là 5 phút.\n\n" +
                "  Của cá vàng là 9 giây. Của những đối tượng tăng động giảm chú ý thì cũng khó nói. Nếu bạn không phải 2 đối tượng này thì không cần phải lo gì về khả năng tập trung của mình cả đâu nhé.");
        layout.addView(textView7);

        ImageView iv2 = new ImageView(this);
        iv2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        iv2.setImageResource(R.drawable.fish);
        layout.addView(iv2);

        TextView textView8 = new TextView(this);
        textView8.setTextSize(20);
        textView8.setTextColor(Color.parseColor("#000033"));
        textView8.setText("  Phần cứng bên trong cho sự tập trung bạn biết là bạn có thừa. Vậy chỉ còn yếu tố bên ngoài thôi. Những yếu tố bên ngoài gây xao nhãng khi nghe thường gặp là:\n" +
                "   - Băng nghe không rõ\n" +
                "   - Tiếng động bên ngoài\n" +
                "   - Nhiều người đi qua đi lại\n" +
                "   - Còn 1 lý do nữa: Buồn ngủ\n\n"+
                "  Để loại bỏ triệt để những yếu tố này thì chả có cái cách quái nào cả. Bạn phải sống chung với lũ, quen dần với nó, kệ nó.\n");
        layout.addView(textView8);


        TextView textView9 = new TextView(this);
        textView9.setTextSize(22);
        textView9.setTextColor(Color.parseColor("#0000EE"));
        textView9.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView9.setText("3. Nắm rõ cấu trúc và hiểu đề thi trong lòng bàn tay");
        layout.addView(textView9);

        TextView textView10 = new TextView(this);
        textView10.setTextSize(20);
        textView10.setTextColor(Color.parseColor("#000033"));
        textView10.setText("  Ối giời! Ai thi TOEIC mà chẳng biết bài nghe TOEIC có 4 phần với 100 câu hỏi.\n" +
                "\n" +
                "  Đúng, chính xác, nhưng bạn có đủ “hiểu” và tự tin trả lời được các câu hỏi sau không:\n" +
                "   - Điểm khác biệt giữa các phần?\n" +
                "   - Cách luyện nghe cho từng phần?\n" +
                "   - Cần tập trung nghe gì cho mỗi phần?\n" +
                "   - Kỹ thuật làm bài tốt nhất cho các phần?\n" +
                "   - Cách kiếm điểm dễ dàng nhất?\n" +
                "   - Những bẫy thường gặp trong phần nghe?");
        layout.addView(textView10);

        scrollView.addView(layout);
    }

    public void showTipPart1(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView1 = new TextView(this);
        textView1.setTextSize(22);
        textView1.setTextColor(Color.parseColor("#0000EE"));
        textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView1.setText("Phần 1 - Hình ảnh");
        layout.addView(textView1);


        ImageView iv1 = new ImageView(this);
        iv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        iv1.setImageResource(R.drawable.part1toeic);
        layout.addView(iv1);

        TextView textView2 = new TextView(this);
        textView2.setTextSize(20);
        textView2.setTextColor(Color.parseColor("#000033"));
        textView2.setText("  Phần này là phần ăn điểm, có thể gọi là dễ nhất trong các phần thi TOEIC. Trong phần 1 này để đạt được điểm cao TOEIC, bạn cần thực hiện những bước sau khi làm đề và làm bài:\n" +
                " - Quan sát hình để chuẩn bị tâm lý về những từ vựng có thể xuất hiện\n" +
                " - Trả lời 2 câu hỏi: What is(are) he(she, they) doing? Where is(are) he(she, they) doing that?\n" +
                " - LUÔN CÓ 1 PHỎNG ĐOÁN TRONG ĐẦU BẰNG TIẾNG ANH. Khi đã có phỏng đoán trong đầu, bạn sẽ dễ đàng đối chiếu với câu được đọc trong đoạn băng\n" +
                " - Sử dụng kỹ thuật đầu bút chì để làm bài\n" +
                " - Chọn đáp án ngay khi băng đọc xong\n" +
                " - Không nghe được thì chọn đại đừng mất thời gian tiếc núi\n" +
                " - Tận dụng thời gian giữa 2 câu để phân tích hình kế tiếp\n");
        layout.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setTextSize(20);
        textView3.setTextColor(Color.parseColor("#0000EE"));
        textView3.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView3.setText("Kỹ thuật đầu bút chì");
        layout.addView(textView3);

        TextView textView4 = new TextView(this);
        textView4.setTextSize(20);
        textView4.setTextColor(Color.parseColor("#000033"));
        textView4.setText(" Kỹ năng làm bài thi là rất quan trọng trong mọi bài Thi. TOEIC cũng không ngoại lệ, kỹ thuật làm bài tốt sẽ giúp bạn tăng 50 – 70 điểm trong bài thi này. Với phần 1 – 2, kỹ thuật đầu bút chì rất hữu ích:\n" +
                " - Đặt đầu bút chì lên câu A trong tờ đáp án\n" +
                " - Nghe câu A: Nếu câu A có vẻ đúng thì vẫn giữ đầu bút chì ở Câu A\n" +
                " - Nghe câu B: Nếu câu B đúng hơn thì duy chuyển bút chì sang câu B, nếu không thì vẫn giữ đầu bút chì ở câu A\n" +
                " - Làm tương tự như vậy với câu B và C\n" +
                " - Chọn đáp án hợp lý nhất trong A, B, C\n\n" +
                "  Kỹ thuật này loại bỏ các nguyên nhân gây mất điểm lãng xẹt khi nghe phần 1 & 2:\n" +
                " - Không đánh giá được câu nào hợp lý nhất sau khi băng đọc xong\n" +
                " - Biết câu trả lời đúng mà không nhớ nó nằm vị trí nào trong A,B, C, D\n" +
                " - Dính bẫy\n");
        layout.addView(textView4);

        TextView textView5 = new TextView(this);
        textView5.setTextSize(20);
        textView5.setTextColor(Color.parseColor("#0000EE"));
        textView5.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView5.setText("Bẫy");
        layout.addView(textView5);


        TextView textView6 = new TextView(this);
        textView6.setTextSize(20);
        textView6.setTextColor(Color.parseColor("#000033"));
        textView6.setText("  Bẫy hay còn gọi là những câu trả lời gây nhiễu. Để tránh bẫy cần chú ý:\n" +
                " - Những từ đọc tương tự nhưng khác nghĩa: He is walking – He is working\n" +
                " - Cấu trúc câu trả lời ở dạng: S + be + Ving + 0 Những câu trả lời gây nhiễu thường đúng 2/3 yếu tố, có thể sai S – chủ từ, hoặc sai V – động từ, hoặc sai 0b. Thường gặp nhất là đúng động từ (V) nhưng sai đối tượng nhận hành động (Ob)\n" +
                " - Những câu trả lời chỉ đúng 1 phần\n" +
                " - Những từ liên quan nhưng không đúng với bức hình đã cho: Hình – He is climbing the mountain; câu trả lời nhiễu – He is climbing the ladder\n");
        layout.addView(textView6);

        TextView textView7 = new TextView(this);
        textView7.setTextSize(20);
        textView7.setTextColor(Color.parseColor("#0000EE"));
        textView7.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView7.setText("Ôn nghe phần 1 hiệu quả");
        layout.addView(textView7);

        TextView textView8 = new TextView(this);
        textView8.setTextSize(20);
        textView8.setTextColor(Color.parseColor("#000033"));
        textView8.setText("  Để ôn phần này hiệu quả, không chỉ ngồi nghe đề không. Bạn cần sử dụng 1 số cách như sau:\n" +
                " - Tự diễn tả mọi hành động của mình trong ngày ở dạng hiện tại tiếp diễn\n" +
                " - Tìm hình ngẫu nhiên trên internet hoặc các đề thi TOEIC. Nhìn hình và dùng ít nhất 4 câu để miêu tả hình đó. Đọc lớn thành tiếng\n" +
                " - Xem những bộ phim hài tính huống và miêu tả lại những gì đang diễn ra trong 1 cảnh phim\n\n");
        layout.addView(textView8);

        scrollView.addView(layout);
    }

    public void showTipPart2(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView1 = new TextView(this);
        textView1.setTextSize(22);
        textView1.setTextColor(Color.parseColor("#0000EE"));
        textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView1.setText("Phần 2 - Hỏi đáp");
        layout.addView(textView1);

        ImageView iv1 = new ImageView(this);
        iv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        iv1.setImageResource(R.drawable.part2toeic);
        layout.addView(iv1);

        TextView textView2 = new TextView(this);
        textView2.setTextSize(20);
        textView2.setTextColor(Color.parseColor("#000033"));
        textView2.setText("  Điểm khó cũng như điểm dễ của phần nghe này là các đoạn hỏi đáp diễn ra tự nhiên, đó là những tình huống trong tiếng Anh thực sự. Cho nên câu trả lời không phải lúc nào cũng rập khuôn như bạn học.\n\n" +
                "  Sẽ không có chuyện câu hỏi Yes/No mà bạn sẽ nghe câu trả lời kiểu mẫu như là Yes, I do/No, I don’t. Cũng không có chuyện câu trả lời sẽ lập lại 1 phần câu hỏi và thêm thông tin vào như bạn từng nghĩ đâu.\n\n" +
                "  Chẳng hạn, Where are you going to have dinner? – I’m going to have dinner in ____, đó là tiếng Anh lớp 2, bạn sẽ không nghe những câu trả ngô nghê như vậy trong bài nghe Toiec phần 2 đâu, nên hãy cứ vững tin đi nhé.\n");
        layout.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setTextSize(20);
        textView3.setTextColor(Color.parseColor("#0000EE"));
        textView3.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView3.setText("Để làm tốt phần 2 bạn cần chú ý:");
        layout.addView(textView3);

        TextView textView4 = new TextView(this);
        textView4.setTextSize(20);
        textView4.setTextColor(Color.parseColor("#000033"));
        textView4.setText(" - Luôn phải xác định được loại của câu được nói ra đầu tiên.\n" +
                " - Nếu là câu hỏi thì phải nghe được là who what, when, where, why, hay how. Trong trường hợp không nghe được đáp án rõ ràng, thì cần loại bỏ những đáp án không cung cấp được thông tin cần thiết.\n" +
                " - Loại bỏ ngay những câu trả lời có yes/no với câu hỏi W/H\n" +
                " - Nếu là câu hỏi Yes/No hoặc câu hỏi đuôi (tag question) thì cần chú ý câu trả lời không đơn giản chỉ là Yes hoặc No\n" +
                " - Trong trường hợp không phải câu hỏi, thì có các câu sau đây: Câu ra lệnh, câu phàn nàn, câu hỏi thăm sức khoẻ, câu miêu tả tình huống\n" +
                " - Ngay khi nghe xong câu đầu phải đoán trước được câu trả lời trong đầu (cần phải luyện tập, tôi sẽ hướng dẫn bên dưới)\n" +
                " - Những câu trả lời chính xác thường cực kì ngắn hoặc cung cấp nhiều thông tin hơn so với yêu cầu\n" +
                " - Tiếp tục sử dụng kỹ thuật đầu bút chì\n" +
                " - Không nghe được thì chọn đại, đừng phí thời gian nghĩ lại câu đó. Chuẩn bị tinh thần cho câu tiếp theo\n");
        layout.addView(textView4);

        TextView textView5 = new TextView(this);
        textView5.setTextSize(20);
        textView5.setTextColor(Color.parseColor("#0000EE"));
        textView5.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView5.setText("Bẫy:");
        layout.addView(textView5);

        TextView textView6 = new TextView(this);
        textView6.setTextSize(20);
        textView6.setTextColor(Color.parseColor("#000033"));
        textView6.setText("  Những câu trả lời gây nhiễu trong phần này thường có 2 dạng sau:\n" +
                " - Có những từ giống với những từ trong câu hỏi nhưng thật ra không liên quan\n" +
                " - Những từ đọc tương tự, dễ gây nhầm lẫn với câu hỏi\n");
        layout.addView(textView6);

        TextView textView7 = new TextView(this);
        textView7.setTextSize(20);
        textView7.setTextColor(Color.parseColor("#0000EE"));
        textView7.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView7.setText("Ôn phần 2 hiệu quả:");
        layout.addView(textView7);

        TextView textView8 = new TextView(this);
        textView8.setTextSize(20);
        textView8.setTextColor(Color.parseColor("#000033"));
        textView8.setText("  Để ôn phần này hiệu quả phải biết rõ cách đối đáp, những cách trả lời có thể cho câu hỏi. Để luyện tập kỹ năng này, trong quá trình ôn thi:\n" +
                " - Khi nghe câu hỏi, hãy bấm dừng, TỰ MÌNH tạo 3 câu trả lời có thể nhất cho câu hỏi đó, sau đó nghe tiếp tìm câu trả lời đúng. Việc này sẽ tạo cho bạn phản xạ khi nghe câu hỏi sẽ lập tức hình dung ra câu trả lời.\n" +
                " - Sau khi nghe, lật lại transcript để phân tích câu hỏi và câu trả lời. Bạn sẽ rút ra những “mô tuýp” cho câu trả lời đúng và câu trả lời sai để lần nghe tiếp theo, khi nghe câu hỏi là sẽ biết câu trả lời đúng ở dạng nào, câu trả lời sai bẫy ra sao.\n" +
                " - Nghe các đoạn hội thoại thực sự. Lên youtube, tìm kiếm từ khoá Interview with _____. Điều vào chỗ trống nhân vật bạn yêu thích (nói tiếng Anh nhé): Bill Gates, Larry Page, Steve Job, Ronaldo, Warrant Buffet, Taylor Swift, tuỳ bạn chọn nhé.\n\n" +
                "  Tiếp theo là 2 phần quan trọng nhất trong bài nghe TOEIC: Phần 3 và phần 4. Hai phần này có đến 60 câu hỏi và chiếm đa số điểm trong bài thi TOEIC. Các vấn đề mọi người thường gặp trong phần này bao gồm:\n" +
                " - Không kịp đọc câu hỏi và câu trả lời\n" +
                " - Không nhớ được nội dung trả lời\n" +
                " - Không nghe kịp\n" +
                "  Tôi sẽ hướng dẫn các bạn làm thế nào được vượt qua và tập luyện để vượt qua các vấn đề trên trong phần 3 và 4 để đạt được kết quả như ý nhất.");
        layout.addView(textView8);

        scrollView.addView(layout);
    }

    public void showTipPart3(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView1 = new TextView(this);
        textView1.setTextSize(22);
        textView1.setTextColor(Color.parseColor("#0000EE"));
        textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView1.setText("Phần 3 - Đoạn hội thoại ngắn");
        layout.addView(textView1);

        ImageView iv1 = new ImageView(this);
        iv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        iv1.setImageResource(R.drawable.part3toeic);
        layout.addView(iv1);

        TextView textView2 = new TextView(this);
        textView2.setTextSize(20);
        textView2.setTextColor(Color.parseColor("#000033"));
        textView2.setTypeface(textView1.getTypeface(), Typeface.BOLD);
        textView2.setText("  Khi nghe phần 3, cần phải chú ý:");
        layout.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setTextSize(20);
        textView3.setTextColor(Color.parseColor("#000033"));
        textView3.setText(" - Đọc kỹ và hiểu câu hỏi\n" +
                " - Mối quan hệ giữa người nói và người nghe\n" +
                " - Họ đang ở đâu? – Nói về chủ đề gì? – vấn đề của họ là gì?\n" +
                " - Những cái tên được nhắc tới trong bài là ai?\n" +
                " - Những thông tin cụ thể: nơi chốn cụ thể, thời gian cụ thể, số lượng cụ thể\n" +
                " - Ghi nhớ rằng thứ tự của câu hỏi theo tiến trình của bài nghe – câu trả lời cho câu đầu thường nằm ở đoạn đầu, câu 2 – 3 thường nằm ở đoạn giữa và cuối.\n" +
                "  Biết vậy để tập trung nghe đúng chỗ và khắc phục được phần nào việc không nhớ được nội dung trả lời.\n" +
                " - Tận dụng tối đa thời gian trống và Phương Pháp T636 để chuẩn bị đọc câu hỏi và câu trả lời tiếp theo\n" +
                " - Câu nào không nghe kịp, chọn đại và bỏ qua. Tập trung cho đoạn hội thoại kế tiếp\n");
        layout.addView(textView3);

        TextView textView4 = new TextView(this);
        textView4.setTextSize(20);
        textView4.setTextColor(Color.parseColor("#0000EE"));
        textView4.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView4.setText("Bẫy:");
        layout.addView(textView4);

        TextView textView5 = new TextView(this);
        textView5.setTextSize(20);
        textView5.setTextColor(Color.parseColor("#000033"));
        textView5.setText("  Bẫy của phần này thì không có nhiều, đơn giản là nó đã quá thử thách rồi. Tuy nhiên cũng cần chú ý những thông tin gây nhiễu sau:\n" +
                " - Câu trả lời sai có sẵn có 1 số từ giống hệt trong đoạn hội thoại\n" +
                " - Câu trả lời sai có những từ phủ định làm câu trở nên sai (not, hardly, don’t, won’t shouldn’t)\n" +
                " - Câu trả lời sai dùng sai những trạng từ chỉ mức độ thường xuyên ( always, never, sometimes, occasionally, etc)\n");
        layout.addView(textView5);

        TextView textView6 = new TextView(this);
        textView6.setTextSize(20);
        textView6.setTextColor(Color.parseColor("#0000EE"));
        textView6.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView6.setText("Ôn phần 3 TOEIC hiệu quả:");
        layout.addView(textView6);

        TextView textView7 = new TextView(this);
        textView7.setTextSize(20);
        textView7.setTextColor(Color.parseColor("#000033"));
        textView7.setText("  Với phần này, cách ôn tập hiệu quả nhất là nâng cao trình độ tiếng Anh nói chung của bạn lên và tập luyện thật thành thục kỹ năng làm bài, cụ thể là:\n" +
                " - Chăm chỉ luyện nghe các nội dung dài, nhanh, nhiều thông tin. Lý tưởng là các bản tin của CNN hoặc BBC về chủ đề kinh tế, xã hội.\n" +
                " - Nghe các chương trình đối thoại kinh doanh (business talk show) bằng tiếng Anh, các chương trình thực tế về kinh doanh như tôi đã giới thiệu ở bài 5 tuyệt chiêu nghe tiếng Anh thần thánh\n" +
                " - Khai thác tối đa, tận dụng cho hết các bài luyện tập nghe. Sau khi nghe xong. Mở transcript ra NGHE LẠI, ĐỌC LẠI, NÓI TO LẠI đoạn hội thoại.\n" +
                " - Gạch dưới từ mới trong câu hỏi, câu trả lời, transcript, ghi chép lại và học mỗi ngày 5- 10 từ.\n");
        layout.addView(textView7);

        scrollView.addView(layout);
    }

    public void showTipPart4(){
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView1 = new TextView(this);
        textView1.setTextSize(22);
        textView1.setTextColor(Color.parseColor("#0000EE"));
        textView1.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView1.setText("Phần 4 - Đoạn thông tin ngắn");
        layout.addView(textView1);

        ImageView iv1 = new ImageView(this);
        iv1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        iv1.setImageResource(R.drawable.part4toiec);
        layout.addView(iv1);

        TextView textView2 = new TextView(this);
        textView2.setTextSize(20);
        textView2.setTextColor(Color.parseColor("#000033"));
        textView2.setText("  Phần này được cho là phần khó nuốc nhất trong bài nghe Toiec, tuy độ khó tương đương phần 3 nhưng do chỉ có 1 giọng đọc và là phần cuối cùng nên khi làm đến phần này các bạn thường cảm thấy mệt mỏi và buồn ngủ.\n\n" +
                "  Cố lên nào! Khi tất cả mọi người đều mệt mỏi thì chính là lúc cho bạn bức phá. Tiếp tục chiến đấu đến câu 100. Trong phần này cần chú ý:\n" +
                " - Một lần nữa, đọc hiểu câu hỏi và câu trả lời\n" +
                " - Xác định xem đoạn thông tin thuộc dạng nào: tin tức, quảng cáo, bài nói, hướng dẫn. Đừng lo, thông tin này được cho sẵn khi giới thiệu đoạn thông tin: “Question xx to Question xy refers to the following _____”. Cần chú ý nghe xem đó là gì để chuẩn bị tinh thần và có thể loại trừ 1 số câu trả lời sai.\n" +
                " - Xác định ai là người nói và chú ý kỹ đến các tên được nêu ra trong câu hỏi và câu trả lời trong bài nói\n" +
                " - Chú ý kỹ các thông tin cụ thể như địa danh, số lượng\n" +
                " - Ghi nhớ rằng thứ tự của câu hỏi theo tiến trình của bài nghe – câu trả lời cho câu đầu thường nằm ở đoạn đầu, câu 2 – 3 thường nằm ở đoạn giữa và cuối.\n\n" +
                "  Biết vậy để tập trung nghe đúng chỗ và khắc phục được phần nào việc không nhớ được nội dung trả lời.\n" +
                " - Tận dụng tối đa thời gian trống và Phương Pháp T636 để chuẩn bị đọc câu hỏi và câu trả lời tiếp theo\n" +
                " - Câu nào không nghe kịp, chọn đại và bỏ qua. Tập trung cho đoạn hội thoại kế tiếp\n");
        layout.addView(textView2);

        TextView textView3 = new TextView(this);
        textView3.setTextSize(20);
        textView3.setTextColor(Color.parseColor("#000033"));
        textView3.setTypeface(textView1.getTypeface(), Typeface.BOLD_ITALIC);
        textView3.setText("  Bẫy và cách ôn luyện phần này hiệu quả tương tự phần 3");
        layout.addView(textView3);

        scrollView.addView(layout);
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
