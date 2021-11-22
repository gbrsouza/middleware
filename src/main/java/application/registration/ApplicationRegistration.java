package application.registration;

import application.registration.controller.ApplicationUserController;
import middleware.Autumn;

public class ApplicationRegistration {
    public static void main (String[] args){
        ApplicationUserController applicationUserController = new ApplicationUserController();
        Autumn server = new Autumn();
        server.addMethods(applicationUserController);
        server.start(7080);
    }
}
