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
		byte data [] = {97,98,99,100};
		MWOEncryption c = new MWOEncryption();
		byte [] keys = {50};
		byte[] encData = c.Encrypt(keys,data);
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException{
		byte data [] = {97,98,99,100};
		MWOEncryption c = new MWOEncryption();
		byte [] keys = {51};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)(97*51);
		assert encData [1] == (byte)(98*51);
		assert encData [2] == (byte)(99*51);
		assert encData [3] == (byte)(100*51);
	}
}
