package DecryptionAlgoritems;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

public abstract class Decryption{
	
	protected void saveFile (byte[] data,Path filePath) throws IOException{
		String savePath = filePath.toString();
		String [] extention = savePath.split("\\.");
		if(extention.length >=3){
			savePath = savePath.concat("_decd."+extention[extention.length-2]);
		}
		else{
			savePath = savePath.concat("_decd."+extention[extention.length-1]);
		}
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(data);
		out.close();
	}
	
	abstract public byte[] Decrypt(byte [] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal;
}
