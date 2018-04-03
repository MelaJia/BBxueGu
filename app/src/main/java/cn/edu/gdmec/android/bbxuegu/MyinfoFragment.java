package cn.edu.gdmec.android.bbxuegu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.bbxuegu.utils.AnalysisUtils;


public class MyinfoFragment extends Fragment implements View.OnClickListener{
    private Context mContext;
    private View mCurrentView;
    private LinearLayout ll_head;
    private ImageView iv_head_icon;
    private RelativeLayout rl_course_history;
    private RelativeLayout rl_setting;
    private TextView tv_user_name;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView(inflater.inflate(R.layout.fragment_mynfo, null));
        return inflater.inflate(R.layout.fragment_mynfo, null);
    }

    private void initView(View inflate) {


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ll_head = (LinearLayout) view.findViewById(R.id.ll_head);
        iv_head_icon = view.findViewById(R.id.iv_head_icon);

        rl_course_history = (RelativeLayout) view.findViewById(R.id.rl_course_history);
        rl_setting = (RelativeLayout) view.findViewById(R.id.rl_setting);
        tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);

        ll_head.setOnClickListener(this);
        rl_course_history.setOnClickListener(this);
        rl_setting.setOnClickListener(this);

        if (AnalysisUtils.readLoginStatus(getActivity())){
            tv_user_name.setText(AnalysisUtils.readLoginUserName(getActivity()));

        }else {
            tv_user_name.setText("点击登陆");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_head:
                if (AnalysisUtils.readLoginStatus(getActivity())){

                }else {
                    Intent intent=new Intent(getActivity(),LoginActivity.class);
                    getActivity().startActivityForResult(intent,1);
                }
                break;
            case R.id.rl_course_history:
                if (AnalysisUtils.readLoginStatus(getActivity())){

                }else {
                    Toast.makeText(getActivity(),"未登录",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.rl_setting:
                if (AnalysisUtils.readLoginStatus(getActivity())){
                    Intent intent=new Intent(getActivity(),SettingActivity.class);
                    getActivity().startActivityForResult(intent,1);

                }else {
                    Toast.makeText(getActivity(),"未登录",Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}
