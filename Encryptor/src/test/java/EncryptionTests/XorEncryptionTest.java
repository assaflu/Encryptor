package EncryptionTests;


import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import EncryptionAlgoritems.XOREncryption;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

public class XorEncryptionTest {

	@Test
	public void testSimple() throws URISyntaxException, IOException {
		byte data [] = {97,98,99,100};
		EncryptionDecryptionManager man = new AlgoritemManaging();
		XOREncryption c = new XOREncryption(man);
		byte [] keys = {50};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)(97^50);
		assert encData [1] == (byte)(98^50);
		assert encData [2] == (byte)(99^50);
		assert encData [3] == (byte)(100^50);
	}
}
