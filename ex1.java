public class ModifiedHillCipher {
    public static void main(String[] args) throws java.io.IOException {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        int[][] key = {
            {3, 25},
            {24, 17}
        };

        System.out.print("Enter the plaintext (letters only): ");
        String input = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        if (input.length() % 2 != 0) {
            input += "X";
        }

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < input.length(); i += 2) {
            int a = input.charAt(i) - 'A';
            int b = input.charAt(i + 1) - 'A';

            int e1 = (key[0][0] * a + key[0][1] * b) % 26;
            int e2 = (key[1][0] * a + key[1][1] * b) % 26;

            encryptedText.append((char) (e1 + 'A'));
            encryptedText.append((char) (e2 + 'A'));
        }

        System.out.println("Encrypted Text: " + encryptedText);

        scanner.close();
    }
}
