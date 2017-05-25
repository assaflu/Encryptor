package DecryptionAlgoritems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Encryptor.Encryptor.DecryptionClass;
import Exceptions.IllegalKeyException;

@DecryptionClass(name = "XOR Decryption", serialNumber = 2)
public class XORDecryption extends Decryption{

	@Override
	public void Decrypt(byte key, Path filePath) throws IOException, IllegalKeyException {
		
		byte fileContent [] = Files.readAllBytes(filePath);
		for(int i=0; i< fileContent.length;i++){
			fileContent[i] = (byte)(fileContent[i] ^ key);
		}
		saveFile(fileContent ,filePath);
	}

}
