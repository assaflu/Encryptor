package Encryptor.Encryptor;

import java.io.IOException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class MainClass 
{
    public static void main( String[] args )
    {
    	Scanner reader = new Scanner (System.in);
    	boolean flag = true;
    	System.out.println("Welcome to the Encryption and Decryption application");
    	while(flag){
            System.out.println("Choose your operation:");
            System.out.println("1.\tEncrypt file");
            System.out.println("2.\tDecrypt file");
            String userInput = reader.nextLine();
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

        reader.close();
        
        
        
    }
}
