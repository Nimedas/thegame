import java.util.Set;

public class Player extends Entity{
	Inventory bag;
	
	public Player(String name, String title, String description, Type type) {
		super(name, title, description, type);
		bag = new Inventory();
	}
	
	public Entity getItem(String name){
		return bag.getItem(name);
	}
	
	public boolean  addItem(Entity item){
		return bag.addItem(item);
	}
	
	public void removeItem(String name){
		bag.removeItem(name);
	}
	
	public Inventory getBag(){
		return bag;
	}
	
	public Set<String> getKeys(){
		return bag.getBag();
	}
	
	public boolean giveItem(Player otherPlayer, Entity item){
		boolean success = otherPlayer.addItem(item);
		if(success){
			removeItem(item.getName());
		}
		return success;
	}
}
