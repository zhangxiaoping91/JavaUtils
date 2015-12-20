package com.xx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.security.PrivateKey;

public class SendHttpRequest {
	private static final String alg="MD5withRSA";
	public static String sendRequestHttp(String requrl,String data)
	{
		try
		{
			data=URLDecoder.decode(data, "utf-8");
		} catch (UnsupportedEncodingException e1)
		{
			System.out.println("����ת��ʧ�ܣ�"+e1.getMessage());
		}
		OutputStream out=null;
		HttpURLConnection con=null;
		StringBuilder sb = new StringBuilder();
		try
		{
			URL url=new URL(data);
			con=(HttpURLConnection) url.openConnection();

			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
//			con.setRequestProperty("Content-Type", "text/xml");
			con.setUseCaches(false);
			out=con.getOutputStream();
			BufferedReader in = null;
			
//			out.write(data.getBytes());
			try
			{
				in = new BufferedReader(new InputStreamReader(con.getInputStream(), "GBK"));
				String str = null;
				while ((str = in.readLine()) != null)
				{
					sb.append(str);
				}
			} catch (Exception e)
			{
				e.getStackTrace();
			}
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally{
			if(out!=null)
			{
				try
				{
					out.flush();
					out.close();
				} catch (IOException e)
				{
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				con.disconnect();
			}
		}
		return sb.toString();
	}	
	
	public static void main(String[] args) {
		String url="http://115.28.109.25/O2OV2_test/seller/index.php?m=ApiPay&a=get_cashier_result&";
		String plain="RespCode=00|MsgCode=null|Message=null|BoundNo=123|MerchNo=456|MercName=zhangs|AcNo=789|result=0|RefuseReason=ͼƬ����ȷ";
		String strPfx = "E://merchant.pfx";
		String strPassword = "test";
		PrivateKey pk=GetPrivateKey.GetPvkformPfx(strPfx, strPassword);
		String signature=GetPrivateKey.signData(pk, plain, alg);
		url=url+"plain="+plain+"&signature="+signature;
		String returnData=sendRequestHttp(url, "");
		System.out.println(returnData);
	}

}