package EncryptionAlgoritems;

import java.util.ArrayList;

import Encryptor.Encryptor.AlgoritemManaging;
import Encryptor.Encryptor.EncryptionClass;
import Encryptor.Encryptor.EncryptionDecryptionLevel;
import Exceptions.IllegalKeyException;

@EncryptionClass(name = "Split Encryption", serialNumber = 6, numberOfKeys = 2, level = EncryptionDecryptionLevel.ADVANCE)
public class SplitEncryption extends Encryption {

	@Override
	public byte[] Encrypt(byte[] key, byte[] data) throws IllegalKeyException {
		ArrayList<Class<? extends Encryption>> methods = 
				AlgoritemManaging.instance.chooseBasicEncryptionAlgoritem(1);
		
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
		byte encEvenData[] = null,encOddData[] = null;
		try {
			encEvenData = methods.get(0).newInstance().Encrypt(key, even);
			encOddData= methods.get(0).newInstance().Encrypt(key, odd);
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
				encData[i] = even[i/2];
			else
				encData[i] = odd[i/2];
		}
		return encData;
	}

}
