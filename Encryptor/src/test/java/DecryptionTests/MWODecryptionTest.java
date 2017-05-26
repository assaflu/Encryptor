package DecryptionTests;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import DecryptionAlgoritems.MWODecryption;
import EncryptionAlgoritems.MWOEncryption;
import EncryptionTests.CaserEncryptiontest;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

public class MWODecryptionTest {
	
	@Test(expected = IllegalKeyException.class)
	public void testValidkey() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt").toURI();
		MWODecryption c = new MWODecryption();
		c.Decrypt((byte)50,Paths.get(path));
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		/*encrypt the file to the test, assume that the encryption works*/
		URI pathe = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt").toURI();
		MWOEncryption ce = new MWOEncryption();
		ce.Encrypt((byte)51,Paths.get(pathe));
		
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt.encrypted").toURI();
		MWODecryption c = new MWODecryption();
		c.Decrypt((byte)51,Paths.get(path));
		URI encPath = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt.encrypted_decd.txt").toURI();
		byte[] decData = Files.readAllBytes(Paths.get(encPath));
		assert decData [0] == (byte)(97);
		assert decData [1] == (byte)(98);
		assert decData [2] == (byte)(99);
		assert decData [3] == (byte)(100);
	}
	
	@Test
	public void testEmpty() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		/*encrypt the file to the test, assume that the encryption works*/
		URI pathe = CaserEncryptiontest.class.getClassLoader().getResource("empty.txt").toURI();
		MWOEncryption ce = new MWOEncryption();
		ce.Encrypt((byte)51,Paths.get(pathe));
		
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("empty.txt.encrypted").toURI();
		MWODecryption c = new MWODecryption();
		c.Decrypt((byte)51,Paths.get(path));
		URI encPath = CaserEncryptiontest.class.getClassLoader().getResource("empty.txt.encrypted_decd.txt").toURI();
		byte[] encData = Files.readAllBytes(Paths.get(encPath));
		assert encData.length == 0;
	}
}
