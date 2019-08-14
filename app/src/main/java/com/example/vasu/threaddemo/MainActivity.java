package com.example.vasu.threaddemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button click,gallery;
    public static TextView data;
    ImageView profilepic;
    String value;
    Uri uriprofileimage;
    private static final int SELECT_PICTURE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if(extras!=null)
            value = extras.getString("Data");
        else
            value = "Vasu";
        click = (Button)findViewById(R.id.button);
        data=(TextView)findViewById(R.id.respo);
        gallery = (Button)findViewById(R.id.gallery);
        data.setText(value);
        profilepic = (ImageView)(findViewById(R.id.imageview));

        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data!=null && data.getData()!=null)
        {
            uriprofileimage = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getBaseContext().getContentResolver(),uriprofileimage);
                profilepic.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    //Hey There Vasu
    void openImageChooser()
    {   String action;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),SELECT_PICTURE);

    }
}
