package com.example.command;

import com.example.exceptions.CommandCreationException;

public class CommandFactory {

    private static CommandFactory factory = null;

    private CommandFactory() {
    }

    /**
     * Get an instance of the CommandFactory
     * @return The singleton CommandFactory object
     */
    public synchronized static CommandFactory getInstance() {
        if (factory == null) {      // first time

            factory = new CommandFactory();
        }
        return factory;
    }

    /**
     * 
     * @param commandStr Identifier for the exact Command object required
     * @return The specific Command object requested
     * @throws CommandCreationException 
     */
    public synchronized Command createCommand(String commandStr) throws CommandCreationException {

    	Command command = null;
    	String packageName = "com.example.command.";    	
    	
        try {
            commandStr = packageName + commandStr + "Command";

            //...
            Class<?> theClass = Class.forName(commandStr);
            
            //...
            Object theObject = theClass.newInstance();

            command = (Command) theObject;
        } catch (InstantiationException e) {
            throw new CommandCreationException("CommandFactory: " + e);
        } catch (IllegalAccessException e) {
            throw new CommandCreationException("CommandFactory: " + e);
        } catch (ClassNotFoundException e) {
            throw new CommandCreationException("CommandFactory: " + e);
        }

    	//Return the instantiated Command object to the calling code...
    	return command;		// may be null

    }
    
}

