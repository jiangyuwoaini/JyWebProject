package com.lblz.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * @author lblz
 * @description 常见的加密工具类
 * @date 2022/4/6 21:55
 */
public class EncryptionUtils {


    public static final String KEY_MD5 = "MD5";

    public static final String KEY_SHA = "SHA";

    class BASE64Encryption {
        /**
         * BASE64解密
         */
        public byte[] decryptBASE64(String key) throws Exception {
            return (new BASE64Decoder()).decodeBuffer(key);
        }

        /**
         * BASE64加密
         */
        public String encryptBASE64(byte[] key) throws Exception {
            return (new BASE64Encoder()).encodeBuffer(key);
        }

    }

    class MD5Encryption {
        public String getResult(String inputStr) {
            System.out.println("=======加密前的数据:" + inputStr);
            BigInteger bigInteger = null;
            try {
                MessageDigest md = MessageDigest.getInstance(KEY_MD5);
                byte[] inputData = inputStr.getBytes();
                md.update(inputData);
                bigInteger = new BigInteger(md.digest());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("MD5加密后:" + bigInteger.toString(16));
            return bigInteger.toString(16);
        }
    }

    class SHAEncryption {
        public String getResult(String inputStr) {
            BigInteger sha = null;
            System.out.println("=======加密前的数据:" + inputStr);
            byte[] inputData = inputStr.getBytes();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
                messageDigest.update(inputData);
                sha = new BigInteger(messageDigest.digest());
                System.out.println("SHA加密后:" + sha.toString(32));
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sha.toString(32);
        }
    }

    class HmacSHA256Encryption {
        /**
         * HMAC算法加密
         * @param message 待加密信息
         * @param key 密钥
         * @return
         */
        public  String HmacSHA256(byte[] message, byte[] key){
            long begin = System.currentTimeMillis();
            try {
                Mac hmacSha256Mac = Mac.getInstance("HMACSha256");
                SecretKeySpec secretKey = new SecretKeySpec(key, "HMACSha256");
                hmacSha256Mac.init(secretKey);
                byte[] result = hmacSha256Mac.doFinal(message);
                long end = System.currentTimeMillis();
                return Base64.encodeBase64String(result);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
    }
}
