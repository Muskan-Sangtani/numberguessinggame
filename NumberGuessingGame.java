import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int targetNumber = random.nextInt(100) + 1; // Generate a random number between 1 and 100
        int numberOfAttempts = 0;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.");
        System.out.println("You have 10 attempts.");

        while (numberOfAttempts < 10) {
            System.out.print("Enter your guess: ");
            int guess = scanner.nextInt();
            numberOfAttempts++;
            
            if (guess < 1 || guess > 100) {
                System.out.println("Please enter a number between 1 and 100.");
                continue;
            }
            
            if (guess < targetNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Congratulations! You've guessed the correct number.");
                System.out.println("Number of attempts: " + numberOfAttempts);
                break;
            }
        }
        
        if (numberOfAttempts == 10) {
            System.out.println("Sorry, you've run out of attempts.");
            System.out.println("The correct number was: " + targetNumber);
        }
        
        scanner.close();
    }
}