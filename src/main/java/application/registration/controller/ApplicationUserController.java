package application.registration.controller;

import application.registration.model.ApplicationUser;
import application.registration.service.ApplicationUserService;
import middleware.annotations.Get;
import middleware.annotations.Post;
import middleware.annotations.RequestMap;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 *Class ApplicationUserController, implemented by the client using the annotations
 *implemented by our middleware. Note that the return of all methods
 *is a JSONObject.
 */

@RequestMap(router = "/user")
public class ApplicationUserController {

	//Instance of Service
    private final ApplicationUserService applicationUserService;

    //Constructor
    public ApplicationUserController() {
        this.applicationUserService = new ApplicationUserService();
    }
    
	//Post method, the attribute "router" is what sets the endpoint route 
    @Post(router = "/add")
    public JSONObject insertNewUser(JSONObject jsonObject){
    	//Instance of model
        ApplicationUser applicationUser = new ApplicationUser();
		//Get the variables from JSON
        applicationUser.setUsername(jsonObject.getString("username"));
        applicationUser.setEmail(jsonObject.getString("email"));
        applicationUser.setPassword(jsonObject.getString("password"));
        
        //Call service method
        applicationUserService.insertUser(applicationUser);

		//Build the return JSON
        JSONObject result = new JSONObject();
        result.put("id", applicationUser.getId());
        return result;
    }
    
	//Get method, the attribute "router" is what sets the endpoint route 
    @Get(router = "/list/id")
    public JSONObject getUser(JSONObject jsonObject){
		//Get the variables from JSON
        Integer id = jsonObject.getInt("id");
        //Call service method
        ApplicationUser applicationUser = applicationUserService.getUserById(id);
        
		//Build the return JSON
        JSONObject result = new JSONObject();

        result.put("id", applicationUser.getId());
        result.put("username", applicationUser.getUsername());
        result.put("email", applicationUser.getEmail());
        result.put("password", applicationUser.getPassword());

        return result;
    }
    
	//Get method, the attribute "router" is what sets the endpoint route 
    @Get(router = "/list")
    public JSONObject getAllUsers(JSONObject jsonObject){
    	
        //Call service method
        ArrayList<ApplicationUser> users = applicationUserService.listAllUsers();
        
		//Build the return JSON
        JSONObject result = new JSONObject();

        result.put("users", users);

        return result;
    }


}
