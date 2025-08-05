import java.util.Scanner;

public class Main1 {
    public static String cleanText(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                result += Character.toLowerCase(ch);
            }
        }
        return result;
    }

    public static String encrypt(String text, String key) {
        String result = "";
        text = cleanText(text);
        key = cleanText(key);

        for (int i = 0; i < text.length(); i++) {
            char plainChar = text.charAt(i);
            char keyChar = key.charAt(i % key.length());

            int shift = keyChar - 'a';
            char encryptedChar = (char) ((plainChar - 'a' + shift) % 26 + 'a');
            result += encryptedChar;
        }
        return result;
    }

    public static String decrypt(String text, String key) {
        String result = "";
        text = cleanText(text);
        key = cleanText(key);

        for (int i = 0; i < text.length(); i++) {
            char cipherChar = text.charAt(i);
            char keyChar = key.charAt(i % key.length());

            int shift = keyChar - 'a';
            char decryptedChar = (char) ((cipherChar - 'a' - shift + 26) % 26 + 'a');
            result += decryptedChar;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();

        String encrypted = encrypt(plaintext, keyword);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, keyword);
        System.out.println("Decrypted text: " + decrypted);

        scanner.close();
    }
}

