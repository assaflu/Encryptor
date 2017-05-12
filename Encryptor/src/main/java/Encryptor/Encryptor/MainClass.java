package Encryptor.Encryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class MainClass 
{
	private static int GenerateKey (int upperLimit){
		Random rand = new Random();
		int returnMe = rand.nextInt(upperLimit);
		System.out.println(returnMe);
		return returnMe;
	}
	
	private static void CaesarDecryption(int Key, String filePath){
		
	}
		
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
				flag = false;
				break;
			case "2":
				System.out.println("You choose decryption");
				flag=false;
				break;
			default:
				System.out.println("Worng Input try again\n");
				break;
			}
            
    	}
    	System.out.println("enter file path:");
    	userInput = reader.nextLine();
    	File inputFile = new File(userInput);
    	flag = inputFile.exists() && inputFile.isFile();
    	while(!flag){
    		System.out.println("the path either not leads to a file or not exists.\nenter new path:");
    		userInput = reader.nextLine();
    		inputFile = new File(userInput);
    		//System.out.println(inputFile.exists()+ " "+  inputFile.isFile());
    		flag = inputFile.exists() && inputFile.isFile();
    	} 	
        reader.close();
        
        try {
			EncryptionAlgoritems.EncryptionAlgoritems.CaesarEncryption(GenerateKey(Byte.MAX_VALUE), userInput);
			System.out.println("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
    }
}
