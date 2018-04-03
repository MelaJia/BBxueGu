package cn.edu.gdmec.android.bbxuegu.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by student on 17/12/25.
 */

public class Md5Utils {
    //md5
    public static String md5(String text){
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            //text.getBytes()原本对应的字节
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : result){
                int number = b & 0xff;
                //number转换成字符
                String hex = Integer.toHexString(number);
                if (hex.length() == 1){
                    sb.append("0"+hex);

                }else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }


    }
}
