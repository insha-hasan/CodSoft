import java.util.*;

class task1 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n;
        Random random = new Random();
        int ran = random.nextInt(100);
        int flag = 0;
        int round = 0;
        int attempt = 0;
        while (flag == 0 && attempt < 5) {
            System.out.println("Guess a number between 1 to 100");
            n = sc.nextInt();
            if (n > ran) {
                System.out.println("Too high");
                attempt += 1;
            } else if (n < ran) {
                System.out.println("Too low");
                attempt += 1;
            } else {
                System.out.println("Congrats! Your guess is correct");
                flag = 1;
            }
        }
        System.out.println("Attempts over");
    }

}
