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

@Retention(RetentionPolicy.RUNTIME)
@interface DecryptionnMethod {
	String name();
	int serialNumber();
}

public class DecryptionAlgoritems {
	private List<String> AlgoritemOptions;
	public final static DecryptionAlgoritems instance = new DecryptionAlgoritems();
	
	private DecryptionAlgoritems(){
		AlgoritemOptions = new ArrayList<>();
		for(Method m : EncryptionAlgoritems.class.getMethods()){
			if(m.isAnnotationPresent(DecryptionnMethod.class))
				AlgoritemOptions.add(m.getAnnotation(DecryptionnMethod.class).serialNumber()+". "+
						m.getAnnotation(DecryptionnMethod.class).name());
		}
	}
	
	@DecryptionnMethod(name = "Caesar Decryption", serialNumber = 1)
	public static void caesarDecryption(int key, String filePath,String extention) throws IOException{
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
	
	public void printOptions (){
		for(String e : AlgoritemOptions)
			System.out.println(e);
	}
	
	public void chooseAlgoritem(){
		System.out.println();
		System.out.println("choose your decryption algoritem:");
    	Scanner reader = new Scanner (System.in);
    	String userInput;
    	boolean correctInput = false;
    	while(!correctInput){            	
        	userInput = reader.nextLine();
        	try{
        		correctInput = Integer.parseInt(userInput)<=AlgoritemOptions.size() && Integer.parseInt(userInput)>0;
        		if(!correctInput) System.out.println("index out of range, enter again");
        	}
        	catch(NumberFormatException e){
        		System.out.println("choose a number between 1 to "+AlgoritemOptions.size());
        		correctInput=false;
        	}
    	}
    	reader.close();
	}
}
