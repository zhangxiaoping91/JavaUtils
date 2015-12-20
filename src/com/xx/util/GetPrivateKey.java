package com.xx.util;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Enumeration;

/**
 * ��ȡ˽Կ��Կ�ļ�
 * @author zhangxp
 *
 */
public class GetPrivateKey {
	private static final String alg="MD5withRSA";
	/**
	 * ��pfx��ʽ��˽Կ�ļ��л�ȡ ��Կ��public key�� ��˽Կ ��private key��
	 * @param strPfx  �ļ�·��
	 * @param strPassword  �ļ�����
	 * @return
	 */
	public static PrivateKey GetPvkformPfx(String strPfx, String strPassword)
	{
		try
		{
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(strPfx);
			char[] nPassword = null;
			if ((strPassword == null) || strPassword.trim().equals(""))
			{
				nPassword = null;
			} else
			{
				nPassword = strPassword.toCharArray();
			}
			ks.load(fis, nPassword);
			fis.close();
			System.out.println("keystore type=" + ks.getType());
			//����key����
			Enumeration enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements())// we are readin just one certificate.
			{
				keyAlias = (String) enumas.nextElement();
				System.out.println("alias=[" + keyAlias + "]");
			}
			System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
			Certificate cert = ks.getCertificate(keyAlias);
			PublicKey pubkey = cert.getPublicKey();
			System.out.println("cert class = " + cert.getClass().getName());
			System.out.println("cert = " + cert);
			System.out.println("public key = " + pubkey);
			System.out.println("private key = " + prikey);
			return prikey;
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	/**
	 * ����˽Կ����ǩ���ļ�
	 * @param pk  ˽Կ
	 * @param data  ǩ������
	 * @param alg ǩ����ʽ
	 * @return 
	 */
	public static String signData(PrivateKey pk,String data,String alg)
	{	
		try
		{
			Signature dsa=Signature.getInstance(alg);
			dsa.initSign(pk);
			dsa.update(data.getBytes("GBK"));
			byte[] sig=dsa.sign();
			return byteToHex(sig);
		} catch (Exception e)
		{
			e.getStackTrace();
			return null;
		}
		
	}
	/**
	 * ����ǩ������ 16����
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
	
	public static void main(String[] args) {
		
		String strPfx = "E://merchant.pfx";
		String strPassword = "test";
		String param="RespCode=00|MsgCode=null|Message=null|BoundNo=123|MerchNo=456|MercName=zhangs|AcNo=789|result=0|RefuseReason=ͼƬ����ȷ";
		PrivateKey pk = GetPvkformPfx(strPfx, strPassword);
        String data=signData(pk, param, alg);
        System.out.println(data);
	}

}
