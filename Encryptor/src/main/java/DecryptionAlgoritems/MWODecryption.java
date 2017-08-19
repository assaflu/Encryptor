package DecryptionAlgoritems;

import Encryptor.Encryptor.DecryptionClass;
import Exceptions.DecryptionKeyIllegal;
import Exceptions.IllegalKeyException;
import Managing.EncryptionDecryptionManager;

@DecryptionClass(name = "MWO Decryption", serialNumber = 3, numberOfKeys = 1,type = DecryptionType.MWODecryption)
public class MWODecryption extends Decryption{
	
	/*public MWODecryption(){
		super(AlgoritemManaging.instance);
	}*/
	
	public MWODecryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	private static int MWOKeyFinder (byte key){
		for(int i = Byte.MIN_VALUE; i<=Byte.MAX_VALUE;i++){
			if((byte)(key*i)== 1)
				return i;				
		}
		return Byte.MAX_VALUE+1;
	}
	
	@Override
	public byte[] Decrypt(byte [] key, byte[] data) throws IllegalKeyException, DecryptionKeyIllegal {
		if(key[0]%2 == 0){
			throw new IllegalKeyException();
		}
		
		int decryptionKey = MWOKeyFinder(key[0]);
		if(decryptionKey%2 ==0 || decryptionKey ==Byte.MAX_VALUE+1){
			throw new DecryptionKeyIllegal();
		}
		
		byte decryptData [] = new byte[data.length];
		for(int i=0; i< data.length;i++){
			decryptData[i] = (byte)(data[i] * decryptionKey);
		}
		return decryptData;		
	}
}
