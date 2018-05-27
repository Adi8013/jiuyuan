package com.jiuyuan.utils.security;

/**
 * 加密算法的枚举类
 */
public enum EncryptAlgorithm {
	/**AES加密算法*/
	AES("AES"),
	/**DES加密算法*/
	DES("DES"),
	/**3DES加密算法，对应DESede*/
	ThreeDES("DESede"),
	ThreeDESWithPadding("DESede/ECB/PKCS5Padding"),
	/**RSA加密算法*/
	RSA("RSA"),
	RSAWithPadding("RSA/ECB/PKCS1Padding");

	private final String alg_code;
	private EncryptAlgorithm(String code){
    	alg_code = code;
	}
    
	public String getAlgorithm(){
    	return alg_code;
	}

	public static void main(String args[]){
    	for (Object obj : java.security.Security.getAlgorithms("Cipher")) {
    		System.out.println(obj);
        }
    	//用上面的代码打印系统算法时显示包含了上面的算法。 
	}
}
