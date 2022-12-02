package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;

public class DeleteCmd implements Command{
	private final GreetingsInvoker receiver;
	private Engine engine;

	public DeleteCmd(GreetingsInvoker receiver,Engine e) {
		this.receiver = receiver;
	    this.engine = e;
	}
	@Override
	public void execute() {
		try{
			this.engine.delete();
		}catch(Exception e){
			Logger.getGlobal().info(e.toString());
		}
	}
}
