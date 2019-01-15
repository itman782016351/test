package com.ideal.test1;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: wanglin Date: 2009-1-6 17:52:01
 */
public class TestMD5_2 {
	public static void main(String[] args) {
		String str=getMD5("dddddd");
		System.out.println(str);
	}

	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static String getMD5(String sInput) {

		MessageDigest alga = null;
		try {
			alga = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		alga.update(sInput.getBytes());
		byte[] digesta = alga.digest();
		return byte2hex(digesta);

	}
}

