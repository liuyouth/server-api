package com.iolll.nicesome.net;

import com.google.gson.Gson;
import com.iolll.nicesome.OkHttpUtil;
import io.reactivex.functions.Consumer;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class Madin {

    /**
     * Java中的main()方法详解
     */


    public static void main(String args[]) {
        final String APPSECRIT = "8098615395fae44857ac080fc7c9b59e";
        System.out.println("Hello World!");
        String url = "http://api.auvgo.com/common/city";
        Map<String, Object> map=new HashMap<String, Object>();
//        {"type":"hotel","loginuserid":"7519"}
//        map.put("type", "hotel");
//        map.put("loginuserid", "7519");
        map.put("cid", "61");
        map.put("loginuserid", "16495");

        Gson gson1=new Gson();
        String json1 = gson1.toJson(map);

        String sign = getMD5(getMD5(APPSECRIT).toUpperCase() + json1).toUpperCase();//签名
        Map<String, Object> maps=new HashMap<String, Object>();

        maps.put("data", json1);
        maps.put("appkey", "xinglvand");
        maps.put("sign", "0A451FD864AAAD1CE6B6B0F63882445D");
        Gson gson=new Gson();
        String json = gson.toJson(maps);
        System.out.println("输出的结果是：" + maps.toString());
        Map map1 = new HashMap();
//        System.out.println(OkHttpUtil.INSTANCE.postOrPutOrDelete(url,maps,map1,OkHttpUtil.OkHttpMethod.POST));
        DataManager dataManager = new DataManager();
        dataManager.get(json1,sign,"xinglvand").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    public static String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuilder strBuf = new StringBuilder();
            for (byte anEncryption : encryption) {
                String str = Integer.toHexString(0xff & anEncryption);
                if (str.length() == 1) {
                    strBuf.append("0").append(str);
                } else {
                    strBuf.append(str);
                }
            }
            return strBuf.toString();


//            return new BigInteger(1, encryption).toString(16);

        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
