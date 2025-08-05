import java.util.Scanner;
public class Main {
    public static String encrypt(String text, int shift) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (Character.isLetter(character)) {
                character = Character.toLowerCase(character);
                char shifted = (char) ((character - 'a' + shift) % 26 + 'a');
                result = result + shifted;
            }
        }
        return result;
    }
    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String inputText = scanner.nextLine();

        System.out.print("Enter shift (key): ");
        int shift = scanner.nextInt();

        String encrypted = encrypt(inputText, shift);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, shift);
        System.out.println("Decrypted text: " + decrypted);

        scanner.close();
    }
}}}

