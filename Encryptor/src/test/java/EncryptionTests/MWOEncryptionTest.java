package EncryptionTests;


import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.Test;

import EncryptionAlgoritems.MWOEncryption;
import Exceptions.IllegalKeyException;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

public class MWOEncryptionTest {

	@Test(expected = IllegalKeyException.class)
	public void testValidkey() throws IOException, URISyntaxException, IllegalKeyException{
		byte data [] = {97,98,99,100};
		EncryptionDecryptionManager man = new AlgoritemManaging();
		MWOEncryption c = new MWOEncryption(man);
		byte [] keys = {50};
		@SuppressWarnings("unused")
		byte[] encData = c.Encrypt(keys,data);
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException{
		byte data [] = {97,98,99,100};
		EncryptionDecryptionManager man = new AlgoritemManaging();
		MWOEncryption c = new MWOEncryption(man);
		byte [] keys = {51};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)(97*51);
		assert encData [1] == (byte)(98*51);
		assert encData [2] == (byte)(99*51);
		assert encData [3] == (byte)(100*51);
	}
}
