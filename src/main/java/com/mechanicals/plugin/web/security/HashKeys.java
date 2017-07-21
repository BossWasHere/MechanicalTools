package com.mechanicals.plugin.web.security;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class HashKeys {

	private final String MD5;
	private final String SHA256;
	
	public HashKeys(File generate) {
		MD5 = getHash(generate, HashFunction.MD5).toUpperCase();
		SHA256 = getHash(generate, HashFunction.SHA256).toUpperCase();
	}
	
	public HashKeys(String MD5, String SHA256) {
		this.MD5 = MD5.toUpperCase();
		this.SHA256 = SHA256.toUpperCase();
	}
	
	public boolean compareKeys(HashKeys hashKeys) {
		return hashKeys.getMD5Hash().equalsIgnoreCase(this.getMD5Hash()) && hashKeys.getSHA256Hash().equalsIgnoreCase(this.getSHA256Hash());
	}
	
	public String getMD5Hash() {
		return MD5;
	}
	
	public String getSHA256Hash() {
		return SHA256;
	}
	
	private static String getHash(File file, HashFunction method) {
		try {
			byte[] digest = HashKeys.createChecksum(file, method.getFunction());
			String output = "";
			for (int i = 0; i < digest.length; i++) {
				output += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1);
			}
			return new String(output);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static byte[] createChecksum(File file, String method) {
		try {
			InputStream fis = new FileInputStream(file);

			byte[] buffer = new byte[1024];
			MessageDigest complete = MessageDigest.getInstance(method);
			int numRead;

			do {
				numRead = fis.read(buffer);
				if (numRead > 0) {
					complete.update(buffer, 0, numRead);
				}
			} while (numRead != -1);

			fis.close();
			return complete.digest();
		} catch (Exception e) {
			return new byte[1024];
		}
	}
}

enum HashFunction {
	MD5 ("MD5"),
	SHA1 ("SHA-1"),
	SHA256 ("SHA-256");
	
	private final String id;
	
	private HashFunction(String id) {
		this.id = id;
	}
	
	public String getFunction() { return id; }
}