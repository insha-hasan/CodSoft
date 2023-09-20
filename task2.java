import java.util.Scanner;

public class task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a text");
        String s = sc.nextLine();
        String[] words = s.split("[ ,]+");
        int totalWords = 0;
        int uniqueWords = 0;

        for (int i = 0; i < words.length; i++) {
            if (!words[i].isEmpty()) {
                totalWords++;
                boolean isUnique = true;

                for (int j = 0; j < i; j++) {
                    if (words[i].equalsIgnoreCase(words[j])) {
                        isUnique = false;
                        break;
                    }
                }

                if (isUnique) {
                    uniqueWords++;
                }
            }
        }

        System.out.println("Total Words = " + totalWords);
        System.out.println("Unique Words = " + (uniqueWords));
    }
}
