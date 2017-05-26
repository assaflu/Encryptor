package DecryptionAlgoritems;

import Encryptor.Encryptor.DecryptionClass;

@DecryptionClass(name = "XOR Decryption", serialNumber = 2)
public class XORDecryption extends Decryption{

	@Override
	public byte[] Decrypt(byte key, byte[] data){
		
		byte decryptData [] = new byte [data.length];
		for(int i=0; i< data.length;i++){
			decryptData[i] = (byte)(data[i] ^ key);
		}
		return decryptData;
	}

}
