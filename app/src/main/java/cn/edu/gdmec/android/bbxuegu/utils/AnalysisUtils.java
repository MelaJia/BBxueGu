package cn.edu.gdmec.android.bbxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by student on 17/12/25.
 */

public class AnalysisUtils {

    //从SharedPreferences中读取登录用户名
    public static String readLoginUserName(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        String userName=sp.getString("loginUserName","");
        return userName;
    }
    //读取登陆状态
    public static boolean readLoginStatus(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        boolean isLogin=sharedPreferences.getBoolean("isLogin",false);
        return isLogin;
    }
    //清除状态
    public static void clearLoginStatus(Context context){
        SharedPreferences sp=context.getSharedPreferences("loginInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isLogin",false);
        editor.putString("loginUserName","");
        editor.commit();
    }

}




