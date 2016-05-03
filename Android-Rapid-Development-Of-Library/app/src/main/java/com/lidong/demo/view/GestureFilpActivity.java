package com.lidong.demo.view;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.lidong.demo.AppComponent;
import com.lidong.demo.BaseActivity;
import com.lidong.demo.R;
import com.lidong.demo.view.adapter.ChineseMedicineReportAdapter;
import com.lidong.demo.view.model.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
*@类名 : GestureFilpActivity
*@描述 : 
*@时间 : 2016/5/3  16:11
*@作者: 李东
*@邮箱  : lidong@chni.com.cn
*@company: chni
*/
public class GestureFilpActivity extends BaseActivity implements GestureDetector.OnGestureListener{

    @Bind(R.id.viewFlipper)
    ViewFlipper mViewFlipper;
    //1.定义手势检测器对象
    GestureDetector mGestureDetector;
    //2.定义一个动画数组，用于为ViewFilpper指定切换动画效果。
    Animation[]  animations = new  Animation[4];
    //3.定义手势两点之间的最小距离
    final int FLIP_DISTANCE = 50 ;

    List<Question> mQuestion =  new ArrayList<>();
    ChineseMedicineReportAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_filp);
        ButterKnife.bind(this);
        setActivityTitle("答题器的实现");
        //1.构建手势检测器
        mGestureDetector =  new GestureDetector(this,this);
        //2准备数据
        List<Question> questions = initData();
        mQuestion.addAll(questions);
        //3.为ViewFilpper添加子控件。
        for (int i = 0;i<mQuestion.size();i++){
            Question question = mQuestion.get(i);
            mViewFlipper.addView(addQuestionView(question));
        }
        //4.初始化Animation数组
        animations[0] = AnimationUtils.loadAnimation(this,R.anim.left_in);
        animations[1] = AnimationUtils.loadAnimation(this,R.anim.left_out);
        animations[2] = AnimationUtils.loadAnimation(this,R.anim.right_in);
        animations[3] = AnimationUtils.loadAnimation(this,R.anim.right_out);

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    private View  addQuestionView(Question question){
        View view = View.inflate(this, R.layout.activity_chnihealthreport, null);
        TextView tes = (TextView) view.findViewById(R.id.tv_question);
        ListView listview = (ListView) view.findViewById(R.id.lv_question_answer);
        adapter = new ChineseMedicineReportAdapter(this,question);
        listview.setAdapter(adapter);
        tes.setText(question.getQuestion());
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GestureFilpActivity.this,position+"",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }




    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e2.getX() - e1.getX()>FLIP_DISTANCE){
            if (mViewFlipper.getDisplayedChild() == 0) {
                mViewFlipper.stopFlipping();
                Toast.makeText(GestureFilpActivity.this,"第一个题",Toast.LENGTH_SHORT).show();
                return false;
            } else {
                mViewFlipper.setInAnimation(animations[2]);
                mViewFlipper.setOutAnimation(animations[3]);
                mViewFlipper.showPrevious();
                return  true;
            }
        }else if (e1.getX() - e2.getX()>FLIP_DISTANCE){
            if (mViewFlipper.getDisplayedChild() == mQuestion.size() - 1) {
                Toast.makeText(GestureFilpActivity.this,"最后一个题",Toast.LENGTH_SHORT).show();
                mViewFlipper.stopFlipping();
                return false;
            }else {
                mViewFlipper.setInAnimation(animations[0]);
                mViewFlipper.setOutAnimation(animations[1]);
                mViewFlipper.showNext();
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将Activity上的触发的事件交个GestureDetector处理
        return this.mGestureDetector.onTouchEvent(event);
    }

    private List<Question> initData(){
       List<Question> questions = new ArrayList<>();
       Question q1 = new Question();
       q1.setQuestion("1、\"红娘\"由来是出自下列哪部古典名剧:");
       List<Question.Answer> mA = new ArrayList<>();
       Question.Answer  a1 = new Question.Answer();
       a1.setAnswerMessage("A《琵琶记》");
       Question.Answer  a2 = new Question.Answer();
       a2.setAnswerMessage("B《西厢记》");
       Question.Answer  a3 = new Question.Answer();
       a3.setAnswerMessage("C《长生殿》");
       Question.Answer  a4 = new Question.Answer();
       a4.setAnswerMessage("D《桃花扇》");
       mA.add(a1);
       mA.add(a2);
       mA.add(a3);
       mA.add(a4);
       q1.setAnswer(mA);
       questions.add(q1);

       Question q2 = new Question();
       q2.setQuestion("2.我国第一部有声影片是：");
       List<Question.Answer> mB = new ArrayList<>();
       Question.Answer  b1 = new Question.Answer();
       b1.setAnswerMessage("A《歌女红牡丹》");
       Question.Answer  b2 = new Question.Answer();
       b2.setAnswerMessage("B《定军山》");
       Question.Answer  b3 = new Question.Answer();
       b3.setAnswerMessage("C《林则徐》");
       Question.Answer  b4 = new Question.Answer();
       b4.setAnswerMessage("D《玉人何处》");
       mB.add(b1);
       mB.add(b2);
       mB.add(b3);
       mB.add(b4);
       q2.setAnswer(mB);
       questions.add(q2);
       Question q3= new Question();
       q3.setQuestion("3.下列哪座山不属于我国四大佛山之一：（ A）");
       List<Question.Answer> mC = new ArrayList<>();
       Question.Answer  c1 = new Question.Answer();
       c1.setAnswerMessage("A《歌女红牡丹》");
       Question.Answer  c2 = new Question.Answer();
       c2.setAnswerMessage("B《定军山》");
       Question.Answer  c3 = new Question.Answer();
       c3.setAnswerMessage("C《林则徐》");
       Question.Answer  c4 = new Question.Answer();
       c4.setAnswerMessage("D《玉人何处》");
       mC.add(c1);
       mC.add(c2);
       mC.add(c3);
       mC.add(c4);
       q3.setAnswer(mC);
       questions.add(q3);
       Question q4 = new Question();
       q4.setQuestion("4.下面哪个是对“惊蛰”这个节气的正确描述？");
       List<Question.Answer> mD = new ArrayList<>();
       Question.Answer  d1 = new Question.Answer();
       d1.setAnswerMessage("A《歌女红牡丹》");
       Question.Answer  d2 = new Question.Answer();
       d2.setAnswerMessage("B《定军山》");
       Question.Answer  d3 = new Question.Answer();
       d3.setAnswerMessage("C《林则徐》");
       Question.Answer  d4 = new Question.Answer();
       d4.setAnswerMessage("D《玉人何处》");
       mD.add(d1);
       mD.add(d2);
       mD.add(d3);
       mD.add(d4);
       q4.setAnswer(mD);
       questions.add(q4);

       return questions;
   }
}
