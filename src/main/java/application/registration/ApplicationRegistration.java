package application.registration;

import application.registration.controller.ApplicationUserController;
import middleware.Autumn;

/**
 * Main class of middleware,
 */

public class ApplicationRegistration {
    public static void main (String[] args){
    	//Instance of the class
        ApplicationUserController applicationUserController = new ApplicationUserController();
    	//Instance of the middleware
        Autumn server = new Autumn();
    	//Add method annotations and save in hashmaps
        server.addMethods(applicationUserController);
    	//Start middleware in parameter port
        server.start(7080);
    }
}
