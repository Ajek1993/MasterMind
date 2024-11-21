import java.util.Random;
import java.util.Scanner;

public class MasterMind {
    public static void main(String[] args) {
        System.out.println("Witaj w grze MasterMind!");
        System.out.println("Zgadnij 4 cyfrowy kod składający się z cyfr od 0 do 9");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxDigit = 10;
        int codeLength = 4;
        StringBuilder secretCode = new StringBuilder();

        for (int i = 0; i < codeLength; i++) {
            secretCode.append(random.nextInt(maxDigit));
        }
        
        System.out.println("Wygenerowany kod: " + secretCode);

        int guess = 0;
        boolean isGuessed = false;

        while (!isGuessed) {
            System.out.println("Wprowadź swoją próbę: ");

            try {
                guess = scanner.nextInt();
                String guessStr = Integer.toString(guess);

                if (guessStr.length() != codeLength) {
                    System.out.println("Niepoprawna długość kodu, spróbuj jeszcze raz.");
                    scanner.next();
                }

                if (guessStr.equals(secretCode.toString())) {
                    isGuessed = true;
                    System.out.println("Gratulacje, odgadłeś ukryty kod.");
                } else {
                    System.out.println("Niepoprawny kod, spróbuj jeszcze raz.");
                }
            } catch (Exception e) {
                System.out.println("Proszę wprowadzić liczbę.");
                scanner.next();
            }
        }

        System.out.println("Twoja próba: " + guess);

        scanner.close();
 }
    
}