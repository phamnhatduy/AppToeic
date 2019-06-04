package com.example.phamn.learningtoeic.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phamn.learningtoeic.R;

import java.io.File;
import java.io.IOException;

public class UploadActivity extends AppCompatActivity {
    ImageView imgPreview;
    Button btnPick,btnUpload;
    TextView txtFilename;
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Learning Toeic");


        imgPreview=findViewById(R.id.preview);
        btnPick=findViewById(R.id.btn_pick);
        btnUpload=findViewById(R.id.btn_upload);
        txtFilename=findViewById(R.id.txt_filename);

        btnPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseFile();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent emailIntent = new Intent(Intent.ACTION_SEND);
                // set the type to 'email'
                emailIntent .setType("vnd.android.cursor.dir/email");
                String to[] = {"thathaivuong151@gmail.com"};
                emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
                // the attachment
                emailIntent .putExtra(Intent.EXTRA_STREAM, filePath);
                // the mail subject
                emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Share file");
                startActivity(Intent.createChooser(emailIntent , "Send email...."));
                //Toast.makeText(UploadActivity.this, "Thank for your support ! ", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                if(bitmap !=null) {
                    imgPreview.setImageBitmap(bitmap);
                }
                String doc = filePath.getPath();
                Toast.makeText(this, doc+"", Toast.LENGTH_SHORT).show();
                txtFilename.setText(doc);


            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("data/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

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
