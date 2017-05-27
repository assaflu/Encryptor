package EncryptionAlgoritems;


import java.util.ArrayList;

import Encryptor.Encryptor.AlgoritemManaging;
import Encryptor.Encryptor.EncryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

@EncryptionClass(name = "Double Encryption", serialNumber = 4, numberOfKeys = 2,
				level = EncryptionDecryptionLevel.ADVANCE)
public class DoubleEncryption extends Encryption{

	@Override
	public byte[] Encrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		ArrayList<Class<? extends Encryption>> methods = 
				AlgoritemManaging.instance.chooseBasicEncryptionAlgoritem(2);
		byte firstKey[],secondKey[];
		firstKey = new byte [1];
		firstKey[0] = key[0];
		secondKey = new byte [1];
		secondKey[0] = key[1];
		try {
			return methods.get(1).newInstance().Encrypt(secondKey,methods.get(0).newInstance().Encrypt(firstKey, data));
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
