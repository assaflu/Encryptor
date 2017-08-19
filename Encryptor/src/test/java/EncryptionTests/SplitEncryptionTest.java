package EncryptionTests;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import EncryptionAlgoritems.EncryptionType;
import EncryptionAlgoritems.SplitEncryption;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

public class SplitEncryptionTest {

	@Test(expected = IllegalKeyException.class)
	public void testException() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		byte data [] = {97,98,99,100};
		ArrayList<EncryptionType> arr = new ArrayList<>();
		arr.add(EncryptionType.MWOEncryption);		
		EncryptionDecryptionManager m = Mockito.mock(EncryptionDecryptionManager.class);
		Mockito.when(m.chooseBasicEncryptionAlgoritem(1)).thenReturn(arr);
		SplitEncryption c = new SplitEncryption(m);
		byte [] keys = {50,51};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)((97+51)^53);
		assert encData [1] == (byte)((98+51)^53);
		assert encData [2] == (byte)((99+51)^53);
		assert encData [3] == (byte)((100+51)^53);
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		byte data [] = {97,98,99,100};
		ArrayList<EncryptionType> arr = new ArrayList<>();
		arr.add(EncryptionType.XOREncryption);			
		EncryptionDecryptionManager m = Mockito.mock(EncryptionDecryptionManager.class);
		Mockito.when(m.chooseBasicEncryptionAlgoritem(1)).thenReturn(arr);
		SplitEncryption c = new SplitEncryption(m);
		byte [] keys = {53,52};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)(97^53);
		assert encData [1] == (byte)(98^52);
		assert encData [2] == (byte)(99^53);
		assert encData [3] == (byte)(100^52);
	}

}
