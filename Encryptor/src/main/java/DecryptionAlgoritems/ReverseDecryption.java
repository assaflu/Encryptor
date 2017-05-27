package DecryptionAlgoritems;

import java.util.ArrayList;

import EncryptionAlgoritems.Encryption;
import Encryptor.Encryptor.AlgoritemManaging;
import Encryptor.Encryptor.DecryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

@DecryptionClass(name = "Reverse Decryption", serialNumber = 5, numberOfKeys = 1, level = EncryptionDecryptionLevel.ADVANCE)
public class ReverseDecryption extends Decryption {

	@Override
	public byte[] Decrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		ArrayList<Class<? extends Encryption>> methods = 
				AlgoritemManaging.instance.chooseBasicEncryptionAlgoritem(1);
		try {
			return methods.get(0).newInstance().Encrypt(key, data);
		} catch (InstantiationException e) {
			System.out.println("Could not annitiate encryption class");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Could not access encryption class");
			e.printStackTrace();
		}
		return null;
	}

}
