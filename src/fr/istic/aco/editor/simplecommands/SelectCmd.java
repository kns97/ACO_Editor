package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.Scanner;
import java.util.logging.Logger;
/**
 * Select command class
 */
public class SelectCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    /**
     * Constructor
     * @param receiver receiver
     * @param e engine
     */
    public SelectCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }

    /**
     * Execute the program
     */
    @Override
    public void execute() {

        Scanner sc= new Scanner(System.in);
        System.out.print("Enter start of the selection: ");
        int start= sc.nextInt();
        System.out.print("Enter end of the selection: ");
        int stop= sc.nextInt();
        try{
            this.engine.setSelection(start,stop);
        }catch(Exception e){
            Logger.getGlobal().info(e.toString());
        }

    }
}
