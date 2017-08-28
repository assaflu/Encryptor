package Managing;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import DecryptionAlgoritems.Decryption;
import DecryptionAlgoritems.DecryptionFactory;
import DecryptionAlgoritems.DecryptionType;
import EncryptionAlgoritems.Encryption;
import EncryptionAlgoritems.EncryptionFactory;
import EncryptionAlgoritems.EncryptionType;
import Encryptor.Encryptor.DecryptionClass;
import Encryptor.Encryptor.EncryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Encryptor.Encryptor.InputMod;
import Encryptor.Encryptor.WorkingMod;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Reader.InputOutputManager;
import Reader.MyReader;


public class AlgoritemManaging implements EncryptionDecryptionManager {

	protected Map<Integer, String> AlgoritemOptions;
	protected Map<Integer, Class<? extends Encryption>> encryptionMethods;
	protected Map<Integer, Class<? extends Decryption>> decryptionMethods;
	protected int choosenMethod;
	protected WorkingMod mode;
	protected InputMod inputMod;
	private Clock clock;
	private long time;
	private InputOutputManager reader;
	
	public AlgoritemManaging(){
		choosenMethod=0;
		AlgoritemOptions = new HashMap<Integer, String>();
		encryptionMethods = new HashMap<Integer, Class<? extends Encryption>>();
		decryptionMethods = new HashMap<Integer, Class<? extends Decryption>>();
		clock = Clock.systemDefaultZone();
		reader = new MyReader(System.in,System.out);
	}
	
	private void endProcess(){
		time = clock.millis() - time;
		reader.write(AlgoritemOptions.get(choosenMethod)+" ended\n");
		reader.write("time to execute is "+ time + " millisecond\n");
	}
	
	private void startProcess(){
		time = clock.millis();
		reader.write(AlgoritemOptions.get(choosenMethod)+" started\n");
	}
	
	private void printOptions (){
		Iterator<Integer> it = AlgoritemOptions.keySet().iterator();
		while(it.hasNext()){
			Integer entry = it.next();
			reader.write(entry+". "+AlgoritemOptions.get(entry));
			reader.write("\n");
		}
	}
	
	private byte[] keyGenerate(int numberOfKeys){
		Random rand = new Random();
		byte key[] = new byte [numberOfKeys] ;
		rand.nextBytes(key);
		return key;
	}
	
	private byte[] loadData (Path filePath){
		return reader.readFile(filePath);
	}

	public void SetInputStream (InputStream is){
		((MyReader) reader).SetInputStream(is);
	}
	
	public void SetOutputStream (OutputStream os){
		((MyReader) reader).SetOutputStream(os);
	}
	
	@Override
	public void SetInputMode(InputMod mode) {
		this.inputMod = mode;
		
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
		boolean visit = false;
		reader.write("choose your algoritem:\n");
		printOptions();
    	String userInput = null;
    	boolean correctInput = false;
    	while(!correctInput){            	
        	userInput = reader.readString(1);
        	try{
        		correctInput = Integer.parseInt(userInput)<=AlgoritemOptions.size() && Integer.parseInt(userInput)>0;
        	}catch(NumberFormatException e){
        		if(!visit){
        			visit = true;
            		reader.write("choose a number between 1 to "+AlgoritemOptions.size()+"\n");
            		correctInput=false;
        		}
        	}
        	if(!correctInput && !visit) reader.write("index out of range, enter again\n");
        	visit = false;
    	}
    	choosenMethod = Integer.parseInt(userInput);
	}
	
	public int getChosenMethod(){
		return choosenMethod;
	}
	
	public ArrayList<EncryptionType> chooseBasicEncryptionAlgoritem(int numberOfAlgoritems){
		
		ArrayList<EncryptionType> methodsToReturn = new ArrayList<>();
		Iterator<Integer> it = encryptionMethods.keySet().iterator();
		Map<Integer, String> basicAlgoritems = new HashMap<Integer, String>();
		while(it.hasNext()){
			Integer entry = it.next();
			if(encryptionMethods.get(entry).getDeclaredAnnotation(EncryptionClass.class).level() == EncryptionDecryptionLevel.BASIC)
				basicAlgoritems.put(entry, AlgoritemOptions.get(entry));
		}
		
		it = basicAlgoritems.keySet().iterator();
		reader.write("Choose " +numberOfAlgoritems+" from these basic Algoritem:\n");
		while(it.hasNext()){
			Integer entry = it.next();
			reader.write(entry+". "+basicAlgoritems.get(entry)+"\n");
		}
		
		for(int i=0; i<numberOfAlgoritems; i++){
	    	String userInput = null;
	    	boolean correctInput = false;
	    	while(!correctInput){            	
	        	userInput = reader.readString(1);
	        	try{
	        		correctInput = basicAlgoritems.get(Integer.parseInt(userInput)) != null;
	        		if(!correctInput)
	        			reader.write("unmatching index\n");
	        		else
	        			methodsToReturn.add(encryptionMethods.get(Integer.parseInt(userInput)).getDeclaredAnnotation(EncryptionClass.class).type());
	        	}
	        	catch(NumberFormatException e){
	        		reader.write("choose a number between 1 to "+AlgoritemOptions.size()+"\n");
	        		correctInput=false;
	        	}
	    	}
		}
		
		return methodsToReturn;
	}
	
