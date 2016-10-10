/**
 * Abstract class used to create placeable entities in a room
 *	Contains basic methods used to interact with any type of entity
 */
public abstract class Entity {
	private String name;
	private String title;
	private String description;
	private Type type;
	
	/**
	 * Enum used to identify the Entity's type (mostly object or character)
	 */
	public enum Type {
		OBJECT,
		NEUTRAL,
		FRIENDLY,
		AGGRESSIVE,
		PLAYER
	}
	
	/**
	 * Create an entity
	 * @param name The entity's name
	 * @param title Its title, optional but then must be an empty string
	 * @param description Its description 
	 * @param type Its type
	 */
	public Entity(String name, String title, String description, Type type){
		setName(name);
		setTitle(title);
		setDescription(description);
		setType(type);
	}
	
	/**
	 * Getter
	 * @return The name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter
	 * @param name The name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter
	 * @param title The title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Getter
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Setter
	 * @param description The description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Getter
	 * @return The type
	 */
	public Type getType() {
		return type;
	}
	
	/**
	 * Setter
	 * @param type The type
	 */
	public void setType(Type type) {
		this.type = type;
	}
	
	/**
	 * @return A string corresponding to the entity, without its description
	 */
	public String toString(){
		return (this.getType() == Type.OBJECT ? "a " : this.getType() + " | ") + this.getName() + (this.getTitle() != null && !this.getTitle().isEmpty() ? " | "+this.getTitle() : "");
	}
	
	/**
	 * @return Return the previous string but add its description
	 */
	public String getLongString(){
		return this.toString() + "\n" + this.getDescription();
	}
	
	/**
	 * @return Wheter the entity is a character or not
	 */
	public boolean isCharacter(){
		boolean isCharacter = type == Type.AGGRESSIVE || type == Type.FRIENDLY || type == Type.NEUTRAL || type == Type.PLAYER;
		return isCharacter;
	}
	
	/**
	 * @return Wheter the entity is an item or not
	 */
	public boolean isItem(){
		boolean isItem = type == Type.OBJECT;
		return isItem;
	}
	
	/**
	 * Test whether the objective is complete or not
	 * For now it simply test if the sandwich has been given to Michel
	 * @param item The given item
	 * @param character The character who receive the item
	 * @return True or false, if true, the game is won
	 */
	public static boolean testObjective(Entity item, Entity character){
		String objectiveItem = "Sandwich".toUpperCase();
		String objectiveCharacter = "Michel".toUpperCase();
		
		String givenItem = item.getName().toUpperCase();
		String givenCharacter = character.getName().toUpperCase();
		
		return (givenItem.equals(objectiveItem) && givenCharacter.equals(objectiveCharacter));
	}
}
