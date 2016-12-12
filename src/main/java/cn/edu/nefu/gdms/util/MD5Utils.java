package cn.edu.nefu.gdms.util;

import java.security.MessageDigest;

/**
 * Created by dingyunxiang on 16/12/12.
 */
public class MD5Utils {

    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f' };

    public final static String MD5(String s) {

        try {
            byte[] strTemp = s.getBytes("UTF-8");
            //byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                //右移四位并截掉高四位
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                //直接截掉高四位
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public final static String MD5(String s,String encoding) {

        try {
            byte[] strTemp;
            if(encoding!=null)
                strTemp= s.getBytes(encoding);
            else
                strTemp= s.getBytes();
            //byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                //右移四位并截掉高四位
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                //直接截掉高四位
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

}
