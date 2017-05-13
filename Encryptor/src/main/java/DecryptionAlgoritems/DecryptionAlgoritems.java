package DecryptionAlgoritems;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.AlgorithmMethod;

import EncryptionAlgoritems.EncryptionAlgoritems;
import Encryptor.Encryptor.AlgoritemManaging;

@Retention(RetentionPolicy.RUNTIME)
@interface DecryptionnMethod {
	String name();
	int serialNumber();
}

public class DecryptionAlgoritems extends AlgoritemManaging {
	public final static DecryptionAlgoritems instance = new DecryptionAlgoritems();
	
	private DecryptionAlgoritems(){
		for(Method m : DecryptionAlgoritems.class.getDeclaredMethods()){
			if(m.isAnnotationPresent(DecryptionnMethod.class))
				AlgoritemOptions.put(m.getAnnotation(DecryptionnMethod.class).serialNumber(),
						m.getAnnotation(DecryptionnMethod.class).name());
			ExecutableMethods.put(m.getAnnotation(DecryptionnMethod.class).serialNumber(),m);
		}
	}
	
	@DecryptionnMethod(name = "Caesar Decryption", serialNumber = 1)
	private static void caesarDecryption(int key, String filePath,String extention) throws IOException{
		int loopCounter = 0;
		FileInputStream  fileinputstream =new FileInputStream(filePath);
		byte encryptedFile[] = new byte[(int) fileinputstream.getChannel().size()];
		while (fileinputstream.getChannel().position()<fileinputstream.getChannel().size()){
			int read = fileinputstream.read();
			read = read-key;
			if(read<Byte.MIN_VALUE){
				read=Byte.MAX_VALUE+read-(Byte.MIN_VALUE+1);
			}
			encryptedFile[loopCounter] = (byte) read;
			loopCounter++;
		}
		fileinputstream.close();
		StringBuilder savePath = new StringBuilder(filePath);
		savePath.append("_decd."+extention);
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(encryptedFile);
		out.close();
	}

}