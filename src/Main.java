import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("mask.jpg");
        File key = new File("public.key");
        //Encryption.encrypt(file);
        KeyPair kp = new KeyPair(Files.readAllBytes(key.toPath()), Files.readAllBytes(file.toPath()));
        Encryption.decrypt(file, kp);
    }
}