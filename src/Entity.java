
public abstract class Entity {
	private String name;
	private String title;
	private String description;
	private Type type;
	
	public enum Type {
		OBJECT,
		NEUTRAL,
		FRIENDLY,
		AGGRESSIVE,
		PLAYER
	}
	
	public Entity(String name, String title, String description, Type type){
		setName(name);
		setTitle(title);
		setDescription(description);
		setType(type);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public String toString(){
		return (this.getType() == Type.OBJECT ? "a " : this.getType() + " | ") + this.getName() + (this.getTitle() != null ? " | "+this.getTitle() : "");
	}
	
	public String getLongString(){
		return this.toString() + "\n" + this.getDescription();
	}
	
	public boolean isCharacter(){
		boolean isCharacter = type == Type.AGGRESSIVE || type == Type.FRIENDLY || type == Type.NEUTRAL || type == Type.PLAYER;
		return isCharacter;
	}
	
	public boolean isItem(){
		boolean isItem = type == Type.OBJECT;
		return isItem;
	}
	
	public static boolean testObjective(Entity item, Entity character){
		return (item.getName().toUpperCase().equals(new String("sandwich").toUpperCase()) && character.getName().toUpperCase().equals(new String("Michel_Dubois").toUpperCase()));
	}
}
