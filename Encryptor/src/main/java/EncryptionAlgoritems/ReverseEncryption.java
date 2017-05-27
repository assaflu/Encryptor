package EncryptionAlgoritems;

import java.util.ArrayList;

import DecryptionAlgoritems.Decryption;
import Encryptor.Encryptor.AlgoritemManaging;
import Encryptor.Encryptor.EncryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

@EncryptionClass(name = "Reverse Encryption" , serialNumber = 5, numberOfKeys = 1,
				level = EncryptionDecryptionLevel.ADVANCE)
public class ReverseEncryption extends Encryption {

	@Override
	public byte[] Encrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		ArrayList<Class<? extends Decryption>> methods = 
				AlgoritemManaging.instance.chooseBasicDecryptionAlgoritem(1);
		try {
			return methods.get(0).newInstance().Decrypt(key, data);
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
