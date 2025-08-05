import java.util.Scanner;

public class RailFenceCipher {

       public static String encrypt(String text, int rails) {
        if (rails <= 1) return text;

        StringBuilder[] rail = new StringBuilder[rails];
        for (int i = 0; i < rails; i++) rail[i] = new StringBuilder();

        int row = 0;
        boolean down = false;

        for (char ch : text.toCharArray()) {
            rail[row].append(ch);

            // Change direction if on top or bottom rail
            if (row == 0 || row == rails - 1) down = !down;

            row += down ? 1 : -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder r : rail) result.append(r);
        return result.toString();
    }

    public static String decrypt(String cipher, int rails) {
        if (rails <= 1) return cipher;

        boolean[][] mark = new boolean[rails][cipher.length()];
        int row = 0;
        boolean down = false;

        for (int col = 0; col < cipher.length(); col++) {
            mark[row][col] = true;

            if (row == 0 || row == rails - 1) down = !down;
            row += down ? 1 : -1;
        }

        char[][] matrix = new char[rails][cipher.length()];
        int index = 0;
        for (int i = 0; i < rails; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (mark[i][j] && index < cipher.length()) {
                    matrix[i][j] = cipher.charAt(index++);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        row = 0;
        down = false;

        for (int col = 0; col < cipher.length(); col++) {
            result.append(matrix[row][col]);
            if (row == 0 || row == rails - 1) down = !down;
            row += down ? 1 : -1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plain text: ");
        String plainText = sc.nextLine(); // Preserve spaces and case

        System.out.print("Enter number of rails: ");
        int rails = sc.nextInt();

        String encrypted = encrypt(plainText, rails);
        System.out.println("Encrypted Text: " + encrypted);

        String decrypted = decrypt(encrypted, rails);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
