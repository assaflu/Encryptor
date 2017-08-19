package Encryptor.Encryptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import DecryptionAlgoritems.DecryptionType;

@Retention(RetentionPolicy.RUNTIME)
public @interface DecryptionClass {
	String name();
	int serialNumber();
	int numberOfKeys();
	DecryptionType type();
	EncryptionDecryptionLevel level() default EncryptionDecryptionLevel.BASIC;
}

