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
		URI pathe = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt").toURI();
		CaesarEncryption ce = new CaesarEncryption();
		ce.Encrypt((byte)50,Paths.get(pathe));
		
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt.encrypted").toURI();
		CaesarDecryption c = new CaesarDecryption();
		c.Decrypt((byte)50,Paths.get(path));
		URI encPath = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt.encrypted_decd.txt").toURI();
		byte[] decData = Files.readAllBytes(Paths.get(encPath));
		assert decData [0] == (byte)(97);
		assert decData [1] == (byte)(98);
		assert decData [2] == (byte)(99);
		assert decData [3] == (byte)(100);
	}
	
	@Test
	public void testEmpty() throws IOException, URISyntaxException, IllegalKeyException{
		/*encrypt the file to the test, assume that the encryption works*/
		URI pathe = CaserEncryptiontest.class.getClassLoader().getResource("empty.txt").toURI();
		CaesarEncryption ce = new CaesarEncryption();
		ce.Encrypt((byte)50,Paths.get(pathe));
		
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("empty.txt.encrypted").toURI();
		CaesarDecryption c = new CaesarDecryption();
		c.Decrypt((byte)50,Paths.get(path));
		URI encPath = CaserEncryptiontest.class.getClassLoader().getResource("empty.txt.encrypted_decd.txt").toURI();
		byte[] encData = Files.readAllBytes(Paths.get(encPath));
		assert encData.length == 0;
	}
}
