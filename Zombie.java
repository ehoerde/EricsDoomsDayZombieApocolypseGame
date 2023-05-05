/*
    Eric Hoerdemann
    CPSC 1060: RPG 
    5/04/2023
*/

public class Zombie {
    private String name;
    private String description;
    private int health;
    private int attackPower;
    private Room currentRoom;

    public Zombie(String name, int health, Room currentRoom, String description) {
        this.name = name;
        this.health = health;
        this.currentRoom = currentRoom;
        this.description = description;
        this.attackPower = 5;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void attackPlayer(Player player) {
        int damage = this.attackPower;
        System.out.println("The " + this.name + " attacks you for " + damage + " damage!");
        player.takeDamage(damage);
    }
}