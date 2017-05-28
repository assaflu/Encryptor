package DecryptionAlgoritems;

import java.util.ArrayList;

import EncryptionAlgoritems.Encryption;
import Encryptor.Encryptor.DecryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Encryptor.Encryptor.WorkingMod;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

@DecryptionClass(name = "Reverse Decryption", serialNumber = 5, numberOfKeys = 1, level = EncryptionDecryptionLevel.ADVANCE)
public class ReverseDecryption extends Decryption {

	public ReverseDecryption(){
		super(AlgoritemManaging.instance);
	}
	
	public ReverseDecryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Decrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		manager.SetMode(WorkingMod.ENCRYPTION);
		ArrayList<Class<? extends Encryption>> methods = 
				manager.chooseBasicEncryptionAlgoritem(1);
		manager.SetMode(WorkingMod.DECRYPTION);
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
