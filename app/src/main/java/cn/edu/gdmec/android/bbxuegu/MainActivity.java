package cn.edu.gdmec.android.bbxuegu;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;

import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.bbxuegu.utils.AnalysisUtils;


public class MainActivity extends FragmentActivity implements View.OnClickListener{

    //中间内容栏
    private RelativeLayout mBodyLayout;
    //底部按钮栏
    private LinearLayout mBottomLayout;
    //底部按钮
    private View mCourseBtn;
    private View mExercisesBtn;
    private View mMyInfoBtn;


    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;
    private TextView tv_course;
    private TextView tv_exercises;
    private TextView tv_myInfo;
    private ImageView iv_course;
    private ImageView iv_exercise;
    private ImageView iv_myInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
        initBottomBar();
        setMain();



    }
    private void setMain(){
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new CourseFragment()).commit();
        setSelectedStatus(0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            boolean isLogin=data.getBooleanExtra("isLogin",false);
            if (isLogin){
                setSelectedStatus(0);
            }else {
                setSelectedStatus(2);
            }
        }
    }

    /**
     * 获取界面上的UI控件
     * */
    private void init(){
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        //隐藏返回按钮
        tv_back.setVisibility(View.GONE);



    }
    /**
     * 获取底部导航栏上的控件
     * */
    private void initBottomBar(){
        mBottomLayout = (LinearLayout) findViewById(R.id.main_bottom_bar);
        mBodyLayout=(RelativeLayout) findViewById(R.id.main_body);

        mCourseBtn = findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn = findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);

        tv_course = (TextView) findViewById(R.id.bottom_bar_text_course);
        tv_exercises = (TextView) findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo = (TextView) findViewById(R.id.bottom_bar_text_myinfo);

        iv_course = (ImageView) findViewById(R.id.bottom_bar_image_course);
        iv_exercise = (ImageView) findViewById(R.id.bottom_bar_image_exercises);
        iv_myInfo = (ImageView) findViewById(R.id.bottom_bar_image_myinfo);

        mCourseBtn.setOnClickListener(this);
        mExercisesBtn.setOnClickListener(this);
        mMyInfoBtn.setOnClickListener(this);



    }

    /*
     * 控件的点击事件
      */
    @Override
    public void onClick(View v) {
        //课程的点击事件
        switch (v.getId()){
            case R.id.bottom_bar_course_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new CourseFragment()).commit();
                setSelectedStatus(0);

                break;
            //习题的点击事件
            case R.id.bottom_bar_exercises_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new ExerciseFragment()).commit();
                setSelectedStatus(1);

                break;
            case R.id.bottom_bar_myinfo_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new MyinfoFragment()).commit();
                setSelectedStatus(2);
                break;
            default:
                break;
        }

    }

    //记录第一次点击时的时间
    protected long exitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis() - exitTime)>2000){
                Toast.makeText(MainActivity.this,"再按一次退出博学谷",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                MainActivity.this.finish();
                if (AnalysisUtils.readLoginStatus(this)){
                    //如果退出此应用时事登录状态，则需要清除登录状态，同时清除登录时的登录名
                    AnalysisUtils.clearLoginStatus(this);
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new MyinfoFragment()).commit();
                    setSelectedStatus(2);

                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    /**
     * 设置底部按钮选中状态
     * */
    public void setSelectedStatus(int index){
        switch (index){
            case 0:
                //mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097F7"));
                iv_exercise.setImageResource(R.drawable.main_exercises_icon);
                tv_exercises.setTextColor(Color.parseColor("#666666"));
                iv_myInfo.setImageResource(R.drawable.main_my_icon);
                tv_myInfo.setTextColor(Color.parseColor("#666666"));
                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷课程");
                break;
            case 1:
                //mExercisesBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon);
                tv_course.setTextColor(Color.parseColor("#666666"));
                iv_exercise.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercises.setTextColor(Color.parseColor("#0097F7"));
                iv_myInfo.setImageResource(R.drawable.main_my_icon);
                tv_myInfo.setTextColor(Color.parseColor("#666666"));

                rl_title_bar.setVisibility(View.VISIBLE);
                tv_main_title.setText("博学谷习题");
                break;
            case 2:
                //mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
                iv_course.setImageResource(R.drawable.main_course_icon);
                tv_course.setTextColor(Color.parseColor("#666666"));
                iv_exercise.setImageResource(R.drawable.main_exercises_icon);
                tv_exercises.setTextColor(Color.parseColor("#666666"));
                rl_title_bar.setVisibility(View.GONE);
        }
    }

}



