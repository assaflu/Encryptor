package EncryptionTests;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;

import EncryptionAlgoritems.CaesarEncryption;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

public class CaserEncryptiontest {

	@Test
	public void testSimple() throws IOException, URISyntaxException{
		byte data [] = {97,98,99,100};
		EncryptionDecryptionManager man = new AlgoritemManaging();
		CaesarEncryption c = new CaesarEncryption(man);
		byte [] keys = {50};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)(97+50);
		assert encData [1] == (byte)(98+50);
		assert encData [2] == (byte)(99+50);
		assert encData [3] == (byte)(100+50);
	}
}
