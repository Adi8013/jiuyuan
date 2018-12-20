package com.jiuyuan.utils.security;

/**
 * 提供加解密的字符串转换需求
 */
public class EncryptStringUtils {

	/**
	 * 将java字节数组转换成16进制字符串
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String tmp = "";
		for (int n = 0; n < b.length; n++) {
			//整数转成十六进制表示
            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
            	hs = hs + "0" + tmp;
            } else {
            	hs = hs + tmp;
            }
		}
		tmp = null;
        return hs.toUpperCase();//转成大写
	}

    /**
     * 将16进制字节数组转java字节数组
     * @param b
     * @return
     */
	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			// 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		b = null;
        return b2;
	}

	private static final char[] digital = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
    
	/**
	 * 将文本字符串转换成十六进制值
	 * @param bin 我们看到的要转换成十六进制的文本字符串
	 * @return
	 */
	public static String bin2hex(String bin) {
		StringBuffer sb = new StringBuffer("");
		byte[] bs = bin.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(digital[bit]);
			bit = bs[i] & 0x0f;
			sb.append(digital[bit]);
		}
		return sb.toString();
	}
	
	/**
	 * 十六进制转换文本字符串
	 * @param hex 十六进制字符串
	 * @return 转换后的文本字符串
	 */
	public static String hex2bin(String hex) {
		String digital = "0123456789ABCDEF";
		char[] hex2char = hex.toCharArray();
		byte[] bytes = new byte[hex.length() / 2];
		int temp;
		for (int i = 0; i < bytes.length; i++) {
			temp = digital.indexOf(hex2char[2 * i]) * 16;
			temp += digital.indexOf(hex2char[2 * i + 1]);
			bytes[i] = (byte) (temp & 0xff);
		}
		return new String(bytes);
	}

	/**首先将文本转换成16进制字符串，然后再转换成字节数组，最后用于进行加密*/
	public static byte[] getEncByteFromStr(String bin){
    	return hex2byte(bin2hex(bin).getBytes());
	}
	/**将16进制字符串转换成字节数组，最后用于进行解密*/
	public static byte[] getDecByteFromStr(String hex){
		return hex2byte(hex.getBytes());
	}
}
