package ankita;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    static ArrayList<Integer> attemptsList = new ArrayList<>();
    
    public static void main(String[] args) {
        GuessTheNumber gameInstance = new GuessTheNumber();
        gameInstance.displayMenu(attemptsList);
    }

    public void displayMenu(ArrayList<Integer> attemptsList) {
        GuessTheNumber gameInstance = new GuessTheNumber();
        Scanner scanner = new Scanner(System.in);
        System.out.println("1) Play Number Guessing Game");
        System.out.println("2) View Scoreboard");
        System.out.println("3) Exit");
        System.out.println("__________________");
        System.out.print("Choose an option: ");
        
        try {
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    System.out.println("Enter the maximum range for the random number: ");
                    int maxRange = scanner.nextInt();
                    int generatedNumber = gameInstance.generateRandomNumber(maxRange);
                    // Uncomment to see the generated number for testing
                    // System.out.println("Generated -> " + generatedNumber);
                    gameInstance.startGuessing(generatedNumber);
                    break;
                case 2:
                    gameInstance.showScoreboard();
                    break;
                case 3:
                    System.out.println("Thank you for playing!");
                    System.exit(0);
                    break;
                default:
                    throw new InputMismatchException("Invalid choice. Please select a valid option.");
            }
        } catch(InputMismatchException e) {
            System.err.println(e.getMessage());
            displayMenu(attemptsList);
        }
        scanner.close();
    }

    public int generateRandomNumber(int maxRange) {
        Random random = new Random();
        return random.nextInt(maxRange) + 1;
    }

    public void startGuessing(int generatedNumber) {
        System.out.println("Guess the number: ");
        Scanner scanner = new Scanner(System.in);
        int userGuess;
        int attempts = 0;
        
        do {
            userGuess = scanner.nextInt();
            if(userGuess < generatedNumber) {
                System.out.println("Guess higher!");
                attempts++;
            } else if(userGuess > generatedNumber) {
                System.out.println("Guess lower!");
                attempts++;
            }
        } while(generatedNumber != userGuess);
        
        System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
        attemptsList.add(attempts);
        displayMenu(attemptsList);
        scanner.close();
    }

    public void showScoreboard() {
        System.out.println("''''''''''''Scoreboard''''''''''''");
        Collections.sort(attemptsList);
        System.out.println("Sorted list of attempts: ");
        for(Integer attempt : attemptsList) {
            System.out.println("Attempts: " + attempt);
        }
        displayMenu(attemptsList);
    }
}
