package com.lidong.demo.baidu_map_first;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.lidong.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DisplayBaiduMapActivity extends AppCompatActivity {

    @Bind(R.id.imge_map)
    ImageView mImgeMap;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_baidu_map);
        ButterKnife.bind(this);
        intent = getIntent();
        Bitmap bitmap=intent.getParcelableExtra("bitmap");
        mImgeMap.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
