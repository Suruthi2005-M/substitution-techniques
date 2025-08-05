import java.util.*;

public class NumericColumnarCipher {

        public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.replaceAll("\\s", "").toUpperCase();
        int columns = key.length();
        int rows = (int) Math.ceil((double) plaintext.length() / columns);

        int totalLen = rows * columns;
        while (plaintext.length() < totalLen) {
            plaintext += "X";
        }

        char[][] matrix = new char[rows][columns];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = plaintext.charAt(index++);
            }
        }

        StringBuilder cipherText = new StringBuilder();
        for (int k = 1; k <= columns; k++) {
            int col = key.indexOf(Integer.toString(k));
            for (int i = 0; i < rows; i++) {
                cipherText.append(matrix[i][col]);
            }
        }

        return cipherText.toString();
    }

        public static String decrypt(String cipherText, String key) {
        int columns = key.length();
        int rows = cipherText.length() / columns;

        char[][] matrix = new char[rows][columns];
        int index = 0;

               for (int k = 1; k <= columns; k++) {
            int col = key.indexOf(Integer.toString(k));
            for (int i = 0; i < rows; i++) {
                matrix[i][col] = cipherText.charAt(index++);
            }
        }

         StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                plainText.append(matrix[i][j]);
            }
        }

        return plainText.toString().replaceAll("X+$", "");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter numeric key (e.g., 431256): ");
        String key = sc.nextLine();

        String encrypted = encrypt(plaintext, key);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted text: " + decrypted);
    }
}
