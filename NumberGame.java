import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        int attempts = 0;
        int userGuess = 0;
        int totalScore = 0;
        int round = 1;

        while (true) {
            System.out.println("Welcome to the Number Guessing Game! Round " + round);
            System.out.println("I'm thinking of a number between 1 and 100.");

            while (userGuess != randomNumber) {
                System.out.print("Enter your guess: ");
                userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number!");
                    break;
                } else if (userGuess > randomNumber) {
                    System.out.println("Your guess is too high. Try again.");
                } else {
                    System.out.println("Your guess is too low. Try again.");
                }

                if (attempts == 5) {
                    System.out.println("You have reached the maximum number of attempts.");
                    break;
                }
            }

            if (userGuess == randomNumber) {
                totalScore += attempts;
                System.out.println("It took you " + attempts + " attempts to guess the correct number.");
                System.out.println("Your total score is " + totalScore + ".");
                round++;
                attempts = 0;
                randomNumber = random.nextInt(100) + 1;
            } else {
                System.out.println("Better luck next time!");
                System.out.println("Your total score is " + totalScore + ".");
                break;
            }

            System.out.println("Do you want to play again? (yes/no)");
            String response = scanner.next();
            if (!response.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }
}

 
