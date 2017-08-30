package DecryptionAlgoritems;

import Managing.EncryptionDecryptionManager;

public class DecryptionFactory {

	public Decryption create(DecryptionType type, EncryptionDecryptionManager manager){
		switch(type){
		case CaesarDecryption:
			return new CaesarDecryption(manager);
		case DoubleDecryption:
			return new DoubleDecryption(manager);
		case MWODecryption:
			return new MWODecryption(manager);
		case ReverseDecryption:
			return new ReverseDecryption(manager);
		case SplitDecryption:
			return new SplitDecryption(manager);
		case XORDecryption:
			return new XORDecryption(manager);
		default:
			return null;
		}
	}
}
