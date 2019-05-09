package com.example.phamn.learningtoeic.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.R;

import java.util.Random;

public class ReviewActivity extends AppCompatActivity {
    ImageView imgSpreaker,imgX;
    TextView txtShowAnswer;
    EditText edtAnswer;
    Button btnAnswer,btnChange;
    SoundManager soundManager;
    Random random;
    int pos;
    int countClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        soundPlay();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Learning Toeic");
        //init
        imgSpreaker = findViewById(R.id.image_speaker);
        imgX=findViewById(R.id.image_x);
        txtShowAnswer=findViewById(R.id.txt_showAnswer);
        edtAnswer = findViewById(R.id.edt_answer);
        btnAnswer=findViewById(R.id.btn_play);
        btnChange=findViewById(R.id.btn_change);
        final Animation animation = AnimationUtils.loadAnimation(this,R.anim.scale_sound);
        final String[] wordAnswer = {"abide","accommodate","address","agreement","arrangement","association",
                "assurance","attend","attract","avoid","cancellation","characteristic","compare","competition","consequence",
                "consider","consume","convince","cover","currently","demonstrate","determine","develop","engage","establish",
                "evaluate","expiration","fad","frequently","gather","hold","imply","inspiration","location","market","obligate",
                "offer","overcrowded","party","persuasion","primarily","productive","promise","protect","provision","register",
                "reputation","require","resolve","risk","satisfaction","select","session","specific","strategy","strong","substitution",
                "variety"};
        final String[] wordTranslate = {"/ə'baid/:(v)tôn trọng,tuân theo","/ə'kɔmədeit/:(v)điều tiết, điều chỉnh","/ə'dres/:(n)địa chỉ",
         "/ə'gri:mənt/:(n)hợp đồng","/ə'reindʤmənt/:(n)sắp xếp","/ə,sousi'eiʃn/:(n)liên kết","/ə'ʃuərəns/:(n)bảo đảm,chắc chắn",
         "/ə'tend/:(v)tham dự","/ə'trækt/:(v)hấp dẫn,thu hút","/ə'vɔid/:(v)tránh","/,kænse'leiʃn/:(n)sự hủy bỏ","/,kæriktə'ristik/:(adj)đặc thù,(n)đặc trưng",
         "/kəm'peə/:(v)so sánh","/,kɔmpi'tiʃn/:(n)tranh giành,thi đấu","/'kɔnsikwəns/:(n)kết quả","/kən'sidə/:(n)cân nhắc,suy xét",
         "/kən'sju:m/:(v)tiêu thụ","/kən'vins/:(v)thuyết phục","/'kʌvə/:(v)che,phủ","/ˈkʌrəntli/:(adv)hiện nay","/'demənstreit/:(v)bày tỏ",
         "/di'tə:min/:(v)quyết định,xác định","/di'veləp/:(v)phát triển","/in'geidʤ/:(n)sự hứa hẹn","/is'tæbliʃ/:(n)thiết lập,thành lập,",
         "/i'væljueit/:(v)đánh giá, ước lượng","/,ekspaiə'reiʃn/:(n)sự hết hạn","/fæd/:(n)sự nhất thời","/'friːkwəntli/:(adv)thường xuyên",
         "/'gæðə/:(v)tập hợp","/hould/:(v)giữ","/im'plai/:(v)ẩn ý","/,inspə'reiʃn/:(n)‹sự/người/vật› truyền cảm hứng","/lou'keiʃn/:(n)vị trí",
         "/'mɑ:kit/:(n)thị trường,chợ","/'ɔbligeit/:(v)bắt buộc","/'ɔfə/:(n,v)đề xuất","/əʊvəˈkraʊdɪd/:(n)chật ních","/'pɑ:ti/:(n)đảng,buổi tiệc",
         "/pə'sweiʤn/:(n)sự thuyết phục","/'praimərili/:(adv)trước hết","/prəˈdʌktɪv/:(adj):sản xuất","/promise/:(n)lời hứa","/protect/:(v)bảo vệ",
         "/provision/:(n)sự dự trữ","/'redʤistə/:(v)đăng ký,(n)danh sách","/,repju:'teiʃn/:(n)danh tiếng","/ri'kwaiə/:(v)yêu cầu","/ri'zɔlv/:(v)giải quyết",
         "/rɪsk/:(n)rủi ro","/,sætis'fækʃn/:(n)sự hài lòng","/si'lekt/:(v)chọn lựa","/'seʃn/:(n)phiên,kỳ","/spi'sifik/:(adj)riêng biệt",
         "/ˈstrætədʒi/:(n)chiến lược","/strɔɳ/:(adj)khoẻ,mạnh","/,sʌbsti'tju:ʃn/:(n)sự thay thế","/və'raiəti/:(n)đa dạng"
        };
        random = new Random();
        pos = random.nextInt(10);
        imgSpreaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(animation);
                imgSpreaker.setImageResource(R.drawable.sound_icon);
                // pos = random.nextInt(58);
                soundManager.playSound(pos);
            }
        });

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // imageView.setEnabled(false);
                pos = random.nextInt(58);
                soundManager.playSound(pos);
                Toast.makeText(ReviewActivity.this, wordAnswer[pos], Toast.LENGTH_SHORT).show();
                edtAnswer.getText().clear();
                imgX.setVisibility(View.INVISIBLE);
                txtShowAnswer.setVisibility(View.INVISIBLE);
                countClick=0;
            }
        });
        btnAnswer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ans = edtAnswer.getText().toString().trim();
                /*
                if(ans.equals(""))
                {
                    Toast.makeText(ReviewActivity.this, "Please insert your answer ", Toast.LENGTH_SHORT).show();
                }
               else {
                    if (ans.equalsIgnoreCase(wordAnswer[pos])) {
                        imageViewX.setVisibility(View.INVISIBLE);
                        Toast.makeText(ReviewActivity.this, "That's correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        imageViewX.setVisibility(View.VISIBLE);
                        Toast.makeText(ReviewActivity.this, "That's wrong.Try Again!", Toast.LENGTH_SHORT).show();
                    }
                }*/
               countClick+=1;
               if(countClick==1)
               {
                   if(ans.equals(""))
                   {
                       Toast.makeText(ReviewActivity.this, "Please insert your answer ", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       if (ans.equalsIgnoreCase(wordAnswer[pos])) {
                           imgX.setVisibility(View.INVISIBLE);
                          // imgX.setImageResource(R.drawable.correct_icon);
                           Toast.makeText(ReviewActivity.this, "That's correct!", Toast.LENGTH_SHORT).show();
                       } else {
                           imgX.setVisibility(View.VISIBLE);
                           Toast.makeText(ReviewActivity.this, "That's wrong.Try Again!", Toast.LENGTH_SHORT).show();
                           edtAnswer.getText().clear();
                       }
                   }
               }
               if(countClick==2)
               {
                   txtShowAnswer.setText(wordTranslate[pos]);
                   txtShowAnswer.setVisibility(View.VISIBLE);
                   if(ans.equals(""))
                   {
                       Toast.makeText(ReviewActivity.this, "Please insert your answer ", Toast.LENGTH_SHORT).show();

                   }
                   else {
                       if (ans.equalsIgnoreCase(wordAnswer[pos])) {
                           imgX.setVisibility(View.INVISIBLE);
                           //imgX.setImageResource(R.drawable.correct_icon);
                           Toast.makeText(ReviewActivity.this, "That's correct!", Toast.LENGTH_SHORT).show();
                       } else {
                           imgX.setVisibility(View.VISIBLE);
                           Toast.makeText(ReviewActivity.this, "That's wrong.Try Again!", Toast.LENGTH_SHORT).show();
                           edtAnswer.getText().clear();
                       }
                   }
               }
               if(countClick>=3)
               {
                   txtShowAnswer.setText(wordAnswer[pos]);
                   txtShowAnswer.setVisibility(View.VISIBLE);
                   if(ans.equals(""))
                   {
                       Toast.makeText(ReviewActivity.this, "Please insert your answer ", Toast.LENGTH_SHORT).show();
                   }
                   else {
                       if (ans.equalsIgnoreCase(wordAnswer[pos])) {
                           imgX.setVisibility(View.INVISIBLE);
                          // imgX.setImageResource(R.drawable.correct_icon);
                           Toast.makeText(ReviewActivity.this, "That's correct!", Toast.LENGTH_SHORT).show();
                       } else {
                           imgX.setVisibility(View.VISIBLE);
                           Toast.makeText(ReviewActivity.this, "That's wrong.Try Again!", Toast.LENGTH_SHORT).show();
                           edtAnswer.getText().clear();
                       }
                   }
               }
            }
        });
    }
    public void soundPlay()
    {
        soundManager = new SoundManager();
        soundManager.initSounds(getBaseContext());
        soundManager.addSound(0, R.raw.abide);
        soundManager.addSound(1,R.raw.accommodate);
        soundManager.addSound(2,R.raw.address);
        soundManager.addSound(3,R.raw.agreement);
        soundManager.addSound(4,R.raw.arrangement);
        soundManager.addSound(5,R.raw.association);
        soundManager.addSound(6,R.raw.assurance);
        soundManager.addSound(7,R.raw.attend);
        soundManager.addSound(8,R.raw.attract);
        soundManager.addSound(9,R.raw.avoid);
        soundManager.addSound(10,R.raw.cancellation);
        soundManager.addSound(11,R.raw.characteristic);
        soundManager.addSound(12,R.raw.compare);
        soundManager.addSound(13,R.raw.competition);
        soundManager.addSound(14,R.raw.consequence);
        soundManager.addSound(15,R.raw.consider);
        soundManager.addSound(16,R.raw.consume);
        soundManager.addSound(17,R.raw.convince);
        soundManager.addSound(18,R.raw.cover);
        soundManager.addSound(19,R.raw.currently);
        soundManager.addSound(20,R.raw.demonstrate);
        soundManager.addSound(21,R.raw.determine);
        soundManager.addSound(22,R.raw.develop);
        soundManager.addSound(23,R.raw.engage);
        soundManager.addSound(24,R.raw.establish);
        soundManager.addSound(25,R.raw.evaluate);
        soundManager.addSound(26,R.raw.expiration);
        soundManager.addSound(27,R.raw.fad);
        soundManager.addSound(28,R.raw.frequently);
        soundManager.addSound(29,R.raw.gather);
        soundManager.addSound(30,R.raw.hold);
        soundManager.addSound(31,R.raw.imply);
        soundManager.addSound(32,R.raw.inspiration);
        soundManager.addSound(33,R.raw.location);
        soundManager.addSound(34,R.raw.market);
        soundManager.addSound(35,R.raw.obligate);
        soundManager.addSound(36,R.raw.offer);
        soundManager.addSound(37,R.raw.overcrowded);
        soundManager.addSound(38,R.raw.party);
        soundManager.addSound(39,R.raw.persuasion);
        soundManager.addSound(40,R.raw.primarily);
        soundManager.addSound(41,R.raw.productive);
        soundManager.addSound(42,R.raw.promise);
        soundManager.addSound(43,R.raw.protect);
        soundManager.addSound(44,R.raw.provision);
        soundManager.addSound(45,R.raw.register);
        soundManager.addSound(46,R.raw.reputation);
        soundManager.addSound(47,R.raw.require);
        soundManager.addSound(48,R.raw.resolve);
        soundManager.addSound(49,R.raw.risk);
        soundManager.addSound(50,R.raw.satisfaction);
        soundManager.addSound(51,R.raw.select);
        soundManager.addSound(52,R.raw.session);
        soundManager.addSound(53,R.raw.specific);
        soundManager.addSound(54,R.raw.strategy);
        soundManager.addSound(55,R.raw.strong);
        soundManager.addSound(56,R.raw.substitution);
        soundManager.addSound(57,R.raw.variety);

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
