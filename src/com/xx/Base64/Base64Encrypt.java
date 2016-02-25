package com.xx.Base64;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author zhangxp
 * base64加密
 */
public class Base64Encrypt {
//	public static final String src="zhang xiao ping hao shuai";
	public static final String src="张孝平好帅";
	
	/**
	 * 用base64加密
	 * @return
	 */
	public static String jdkBase64Encrypt()
	{
		BASE64Encoder encoder=new BASE64Encoder();
		String encode=encoder.encode(src.getBytes());
		return encode;
	}
	/**
	 * base64解密
	 * @param encode
	 * @return
	 * @throws IOException
	 */
	public static String jdkBase64Decode(String encode) throws IOException
	{
		BASE64Decoder decode=new BASE64Decoder();
		return new String(decode.decodeBuffer(encode));
		
	}
	/**
	 * base64加密、解密测试
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String encode=jdkBase64Encrypt();
		System.out.println("encode："+encode);
		String decode=jdkBase64Decode(encode);
		System.out.println("decode："+decode);
	}

}
