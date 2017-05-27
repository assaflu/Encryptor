package EncryptionTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import DecryptionAlgoritems.Decryption;
import DecryptionAlgoritems.MWODecryption;
import DecryptionAlgoritems.XORDecryption;
import EncryptionAlgoritems.CaesarEncryption;
import EncryptionAlgoritems.Encryption;
import EncryptionAlgoritems.MWOEncryption;
import EncryptionAlgoritems.ReverseEncryption;
import EncryptionAlgoritems.XOREncryption;
import Encryptor.Encryptor.EncryptionDecryptionManager;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

public class ReverseEncryptionTest {

	@Test(expected = IllegalKeyException.class)
	public void testException() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		byte data [] = {97,98,99,100};
		ArrayList<Class<? extends Decryption>> arr = new ArrayList<>();
		arr.add(MWODecryption.class);		
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
		ArrayList<Class<? extends Decryption>> arr = new ArrayList<>();
		arr.add(XORDecryption.class);			
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