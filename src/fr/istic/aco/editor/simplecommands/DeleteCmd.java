package fr.istic.aco.editor.simplecommands;

import fr.istic.aco.editor.Engine;

import java.util.logging.Logger;
/**
 * Delete command class
 */
public class DeleteCmd implements Command{
	private final GreetingsInvoker receiver;
	private Engine engine;
	/**
	 * Constructor
	 * @param receiver Receiver obj
	 * @param e engine obj to execute the command on
	 */
	public DeleteCmd(GreetingsInvoker receiver,Engine e) {
		this.receiver = receiver;
	    this.engine = e;
	}

	/**
	 * Execute the command
	 */
	@Override
	public void execute() {
		try{
			this.engine.delete();
		}catch(Exception e){
			Logger.getGlobal().info(e.toString());
		}
	}
}
