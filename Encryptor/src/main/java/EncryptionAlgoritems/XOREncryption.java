package EncryptionAlgoritems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Encryptor.Encryptor.EncryptionClass;

@EncryptionClass(name = "XOR Encryption",serialNumber = 2)
public class XOREncryption extends Encryption{

	@Override
	public void Encrypt(byte key, Path filePath) throws IOException {
		byte fileContent [] = Files.readAllBytes(filePath);
		
		for(int i=0; i< fileContent.length;i++){
			fileContent[i] = (byte) (fileContent[i] ^ key);
		}
		saveFile(fileContent,filePath);
	}

}
