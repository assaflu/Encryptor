package Encryptor.Encryptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import DecryptionAlgoritems.Decryption;
import EncryptionAlgoritems.Encryption;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;


public class AlgoritemManaging {

	protected Map<Integer, String> AlgoritemOptions;
	protected Map<Integer, Class<? extends Encryption>> encryptionMethods;
	protected Map<Integer, Class<? extends Decryption>> decryptionMethods;
	protected int choosenMethod;
	protected WorkingMod mode;
	private long time;
	
	public static final AlgoritemManaging instance = new AlgoritemManaging();
	
	protected AlgoritemManaging(){
		choosenMethod=0;
		AlgoritemOptions = new HashMap<>();
		encryptionMethods = new HashMap<>();
		decryptionMethods = new HashMap<>();
	}
	
	private void endProcess(){
		time = System.currentTimeMillis() - time;
		System.out.println(AlgoritemOptions.get(choosenMethod)+" ended");
		System.out.println("time to execute is "+ time + " millisecond");
	}
	
	private void startProcess(){
		time = System.currentTimeMillis();
		System.out.println(AlgoritemOptions.get(choosenMethod)+" started");
	}
	
	private void printOptions (){
		Iterator<Integer> it = AlgoritemOptions.keySet().iterator();
		while(it.hasNext()){
			Integer entry = it.next();
			System.out.println(entry+". "+AlgoritemOptions.get(entry));
		}
	}
	
	private byte[] loadData (Path filePath) throws IOException{
		return Files.readAllBytes(filePath);
	}
	
	private void saveData (Path filePath, byte[] data) throws IOException{
		String savePath = filePath.toString();
		FileOutputStream out;
		switch (this.mode){
		case ENCRYPTION:
			savePath = savePath.concat(".encrypted");
			out = new FileOutputStream(savePath.toString());
			out.write(data);
			out.close();
			break;
		case DECRYPTION:
			String [] extention = savePath.split("\\.");
			if(extention.length >=3){
				savePath = savePath.concat("_decd."+extention[extention.length-2]);
			}
			else{
				savePath = savePath.concat("_decd."+extention[extention.length-1]);
			}
			out = new FileOutputStream(savePath.toString());
			out.write(data);
			out.close();
		break;
		}
	}
	
	public void SetMode(WorkingMod mode){
		this.mode = mode;
		switch (this.mode){
		case ENCRYPTION:
			Reflections encryptionReflection = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("EncryptionAlgoritems")));
	    	Set<Class<? extends Encryption>> encryptionClasses = encryptionReflection.getSubTypesOf(Encryption.class);
	    	for(Class<? extends Encryption> e : encryptionClasses){
	    		AlgoritemOptions.put(e.getDeclaredAnnotation(EncryptionClass.class).serialNumber(),
	    				e.getDeclaredAnnotation(EncryptionClass.class).name());
	    		encryptionMethods.put(e.getDeclaredAnnotation(EncryptionClass.class).serialNumber(),
	    				e);
	    	}
			break;
		case DECRYPTION:
			Reflections decryptionReflection = new Reflections(new ConfigurationBuilder().setUrls(ClasspathHelper.forPackage("EncryptionAlgoritems")));
	    	Set<Class<? extends Decryption>> decryptionClasses = decryptionReflection.getSubTypesOf(Decryption.class);
	    	for(Class<? extends Decryption> e : decryptionClasses){
	    		AlgoritemOptions.put(e.getDeclaredAnnotation(DecryptionClass.class).serialNumber(),
	    				e.getDeclaredAnnotation(DecryptionClass.class).name());
	    		decryptionMethods.put(e.getDeclaredAnnotation(DecryptionClass.class).serialNumber(),
	    				e);
	    	}
			break;
		default:
			//throw 
		}
		
	}
	
	public void chooseAlgoritem(){
		System.out.println("choose your algoritem:");
		printOptions();
    	@SuppressWarnings("resource")
		Scanner reader = new Scanner (System.in);
    	String userInput = null;
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
    	choosenMethod = Integer.parseInt(userInput);
	}
	
	public int getChosenMethod(){
		return choosenMethod;
	}
	
	public void executeMethod(byte key, Path filePath) throws InstantiationException, IOException, IllegalKeyException, IllegalAccessException, DecryptionKeyIllegal{
		startProcess();
		byte data [] = null;
		switch (this.mode){
		case ENCRYPTION:
			data = encryptionMethods.get(choosenMethod).newInstance().Encrypt(key, loadData(filePath));
			break;
		case DECRYPTION:
			data = decryptionMethods.get(choosenMethod).newInstance().Decrypt(key, loadData(filePath));
			break;
		}
		saveData(filePath, data);
		endProcess();
		choosenMethod=0;
	}
	
	public void executeMethod(Path filePath) throws InstantiationException, IOException, IllegalKeyException, IllegalAccessException{
		startProcess();
		encryptionMethods.get(choosenMethod).newInstance().Encrypt(Encryption.keyGenerate(), loadData(filePath));
		endProcess();
		choosenMethod=0;
	}
}
