package kr.letech.study02.test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.springframework.security.crypto.encrypt.AesBytesEncryptor;

public class Test {
	public static void main(String[] args) {
    	// 첫번째 파라미터는 비밀번호
    	// 비밀번호가 같은 사용자가 있더라도 그것을 방지할 수 있음
		AesBytesEncryptor encryptor = new AesBytesEncryptor("tt142857", "1cf501ac32624f2d874accf1cf8a8764");
		byte[] encrypt = encryptor.encrypt("qweqwe".getBytes());
		String encryptString = byteArrayToString(encrypt);
		System.out.println(encryptString);

		byte[] decryptBytes = stringToByteArray("115 44 106 20 -22 66 -92 90 -44 29 79 -83 -87 -46 -88 30");
		byte[] decrypt = encryptor.decrypt(decryptBytes);
		System.out.println(new String(decrypt, StandardCharsets.UTF_8));
	}

    public static String byteArrayToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte abyte :bytes){
            sb.append(abyte);
            sb.append(" ");
        }
        return sb.toString();
    }

    public static byte[] stringToByteArray(String byteString) {
        String[] split = byteString.split("\\s");
        ByteBuffer buffer = ByteBuffer.allocate(split.length);
        for (String s : split) {
            buffer.put((byte) Integer.parseInt(s));
        }
        return buffer.array();
    }
}
