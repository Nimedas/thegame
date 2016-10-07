import java.util.HashMap;
import java.util.Set;

/**
 * Inventory class.
 * Used to create an inventory for a character
 * Contains methods to interact with this inventory
 */
public class Inventory {
	private HashMap<String, Entity> bag;
	
	/**
	 * Create a new, empty inventory
	 */
	public Inventory(){
		bag = new HashMap<String, Entity>();
	}
	
	/**
	 * Add an item to the inventory (The key is the item's name upper cased)
	 * @param item The item to add
	 * @return False if the entity is not an item
	 */
	public boolean addItem(Entity item){
		if(item.isItem()){
			bag.put(item.getName().toUpperCase(),item);
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * Return the wanted item
	 * @param name The item's name
	 * @return The item
	 */
	public Entity getItem(String name){
		return bag.get(name.toUpperCase());
	}
	
	/**
	 * Remove an item from the inventory
	 * @param name The item to remove
	 */
	public void removeItem(String name){
		bag.remove(name.toUpperCase());
	}

	/**
	 * @return A Set containing each item's name (the keys), can then be looped on to retrieve each Entity
	 */
	public Set<String> getBag(){
		return bag.keySet();
	}
}
