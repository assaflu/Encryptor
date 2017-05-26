package EncryptionTests;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import EncryptionAlgoritems.CaesarEncryption;
import EncryptionAlgoritems.MWOEncryption;
import Exceptions.IllegalKeyException;

public class MWOEncryptionTest {

	@Test(expected = IllegalKeyException.class)
	public void testValidkey() throws IOException, URISyntaxException, IllegalKeyException{
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt").toURI();
		MWOEncryption c = new MWOEncryption();
		c.Encrypt((byte)50,Paths.get(path));
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException{
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt").toURI();
		MWOEncryption c = new MWOEncryption();
		c.Encrypt((byte)51,Paths.get(path));
		URI encPath = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt.encrypted").toURI();
		byte[] encData = Files.readAllBytes(Paths.get(encPath));
		assert encData [0] == (byte)(97*51);
		assert encData [1] == (byte)(98*51);
		assert encData [2] == (byte)(99*51);
		assert encData [3] == (byte)(100*51);
	}
	
	@Test
	public void testEmpty() throws IOException, URISyntaxException{
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("empty.txt").toURI();
		CaesarEncryption c = new CaesarEncryption();
		c.Encrypt((byte)50,Paths.get(path));
		URI encPath = CaserEncryptiontest.class.getClassLoader().getResource("empty.txt.encrypted").toURI();
		byte[] encData = Files.readAllBytes(Paths.get(encPath));
		assert encData.length == 0;
	}
}
