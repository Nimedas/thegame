import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 */
public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private HashMap<String, Entity> entites;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
        this.entites = new HashMap<String, Entity>();
    }
    
    /**
     * Return the wanted direction from the hashmap
     * @param direction the direction
     * @return the direction
     */
    public Room getExit(String direction){
    	return this.exits.get(direction);
    }

    /**
     * Put a new room in the hashmap, if the parameter is valid
     * @param direction The direction to the room
     * @param neighbor The room
     */
    public void setExit(String direction, Room neighbor){
    	if(neighbor != null){
    		this.exits.put(direction, neighbor);
    	}else{
    		System.out.println("Sortie incorrecte.");
    	}
    }
    
    /**
     * Return a string containing the available room next to the current
     * @return the string
     */
    public String getExitString(){
    	String res = "Exits: ";
    	for(String direction : this.exits.keySet()){
    		res += direction + " ";
    	}
    	return res;
    }
    
    /**
     * Return whether the room has a wanted exit or not
     * @param direction The wanted exit
     * @return True or false
     */
    public boolean hasExit(String direction){
    	return this.exits.containsKey(direction);
    }
    
    /**
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }
    
    public String getLongDescription(){
    	return description + "\n" + this.getExitString() + "\n";
    }

    public String getEntitiesString(){
    	String objects = "In the room, you find:\n";
    	String characters = "You come into contact with:\n";
    	boolean hasObjects = false;
    	boolean hasCharacters = false;
    	
    	for(String name : entites.keySet()){
    		Entity entite = getEntity(name);
    		if(entite.isItem()){
    			objects += entite.toString() + "\n";
    			hasObjects = true;
    		}else{
    			characters  += entite.toString() + "\n";
    			hasCharacters = true;
    		}
    	}
    	
    	String res = "";
    	
    	if(hasCharacters){
    		res += characters;
    	}
    	if(hasObjects){
    		res += (hasCharacters ? "\n" : "" ) + objects;
    	}
    	
    	return res;
    }
    
    public void addEntity(Entity entite){
    	entites.put(entite.getName().toUpperCase(),entite);
    }
    
    public Entity getEntity(String name){
    	return entites.get(name.toUpperCase());
    }
    
    public void removeEntity(String name){
    	entites.remove(name.toUpperCase());
    }
}
