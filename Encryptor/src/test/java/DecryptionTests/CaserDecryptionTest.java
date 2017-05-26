package DecryptionTests;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import DecryptionAlgoritems.CaesarDecryption;
import EncryptionAlgoritems.CaesarEncryption;
import EncryptionTests.CaserEncryptiontest;
import Exceptions.IllegalKeyException;

public class CaserDecryptionTest {
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException{
		/*encrypt the file to the test, assume that the encryption works*/
		byte data [] = {97,98,99,100};
		CaesarEncryption ce = new CaesarEncryption();
		byte[] encData = ce.Encrypt((byte)50,data);
		
		CaesarDecryption c = new CaesarDecryption();
		byte[] decData = c.Decrypt((byte)50,encData);
		assert decData [0] == (byte)(97);
		assert decData [1] == (byte)(98);
		assert decData [2] == (byte)(99);
		assert decData [3] == (byte)(100);
	}
}
