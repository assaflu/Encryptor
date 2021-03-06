package Encryptor.Encryptor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;


import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

/**
 * Hello world!
 *
 */
public class MainClass 
{
    public static void main( String[] args )
    {
    	Scanner reader = new Scanner(System.in);
    	String userInput = null;
    	boolean flag = true;
    	System.out.println("Welcome to the Encryption and Decryption application");
    	EncryptionDecryptionManager manager = new AlgoritemManaging();
    	
    	SetInputType(reader,manager);
    	SetMode(reader,manager);
    	//AlgoritemManaging.instance.chooseAlgoritem();
    	manager.chooseAlgoritem();
    	System.out.println("enter file path: ");
    	userInput = reader.nextLine();
    	File inputFile = new File(userInput);
    	/*flag = inputFile.exists() && inputFile.isFile();
    	while(!flag){
    		System.out.println("the path either not leads to a file or not exists.\nenter new path:");
    		userInput = reader.nextLine();
    		inputFile = new File(userInput);
    		flag = inputFile.exists() && inputFile.isFile();
    	} 	*/
        //userInput = "C:\Users\assaflu\Desktop\LocationManagerNotSpart.txt";
        //C:\Users\assaflu\Desktop\ReviewManagerNotSpart.txt
        try {
			manager.executeMethod(Paths.get(userInput));
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
    
    public static void SetInputType(Scanner reader, EncryptionDecryptionManager manager){
    	boolean flag = true;
    	String userInput;
    	while(flag){
            System.out.println("Choose your input plan:");
            System.out.println("1.\tFile");
            System.out.println("2.\tFolder");
            userInput = reader.nextLine();
            if(userInput.length() != 1 ){
            	System.out.println("You should enter a number 1 for Filer and 2 for Folder\n");
            	continue;
            }
            switch (userInput) {
			case "1":
				System.out.println("You choose File");
				manager.SetInputMode(InputMod.File);
				flag = false;
				break;
			case "2":
				System.out.println("You choose Folder");
				manager.SetInputMode(InputMod.Folder);
				SyncAsync(reader, manager);
				flag=false;
				break;
			default:
				System.out.println("Worng input try again\n");
				break;
			}
            
    	}
    }
    
    public static void SyncAsync(Scanner reader, EncryptionDecryptionManager manager){
       	boolean flag = true;
    	String userInput;
    	while(flag){
            System.out.println("work sync or async:");
            System.out.println("1.\tSync");
            System.out.println("2.\tAsync");
            userInput = reader.nextLine();
            if(userInput.length() != 1 ){
            	System.out.println("You should enter a number 1 for Sync and 2 for Async\n");
            	continue;
            }
            switch (userInput) {
			case "1":
				System.out.println("You choose Sync");
				manager.SetParallel(false);
				flag = false;
				break;
			case "2":
				System.out.println("You choose Async");
				manager.SetParallel(true);
				flag=false;
				break;
			default:
				System.out.println("Worng input try again\n");
				break;
			}
            
    	}
    }
    
    public static void SetMode(Scanner reader, EncryptionDecryptionManager manager){
    	boolean flag = true;
    	String userInput;
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
				manager.SetMode(WorkingMod.ENCRYPTION);
				flag = false;
				break;
			case "2":
				System.out.println("You choose decryption");
				manager.SetMode(WorkingMod.DECRYPTION);
				flag=false;
				break;
			default:
				System.out.println("Worng Input try again\n");
				break;
			}
            
    	}
    }
}
