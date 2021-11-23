package application;

import middleware.Autumn;

/**
 * Main class of middleware,
 */

public class Main {
    public static void main (String[] args){
    	//Instance of the class
        Calculator calc = new Calculator();
    	//Instance of the middleware
        Autumn server = new Autumn();
    	//Add method annotations and save in hashmaps
        server.addMethods(calc);
    	//Start middleware in parameter port
        server.start(7080);
    }
}
