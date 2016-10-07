import java.util.Set;

/**
 *  This class is the main class, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this adventure game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 */

public class Game {
    private Parser parser;
    private Room currentRoom;
    private Player player;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game()  {
        createRooms();
        parser = new Parser();
        player = new Player("Player", "Challenger", "The user", Entity.Type.PLAYER);
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room village, iut, salle_029, cafeteria, salle_120, amphi_a, salle_209, vandb, corridor;
        // create the rooms
        
        village = new Room("You cross the street and get to the Student's village. This place feels too good to be true. You know there's no point in staying anymore.");
        iut = new Room("This is the school where you go everyday to study.");
        salle_029 = new Room("Home sweet home. You are in room 029.");
        cafeteria = new Room("Cafeteria. Seriously ?");
        salle_120 = new Room("As you enter room 120, you hear a noise. Someone's here.");
        amphi_a = new Room("As you enter amphitheater A, your entire body feels numb. You don't want to go there. It reminds you of horrible memories. Ugh.");
        vandb = new Room("Student during the day, drinker at night. This is your one true home. Welcome to V&B.");
        corridor = new Room("You wander in the corridors you know so well. It is useless to stay here, hurry the fuck up.");
        salle_209 = new Room("This was your favourite teacher's favourite room. As you slightly remember Philippe's lessons, you shed a tear.");
        
        // initialise room exits
        village.setExit("amphiA", amphi_a);
        iut.setExit("amphiA", amphi_a);
        iut.setExit("couloir", corridor);
        salle_029.setExit("couloir", corridor);
        cafeteria.setExit("couloir", corridor);
        cafeteria.setExit("vandb", vandb);
        salle_120.setExit("couloir", corridor);
        amphi_a.setExit("village", village);
        amphi_a.setExit("iut", iut);
        vandb.setExit("cafeteria", cafeteria);
        corridor.setExit("iut", iut);
        corridor.setExit("029", salle_029);
        corridor.setExit("cafeteria", cafeteria);
        corridor.setExit("209", salle_209);
        corridor.setExit("120", salle_120);
        salle_209.setExit("couloir", corridor);
        
        Entity dubois = new NonPlayableCharacter("Michel", "", "The way he looks at you makes you understand he is hunngry. Maybe you should go and fetch him some food.", Entity.Type.NEUTRAL);
        Entity bogdaniu = new NonPlayableCharacter("Bogdaniu", "Ballooning man", "He's sleeping. He must be dreaming of ballooning.", Entity.Type.NEUTRAL);
        Entity sandwich = new PickableObject("Sandwich","Rotten","It smells bad but hey, he might like it.", Entity.Type.OBJECT);
        salle_029.addEntity(dubois);
        salle_120.addEntity(bogdaniu);
        cafeteria.addEntity(sandwich);

        currentRoom = iut; 
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()  {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Adventure!");
        System.out.println("World of Adventure is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;
        boolean objectiveComplete = false;
        
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        CommandWords.CommandWord commandWord = command.getCommandWord();
        if (commandWord.equals(CommandWords.CommandWord.HELP)){
            printHelp();
        }else if (commandWord.equals(CommandWords.CommandWord.GO)){
            goRoom(command);
    	}else if (commandWord.equals(CommandWords.CommandWord.QUIT)){
            wantToQuit = quit(command);
		}else if (commandWord.equals(CommandWords.CommandWord.LOOK)){
			printLocationInfo();
		}else if (commandWord.equals(CommandWords.CommandWord.INSPECT)){
			printEntityInfo(command);
		}else if (commandWord.equals(CommandWords.CommandWord.PICKUP)){
			pickupItem(command);
		}else if (commandWord.equals(CommandWords.CommandWord.BAG)){
			printInventory();
		}else if (commandWord.equals(CommandWords.CommandWord.DROP)){
			dropItem(command);
		}else if (commandWord.equals(CommandWords.CommandWord.GIVE)){
			objectiveComplete = giveItem(command);
		}
        if(objectiveComplete){
        	System.out.println("Congratulation, you won THE GAME!");
        }
        return wantToQuit || objectiveComplete;
    }

    // implementations of user commands:

    /**
     * Inspect an entity and print its description
     * @param command The command used to retrieve the parameter
     */
    private void printEntityInfo(Command command) {
        if(!command.hasXArguments(1)) {
            // if there is no second word, what to inspect...
            System.out.println("Inspect what?");
            return;
        }

        String name = command.getArguments().get(0);
        Entity entite = currentRoom.getEntity(name);
        
        if(entite != null){
        	System.out.println(entite.getLongString());
        }else{
        	System.out.println("What you search for is unknown");
        }
	}

	/**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        String commands = "";
        for(CommandWords.CommandWord commandWord : CommandWords.CommandWord.values()){
        	commands += commandWord != CommandWords.CommandWord.UNKNOWN ? "\t" + commandWord + " - " + commandWord.getDescription() + "\n" : "";
        }
        System.out.println(commands);
    }

    /** 
     * Try to go to one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)  {
        if(!command.hasXArguments(1)) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getArguments().get(0);

        // Try to leave current room.
        Room nextRoom = null;
        if(currentRoom.hasExit(direction)){
        	nextRoom = currentRoom.getExit(direction);
        	currentRoom = nextRoom;
            printLocationInfo();
        }else{
        	System.out.println("There is no door!");
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)   {
        if(command.hasXArguments(1)) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Print current location informations
     * Where the player currently is as well as where he can go
     */
    public void printLocationInfo(){
    	System.out.println(currentRoom.getLongDescription());
    	System.out.println(currentRoom.getEntitiesString());
    }
    
    /**
     * Print the player's inventory
     */
    public void printInventory(){
    	Set<String> inventory = player.getKeys();
    	System.out.println("Your current inventory: ");
    	for(String name : inventory){
    		Entity item = player.getItem(name);
    		System.out.println(item.getName() + (item.getTitle() != null ? " | " + item.getTitle() : ""));
    	}
    }
    
    /**
     * Pickup an item and place it in your inventory
     * Check whether the item is present in the room or not
     * @param command The command used to retrieve the parameter
     */
    public void pickupItem(Command command){
        if(!command.hasXArguments(1)) {
            // if there is no second word, we don't know where to go...
            System.out.println("Pick up what ?");
            return;
        }

        String name = command.getArguments().get(0);
        Entity item = currentRoom.getEntity(name);
        
        if(item != null){
        	if(player.addItem(item)){
        		currentRoom.removeEntity(name);
        		System.out.println("Item picked up.");
        	}else{
        		System.out.println("This is not an object.");
        	}
        }else{
        	System.out.println("What you search for is unknown.");
        }
    }
    
    /**
     * Drop an item in the current room from your inventory
     * @param command The command used to retrieve the parameter
     */
    public void dropItem(Command command){
    	if(!command.hasXArguments(1)) {
            // if there is no second word, we don't know where to go...
            System.out.println("Drop what ?");
            return;
        }

        String name = command.getArguments().get(0);
        Entity item = player.getItem(name);
        if(item != null){
        	currentRoom.addEntity(item);
        	player.removeItem(name);
        	System.out.println("You dropped the item on the floor.");
        }else{
        	System.out.println("You don't carry this item.");
        }
    }
    
    /**
     * Give an item from your inventory to another character
     * @param command The command used to retrieve the parameters (Character and item)
     * @return Whether the objective is complete or not
     */
    public boolean giveItem(Command command){
    	boolean objectiveComplete = false;
    	
    	if(!command.hasXArguments(2)) {
            // if there is no second word, we don't know where to go...
            System.out.println("Give to who what?");
            return false;
        }

    	String characterName = command.getArguments().get(0);
        String itemName = command.getArguments().get(1);
        Entity item = player.getItem(itemName);
        Entity character = currentRoom.getEntity(characterName);
        
        if(character != null && character.isCharacter() ){
        	if(item != null && item.isItem()){
        		Player otherPlayer = (Player) character;
        		boolean success = player.giveItem(otherPlayer, item);
        		if(success){
        			System.out.println("Item "+item.getName()+" given to "+character.getName()+".");
        			objectiveComplete = Entity.testObjective(item, character);
        		}
        	}else{
        		System.out.println("You don't carry this item.");
        	}
        }else{
        	System.out.println("This character isn't in the room.");
        }
        
        return objectiveComplete;
    }
    
    public static void main(String[] args) {
    	    Game jeu = new Game();
    	    jeu.play();
    }
    	    
}
