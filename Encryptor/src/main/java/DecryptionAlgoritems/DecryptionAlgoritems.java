package DecryptionAlgoritems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

import javax.print.DocFlavor.BYTE_ARRAY;

import Encryptor.Encryptor.AlgoritemManaging;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Exceptions.NotAllowedException;

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
	
	private static int MWOKeyFinder (Integer key){
		for(int i = Byte.MAX_VALUE; i<=Byte.MAX_VALUE;i++){
			if(new Integer((key.intValue()*i)).byteValue() == 1)
				return i;
		}
		return -1;
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
	
	@DecryptionnMethod(name = "XOR Decryption", serialNumber = 2)
	public static void xorDecryption(DecEncAthorization athorization,Integer key, String filePath) throws IOException, NotAllowedException{
		if(athorization == null){
			throw new NotAllowedException();
		}
		
		int loopCounter = 0;
		FileInputStream  fileinputstream =new FileInputStream(filePath);
		byte decryptedFile[] = new byte[(int) fileinputstream.getChannel().size()];
		
		while (fileinputstream.getChannel().position()<fileinputstream.getChannel().size()){
			int read = fileinputstream.read();
			decryptedFile[loopCounter] = (byte) (read ^ key);
			loopCounter++;
		}
		fileinputstream.close();
		saveFile(filePath, decryptedFile);
	}

	@DecryptionnMethod(name = "MWO Decryption", serialNumber = 3)
	public static void multiplicationAlgorittemDecryption(DecEncAthorization athorization,Integer key, String filePath) throws NotAllowedException, IOException, IllegalKeyException, DecryptionKeyIllegal{
		if(athorization == null){
			throw new NotAllowedException();
		}
		if(key.intValue()%2 == 0){
			throw new IllegalKeyException();
		}
		
		int decryptionKey = MWOKeyFinder(key);
		if(decryptionKey%2 ==0 || decryptionKey ==-1){
			throw new DecryptionKeyIllegal();
		}
		int loopCounter = 0;
		FileInputStream  fileinputstream =new FileInputStream(filePath);
		byte decryptedFile[] = new byte[(int) fileinputstream.getChannel().size()];
		Integer read = new Integer(0);
		
		while (fileinputstream.getChannel().position()<fileinputstream.getChannel().size()){
			read = fileinputstream.read();
			read = read*decryptionKey;
			decryptedFile[loopCounter] = read.byteValue();
			loopCounter++;
		}
		fileinputstream.close();
		saveFile(filePath, decryptedFile);
	}

	
}
