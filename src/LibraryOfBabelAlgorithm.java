import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class LibraryOfBabelAlgorithm {
    private static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz ,".toCharArray();
    private static final int BOOK_LENGTH = 3200; //Length of the books

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write a random number to generate a random book");
        String pageId = scanner.next();
        String pageContent = generatePageContent(pageId);

        System.out.println("Page ID: " + pageId);
        System.out.println(pageContent);
    }

    private static String generatePageContent(String pageId){
        long seed = generateSeedFromPageId(pageId);
        Random random = new Random(seed);

        StringBuilder pageContent = new StringBuilder(BOOK_LENGTH);
        for(int i = 0; i < BOOK_LENGTH; i++){
            int charIndex = random.nextInt(ALPHABET.length);
            pageContent.append(ALPHABET[charIndex]);
        }

        return pageContent.toString();
    }

    private static long generateSeedFromPageId(String pageId) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Here, the digest() method calculate the hash of pageId and return the value in bytes
            byte[] hashBytes = digest.digest(pageId.getBytes(StandardCharsets.UTF_8));

            //And here we return the first 8 bytes of the final hash
            return new BigInteger(hashBytes).longValue();
        }catch(Exception e){
            System.out.println("Error trying to generate seed: " + e.getMessage());
        }

        return 0;
    }
}
