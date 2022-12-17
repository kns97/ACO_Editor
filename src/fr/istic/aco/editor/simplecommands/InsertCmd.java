package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Insert Command class
 */
public class InsertCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;


    /**
     * Constructor
     * @param receiver Receiver obj
     * @param e engine obj to execute the command on
     */
    public InsertCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }

    /**
     * Asks for user input and then execute the command to insert the text
     */
    @Override
    public void execute() {
        Scanner sc= new Scanner(System.in);
        //yes
        System.out.print("Enter the text to insert: ");
        String string= sc.nextLine();
        try{
            this.engine.insert(string);
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }


    }
}
