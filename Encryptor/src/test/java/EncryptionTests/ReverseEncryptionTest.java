package EncryptionTests;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import DecryptionAlgoritems.DecryptionType;
import EncryptionAlgoritems.ReverseEncryption;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

public class ReverseEncryptionTest {

	@Test(expected = IllegalKeyException.class)
	public void testException() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		byte data [] = {97,98,99,100};
		ArrayList<DecryptionType> arr = new ArrayList<>();
		arr.add(DecryptionType.MWODecryption);		
		EncryptionDecryptionManager m = Mockito.mock(EncryptionDecryptionManager.class);
		Mockito.when(m.chooseBasicDecryptionAlgoritem(1)).thenReturn(arr);
		ReverseEncryption c = new ReverseEncryption(m);
		byte [] keys = {50};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)((97+51)^53);
		assert encData [1] == (byte)((98+51)^53);
		assert encData [2] == (byte)((99+51)^53);
		assert encData [3] == (byte)((100+51)^53);
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		byte data [] = {97,98,99,100};
		ArrayList<DecryptionType> arr = new ArrayList<>();
		arr.add(DecryptionType.XORDecryption);			
		EncryptionDecryptionManager m = Mockito.mock(EncryptionDecryptionManager.class);
		Mockito.when(m.chooseBasicDecryptionAlgoritem(1)).thenReturn(arr);
		ReverseEncryption c = new ReverseEncryption(m);
		byte [] keys = {53};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)(97^53);
		assert encData [1] == (byte)(98^53);
		assert encData [2] == (byte)(99^53);
		assert encData [3] == (byte)(100^53);
	}

}