	public ArrayList<DecryptionType> chooseBasicDecryptionAlgoritem (int numberOfAlgoritems){
		ArrayList<DecryptionType> methodsToReturn = new ArrayList<>();
		Iterator<Integer> it = decryptionMethods.keySet().iterator();
		Map<Integer, String> basicAlgoritems = new HashMap<Integer, String>();
		while(it.hasNext()){
			Integer entry = it.next();
			if(decryptionMethods.get(entry).getDeclaredAnnotation(DecryptionClass.class).level() == EncryptionDecryptionLevel.BASIC)
				basicAlgoritems.put(entry, AlgoritemOptions.get(entry));
		}
		
		it = basicAlgoritems.keySet().iterator();
		reader.write("Choose "+numberOfAlgoritems+" from these basic Algoritem:\n");
		while(it.hasNext()){
			Integer entry = it.next();
			reader.write(entry+". "+basicAlgoritems.get(entry)+"\n");
		}
		
		for(int i=0; i<numberOfAlgoritems; i++){
	    	String userInput = null;
	    	boolean correctInput = false;
	    	while(!correctInput){            	
	        	userInput = reader.readString(1);
	        	try{
	        		correctInput = basicAlgoritems.get(Integer.parseInt(userInput)) != null;
	        		if(!correctInput)
	        			reader.write("unmatching index\n");
	        		else
	        			methodsToReturn.add(decryptionMethods.get(Integer.parseInt(userInput)).getDeclaredAnnotation(DecryptionClass.class).type());
	        	}
	        	catch(NumberFormatException e){
	        		reader.write("choose a number between 1 to "+AlgoritemOptions.size()+"\n");
	        		correctInput=false;
	        	}
	    	}
		}
		
		return methodsToReturn;
	}
	
	public void executeMethod(Path filePath) throws InstantiationException, IOException, IllegalKeyException, IllegalAccessException, DecryptionKeyIllegal{
		startProcess();
		byte data [] = loadData(filePath);
		byte returnedData [] = null;
		switch (this.mode){
		case ENCRYPTION:
			returnedData = executeEncryption(data);
			break;
		case DECRYPTION:
			returnedData = executeDecryption(data);
			break;
		}
		saveData(returnedData, filePath);
		endProcess();
		choosenMethod=0;
	}

	
	private byte [] executeEncryption(byte[] data) throws IllegalKeyException, DecryptionKeyIllegal{
		int numberOfKeys = encryptionMethods.get(choosenMethod).getDeclaredAnnotation(EncryptionClass.class).numberOfKeys();
		EncryptionType typeE = encryptionMethods.get(choosenMethod).getDeclaredAnnotation(EncryptionClass.class).type();
		byte[] keys = keyGenerate(numberOfKeys);
		reader.write("The keys are:\n");
		for(byte b : keys){
			reader.write(b);
			reader.write("\n");
		}
		Encryption encryptor = new EncryptionFactory().create(typeE,this);
		byte encryptedData [] = encryptor.Encrypt(keys, data);
		return encryptedData;
	}
	
	private byte [] executeDecryption(byte[] data) throws IllegalKeyException, DecryptionKeyIllegal{
		int numberOfKeys = decryptionMethods.get(choosenMethod).getDeclaredAnnotation(DecryptionClass.class).numberOfKeys();
		DecryptionType typeD = decryptionMethods.get(choosenMethod).getDeclaredAnnotation(DecryptionClass.class).type();
		byte[] keys = new byte [numberOfKeys];
		reader.write("Enter "+numberOfKeys+ " keys:\n");
		for(int i=0; i<numberOfKeys; i++){
			keys[i] = 0;
			byte[] readKey = reader.read(3);
			if(readKey[0] == 45){
				for(int j =1; j<3;j++){
					if(readKey[j]>=48 && readKey[j]<=57)
						keys[i] = (byte) ((-1)*(keys[i]*10+readKey[j]-48));
				}
			}
			else{
				for(int j =0; j<3;j++){
					if(readKey[j]>=48 && readKey[j]<=57)
						keys[i] = (byte) ((keys[i]*10+readKey[j]-48));
				}
			}

		}
		Decryption decryptor = new DecryptionFactory().create(typeD,this);
		byte decryptedData [] = decryptor.Decrypt(keys, data);
		return decryptedData;
	}
	
	@Override
	public void saveData(byte[] data, Path filePath) throws IOException {
		switch(inputMod)
		{
			case File:
				SingleFileSave(data,filePath);
				break;
			case Folder:
				FolderFileSave(data,filePath);
				break;
			default:
					break;
		}
				
		
	}

	private void FolderFileSave(byte[] data, Path filePath) {
		// TODO Auto-generated method stub
		
	}

	private void SingleFileSave(byte[] data, Path filePath) throws IOException {
		switch(mode){
		case ENCRYPTION:
			saveEncryptFile(data,filePath);
			break;
		case DECRYPTION:
			saveDecryptFile(data,filePath);
			break;
		default:
			break;
		}
	}

	private void saveDecryptFile(byte[] data, Path filePath) throws IOException {
		String savePath = filePath.toString();
		String [] extention = savePath.split("\\.");
		if(extention.length >=3){
			savePath = savePath.concat("_decd."+extention[extention.length-2]);
		}
		else{
			savePath = savePath.concat("_decd."+extention[extention.length-1]);
		}
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(data);
		out.close();
		
	}

	private void saveEncryptFile(byte[] data, Path filePath) throws IOException {
		String savePath = filePath.toString();
		savePath = savePath.concat(".encrypted");
		FileOutputStream out = new FileOutputStream(savePath.toString());
		out.write(data);
		out.close();
		
	}

}
