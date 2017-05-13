package Encryptor.Encryptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlgoritemManaging {

	protected List<String> AlgoritemOptions;
	
	protected AlgoritemManaging(){
		AlgoritemOptions = new ArrayList<>();
	}
	
	public void printOptions (){
		for(String e : AlgoritemOptions)
			System.out.println(e);
	}
	
	public void chooseAlgoritem(){
		System.out.println();
		System.out.println("choose your algoritem:");
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

	public void executeMethod(){
		
	}
}
