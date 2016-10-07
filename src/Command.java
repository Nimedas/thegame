import java.util.List;

/**
 * This class is part of the "World of Adventure" application. 
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two strings: a command word and a second
 * word (for example, if the command was "take map", then the two strings
 * obviously are "take" and "map").
 * 
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word, then the second word is <null>.
 * 
 */

public class Command {
    private CommandWords.CommandWord commandWord;
    private List<String> arguments;

    /**
     * Create a command object.
     * @param firstWord The first word of the command. UNKNOWN if the command
     *                  was not recognised.
     * @param arguments The argument list
     */
    public Command(CommandWords.CommandWord firstWord, List<String> arguments) {
        commandWord = firstWord;
        this.arguments = arguments;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is "UNKNOWN".
     * @return The command word.
     */
    public CommandWords.CommandWord getCommandWord()  {
        return commandWord;
    }

    /**
     * @return The argument's list
     */
    public List<String> getArguments() {
        return arguments;
    }

    /**
     * @return true if this command was not understood.
     */
    public boolean isUnknown()   {
        return commandWord == CommandWords.CommandWord.UNKNOWN;
    }

    /**
     * @return true if the command has at least "howMany" arguments.
     */
    public boolean hasXArguments(int howMany)   {
        return (arguments.size() >= howMany);
    }
}

