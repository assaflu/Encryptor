package DecryptionTests;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import DecryptionAlgoritems.XORDecryption;
import EncryptionAlgoritems.CaesarEncryption;
import EncryptionAlgoritems.XOREncryption;
import EncryptionTests.CaserEncryptiontest;
import Exceptions.IllegalKeyException;

public class XorDecryptionTest {

	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException{
		/*encrypt the file to the test, assume that the encryption works*/
		byte data [] = {97,98,99,100};
		XOREncryption ce = new XOREncryption();
		byte[] encData = ce.Encrypt((byte)50,data);
		
		XORDecryption c = new XORDecryption();
		byte[] decData = c.Decrypt((byte)50,encData);
		assert decData [0] == (byte)(97);
		assert decData [1] == (byte)(98);
		assert decData [2] == (byte)(99);
		assert decData [3] == (byte)(100);
	}
}
