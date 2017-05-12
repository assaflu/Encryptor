package EncryptionAlgoritems;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EncryptionAlgoritems {

	private List<String> AlgoritemOptions;
	public final static EncryptionAlgoritems instance = new EncryptionAlgoritems();
	
	private EncryptionAlgoritems(){
		AlgoritemOptions = new ArrayList<>();
		for(Method m : EncryptionAlgoritems.class.getMethods()){
			if(m.getName() != "printOptions")
				AlgoritemOptions.add(m.getName());
		}
	}
	
	public static void CaesarEncryption(int key, String filePath) throws IOException{
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
	
	public static int printOptions (int startingNumber){
		return startingNumber;
		
	}
}
