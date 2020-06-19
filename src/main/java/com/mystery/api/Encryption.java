package com.mystery.api;
import java.util.Base64;

public class Encryption {
	 public static String encrypt(String data) {

			// String originalInput = "test input";
			String encodedString = Base64.getEncoder().encodeToString(data.getBytes());

			return encodedString;

		}
	 
	 public static String decrypt(String encodedString) {

			byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
			String decodedString = new String(decodedBytes);

			return decodedString;

		}
}
