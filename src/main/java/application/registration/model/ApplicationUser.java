package application.registration.model;

import lombok.Data;

/**
 *Class that will be used when manipulating json objects
 */

// Generates getters for all fields, a useful toString method, and hashCode and equals
// implementations that check all non-transient fields.
// Will also generate setters for all non-final fields, as well as a constructor.
@Data
public class ApplicationUser {
    private Integer id;
    private String username;
    private String email;
    private String password;
}
