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
		byte data [] = {97,98,99,100};
		CaesarEncryption c = new CaesarEncryption();
		byte [] keys = {50};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)(97+50);
		assert encData [1] == (byte)(98+50);
		assert encData [2] == (byte)(99+50);
		assert encData [3] == (byte)(100+50);
	}
}
