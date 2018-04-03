package cn.edu.gdmec.android.bbxuegu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.bbxuegu.utils.AnalysisUtils;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    public static SettingActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        instance = this;
        init();
    }

    private void init() {
        TextView tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_main_title.setText("设置");
        TextView tv_back = (TextView) findViewById(R.id.tv_back);
        RelativeLayout rl_title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#3084FF"));

        RelativeLayout rl_modify_psw = (RelativeLayout) findViewById(R.id.rl_modify_psw);
        RelativeLayout rl_security_setting = (RelativeLayout) findViewById(R.id.rl_security_setting);

        RelativeLayout rl_exit_login = (RelativeLayout) findViewById(R.id.rl_exit_login);
        tv_back.setOnClickListener(this);
        rl_exit_login.setOnClickListener(this);
        rl_security_setting.setOnClickListener(this);
        rl_modify_psw.setOnClickListener(this);
//        tv_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                SettingActivity.this.finish();
//            }
//        });
//        rl_modify_psw.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //跳转到修改密码页面
//                Intent intent = new Intent(SettingActivity.this,ModifyPswActivity.class);
//                startActivity(intent);
//
//            }
//        });
//        rl_security_setting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //跳转到设置密保界面
//                Intent intent = new Intent(SettingActivity.this,FindPswActivity.class);
//                intent.putExtra("from","security");
//                startActivity(intent);
//
//
//
//            }
//        });
//        rl_exit_login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(SettingActivity.this,"退出登录成功",Toast.LENGTH_SHORT).show();
//                clearLoginStatus();
//                //把退出登录成功后的状态传递到mainactivity中
//                Intent data = new Intent();
//                data.putExtra("isLogin",false);
//                setResult(RESULT_OK,data);
//                SettingActivity.this.finish();
//
//            }
//        });

    }
    private void clearLoginStatus(){
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_back:
                SettingActivity.this.finish();
                break;
            case R.id.rl_modify_psw:
                Intent intent=new Intent(SettingActivity.this,ModifyPswActivity.class);
                startActivity(intent);

                break;
            case R.id.rl_security_setting:
                Intent intent1=new Intent(SettingActivity.this,FindPswActivity.class);
                intent1.putExtra("from","security");
                startActivity(intent1);

                break;
            case R.id.rl_exit_login:
                Toast.makeText(this, "成功退出登录", Toast.LENGTH_SHORT).show();
                AnalysisUtils.clearLoginStatus(this);
                Intent data=new Intent();
                data.putExtra("isLogin",false);


                setResult(RESULT_OK,data);
                finish();

                break;

        }

    }
}
