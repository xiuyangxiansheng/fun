package com.springboot.fun.util.sign;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.Base64;
import com.qq.weixin.mp.aes.PKCS7Encoder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import weixin.popular.bean.wxa.WxaDUserInfo;
import weixin.popular.util.JsonUtil;
import weixin.popular.util.XMLConverUtil;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Arrays;

public class AESUtil {


	public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte)
			throws InvalidAlgorithmParameterException {
		try {
			Security.addProvider(new BouncyCastleProvider());
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(128);


			Key key = new SecretKeySpec(keyByte, "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
			params.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, key,params);// 初始化

			byte[] result=cipher.doFinal(content);
			//System.out.println(new String(result));
			return  result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    public static JSONObject getUserInfo(String sessionKey, String encryptedData, String iv) throws Exception {
        // 被加密的数据
        byte[] dataByte = Base64.decodeFast(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decodeFast(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decodeFast(iv);

        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");

                return JSONObject.parseObject(result);
            }
        }catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static WxaDUserInfo decryptUserInfo(String session_key, String encryptedData, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            Key sKeySpec = new SecretKeySpec(org.apache.commons.codec.binary.Base64.decodeBase64(session_key), "AES");
            cipher.init(2, sKeySpec, new IvParameterSpec(org.apache.commons.codec.binary.Base64.decodeBase64(iv)));
            byte[] resultByte = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(encryptedData));
            String data = new String(PKCS7Encoder.decode(resultByte),"utf-8");
            return (WxaDUserInfo)JsonUtil.parseObject(data, WxaDUserInfo.class);
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }
    public static StepInfo decryptStepInfo(String session_key, String encryptedData, String iv) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            Key sKeySpec = new SecretKeySpec(org.apache.commons.codec.binary.Base64.decodeBase64(session_key), "AES");
            cipher.init(2, sKeySpec, new IvParameterSpec(org.apache.commons.codec.binary.Base64.decodeBase64(iv)));
            byte[] resultByte = cipher.doFinal(org.apache.commons.codec.binary.Base64.decodeBase64(encryptedData));
            String data = new String(PKCS7Encoder.decode(resultByte),"utf-8");
            return (StepInfo)JsonUtil.parseObject(data, StepInfo.class);
        } catch (Exception var7) {
            var7.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {

        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<!DOCTYPE xdsec [" +
                "    <!ELEMENT methodname ANY>" +
                "    <!ENTITY xxe SYSTEM \"file:///c:/windows/win.ini\">" +
                "]>" +
                "<methodcall>" +
                "<methodname>&xxe;</methodname>" +
                "</methodcall>";
        //System.out.println(getMapFromXML(xml));
        System.out.println(XMLConverUtil.convertToMap(xml));
    }
}


