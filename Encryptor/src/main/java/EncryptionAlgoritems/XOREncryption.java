package EncryptionAlgoritems;

import Encryptor.Encryptor.AlgoritemManaging;
import Encryptor.Encryptor.EncryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionManager;

@EncryptionClass(name = "XOR Encryption",serialNumber = 2, numberOfKeys = 1)
public class XOREncryption extends Encryption{

	public XOREncryption(){
		super(AlgoritemManaging.instance);
	}
	
	public XOREncryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Encrypt(byte [] key, byte[] data) {
		byte encryptedData [] = new byte [data.length];
		
		for(int i=0; i< data.length;i++){
			encryptedData[i] = (byte) (data[i] ^ key[0]);
		}
		return encryptedData;
	}

}
