package Managing;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import DecryptionAlgoritems.Decryption;
import EncryptionAlgoritems.Encryption;
import Encryptor.Encryptor.InputMod;
import Encryptor.Encryptor.WorkingMod;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

public interface EncryptionDecryptionManager {
	
	public void SetMode(WorkingMod mode);
	
	public void SetInputMode(InputMod mode);
	
	public void chooseAlgoritem();
	
	public int getChosenMethod();
	
	public ArrayList<Class<? extends Encryption>> chooseBasicEncryptionAlgoritem(int numberOfAlgoritems);
	
	public ArrayList<Class<? extends Decryption>> chooseBasicDecryptionAlgoritem (int numberOfAlgoritems);
	
	public void executeMethod(Path filePath) throws InstantiationException, IOException, IllegalKeyException, IllegalAccessException, DecryptionKeyIllegal;
}
