package Encryptor.Encryptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptionClass {
	String name();
	int serialNumber();
}
