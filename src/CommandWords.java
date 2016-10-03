/**
 * This class is part of the "World of Advenrture" application. 
 *
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 */

public class CommandWords {

    public enum CommandWord {
    	GO("Move your character. Enter 'go <keyword>'."),
    	QUIT("Quit the game."),
    	HELP("Print this text."),
    	LOOK("Print the current room's informations."),
    	INSPECT("Inspect an entity (character / object). Enter 'inspect <name of the entity>'."),
    	PICKUP("Pickup an object in the current room. Enter 'pickup <name of the object>'"),
    	BAG("Print your current inventory."),
    	DROP("Drop from your bag into the current room. Enter 'drop <name of the object>'"),
    	GIVE("Give to a character an item from your inventory. Enter 'give <name of the character> <name of the object>'"),
    	UNKNOWN("If the user-entered string isn't a known command.");
    	
    	private String description;
    	
    	CommandWord(String description){
    		this.description = description;
    	}
    	
    	public String getDescription(){
    		return this.description;
    	}
    }
    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()   {
    	// nothing
    }

    /**
     * Return a command based on the parameter (to upper case because all commands are written so in the enum)
     * @param command The command to look for
     * @return The command, if it exists
     */
    public CommandWord getCommandWord(String command){
    	try{
    		return CommandWord.valueOf(command.toUpperCase());
    	}catch(Exception e){
    		return CommandWord.UNKNOWN;
    	}
    	
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)  {
    	try{
    		CommandWord.valueOf(aString.toUpperCase());
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }
}
