import java.util.Scanner;

public class MasterMind {
    public static void main(String[] args) {
        System.out.println("Witaj w grze MasterMind!");
        System.out.println("Zgadnij 4 cyfrowy kod składający się z cyfr od 0 do 9");

        Scanner scanner = new Scanner(System.in);
        
        int quess = 0;
        boolean isGuessed = false;

        while (!isGuessed) {
            System.out.println("Wprowadź swoją próbę: ");

            try {
                quess = scanner.nextInt();
                String digits = Integer.toString(quess);

                if (digits.length() != 4) {
                    System.out.println("Niepoprawna długość kodu, spróbuj jeszcze raz.");
                    scanner.next();
                }

                if (quess == 1993) {
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

        System.out.println("Twoja próba: " + quess);

        scanner.close();
 }
    
}