/*
    Eric Hoerdemann
    CPSC 1060: RPG 
    5/04/2023
*/

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Game {
private World world;
private Scanner scanner;
private int playerHealth;

public Game() {
    world = new World();
    scanner = new Scanner(System.in);
    playerHealth = 100;
}

public void play() {
    System.out.println("Welcome to Eric's DoomsDay Zombie Apocalypse Game.");
    System.out.println("\nShall we begin?");
    boolean bregs = false;
    while (!bregs) {
        String AnswerChoice = scanner.nextLine();
        if (AnswerChoice.equalsIgnoreCase("Yes")) {
            System.out.println("\nYou're one of the brave ones... this is not for the faint of heart.");
            System.out.println("");
            bregs = true;
        } else if (AnswerChoice.equalsIgnoreCase("No")) {
            System.out.println("\nWell, well, well... looks like someone is too scared. Goodbye coward");
            System.exit(0);
        } else {
            System.out.println("Please input yes or no.");
        }
    }

    Room currentRoom = world.getStartingRoom();
    while (true) {
        world.describeLocation(currentRoom.getName());

        String input = scanner.nextLine();

        if (input.equals("quit")) {
            saveGame(currentRoom.getName());
            System.out.println("Thanks for playing! Your progress has been saved.");
            break;
        } else if (world.isValidDirection(input)) {
            currentRoom = currentRoom.getExit(input);
            int damagePoints = (int) (Math.random() * 50) + 1; // random damage between 1 and 50
            playerHealth -= damagePoints;
            System.out.println("The zombie attacked you and inflicted " + damagePoints + " damage points.");
            if (playerHealth >= 0) {
                System.out.println("Your current health is " + playerHealth);
            }
            if (playerHealth < 0) {
                System.out.println("Your current health is 0");
                System.out.println("You have died. Game over.");
                System.exit(0);
            }
            if (isFinalRoom(currentRoom) && playerHealth > 0) {
                System.out.println("Congratulations, you have won the game!");
                System.out.println("  /\\");
                System.out.println(" /  \\");
                System.out.println("/____\\");
                System.out.println("|    |");
                System.out.println("|    |");
                System.out.println("|    |");
                System.out.println("|____|");
                System.out.println("  ||");
                System.out.println("  ||");
                System.out.println("  ||");
                System.out.println("  ||");
                System.out.println("  ||");
                System.out.println("  ||");
                System.out.println(" /  \\");
                System.out.println("/    \\");
                System.out.println("| #1 |");
                System.out.println("|    |");
                System.out.println("|____|");
                System.out.println();
                saveGame(currentRoom.getName());
                break;
            }
        } else {
            System.out.println("Invalid command.");
        }
    }
}

private boolean isFinalRoom(Room room) {
    return room.getName().equals(world.getFinalRoom().getName());
}

private void saveGame(String currentRoomName) {
    try (PrintWriter writer = new PrintWriter(new FileWriter("saved_game.txt"))) {
        writer.println(currentRoomName);
        writer.println(playerHealth);
    } catch (IOException e) {
        // handle the exception here
        e.printStackTrace();
    }
}

public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}