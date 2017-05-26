package DecryptionAlgoritems;

import Encryptor.Encryptor.DecryptionClass;


@DecryptionClass(name = "Caesar Decryption", serialNumber = 1, numberOfKeys = 1)
public class CaesarDecryption extends Decryption {

	@Override
	public byte[] Decrypt(byte [] key, byte [] data){
		
		byte decryptData [] = new byte [data.length];
		for(int i=0; i< data.length;i++){
			decryptData[i] = (byte)(data[i] - key[0]);
		}
		return decryptData;
		
	}
}
