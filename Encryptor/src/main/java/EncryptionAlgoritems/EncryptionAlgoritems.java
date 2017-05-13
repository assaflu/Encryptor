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


@Retention(RetentionPolicy.RUNTIME)
@interface EncryptionMethod {
	String name();
	int serialNumber();
}

public class EncryptionAlgoritems {

	private List<String> AlgoritemOptions;
	public final static EncryptionAlgoritems instance = new EncryptionAlgoritems();
	
	private EncryptionAlgoritems(){
		AlgoritemOptions = new ArrayList<>();
		for(Method m : EncryptionAlgoritems.class.getMethods()){
			if(m.isAnnotationPresent(EncryptionMethod.class)){
				AlgoritemOptions.add(m.getAnnotation(EncryptionMethod.class).serialNumber()+". "+
					m.getAnnotation(EncryptionMethod.class).name());
			}
		}
	}
	
	@EncryptionMethod(name = "Caesar Encryption",serialNumber=1)
	public static void caesarEncryption(int key, String filePath) throws IOException{
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
	
	public void printOptions (){
		for(String e : AlgoritemOptions)
			System.out.println(e);
	}
	
	public void chooseAlgoritem(){
		System.out.println();
		System.out.println("choose your encryption algoritem:");
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
