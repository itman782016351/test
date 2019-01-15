package com.ideal.test1;

import java.security.MessageDigest;
import java.util.UUID;

public class TestMD5 {
	public static void main(String[] args) {
		//System.out.println(MD5("60-010-200-01-01014"));
		System.out.println(MD5("dddddd"));
	}
	
	
	
	private static String MD5(String s) {
		final char[] HEX_DIGITS = "0123456789abcdef".toCharArray();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			StringBuilder ret = new StringBuilder(bytes.length * 2);
			for (int i = 0; i < bytes.length; i++) {
				System.out.println(bytes[i]);
				ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
				ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
			}

			return ret.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
