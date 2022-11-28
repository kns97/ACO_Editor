package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

public class DeleteCmd implements Command{
	private Engine e;
	private GreetingsInvoker invoker;
	
	public DeleteCmd(GreetingsInvoker invoker,Engine e) {
		this.e = e;
		this.invoker = invoker;
	}
	
	
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

}
