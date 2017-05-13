package DecryptionAlgoritems;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import Encryptor.Encryptor.AlgoritemManaging;
import Encryptor.Encryptor.NotAllowedException;

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
	public static void caesarDecryption(DecEncAthorization athorization,int key, String filePath) throws IOException, NotAllowedException{
		if(athorization == null){
			throw new NotAllowedException();
		}
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
		//savePath.append("_decd."+extention);
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(encryptedFile);
		out.close();
	}

}
