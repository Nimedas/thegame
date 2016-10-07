import java.util.Set;

/**
 * Player class, extends entity but adds an inventory
 */
public class Player extends Entity{
	Inventory bag;
	
	/**
	 * Create a new player and initialize the inventory
	 * Refer to Entity's documentation on this method, it's the same
	 */
	public Player(String name, String title, String description, Type type) {
		super(name, title, description, type);
		bag = new Inventory();
	}
	
	/**
	 * Return an item from the inventory
	 * @param name The item name
	 * @return The Entity
	 */
	public Entity getItem(String name){
		return bag.getItem(name);
	}
	
	/**
	 * Add an item to the inventory (The key is the item's name upper cased)
	 * @param item The item to add
	 * @return False if the entity is not an item
	 */
	public boolean addItem(Entity item){
		return bag.addItem(item);
	}
	
	/**
	 * Remove an item from the inventory
	 * @param name The item to remove
	 */
	public void removeItem(String name){
		bag.removeItem(name);
	}
	
	/**
	 * @return The player's inventory
	 */
	public Inventory getBag(){
		return bag;
	}
	
	/**
	 * @return A Set containing each item's name (the keys), can then be looped on to retrieve each Entity
	 */
	public Set<String> getKeys(){
		return bag.getBag();
	}
	
	/**
	 * Give an item to another player
	 * @param otherPlayer The player who will receive the item
	 * @param item The item that'll be given
	 * @return True if successful
	 */
	public boolean giveItem(Player otherPlayer, Entity item){
		boolean success = otherPlayer.addItem(item);
		if(success){
			removeItem(item.getName());
		}
		
		return success;
	}
}
