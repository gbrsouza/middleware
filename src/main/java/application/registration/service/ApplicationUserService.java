package application.registration.service;

import application.registration.model.ApplicationUser;
import application.registration.repository.SQLiteJDBCDriverConnection;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

@Slf4j
public class ApplicationUserService {
    private final SQLiteJDBCDriverConnection applicationUserRepository;

    public ApplicationUserService() {
        this.applicationUserRepository = new SQLiteJDBCDriverConnection();
    }

    public void insertUser(ApplicationUser applicationUser){
        try {
            applicationUserRepository.insertApplicationUser(applicationUser);
        } catch (SQLException e) {
            log.error("Error to insert a new user");
        }
    }

    public ApplicationUser getUserById (Integer id) {
        try {
            return applicationUserRepository.getUserByID(id);
        } catch (SQLException e) {
            log.error("Error to get User with id {}", id);
            return null;
        }
    }

    public ArrayList<ApplicationUser> listAllUsers() {
        try{
            return applicationUserRepository.listAllUsers();
        } catch (SQLException e) {
            log.error("Error to list user");
            return null;
        }
    }


}
