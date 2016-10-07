/**
 * Pickable object class
 * Currently it does nothing more than an Entity, but we still need to differentiate it.
 */
public class PickableObject extends Entity{

	/**
	 * Create a new  non playable character
	 * Refer to Entity's documentation on this method, it's the same
	 */
	public PickableObject(String name, String title, String description, Type type) {
		super(name, title, description, type);
	}

}
