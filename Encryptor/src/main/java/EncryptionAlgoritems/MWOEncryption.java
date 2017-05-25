package EncryptionAlgoritems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Encryptor.Encryptor.EncryptionClass;
import Exceptions.IllegalKeyException;

@EncryptionClass(name = "MWO Encryption", serialNumber = 3)
public class MWOEncryption extends Encryption{

	@Override
	public void Encrypt(byte key, Path filePath) throws IOException, IllegalKeyException {
		if(key %2 == 0)
			throw new IllegalKeyException();
		
		byte fileContent [] = Files.readAllBytes(filePath);	
		
		for(int i=0; i< fileContent.length;i++){
			fileContent[i] = (byte)(fileContent[i] * key);
		}
		saveFile(fileContent,filePath);	
	}

}
