package Encryptor.Encryptor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import EncryptionAlgoritems.EncryptionType;

@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptionClass {
	String name();
	int serialNumber();
	int numberOfKeys();
	EncryptionType type();
	EncryptionDecryptionLevel level() default EncryptionDecryptionLevel.BASIC;
}
