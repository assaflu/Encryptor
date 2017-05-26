package EncryptionTests;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import EncryptionAlgoritems.CaesarEncryption;

public class CaserEncryptiontest {

	@Test
	public void testSimple() throws IOException, URISyntaxException{
		URI path = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt").toURI();
		CaesarEncryption c = new CaesarEncryption();
		c.Encrypt((byte)50,Paths.get(path));
		URI encPath = CaserEncryptiontest.class.getClassLoader().getResource("simple.txt.encrypted").toURI();
		byte[] encData = Files.readAllBytes(Paths.get(encPath));
		assert encData [0] == (byte)(97+50);
		assert encData [1] == (byte)(98+50);
		assert encData [2] == (byte)(99+50);
		assert encData [3] == (byte)(100+50);
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
