package EncryptionAlgoritems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Encryptor.Encryptor.AlgoritemManaging;


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
	
	@EncryptionMethod(name = "Caesar Encryption",serialNumber=1)
	private static void caesarEncryption(int key, String filePath) throws IOException{
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
		StringBuilder savePath = new StringBuilder(filePath);
		savePath.append(".encrypted");
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(encryptedFile);
		out.close();
	}
}
