package com.xx.MD;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5���ܡ�����
 * @author zhangxp
 *
 */
public class MDEncrypt {
	public static final String src="zhang xiao ping hao shuai";
	
	public static String jdkMDEncode() throws NoSuchAlgorithmException
	{
		MessageDigest md=MessageDigest.getInstance("MD5");	
		byte[] md5Bytes=md.digest(src.getBytes());
		return MDEncrypt.byteToHex(md5Bytes);
//		System.out.println("encode��"+MDEncrypt.byteToHex(md5Bytes));
	}
	/**
	 * ����16������
	 * @param inbuf
	 * @return
	 */
	public static String byteToHex(byte[] inbuf)
	{
		int i;
		String bytestr;
		StringBuffer strb=new StringBuffer();
		for(i=0;i<inbuf.length;i++)
		{
			bytestr=Integer.toHexString(inbuf[i]&0x00ff);
			if(bytestr.length()!=2)
			{
				strb.append('0').append(bytestr);
			}
			else {
				strb.append(bytestr);
			}
					
		}
		return new String(strb);
	}
	
	
	/**
	 * md5���ܡ����ܲ���
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String encode=jdkMDEncode();
		System.out.println("encode��"+encode);
	}

}
