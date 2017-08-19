package EncryptionAlgoritems;


import java.util.ArrayList;

import Encryptor.Encryptor.EncryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

@EncryptionClass(name = "Double Encryption", serialNumber = 4, numberOfKeys = 2,type = EncryptionType.DoubleEncryption,
				level = EncryptionDecryptionLevel.ADVANCE)
public class DoubleEncryption extends Encryption{

	//private EncryptionDecryptionManager manager;
	/*public DoubleEncryption(){
		//super(AlgoritemManaging.instance);
	}*/
	
	public DoubleEncryption(EncryptionDecryptionManager manager) {
		super(manager);
	}

	@Override
	public byte[] Encrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		ArrayList<EncryptionType> methods = 
				manager.chooseBasicEncryptionAlgoritem(2);
		byte firstKey[],secondKey[];
		firstKey = new byte [1];
		firstKey[0] = key[0];
		secondKey = new byte [1];
		secondKey[0] = key[1];
		EncryptionFactory factory = new EncryptionFactory();
		Encryption first,second;
		first = factory.create(methods.get(0), manager);
		second = factory.create(methods.get(1), manager);
		return second.Encrypt(secondKey, first.Encrypt(firstKey, data));
	}
	

}
