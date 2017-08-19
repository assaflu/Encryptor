package DecryptionAlgoritems;

import java.util.ArrayList;

import Encryptor.Encryptor.DecryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

@DecryptionClass(name = "Split Decryption", serialNumber = 6, numberOfKeys = 2,type = DecryptionType.SplitDecryption, level = EncryptionDecryptionLevel.ADVANCE)
public class SplitDecryption extends Decryption{

	/*public SplitDecryption(){
		super(AlgoritemManaging.instance);
	}*/
	
	public SplitDecryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Decrypt(byte[] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		ArrayList<DecryptionType> methods = 
				manager.chooseBasicDecryptionAlgoritem(1);
		Decryption decryptor = new DecryptionFactory().create(methods.get(0), manager);
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
		byte firstKey [] = {key[0]};
		byte secondKey [] = {key[1]};
		encEvenData = decryptor.Decrypt(firstKey, even);
		encOddData= decryptor.Decrypt(secondKey, odd);
		
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
