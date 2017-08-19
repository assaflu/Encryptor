package EncryptionAlgoritems;

import java.util.ArrayList;

import DecryptionAlgoritems.DecryptionFactory;
import DecryptionAlgoritems.DecryptionType;
import Encryptor.Encryptor.EncryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Encryptor.Encryptor.WorkingMod;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

@EncryptionClass(name = "Reverse Encryption" , serialNumber = 5, numberOfKeys = 1,type = EncryptionType.ReverseEncryption,
				level = EncryptionDecryptionLevel.ADVANCE)
public class ReverseEncryption extends Encryption {

	/*public ReverseEncryption(){
		super(AlgoritemManaging.instance);;
	}*/
	
	public ReverseEncryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Encrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		manager.SetMode(WorkingMod.DECRYPTION);
		ArrayList<DecryptionType> methods = 
				manager.chooseBasicDecryptionAlgoritem(1);
		manager.SetMode(WorkingMod.ENCRYPTION);
		return new DecryptionFactory().create(methods.get(0), manager).Decrypt(key, data);
	}

}
