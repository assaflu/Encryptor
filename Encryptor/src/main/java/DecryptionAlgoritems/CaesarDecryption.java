package DecryptionAlgoritems;

import Encryptor.Encryptor.DecryptionClass;
import Managing.EncryptionDecryptionManager;


@DecryptionClass(name = "Caesar Decryption", serialNumber = 1, numberOfKeys = 1, type = DecryptionType.CaesarDecryption)
public class CaesarDecryption extends Decryption {

	/*public CaesarDecryption(){
		super(AlgoritemManaging.instance);
	}*/
	
	public CaesarDecryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Decrypt(byte [] key, byte [] data){
		
		byte decryptData [] = new byte [data.length];
		for(int i=0; i< data.length;i++){
			decryptData[i] = (byte)(data[i] - key[0]);
		}
		return decryptData;
		
	}
}
