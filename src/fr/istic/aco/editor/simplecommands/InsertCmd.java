package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.Scanner;
import java.util.logging.Logger;

public class InsertCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;



    public InsertCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
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
