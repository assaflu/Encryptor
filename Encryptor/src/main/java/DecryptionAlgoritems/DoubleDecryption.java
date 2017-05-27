package DecryptionAlgoritems;

import java.util.ArrayList;

import Encryptor.Encryptor.AlgoritemManaging;
import Encryptor.Encryptor.DecryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

@DecryptionClass(name = "Double Decryption", serialNumber = 4, numberOfKeys = 2, level = EncryptionDecryptionLevel.ADVANCE)
public class DoubleDecryption extends Decryption{

	@Override
	public byte[] Decrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		ArrayList<Class<? extends Decryption>> methods = 
				AlgoritemManaging.instance.chooseBasicDecryptionAlgoritem(2);
		byte firstKey[],secondKey[];
		firstKey = new byte [1];
		firstKey[0] = key[0];
		secondKey = new byte [1];
		secondKey[0] = key[1];
		try {
			return methods.get(1).newInstance().Decrypt(secondKey,methods.get(0).newInstance().Decrypt(firstKey, data));
		} catch (InstantiationException e) {
			System.out.println("Could not annitiate decryption class");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("Could not access decryption class");
			e.printStackTrace();
		}
		return null;
	}


}
