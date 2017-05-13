package Encryptor.Encryptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import EncryptionAlgoritems.*;
import DecryptionAlgoritems.*;

import EncryptionAlgoritems.EncryptionAlgoritems;

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
				EncryptionAlgoritems.instance.printOptions();
				EncryptionAlgoritems.instance.chooseAlgoritem();
				flag = false;
				break;
			case "2":
				System.out.println("You choose decryption");
				DecryptionAlgoritems.instance.printOptions();
				DecryptionAlgoritems.instance.chooseAlgoritem();
				flag=false;
				break;
			default:
				System.out.println("Worng Input try again\n");
				break;
			}
            
    	}
    	
    	/*
    	System.out.println("enter file path:");
    	userInput = reader.nextLine();
    	File inputFile = new File(userInput);
    	flag = inputFile.exists() && inputFile.isFile();
    	while(!flag){
    		System.out.println("the path either not leads to a file or not exists.\nenter new path:");
    		userInput = reader.nextLine();
    		inputFile = new File(userInput);
    		flag = inputFile.exists() && inputFile.isFile();
    	} 	
        reader.close();
        int x = GenerateKey(Byte.MAX_VALUE);
  		try {
			EncryptionAlgoritems.caesarEncryption(x, userInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  		//userInput = "C:\\Users\\assaflu\\Desktop\\LocationManagerNotSpart.txt.encrypted";
		try {
			DecryptionAlgoritems.caesarDecryption(x, userInput,"txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");       
    */}
}
