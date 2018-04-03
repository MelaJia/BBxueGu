package cn.edu.gdmec.android.bbxuegu;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.bbxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.bbxuegu.utils.Md5Utils;

public class ModifyPswActivity extends Activity implements View.OnClickListener {


    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_save;
    private RelativeLayout title_bar;
    private EditText et_original_psw;
    private EditText et_new_psw;
    private EditText et_new_psw_again;
    private Button btn_save;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_psw);

        initView();
        userName= AnalysisUtils.readLoginUserName(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
              submit();
                break;
            case R.id.tv_back:
                finish();
                break;

        }
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        tv_save = (TextView) findViewById(R.id.tv_save);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        et_original_psw = (EditText) findViewById(R.id.et_original_psw);
        et_new_psw = (EditText) findViewById(R.id.et_new_psw);
        et_new_psw_again = (EditText) findViewById(R.id.et_new_psw_again);
        btn_save = (Button) findViewById(R.id.btn_save);
        tv_main_title.setText("修改密码");

        btn_save.setOnClickListener(this);
        tv_back.setOnClickListener(this);
    }

    private void submit() {
        // validate
        String psw = et_original_psw.getText().toString().trim();


        String newPsw = et_new_psw.getText().toString().trim();


        String again = et_new_psw_again.getText().toString().trim();

        if (TextUtils.isEmpty(psw)){
            Toast.makeText(ModifyPswActivity.this,"请输入原始密码",Toast.LENGTH_SHORT).show();
            return;
        }else if (!Md5Utils.md5(psw).equals(readPsw())){
            Toast.makeText(ModifyPswActivity.this,"输入密码与原始密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }else if (Md5Utils.md5(newPsw).equals(readPsw())){
            Toast.makeText(ModifyPswActivity.this,"输入的新密码与原始密码不能一致",Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(newPsw)){
            Toast.makeText(ModifyPswActivity.this,"请输入新密码",Toast.LENGTH_SHORT).show();
            return;
        }else if (TextUtils.isEmpty(again)){
            Toast.makeText(ModifyPswActivity.this,"请再次输入新密码",Toast.LENGTH_SHORT).show();
            return;
        }else if (!newPsw.equals(newPsw)){
            Toast.makeText(ModifyPswActivity.this,"两次输入的新密码不一致",Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(ModifyPswActivity.this,"新密码设置成功",Toast.LENGTH_SHORT).show();
            modifyPsw(newPsw);
            Intent intent = new Intent(ModifyPswActivity.this,LoginActivity.class);
            startActivity(intent);
            SettingActivity.instance.finish();
            ModifyPswActivity.this.finish();
        }

        // TODO validate success, do something


    }
    private void modifyPsw(String newPsw){
        String md5Psw = Md5Utils.md5(newPsw);
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();
    }

    private String readPsw() {
        SharedPreferences sp = getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw = sp.getString(userName, "");
        return spPsw;
    }


}
