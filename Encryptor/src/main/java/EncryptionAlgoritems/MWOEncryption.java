package EncryptionAlgoritems;


import Encryptor.Encryptor.EncryptionClass;
import Exceptions.IllegalKeyException;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

@EncryptionClass(name = "MWO Encryption", serialNumber = 3, numberOfKeys = 1)
public class MWOEncryption extends Encryption{

	public MWOEncryption(){
		super(AlgoritemManaging.instance);
	}
	
	public MWOEncryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Encrypt(byte [] key, byte[] data) throws IllegalKeyException {
		if(key[0] %2 == 0)
			throw new IllegalKeyException();
		
		byte encryptedData [] = new byte [data.length];
		
		for(int i=0; i< data.length;i++){
			encryptedData[i] = (byte)(data[i] * key[0]);
		}
		return encryptedData;	
	}

}
