import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class is part of the "World of Adventure" application. 
 * 
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 */
public class Parser {
    private CommandWords commands;  // holds all valid command words
    private Scanner reader;         // source of command input
    private List<String> arguments; // arguments of the command

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser()  {
        commands = new CommandWords();
        reader = new Scanner(System.in);
        arguments = new ArrayList<String>();
    }

    /**
     * @return The next command from the user with every arguments
     */
    public Command getCommand()  {
        String inputLine;   // will hold the full input line
        String word1 = null;
        arguments.clear();
        
        System.out.print("> ");     // print prompt

        inputLine = reader.nextLine();

        // Find every words
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();      // get first word
            while(tokenizer.hasNext()) { //get arguments
            	arguments.add(tokenizer.next());
            }
        }
        tokenizer.close();
        // Now check whether this word is known. If so, create a command
        // with it. If not, create an UNNOWN command.
        if(commands.isCommand(word1)) {
            return new Command(commands.getCommandWord(word1), arguments);
        }
        else {
            return new Command(CommandWords.CommandWord.UNKNOWN, arguments); 
        }
    }
    
    /**
     * @return The parser's arguments
     */
    public List<String> getArguments(){
    	return arguments;
    }
}
