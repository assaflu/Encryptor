package EncryptionTests;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import EncryptionAlgoritems.DoubleEncryption;
import EncryptionAlgoritems.EncryptionType;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

public class DoubleEncryptionTest {

	@Test(expected = IllegalKeyException.class)
	public void testException() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		byte data [] = {97,98,99,100};
		ArrayList<EncryptionType> arr = new ArrayList<>();
		arr.add(EncryptionType.CaesarEncryption);
		arr.add(EncryptionType.MWOEncryption);		
		EncryptionDecryptionManager m = Mockito.mock(EncryptionDecryptionManager.class);
		Mockito.when(m.chooseBasicEncryptionAlgoritem(2)).thenReturn(arr);
		DoubleEncryption c = new DoubleEncryption(m);
		byte [] keys = {51,50};
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
		arr.add(EncryptionType.CaesarEncryption);
		arr.add(EncryptionType.XOREncryption);				
		EncryptionDecryptionManager m = Mockito.mock(EncryptionDecryptionManager.class);
		Mockito.when(m.chooseBasicEncryptionAlgoritem(2)).thenReturn(arr);
		DoubleEncryption c = new DoubleEncryption(m);
		byte [] keys = {51,53};
		byte[] encData = c.Encrypt(keys,data);
		assert encData [0] == (byte)((97+51)^53);
		assert encData [1] == (byte)((98+51)^53);
		assert encData [2] == (byte)((99+51)^53);
		assert encData [3] == (byte)((100+51)^53);
	}
}
