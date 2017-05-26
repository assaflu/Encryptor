package EncryptionAlgoritems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Encryptor.Encryptor.EncryptionClass;

@EncryptionClass(name = "Caesar Encryption", serialNumber = 1, numberOfKeys = 1)
public class CaesarEncryption extends Encryption{

	@Override
	public byte[] Encrypt(byte [] key, byte[] data) {
		byte encryptedData [] = new byte [data.length];		
		
		for(int i=0; i< data.length;i++){
			encryptedData[i] = (byte)(data[i] +key[0]);
		}
		return encryptedData;		
	}

}
