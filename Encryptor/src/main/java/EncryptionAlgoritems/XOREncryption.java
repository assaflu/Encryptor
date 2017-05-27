package EncryptionAlgoritems;

import Encryptor.Encryptor.EncryptionClass;

@EncryptionClass(name = "XOR Encryption",serialNumber = 2, numberOfKeys = 1)
public class XOREncryption extends Encryption{

	@Override
	public byte[] Encrypt(byte [] key, byte[] data) {
		byte encryptedData [] = new byte [data.length];
		
		for(int i=0; i< data.length;i++){
			encryptedData[i] = (byte) (data[i] ^ key[0]);
		}
		return encryptedData;
	}

}
