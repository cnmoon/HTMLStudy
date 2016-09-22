package html;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class HMACSHA1 {

	private static final String HMAC_SHA1 = "HmacSHA1";

	public static String getSignature(String strdata, String strkey)
			throws InvalidKeyException, NoSuchAlgorithmException {

		byte[] key = strkey.getBytes();
		byte[] data = strdata.getBytes();

		SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
		Mac mac = Mac.getInstance(HMAC_SHA1);
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(data);

		String result = null;
		result = Base64.getEncoder().encodeToString(rawHmac);
		return result;
	}

	public static void main(String[] args) {
		String timestamp = Long.toString(System.currentTimeMillis() / 1000L); 
		timestamp = "ts=1474432837&ttl=30&uid=UA0DF9E961";
		String key = "tf34emazdmzr2uop";
		
//		timestamp = "ts=1443079775&ttl=30&uid=U123456789";
//		key = "secret";
		
		
		try {
			String tmstr = "https://api.thinkpage.cn/v3/weather/now.json?location=beijing&ts=1474421280&ttl=30&uid=UA0DF9E961&sig=n0nZvPRcGT9yJXYaVtSF2tIPnf8=&callback=showWeather";
			String result = getSignature(timestamp, key);
			System.out.println(result);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
