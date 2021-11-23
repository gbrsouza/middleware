package application.registration.controller;

import application.registration.model.ApplicationUser;
import application.registration.service.ApplicationUserService;
import middleware.annotations.Get;
import middleware.annotations.Post;
import middleware.annotations.RequestMap;
import org.json.JSONObject;

import java.util.ArrayList;

@RequestMap(router = "/user")
public class ApplicationUserController {

    private final ApplicationUserService applicationUserService;

    public ApplicationUserController() {
        this.applicationUserService = new ApplicationUserService();
    }

    @Post(router = "/add")
    public JSONObject insertNewUser(JSONObject jsonObject){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(jsonObject.getString("username"));
        applicationUser.setEmail(jsonObject.getString("email"));
        applicationUser.setPassword(jsonObject.getString("password"));
        applicationUserService.insertUser(applicationUser);

        JSONObject result = new JSONObject();
        result.put("id", applicationUser.getId());
        return result;
    }

    @Get(router = "/list/id")
    public JSONObject getUser(JSONObject jsonObject){
        Integer id = jsonObject.getInt("id");
        ApplicationUser applicationUser = applicationUserService.getUserById(id);
        JSONObject result = new JSONObject();

        result.put("id", applicationUser.getId());
        result.put("username", applicationUser.getUsername());
        result.put("email", applicationUser.getEmail());
        result.put("password", applicationUser.getPassword());

        return result;
    }

    @Get(router = "/list")
    public JSONObject getAllUsers(JSONObject jsonObject){
        ArrayList<ApplicationUser> users = applicationUserService.listAllUsers();
        JSONObject result = new JSONObject();

        result.put("users", users);

        return result;
    }


}
