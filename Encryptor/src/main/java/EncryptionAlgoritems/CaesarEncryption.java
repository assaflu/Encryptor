package EncryptionAlgoritems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Encryptor.Encryptor.EncryptionClass;

@EncryptionClass(name = "Caesar Encryption", serialNumber = 1)
public class CaesarEncryption extends Encryption{

	@Override
	public void Encrypt(byte key, Path filePath) throws IOException {
		byte fileContent [] = Files.readAllBytes(filePath);		
		
		for(int i=0; i< fileContent.length;i++){
			fileContent[i] = (byte)(fileContent[i] +key);
		}
		saveFile(fileContent,filePath);		
	}

}
