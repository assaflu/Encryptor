package DecryptionAlgoritems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Encryptor.Encryptor.DecryptionClass;
import Exceptions.IllegalKeyException;

@DecryptionClass(name = "Caesar Decryption", serialNumber = 1)
public class CaesarDecryption extends Decryption {

	@Override
	public void Decrypt(byte key, Path filePath) throws IOException, IllegalKeyException {
		
		byte fileContent [] = Files.readAllBytes(filePath);
		for(int i=0; i< fileContent.length;i++){
			fileContent[i] = (byte)(fileContent[i] - key);
		}
		saveFile(fileContent ,filePath);
		
	}
}
