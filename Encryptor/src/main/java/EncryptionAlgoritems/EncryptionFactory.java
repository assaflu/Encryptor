package EncryptionAlgoritems;

import Managing.EncryptionDecryptionManager;

public class EncryptionFactory {
	
	public Encryption create(EncryptionType type, EncryptionDecryptionManager manager){
		switch(type){
		case CaesarEncryption:
			return new CaesarEncryption(manager);
		case DoubleEncryption:
			return new DoubleEncryption(manager);
		case MWOEncryption:
			return new MWOEncryption(manager);
		case ReverseEncryption:
			return new ReverseEncryption(manager);
		case SplitEncryption:
			return new SplitEncryption(manager);
		case XOREncryption:
			return new XOREncryption(manager);
		default:
				return null;
		}
	}

}
