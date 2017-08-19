package DecryptionTests;

import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Test;

import DecryptionAlgoritems.MWODecryption;
import EncryptionAlgoritems.MWOEncryption;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

public class MWODecryptionTest {
	
	@Test(expected = IllegalKeyException.class)
	public void testValidkey() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		EncryptionDecryptionManager man = new AlgoritemManaging();
		MWODecryption c = new MWODecryption(man);
		byte [] keys = {50};
		c .Decrypt(keys,null);
	}
	
	@Test
	public void testSimple() throws IOException, URISyntaxException, IllegalKeyException, DecryptionKeyIllegal{
		/*encrypt the file to the test, assume that the encryption works*/
		byte data [] = {97,98,99,100};
		EncryptionDecryptionManager man = new AlgoritemManaging();
		MWOEncryption ce = new MWOEncryption(man);
		byte [] keys = {51};
		byte[] encData = ce.Encrypt(keys,data);
				
		MWODecryption c = new MWODecryption(man);
		byte[] decData = c.Decrypt(keys,encData);
		assert decData [0] == (byte)(97);
		assert decData [1] == (byte)(98);
		assert decData [2] == (byte)(99);
		assert decData [3] == (byte)(100);
	}
}
