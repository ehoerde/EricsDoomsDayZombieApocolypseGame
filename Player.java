/*
    Eric Hoerdemann
    CPSC 1060: RPG 
    5/04/2023
*/

import java.util.ArrayList;

public class Player {
    private String name;
    private int health;
    private int maxHealth;
    private int attackDamage;
    private ArrayList<Item> inventory;
    private Room currentRoom;

    public Player(String name, int maxHealth, int attackDamage) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackDamage = attackDamage;
        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addItemToInventory(Item item) {
        inventory.add(item);
    }

    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }

    public void takeDamage(int damage) {
        health = Math.max(health - damage, 0);
        if (health == 0) {
            System.out.println("You have died. Game over.");
            System.exit(0);
        }
    }

    public void heal(int amount) {
        health = Math.min(health + amount, maxHealth);
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    public boolean isAlive() {
        return health > 0;
    }
}