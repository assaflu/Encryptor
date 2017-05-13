package Encryptor.Encryptor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import EncryptionAlgoritems.EncryptionAlgoritems;

public class AlgoritemManaging {

	protected Map<Integer, String> AlgoritemOptions;
	protected Map<Integer, Method> ExecutableMethods;
	protected int choosenMethod;
	
	public static class DecEncAthorization { private DecEncAthorization(){}}
	private static final DecEncAthorization athorization = new DecEncAthorization();
	
	protected AlgoritemManaging(){
		choosenMethod=0;
		AlgoritemOptions = new HashMap<>();
		ExecutableMethods = new HashMap<>();
	}
	
	public void printOptions (){
		Iterator<Integer> it = AlgoritemOptions.keySet().iterator();
		while(it.hasNext()){
			Integer entry = it.next();
			System.out.println(entry+". "+AlgoritemOptions.get(entry));
		}
	}
	
	public void chooseAlgoritem(){
		System.out.println();
		System.out.println("choose your algoritem:");
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
	
	public void executeMethod(int key, String filePath){
		if(this instanceof EncryptionAlgoritems){
			try {
				System.out.println(ExecutableMethods.get(choosenMethod).getParameterCount());
				ExecutableMethods.get(choosenMethod).invoke(null,athorization, key, filePath);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				System.out.println(e.getMessage());
			}
		}
		choosenMethod=0;
	}
}
