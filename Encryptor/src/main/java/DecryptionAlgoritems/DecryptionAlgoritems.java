package DecryptionAlgoritems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
			if(m.isAnnotationPresent(DecryptionnMethod.class)){
				AlgoritemOptions.put(m.getAnnotation(DecryptionnMethod.class).serialNumber(),
						m.getAnnotation(DecryptionnMethod.class).name());
				ExecutableMethods.put(m.getAnnotation(DecryptionnMethod.class).serialNumber(),m);
			}
		}
	}
	
	private static void saveFile (String filePath, byte[] decryptedFile) throws IOException{
		StringBuilder savePath = new StringBuilder(filePath);
		String [] extention = filePath.split("\\.");
		if(extention.length >=3){
			savePath.append("_decd."+extention[extention.length-2]);
		}
		else{
			savePath.append("_decd."+extention[extention.length-1]);
		}
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(decryptedFile);
		out.close();
	}
	
	@DecryptionnMethod(name = "Caesar Decryption", serialNumber = 1)
	public static void caesarDecryption(DecEncAthorization athorization,Integer key, String filePath) throws IOException, NotAllowedException{
		if(athorization == null){
			throw new NotAllowedException();
		}
		int loopCounter = 0;
		FileInputStream  fileinputstream =new FileInputStream(filePath);
		byte decryptedFile[] = new byte[(int) fileinputstream.getChannel().size()];
		while (fileinputstream.getChannel().position()<fileinputstream.getChannel().size()){
			int read = fileinputstream.read();
			read = read-key;
			if(read<Byte.MIN_VALUE){
				read=Byte.MAX_VALUE+read-(Byte.MIN_VALUE+1);
			}
			decryptedFile[loopCounter] = (byte) read;
			loopCounter++;
		}
		fileinputstream.close();
		saveFile(filePath, decryptedFile);
	}

}
