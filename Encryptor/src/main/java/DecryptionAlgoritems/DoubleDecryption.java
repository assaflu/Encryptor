package DecryptionAlgoritems;

import java.util.ArrayList;

import Encryptor.Encryptor.DecryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

@DecryptionClass(name = "Double Decryption", serialNumber = 4, numberOfKeys = 2, level = EncryptionDecryptionLevel.ADVANCE, type = DecryptionType.DoubleDecryption)
public class DoubleDecryption extends Decryption{

	/*public DoubleDecryption(){
		super(AlgoritemManaging.instance);
	}*/
	
	public DoubleDecryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Decrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		ArrayList<DecryptionType> methods = 
				manager.chooseBasicDecryptionAlgoritem(2);
		byte firstKey[],secondKey[];
		firstKey = new byte [1];
		firstKey[0] = key[0];
		secondKey = new byte [1];
		secondKey[0] = key[1];
		DecryptionFactory factory = new DecryptionFactory();
		Decryption first,second;
		first = factory.create(methods.get(0),manager);
		second = factory.create(methods.get(1), manager);
		return second.Decrypt(secondKey, first.Decrypt(firstKey, data));
	}


}
