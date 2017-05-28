package DecryptionTests;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;

import DecryptionAlgoritems.CaesarDecryption;
import DecryptionAlgoritems.Decryption;
import DecryptionAlgoritems.DoubleDecryption;
import DecryptionAlgoritems.MWODecryption;
import DecryptionAlgoritems.XORDecryption;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

public class DoubleDecryptionTest {

	@Test(expected = IllegalKeyException.class)
	public void testException() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		byte data [] = {97,98,99,100};
		ArrayList<Class<? extends Decryption>> arr = new ArrayList<>();
		arr.add(CaesarDecryption.class);
		arr.add(MWODecryption.class);		
		EncryptionDecryptionManager m = Mockito.mock(EncryptionDecryptionManager.class);
		Mockito.when(m.chooseBasicDecryptionAlgoritem(2)).thenReturn(arr);
		DoubleDecryption c = new DoubleDecryption(m);
		byte [] keys = {51,50};
		byte[] encData = c.Decrypt(keys,data);
		assert encData [0] == (byte)((97+51)^53);
		assert encData [1] == (byte)((98+51)^53);
		assert encData [2] == (byte)((99+51)^53);
		assert encData [3] == (byte)((100+51)^53);
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		byte data [] = {97,98,99,100};
		ArrayList<Class<? extends Decryption>> arr = new ArrayList<>();
		arr.add(CaesarDecryption.class);
		arr.add(XORDecryption.class);		
		EncryptionDecryptionManager m = Mockito.mock(EncryptionDecryptionManager.class);
		Mockito.when(m.chooseBasicDecryptionAlgoritem(2)).thenReturn(arr);
		DoubleDecryption c = new DoubleDecryption(m);
		byte [] keys = {51,53};
		byte[] encData = c.Decrypt(keys,data);
		assert encData [0] == (byte)((97-51)^53);
		assert encData [1] == (byte)((98-51)^53);
		assert encData [2] == (byte)((99-51)^53);
		assert encData [3] == (byte)((100-51)^53);
	}
}
