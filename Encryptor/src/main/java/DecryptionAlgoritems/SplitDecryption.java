package DecryptionAlgoritems;

import java.util.ArrayList;

import Encryptor.Encryptor.AlgoritemManaging;
import Encryptor.Encryptor.DecryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Encryptor.Encryptor.EncryptionDecryptionManager;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;

@DecryptionClass(name = "Split Decryption", serialNumber = 6, numberOfKeys = 2, level = EncryptionDecryptionLevel.ADVANCE)
public class SplitDecryption extends Decryption{

	public SplitDecryption(){
		super(AlgoritemManaging.instance);
	}
	
	public SplitDecryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Decrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		ArrayList<Class<? extends Decryption>> methods = 
				manager.chooseBasicDecryptionAlgoritem(1);
		byte even [] = new byte [data.length/2];
		byte odd [] = null;
		if(data.length %2 == 0){
			odd = new byte [data.length/2];
		}
		else{
			odd = new byte [data.length/2-1];
		}
		for(int i=0; i < data.length; i++){
			if(i%2 == 0)
				even[i/2] = data[i];
			else
				odd[i/2] = data[i];
		}
		byte encEvenData[] = null;
		byte encOddData[] = null;
		
		try {
			encEvenData = methods.get(0).newInstance().Decrypt(key, even);
			encOddData= methods.get(0).newInstance().Decrypt(key, odd);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte encData[] = new byte[data.length];
		for(int i=0; i < data.length; i++){
			if(i%2 == 0)
				encData[i] = encEvenData[i/2];
			else
				encData[i] = encOddData[i/2];
		}
		return encData;
	}

}
