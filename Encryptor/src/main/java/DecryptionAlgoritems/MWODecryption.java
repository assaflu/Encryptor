package DecryptionAlgoritems;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import Encryptor.Encryptor.DecryptionClass;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

@DecryptionClass(name = "MWO Decryption", serialNumber = 3)
public class MWODecryption extends Decryption{
	
	private static int MWOKeyFinder (byte key){
		for(int i = Byte.MAX_VALUE; i<=Byte.MAX_VALUE;i++){
			if((key*i)== 1)
				return i;
		}
		return -1;
	}
	
	@Override
	public void Decrypt(byte key, Path filePath) throws IOException, IllegalKeyException, DecryptionKeyIllegal {
		if(key%2 == 0){
			throw new IllegalKeyException();
		}
		
		int decryptionKey = MWOKeyFinder(key);
		if(decryptionKey%2 ==0 || decryptionKey ==-1){
			throw new DecryptionKeyIllegal();
		}
		
		byte fileContent [] = Files.readAllBytes(filePath);
		for(int i=0; i< fileContent.length;i++){
			fileContent[i] = (byte)(fileContent[i] * decryptionKey);
		}
		saveFile(fileContent ,filePath);		
	}
}
