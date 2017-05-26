package EncryptionAlgoritems;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

import Exceptions.IllegalKeyException;

public abstract class Encryption{

	protected void saveFile (byte[] data,Path filePath) throws IOException{
		String savePath = filePath.toString();
		savePath = savePath.concat(".encrypted");
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(data);
		out.close();
	}
	
	abstract public byte[] Encrypt(byte [] key, byte[] data) throws IllegalKeyException;
	
}
