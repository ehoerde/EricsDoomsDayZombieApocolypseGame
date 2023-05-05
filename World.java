/*
    Eric Hoerdemann
    CPSC 1060: RPG 
    5/04/2023
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class World {
    private HashMap<String, Room> rooms;
    private HashMap<String, Zombie> npcs;
    private Scanner scanner = new Scanner(System.in);
    private Room finalRoom;

    public World() {
    rooms = new HashMap<>();
    npcs = new HashMap<>();

    // Create the rooms
    Room start = new Room("start", "You are in a dark alleyway. Screams of the zombie's victims can be heard.", false);
    Room groceryStore = new Room("grocery store", "You are inside a grocery store. The shelves are empty, and the lights are out.", false);
    Room hospital = new Room("hospital", "You are inside a hospital. The lights flicker on and off, and dark shadows can be seen on the walls.", false);
    Room abandonedBuilding = new Room("abandoned building", "You are inside an abandoned building. The walls are covered in graffiti, and the scene is unsettling.", true);

    // Add the rooms to the hashmap
    rooms.put(start.getName(), start);
    rooms.put(groceryStore.getName(), groceryStore);
    rooms.put(hospital.getName(), hospital);
    rooms.put(abandonedBuilding.getName(), abandonedBuilding);

    // Create the NPCs
    Zombie zombie1 = new Zombie("zombie", 10, start, "A mindless undead creature. Its eyes are clouded over, and it's covered in gore and brains.");
    Zombie zombie2 = new Zombie("zombie", 15, groceryStore, "Another undead creature. Its clothes are torn and its limbs are distorted.");

    // Add the NPCs to the hashmap
    npcs.put(zombie1.getName(), zombie1);
    npcs.put(zombie2.getName(), zombie2);

    // Add the NPCs to the rooms
    start.addZombie(zombie1);
    groceryStore.addZombie(zombie2);

    // Set the exits for the rooms
    start.setExit("North", groceryStore);
    start.setExit("South", hospital);
    start.setExit("East", abandonedBuilding);

    groceryStore.setExit("South", start);

    hospital.setExit("North", start);

    abandonedBuilding.setExit("West", start);

    // Select a random final room
    ArrayList<Room> candidateFinalRooms = new ArrayList<>(rooms.values());
    candidateFinalRooms.remove(start);
    finalRoom = candidateFinalRooms.get((int) (Math.random() * candidateFinalRooms.size()));
}

public Room getStartingRoom() {
    return rooms.get("start");
}

public boolean isValidDirection(String direction) {
    return direction.equals("North") || direction.equals("South") || direction.equals("East") || direction.equals("West");
}

public void describeLocation(String locationName) {
    Room currentRoom = rooms.get(locationName);

    System.out.println(currentRoom.getDescription());

    if (!currentRoom.getZombies().isEmpty()) {
        System.out.println("There are zombies in this room!");
    }

    HashMap<String, Room> exits = currentRoom.getExits();
        System.out.print("Available Exits: ");
        for (String exit : exits.keySet()) {
            System.out.print(exit + " ");
        }
        System.out.println();
}

public Room getFinalRoom() {
        return finalRoom;
    }

    private String getPlayerInput() {
    System.out.print("> ");
    String input = scanner.nextLine();
    return input.trim().toLowerCase();
}


    public void update(Player player) {
    // Check if the player is in the final room
    if (player.getCurrentRoom().equals(finalRoom)) {
        if (player.getHealth() > 0) {
            System.out.println("Congratulations! You have reached the final room and won the game.");
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
            System.exit(0);
        } 
        
        else {
            System.out.println("You have reached the final room, but you are not healthy enough to win the game." + "\nGAME OVER");
            System.exit(0);
        }
    }

    // Check if there are zombies in the current room
    ArrayList<Zombie> zombies = player.getCurrentRoom().getZombies();
    if (!zombies.isEmpty()) {
        for (Zombie zombie : zombies) {
            // Attack the player
            zombie.attackPlayer(player);
        }
        // Check if the player has died
        if (!player.isAlive()) {
            System.out.println("You have died. Game over.");
            System.exit(0);
        }
    }

    // Print the room description and available exits
    describeLocation(player.getCurrentRoom().getName());

    // Get user input
    String input = getPlayerInput();

    // Handle player movement
    if (isValidDirection(input)) {
        Room nextRoom = player.getCurrentRoom().getExit(input);
        if (nextRoom != null) {
            player.setCurrentRoom(nextRoom);
        } else {
            System.out.println("You cannot go that way.");
        }
    } else {
        System.out.println("Invalid direction.");
    }
}
}