package EncryptionAlgoritems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Encryptor.Encryptor.EncryptionClass;

@EncryptionClass(name = "XOR Encryption",serialNumber = 2)
public class XOREncryption extends Encryption{

	@Override
	public byte[] Encrypt(byte key, byte[] data) {
		byte encryptedData [] = new byte [data.length];
		
		for(int i=0; i< data.length;i++){
			encryptedData[i] = (byte) (data[i] ^ key);
		}
		return encryptedData;
	}

}
