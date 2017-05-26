package EncryptionTests;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

import EncryptionAlgoritems.CaesarEncryption;
import EncryptionAlgoritems.MWOEncryption;
import EncryptionAlgoritems.XOREncryption;

public class XorEncryptionTest {

	@Test
	public void testSimple() throws URISyntaxException, IOException {
		byte data [] = {97,98,99,100};
		XOREncryption c = new XOREncryption();
		byte [] keys = {50};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)(97^50);
		assert encData [1] == (byte)(98^50);
		assert encData [2] == (byte)(99^50);
		assert encData [3] == (byte)(100^50);
	}
}
