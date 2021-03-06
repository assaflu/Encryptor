package DecryptionTests;


import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import DecryptionAlgoritems.XORDecryption;
import EncryptionAlgoritems.XOREncryption;
import Exceptions.IllegalKeyException;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

public class XorDecryptionTest {

	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException{
		/*encrypt the file to the test, assume that the encryption works*/
		byte data [] = {97,98,99,100};
		EncryptionDecryptionManager man = new AlgoritemManaging();
		XOREncryption ce = new XOREncryption(man);
		byte [] keys = {50};
		byte[] encData = ce.Encrypt(keys,data);
		
		XORDecryption c = new XORDecryption(man);
		byte[] decData = c.Decrypt(keys,encData);
		assert decData [0] == (byte)(97);
		assert decData [1] == (byte)(98);
		assert decData [2] == (byte)(99);
		assert decData [3] == (byte)(100);
	}
}
