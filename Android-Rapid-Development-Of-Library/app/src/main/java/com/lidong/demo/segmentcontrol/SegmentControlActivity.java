package com.lidong.demo.segmentcontrol;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lidong.android_ibrary.segmentcontrol.SegmentControl;
import com.lidong.demo.R;

public class SegmentControlActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segment_control);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final SegmentControl show = (SegmentControl) findViewById(R.id.segment_control);
        show.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                switch (index){
                    case 0:
                        Snackbar.make(show, " 好友", Snackbar.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Snackbar.make(show, " 同学", Snackbar.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Snackbar.make(show, " 同事", Snackbar.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Snackbar.make(show, " 老师", Snackbar.LENGTH_SHORT).show();
                        break;
                            }
            }
        });
    }

}
