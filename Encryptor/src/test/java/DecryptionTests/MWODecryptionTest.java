package DecryptionTests;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import DecryptionAlgoritems.MWODecryption;
import EncryptionAlgoritems.CaesarEncryption;
import EncryptionAlgoritems.MWOEncryption;
import EncryptionTests.CaserEncryptiontest;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

public class MWODecryptionTest {
	
	@Test(expected = IllegalKeyException.class)
	public void testValidkey() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		MWODecryption c = new MWODecryption();
		c .Decrypt((byte)50,null);
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		/*encrypt the file to the test, assume that the encryption works*/
		byte data [] = {97,98,99,100};
		MWOEncryption ce = new MWOEncryption();
		byte[] encData = ce.Encrypt((byte)51,data);
				
		MWODecryption c = new MWODecryption();
		byte[] decData = c.Decrypt((byte)51,encData);
		assert decData [0] == (byte)(97);
		assert decData [1] == (byte)(98);
		assert decData [2] == (byte)(99);
		assert decData [3] == (byte)(100);
	}
}
