package EncryptionAlgoritems;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

import Exceptions.IllegalKeyException;

public abstract class Encryption{

	public static byte keyGenerate(){
		Random rand = new Random();
		byte key[] = new byte [1] ;
		rand.nextBytes(key);
		System.out.println("key is: "+key[0]);
		return key[0];
	}
	
	protected void saveFile (byte[] data,Path filePath) throws IOException{
		String savePath = filePath.toString();
		savePath = savePath.concat(".encrypted");
		System.out.println(savePath);
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(data);
		out.close();
	}
	
	abstract public void Encrypt(byte key, Path filePath) throws IOException, IllegalKeyException;
	
}
