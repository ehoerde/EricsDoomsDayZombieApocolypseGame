/*
    Eric Hoerdemann
    CPSC 1060: RPG 
    5/04/2023
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Room {
    private String name;
    private String description;
    private HashMap<String, Room> exits;
    private ArrayList<Zombie> zombies;
    private Random random = new Random();
    private boolean isFinalRoom;

    public Room(String name, String description, boolean isFinalRoom) {
        this.name = name;
        this.description = description;
        this.exits = new HashMap<String, Room>();
        this.zombies = new ArrayList<Zombie>();
        this.isFinalRoom = isFinalRoom;
    }

    public String getName() {
        return name;
    }

    public boolean isFinalRoom() {
        return isFinalRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setExit(String direction, Room room) {
        exits.put(direction, room);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public void addZombie(Zombie zombie) {
        zombies.add(zombie);
    }

    public void removeZombie(Zombie zombie) {
        zombies.remove(zombie);
    }

    public ArrayList<Zombie> getZombies() {
        return zombies;
    }

    public HashMap<String, Room> getExits() {
    return exits;
}

    public void attackPlayer(Player player) {
        int damage = random.nextInt(20) + 1; // inflicts a random amount of damage between 1 and 20 points
        player.takeDamage(damage);
        System.out.printf("A zombie has attacked you and inflicted %d damage points!%n", damage);
        if (player.getHealth() <= 0) {
            System.out.println("You have died! Game over.");
            System.exit(0);
        }
    }
}