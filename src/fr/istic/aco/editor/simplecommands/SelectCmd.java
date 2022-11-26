package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.Scanner;

public class SelectCmd implements Command{
    private final GreetingsInvoker receiver;
    private Engine engine;

    public SelectCmd(GreetingsInvoker receiver,Engine e) {
        this.receiver = receiver;
        this.engine = e;
    }
    @Override
    public void execute() {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter start of the selection: ");
        int start= sc.nextInt();
        System.out.print("\nEnter end of the selection: ");
        int stop= sc.nextInt();

        this.engine.setSelection(start,stop);
    }
}
