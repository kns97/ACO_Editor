package fr.istic.aco.editor.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {

    public void eventloop(){
        System.out.println("Please enter your command below: (send 'q' to exit)\n ");
        Scanner input = new Scanner(System.in);
        while (true) {
            String line = input.nextLine();
            if ("q".equalsIgnoreCase(line)) {
                break;
            }
            else{
                decode(line);
            }

        }

    }

    public void decode(String command){

    }
}
