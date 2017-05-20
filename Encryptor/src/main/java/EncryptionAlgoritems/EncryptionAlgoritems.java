package EncryptionAlgoritems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import Encryptor.Encryptor.AlgoritemManaging;
import Exceptions.IllegalKeyException;
import Exceptions.NotAllowedException;


@Retention(RetentionPolicy.RUNTIME)
@interface EncryptionMethod {
	String name();
	int serialNumber();
}

public class EncryptionAlgoritems extends AlgoritemManaging {
	public final static EncryptionAlgoritems instance = new EncryptionAlgoritems();
	
	private EncryptionAlgoritems(){
		for(Method m : EncryptionAlgoritems.class.getDeclaredMethods()){
			if(m.isAnnotationPresent(EncryptionMethod.class)){
				AlgoritemOptions.put(m.getAnnotation(EncryptionMethod.class).serialNumber(),
						m.getAnnotation(EncryptionMethod.class).name());
				ExecutableMethods.put(m.getAnnotation(EncryptionMethod.class).serialNumber(),m);
			}
		}
	}
	private static void saveNewFile(String filePath, byte [] encryptedFile) throws IOException{
		StringBuilder savePath = new StringBuilder(filePath);
		savePath.append(".encrypted");
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(encryptedFile);
		out.close();
	}
	
	@EncryptionMethod(name = "Caesar Encryption",serialNumber=1)
	public static void caesarEncryption(DecEncAthorization athorization,Integer key, String filePath) throws IOException, NotAllowedException{
		if(athorization == null){
			throw new NotAllowedException();
		}
		
		int loopCounter = 0;
		FileInputStream  fileinputstream =new FileInputStream(filePath);
		byte encryptedFile[] = new byte[(int) fileinputstream.getChannel().size()];
		
		while (fileinputstream.getChannel().position()<fileinputstream.getChannel().size()){
			int read = fileinputstream.read();
			read = read+key;
			if(read>Byte.MAX_VALUE){
				read=Byte.MIN_VALUE+read-(Byte.MAX_VALUE+1);
			}
			encryptedFile[loopCounter] = (byte) read;
			loopCounter++;
		}
		fileinputstream.close();
		saveNewFile(filePath, encryptedFile);
	}

	@EncryptionMethod(name = "XOR Encryption",serialNumber=2)
	public static void xorEncryption (DecEncAthorization athorization,Integer key, String filePath) throws NotAllowedException, IOException{
		if(athorization == null){
			throw new NotAllowedException();
		}
		int loopCounter = 0;
		FileInputStream  fileinputstream =new FileInputStream(filePath);
		byte encryptedFile[] = new byte[(int) fileinputstream.getChannel().size()];
		while (fileinputstream.getChannel().position()<fileinputstream.getChannel().size()){
			int read = fileinputstream.read();
			encryptedFile[loopCounter] = (byte) (read ^ key);
			loopCounter++;
		}
		fileinputstream.close();
		saveNewFile(filePath, encryptedFile);
	}

	@EncryptionMethod(name = "MWO Encryption",serialNumber=3)
	public static void multiplicationAlgoritemEncryption(DecEncAthorization athorization,Integer key, String filePath) throws NotAllowedException, IllegalKeyException, IOException{
		if(athorization == null){
			throw new NotAllowedException();
		}
		if(key %2 == 0){
			throw new IllegalKeyException();
		}
		
		int loopCounter = 0;
		FileInputStream  fileinputstream =new FileInputStream(filePath);
		byte encryptedFile[] = new byte[(int) fileinputstream.getChannel().size()];
		Integer read = new Integer(0);
		
		while (fileinputstream.getChannel().position()<fileinputstream.getChannel().size()){
			read = fileinputstream.read();
			read = read*key;
			encryptedFile[loopCounter] = read.byteValue();
			loopCounter++;
		}
		fileinputstream.close();
		saveNewFile(filePath, encryptedFile);
	}
}
