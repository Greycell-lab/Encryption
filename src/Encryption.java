import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class Encryption {
    private static byte[] randomKey(int length){
        byte[] dummy = new byte[length];
        Random random = new Random();
        random.nextBytes(dummy);
        return dummy;
    }
    public static void encrypt(File file){
        byte[] originalBytes = null;
        try{
            originalBytes = Files.readAllBytes(file.toPath());
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        byte[] dummyKey = randomKey(originalBytes.length);
        byte[] encryptedKey = new byte[originalBytes.length];
        for(int i=0;i<originalBytes.length;i++){
            encryptedKey[i] = (byte) (originalBytes[i] ^ dummyKey[i]);
        }
        try(FileOutputStream fos = new FileOutputStream("public.key")){
            fos.write(dummyKey);
        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        try(FileOutputStream fos = new FileOutputStream(file)){
            fos.write(encryptedKey);
        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Encryption finished");
        System.out.println("File encrypted: " + file.getName());
    }
    public static void decrypt(File file, KeyPair kp){
        byte[] decrypted = new byte[kp.key1.length];
        for(int i=0;i<kp.key1.length;i++){
            decrypted[i] = (byte) (kp.key1[i] ^ kp.key2[i]);
        }
        try(FileOutputStream fos = new FileOutputStream(file)){
            fos.write(decrypted);
        }catch(FileNotFoundException e){
            System.err.println(e.getMessage());
        }catch(IOException e){
            System.err.println(e.getMessage());
        }
        System.out.println("Decryption finished");
        System.out.println("File decrypted: " + file.getName());
    }
}
