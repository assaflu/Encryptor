package Encryptor.Encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.AlgoritemManaging;

/**
 * Hello world!
 *
 */
public class MainClass 
{
    public static void main( String[] args )
    {
    	Scanner reader = new Scanner (System.in);
    	String userInput;
    	boolean flag = true;
    	System.out.println("Welcome to the Encryption and Decryption application");
    	while(flag){
            System.out.println("Choose your operation:");
            System.out.println("1.\tEncrypt file");
            System.out.println("2.\tDecrypt file");
            userInput = reader.nextLine();
            if(userInput.length() != 1 ){
            	System.out.println("You should enter a number 1 for Encryption and 2 for Decryption\n");
            	continue;
            }
            switch (userInput) {
			case "1":
				System.out.println("You choose encryption");
				AlgoritemManaging.instance.SetMode(WorkingMod.ENCRYPTION);
				flag = false;
				break;
			case "2":
				System.out.println("You choose decryption");
				AlgoritemManaging.instance.SetMode(WorkingMod.DECRYPTION);
				flag=false;
				break;
			default:
				System.out.println("Worng Input try again\n");
				break;
			}
            
    	}
    	AlgoritemManaging.instance.chooseAlgoritem();
    	System.out.println("enter file path: ");
    	userInput = reader.nextLine();
    	File inputFile = new File(userInput);
    	flag = inputFile.exists() && inputFile.isFile();
    	while(!flag){
    		System.out.println("the path either not leads to a file or not exists.\nenter new path:");
    		userInput = reader.nextLine();
    		inputFile = new File(userInput);
    		flag = inputFile.exists() && inputFile.isFile();
    	} 	
        //userInput = "C:\Users\assaflu\Desktop\LocationManagerNotSpart.txt";
        //C:\Users\assaflu\Desktop\ReviewManagerNotSpart.txt
        try {
			AlgoritemManaging.instance.executeMethod(Paths.get(userInput));
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DecryptionKeyIllegal e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");  
		reader.close();
    }
}
