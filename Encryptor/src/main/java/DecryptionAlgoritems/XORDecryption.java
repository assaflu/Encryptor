package DecryptionAlgoritems;

import Encryptor.Encryptor.DecryptionClass;
import Managing.AlgoritemManaging;
import Managing.EncryptionDecryptionManager;

@DecryptionClass(name = "XOR Decryption", serialNumber = 2, numberOfKeys = 1)
public class XORDecryption extends Decryption{

	public XORDecryption(){
		super(AlgoritemManaging.instance);
	}
	
	public XORDecryption(EncryptionDecryptionManager manager) {
		super(manager);
		// TODO Auto-generated constructor stub
	}

	@Override
	public byte[] Decrypt(byte [] key, byte[] data){
		
		byte decryptData [] = new byte [data.length];
		for(int i=0; i< data.length;i++){
			decryptData[i] = (byte)(data[i] ^ key[0]);
		}
		return decryptData;
	}

}
