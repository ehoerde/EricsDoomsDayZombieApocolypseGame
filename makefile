default: Game.java Player.java Room.java World.java Zombie.java Item.java
	javac Game.java Player.java Room.java World.java Zombie.java Item.java

run: Game.class Player.class Room.class World.class Zombie.class Item.class
	java Game

clean:
	rm -f *.class