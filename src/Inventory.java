import java.util.HashMap;
import java.util.Set;


public class Inventory {
	private HashMap<String, Entity> bag;
	
	public Inventory(){
		bag = new HashMap<String, Entity>();
	}
	
	public boolean addItem(Entity item){
		if(item.isItem()){
			bag.put(item.getName().toUpperCase(),item);
			return true;
		}else{
			return false;
		}
	}
	
	public Entity getItem(String name){
		return bag.get(name.toUpperCase());
	}
	
	public void removeItem(String name){
		bag.remove(name.toUpperCase());
	}

	public Set<String> getBag(){
		return bag.keySet();
	}
}
