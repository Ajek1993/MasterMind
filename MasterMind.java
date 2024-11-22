import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class WrongLongException extends Exception {
    public WrongLongException(String message) {
        super(message);
    }
}

public class MasterMind {

    private static final String MAGENTA = "\033[35m";
    private static final String RED = "\033[31m";
    private static final String GREEN = "\033[32m";
    private static final String BLUE = "\033[34m";
    private static final String RESET = "\033[0m"; // Reset koloru

    public static void main(String[] args) {
        System.out.println(MAGENTA + "Witaj w grze MasterMind!" + RESET);
        System.out.println(MAGENTA + "Zgadnij 4 cyfrowy kod składający się z cyfr od 0 do 9" + RESET);

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxDigit = 10;
        int codeLength = 4;

        int[] secretCode = new int[codeLength];
        int[] userCode = new int[codeLength];

        for (int i = 0; i < codeLength; i++) {
            secretCode[i] = random.nextInt(maxDigit);
        }

        boolean isGuessed = false;
        int shots = 0;

        long startTime = System.currentTimeMillis();

        while (!isGuessed) {
            System.out.print(GREEN + "Wprowadź swoją próbę: " + RESET);

            try {
                String guess = scanner.nextLine().trim();

                if (guess.length() != codeLength) {
                    throw new WrongLongException(RED +
                            "Niepoprawna długość kodu, kod powinien mieć " + codeLength + " cyfry." + RESET);
                }

                for (int i = 0; i < codeLength; i++) {
                    char currentChar = guess.charAt(i);

                    if (Character.isDigit(currentChar)) {
                        userCode[i] = Character.getNumericValue(currentChar);

                    } else {
                        throw new NumberFormatException(
                                RED + "Kod zawiera niepoprawne znaki. Wprowadź tylko cyfry." + RESET);
                    }

                }

                shots++;
                int correctPosition = 0;
                int correctDigit = 0;

                boolean[] usedInSecret = new boolean[codeLength];
                boolean[] usedInGuess = new boolean[codeLength];

                for (int i = 0; i < codeLength; i++) {
                    if (userCode[i] == secretCode[i]) {
                        correctPosition++;
                        usedInGuess[i] = true;
                        usedInSecret[i] = true;
                    }
                }

                for (int i = 0; i < codeLength; i++) {
                    if (!usedInGuess[i]) {
                        for (int j = 0; j < codeLength; j++) {
                            if (!usedInSecret[j] && userCode[i] == secretCode[j]) {
                                correctDigit++;
                                usedInSecret[j] = true;
                                usedInGuess[i] = true;
                                break;
                            }
                        }
                    }
                }

                if (Arrays.equals(userCode, secretCode)) {
                    isGuessed = true;
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    long seconds = duration / 1000;
                    System.out.println(BLUE + "Gratulacje, odgadłeś ukryty kod. Liczba prób: " + shots + RESET);
                    System.out.println(BLUE + "Czas trwania gry: " + seconds + " sekund." + RESET);
                } else {
                    System.out.println(BLUE + "Poprawne cyfry na poprawnej pozycji: " + correctPosition + RESET);
                    System.out.println(BLUE + "Poprawne cyfry na niepoprawnej pozycji: " + correctDigit + RESET);
                }

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            } catch (WrongLongException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(RED + "Nieoczekiwany błąd: " + e.getMessage() + RESET);
            }
        }
        scanner.close();
    }

}