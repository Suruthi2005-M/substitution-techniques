import java.util.*;
class Main {
    static char[][] matrix = new char[5][5];
    static void createMatrix(String key) {
        boolean[] used = new boolean[26];
        used['j' - 'a'] = true;  

        key = key.toLowerCase().replaceAll("[^a-z]", "");
        StringBuilder sb = new StringBuilder();

        for (char c : key.toCharArray()) {
            if (!used[c - 'a']) {
                used[c - 'a'] = true;
                sb.append(c);
            }
        }

        for (char c = 'a'; c <= 'z'; c++) {
            if (!used[c - 'a']) sb.append(c);
        }

        for (int i = 0; i < 25; i++) {
            matrix[i / 5][i % 5] = sb.charAt(i);
        }
    }

    static int[] findPos(char c) {
        if (c == 'j') c = 'i';
        for (int r = 0; r < 5; r++)
            for (int col = 0; col < 5; col++)
                if (matrix[r][col] == c)
                    return new int[]{r, col};
        return null;
    }

    static String prepareText(String text) {
        text = text.toLowerCase().replaceAll("[^a-z]", "").replace('j', 'i');
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length()) ? text.charAt(i + 1) : 'x';

            if (first == second) {
                result.append(first).append('x');
            } else {
                result.append(first);
                if (i + 1 < text.length()) {
                    result.append(second);
                    i++;
                } else {
                    result.append('x');
                }
            }
        }
        return result.toString();
    }
    static String encrypt(String text) {
        text = prepareText(text);
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] pos1 = findPos(text.charAt(i));
            int[] pos2 = findPos(text.charAt(i + 1));

            if (pos1[0] == pos2[0]) { // same row
                encrypted.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                encrypted.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) { // same column
                encrypted.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                encrypted.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else { // rectangle
                encrypted.append(matrix[pos1[0]][pos2[1]]);
                encrypted.append(matrix[pos2[0]][pos1[1]]);
            }
        }
 return encrypted.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Keyword: ");
        String key = sc.nextLine();
        System.out.print("Plaintext: ");
        String plaintext = sc.nextLine();
        createMatrix(key);
        String ciphertext = encrypt(plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        sc.close();
    }
}
