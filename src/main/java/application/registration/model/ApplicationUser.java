package application.registration.model;

import lombok.Data;

@Data
public class ApplicationUser {
    private Integer id;
    private String username;
    private String email;
    private String password;
}
