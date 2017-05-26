package Encryptor.Encryptor;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
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
		AlgoritemOptions = new HashMap<Integer, String>();
		encryptionMethods = new HashMap<Integer, Class<? extends Encryption>>();
		decryptionMethods = new HashMap<Integer, Class<? extends Decryption>>();
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
	
	private byte[] keyGenerate(int numberOfKeys){
		Random rand = new Random();
		byte key[] = new byte [numberOfKeys] ;
		rand.nextBytes(key);
		return key;
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
	
	public ArrayList<Class<? extends Encryption>> chooseBasicEncryptionAlgoritem(int numberOfAlgoritems){
		
		ArrayList<Class<? extends Encryption>> methodsToReturn = new ArrayList<Class<? extends Encryption>>();
		Iterator<Integer> it = encryptionMethods.keySet().iterator();
		Map<Integer, String> basicAlgoritems = new HashMap<Integer, String>();
		while(it.hasNext()){
			Integer entry = it.next();
			if(encryptionMethods.get(entry).getDeclaredAnnotation(EncryptionClass.class).level() == EncryptionDecryptionLevel.BASIC)
				basicAlgoritems.put(entry, AlgoritemOptions.get(entry));
		}
		
		it = basicAlgoritems.keySet().iterator();
		System.out.println("Choose from these basic Algoritem:");
		while(it.hasNext()){
			Integer entry = it.next();
			System.out.println(entry+". "+basicAlgoritems.get(entry));
		}
		
		for(int i=0; i<numberOfAlgoritems; i++){
			@SuppressWarnings("resource")
			Scanner reader = new Scanner (System.in);
	    	String userInput = null;
	    	boolean correctInput = false;
	    	while(!correctInput){            	
	        	userInput = reader.nextLine();
	        	try{
	        		correctInput = basicAlgoritems.get(Integer.parseInt(userInput)) != null;
	        		if(!correctInput)
	        			System.out.println("unmatching index");
	        		else
	        			methodsToReturn.add(encryptionMethods.get(Integer.parseInt(userInput)));
	        	}
	        	catch(NumberFormatException e){
	        		System.out.println("choose a number between 1 to "+AlgoritemOptions.size());
	        		correctInput=false;
	        	}
	    	}
		}
		
		return methodsToReturn;
	}
	
	public void executeMethod(Path filePath) throws InstantiationException, IOException, IllegalKeyException, IllegalAccessException, DecryptionKeyIllegal{
		startProcess();
		byte data [] = null;
		byte[] keys = null;
		int numberOfKeys = 0;
		switch (this.mode){
		case ENCRYPTION:
			numberOfKeys = encryptionMethods.get(choosenMethod).getDeclaredAnnotation(EncryptionClass.class).numberOfKeys();
			keys = keyGenerate(numberOfKeys);
			System.out.println("The keys are:");
			for(byte b : keys){
				System.out.println(b);
			}
			data = encryptionMethods.get(choosenMethod).newInstance().Encrypt(keys, loadData(filePath));
			break;
		case DECRYPTION:
			numberOfKeys = decryptionMethods.get(choosenMethod).getDeclaredAnnotation(DecryptionClass.class).numberOfKeys();
			System.out.println("Enter "+numberOfKeys+ " keys:");
			System.in.read(keys);
			data = decryptionMethods.get(choosenMethod).newInstance().Decrypt(keys, loadData(filePath));
			break;
		}
		saveData(filePath, data);
		endProcess();
		choosenMethod=0;
	}
}
