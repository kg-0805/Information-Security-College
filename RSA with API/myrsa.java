import java.security.*;
import javax.crypto.*;
import java.util.Base64;
import java.util.Scanner;
public class myrsa
{
private static final int KEYSIZE = 512;
public static void main(String[] args)
{
Scanner sc = new Scanner(System.in);
KeyPair mykeyPair;
try
{
KeyPairGenerator mypair = KeyPairGenerator.getInstance("RSA");
SecureRandom random = new SecureRandom();
mypair.initialize(KEYSIZE, random);
mykeyPair = mypair.generateKeyPair();

final Cipher cipher = Cipher.getInstance("RSA");
System.out.println("Enter the text to encrypt");
final String plaintext = sc.nextLine();
System.out.println("Original Message ->"+plaintext +"\n");

cipher.init(Cipher.ENCRYPT_MODE, mykeyPair.getPublic());
byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
String chipertext = new String(Base64.getEncoder().encode(encryptedBytes));
System.out.println("Encrypted chipertext -> " + chipertext +"\n");

cipher.init(Cipher.DECRYPT_MODE, mykeyPair.getPrivate());
byte[] ciphertextBytes = Base64.getDecoder().decode(chipertext.getBytes());
byte[] decryptedBytes = cipher.doFinal(ciphertextBytes);
String decryptedString = new String(decryptedBytes);
System.out.println("Decrypted plaintext -> " + decryptedString + "\n");
}
catch (NoSuchAlgorithmException e)
{
System.err.println("Algorithm not supported! " + e.getMessage() + "!");
}
catch (NoSuchPaddingException | InvalidKeyException e)
{
System.err.println("Cipher cannot be created!");
e.printStackTrace();
}
catch (BadPaddingException | IllegalBlockSizeException e)
{
System.err.println("An error occurred during the encryption!");
e.printStackTrace();
}
}
}
