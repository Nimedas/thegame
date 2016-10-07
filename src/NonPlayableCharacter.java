
/**
 * Non Playable Character class, extends Player because we still need that bag
 * Currently it does nothing more than a player, but we still need to differentiate it.
 */
public class NonPlayableCharacter extends Player{

	/**
	 * Create a new  non playable character
	 * Refer to Entity's documentation on this method, it's the same
	 */
	public NonPlayableCharacter(String name, String title, String description, Type type) {
		super(name, title, description, type);
	}

}
