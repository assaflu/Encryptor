package DecryptionAlgoritems;

import java.util.ArrayList;

import EncryptionAlgoritems.EncryptionFactory;
import EncryptionAlgoritems.EncryptionType;
import Encryptor.Encryptor.DecryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Encryptor.Encryptor.WorkingMod;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

@DecryptionClass(name = "Reverse Decryption", serialNumber = 5, numberOfKeys = 1,type = DecryptionType.ReverseDecryption, level = EncryptionDecryptionLevel.ADVANCE)
public class ReverseDecryption extends Decryption {

	/*public ReverseDecryption(){
		super(AlgoritemManaging.instance);
	}*/
	
	public ReverseDecryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Decrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		manager.SetMode(WorkingMod.ENCRYPTION);
		ArrayList<EncryptionType> methods = 
				manager.chooseBasicEncryptionAlgoritem(1);
		manager.SetMode(WorkingMod.DECRYPTION);
		return new EncryptionFactory().create(methods.get(0),manager).Encrypt(key, data);
	}

}
