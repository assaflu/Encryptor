package EncryptionAlgoritems;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

public abstract class Encryption{

	protected EncryptionDecryptionManager manager;
	
	public Encryption(){
		manager = AlgoritemManaging.instance;
	}
	
	public Encryption (EncryptionDecryptionManager manager){
		this.manager = manager;
	}
	
	protected void saveFile (byte[] data,Path filePath) throws IOException{
		String savePath = filePath.toString();
		savePath = savePath.concat(".encrypted");
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(data);
		out.close();
	}
	
	abstract public byte[] Encrypt(byte [] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal;
	
}
