package com.lgposse.cards.tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.lgposse.cards.models.CardGame;
import com.lgposse.thirdparty.DesEncrypter;

public class DesEncrypterTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new DesEncrypterTest().run();
	}
	
	public void run() {
		try {
			CardGame g = CardGameTest.exampleCardGame();
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("obj1.txt"));
			o.writeObject(g);
			o.close();
			
		    // Generate a temporary key. In practice, you would save this key.
		    // See also Encrypting with DES Using a Pass Phrase.
		    //SecretKey key = KeyGenerator.getInstance("DES").generateKey();
		    //System.out.println(key.getEncoded().length);
		    //FileOutputStream fos = new FileOutputStream("key.txt");
		    //fos.write(key.getEncoded());
		    
			byte []keybyte = new byte[8];
		    FileInputStream fin = new FileInputStream("key.txt");
		    fin.read(keybyte);
		    SecretKey skey = new SecretKeySpec(keybyte, 0, 16, "DES");

		    // Create encrypter/decrypter class
		    DesEncrypter encrypter = new DesEncrypter(skey);

		    // Encrypt
		    encrypter.encrypt(new FileInputStream("obj1.txt"),
		        new FileOutputStream("ciphertext.txt"));

		    // Decrypt
		    encrypter.decrypt(new FileInputStream("ciphertext.txt"),
		        new FileOutputStream("obj2.txt"));
		} catch (Exception e) {
		}
	}

}
