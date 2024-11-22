import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class WrongLongException extends Exception {
    public WrongLongException(String message) {
        super(message);
    }
}

public class MasterMind {
    public static void main(String[] args) {
        System.out.println("Witaj w grze MasterMind!");
        System.out.println("Zgadnij 4 cyfrowy kod składający się z cyfr od 0 do 9");

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxDigit = 10;
        int codeLength = 4;

        int[] secretCode = new int[codeLength];
        int[] userCode = new int[codeLength];

        for (int i = 0; i < codeLength; i++) {
            secretCode[i] = random.nextInt(maxDigit);
        }

        System.out.println("Wygenerowany kod: " + Arrays.toString(secretCode));

        boolean isGuessed = false;

        while (!isGuessed) {
            System.out.println("Wprowadź swoją próbę: ");

            try {
                String guess = scanner.nextLine().trim();

                if (guess.length() != codeLength) {
                    throw new WrongLongException(
                            "Niepoprawna długość kodu, kod powinien mieć " + codeLength + " cyfry.");
                }

                for (int i = 0; i < codeLength; i++) {
                    char currentChar = guess.charAt(i);

                    if (Character.isDigit(currentChar)) {
                        userCode[i] = Character.getNumericValue(currentChar);

                    } else {
                        throw new NumberFormatException("Kod zawiera niepoprawne znaki. Wprowadź tylko cyfry.");
                    }

                }

                if (Arrays.equals(userCode, secretCode)) {
                    isGuessed = true;
                    System.out.println("Gratulacje, odgadłeś ukryty kod.");
                } else {
                    System.out.println("Niepoprawny kod, spróbuj jeszcze raz.");
                }

                System.out.println("Twoja próba: " + Arrays.toString(userCode));

            } catch (WrongLongException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Nieoczekiwany błąd: " + e.getMessage());
            }

        }

        scanner.close();
    }

}