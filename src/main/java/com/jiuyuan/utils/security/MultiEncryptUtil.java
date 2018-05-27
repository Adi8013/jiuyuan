package com.jiuyuan.utils.security;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.KeySpec;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/**
 * 加解密统一接口，支持流行的对称和非对称算法
 * 目前可以使用3DES,AES，RSA进行加解密
 */
public class MultiEncryptUtil {
    /**
     * 对称加密方法
     * @param plainText 明文的16进制字节数组
     * @param encrytKey 16进制的密钥数组
     * @param alg 加密算法的枚举
     * @return 加密结果，返回加密后的字节数组
     * @throws Exception
     * */
    public static byte[] encrypt(byte[] plainText, byte[] encrytKey,EncryptAlgorithm alg) throws Exception{
    	Key k = toKey(encrytKey,alg);
    	return encrypt(plainText,k,alg);
    }
    
    /**
     * 非对称加密方法
     * @param plainText 明文的16进制字节数组
     * @param key 通过KeyPair获得的公钥
     * @param alg 加密算法的枚举
     * @return 加密结果，返回加密后的字节数组
     * @throws Exception
     * */
    public static byte[] encrypt(byte[] plainText, Key key,EncryptAlgorithm alg) throws Exception{
        Cipher cipher = Cipher.getInstance(alg.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plainText);
    }
    /**
     * 对称加密解密方法
     * @param ciperText 密文的16进制字节数组
     * @param decrytKey 16进制的密钥数组
     * @param alg 加密算法的枚举
     * @return 解密结果，返回解密后的字节数组
     * @throws Exception
     * */
    public static byte[] decrypt(byte[] ciperText, byte[] decrytKey,EncryptAlgorithm alg) throws Exception{
        Key k = toKey(decrytKey,alg);
        return decrypt(ciperText,k,alg);
    }
    /**
     * 非对称加密解密方法
     * @param ciperText 密文的16进制字节数组
     * @param key 通过keypair得到的非对称加密私钥
     * @param alg 加密算法的枚举
     * @return 解密结果，返回解密后的字节数组
     * @throws Exception
     * */
    public static byte[] decrypt(byte[] ciperText, Key key,EncryptAlgorithm alg) throws Exception{
        Cipher cipher = Cipher.getInstance(alg.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(ciperText);
    }
    
    /**
     * 获取对称加密算法算法的密钥
     * @param alg 加密算法枚举
     * @return 16进制的密钥数组
     * @throws
     * */
    public static byte[] getDefaultKey(EncryptAlgorithm alg) throws Exception{
        KeyGenerator keygen = KeyGenerator.getInstance(alg.getAlgorithm());        
        SecretKey deskey = keygen.generateKey();        
        return deskey.getEncoded();
    }
    /**
     * 获取非对称加密算法的密钥
     * @param alg 加密算法枚举
     * @return 16进制的密钥数组
     * @throws
     * */
    public static KeyPair getDefaultKeyPair(EncryptAlgorithm alg) throws Exception{
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(alg.getAlgorithm());
        //密钥位数
        keyPairGen.initialize(1024);
        //密钥对
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }
    /**
     * 通过key的字节数组和特定的算法得到用于加解密的密钥对象
     * @param key 密钥数组
     * @param alg 加解密算法的枚举 
     * @return KEY 
     * @throws Exception
     */
    private static Key toKey(byte[] key, EncryptAlgorithm alg) throws Exception {
        if(alg.getAlgorithm().indexOf("DES") > -1 ){
            KeySpec desKey = null;
            SecretKeyFactory keyFactory = null;
            if("DES".equals(alg.getAlgorithm())){
                desKey = new DESKeySpec(key);
                keyFactory = SecretKeyFactory.getInstance(alg.getAlgorithm()); 
            }
            else{
                desKey = new DESedeKeySpec(key);
                keyFactory = SecretKeyFactory.getInstance(EncryptAlgorithm.ThreeDES.getAlgorithm());  
            }// 将DESKeySpec对象转换成SecretKey对象  
            SecretKey securekey = keyFactory.generateSecret(desKey);  
            return securekey;
        }
        SecretKeySpec spec = new SecretKeySpec(key,alg.getAlgorithm());
        return spec;
    }
    
    public static String testSymmEncry(String plainText,byte[] key,EncryptAlgorithm alg) throws Exception{
        /*测试对称加密方法的应用场景类*/
        byte[] encResult = encrypt(EncryptStringUtils.getEncByteFromStr(plainText),key,alg);
        String encStr = EncryptStringUtils.byte2hex(encResult);
        return encStr;
    }
    public static String testAsymmEncry(String plainText,Key key,EncryptAlgorithm alg) throws Exception{
        /*测试非对称加密方法的应用场景类*/
//        byte[] encResult = encryt(EncryptStringUtils.getEncByteFromStr(plainText),key,alg);
        byte[] encResult = encrypt(plainText.getBytes(),key,alg);
        String encStr = EncryptStringUtils.byte2hex(encResult);
        return encStr;
    }
    
    
    public static String testSymmDecry(String ciperText, byte[] key,EncryptAlgorithm alg) throws Exception{
        /*测试解密方法的应用场景类*/
        byte[] decResult = decrypt(EncryptStringUtils.getDecByteFromStr(ciperText),key,alg);
        String decStr = new String(decResult);
        return decStr;
    }
    
    public static String testAsymmDecry(String ciperText, Key key,EncryptAlgorithm alg) throws Exception{
        /*测试非对称解密方法的应用场景类*/
        byte[] decResult = decrypt(EncryptStringUtils.getDecByteFromStr(ciperText),key,alg);
        String decStr = new String(decResult);
        return decStr;
    }
    
    private static byte[] loginKey = {84,11,7,-37,-115,-126,89,117,-100,124,-84,75,-52,-84,67,95};
    /**
     * 针对登录成功后的客户端与服务端保持通讯的信息进行对称加密。
     * 先进行AES128位加密，再Base64编码
     * @param plainText 明文内容
     * @return
     * @throws Exception
     */
    public static String loginEncrypt(String plainText){
    	try{
    		byte[] encResult = encrypt(EncryptStringUtils.getEncByteFromStr(plainText),loginKey,EncryptAlgorithm.AES);
        	return new Base64().encodeToString(encResult);
    	}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    /**
     * 与loginEncrypt函数对应匹配，进行解密动作
     * @param plainText 加密后的内容
     * @return
     * @throws Exception
     */
    public static String loginDecrypt(String plainText){
    	try{
    		byte[] decResult = decrypt(new Base64().decode(plainText),loginKey,EncryptAlgorithm.AES);
            return new String(decResult);
    	}catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
    
    private static void testCreateKey(EncryptAlgorithm alg) throws Exception{
    	byte[] b = getDefaultKey(alg);
    	StringBuffer sb = new StringBuffer("{");
    	for(int i=0;i<b.length;i++){
    		if(i!=0){
    			sb.append(",");
    		}
    		sb.append(b[i]);
    	}
    	sb.append("}");
    	System.out.println(sb.toString());
	}
    public static void main(String args[]){
    	
        String plainText = "123456";
        try {
        	//testCreateKey(EncryptAlgorithm.AES);
        	//if(true)return;
            System.out.println("开始使用AES加密....");
            System.out.println("加密内容："+plainText);
            String strAfterEncrypt = loginEncrypt(plainText);
            System.out.println("AES加密之后:"+strAfterEncrypt);
            
            System.out.println("AES解密之前:"+new Base64().decode(strAfterEncrypt));
            //使用AES解密
            System.out.println("AES解密之后:"+loginDecrypt(strAfterEncrypt));
            
            
            System.out.println("开始使用RSA加密....");
            KeyPair kp = getDefaultKeyPair(EncryptAlgorithm.RSA);
            String rsaEncStr = testAsymmEncry(plainText,kp.getPublic(),EncryptAlgorithm.RSAWithPadding);
            System.out.println("RSA加密之后:"+rsaEncStr);
            //使用RSA解密
            String desDecStr = testAsymmDecry(rsaEncStr,kp.getPrivate(),EncryptAlgorithm.RSAWithPadding);
            System.out.println("RSA解密之后:"+desDecStr);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
