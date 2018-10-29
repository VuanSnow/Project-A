package com.myproject.projecta.domain;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignupForm {

    @NotEmpty
    @Size(min = 5, max = 13)
    String username = "";

    @NotEmpty
    @Size(min = 3, max = 12)
    String firstname = "";

    @NotEmpty
    @Size(min = 8, max = 20)
    String password = "";

    //DEFAULT ROLE FOR ALL NEW USERS = "USER"
    @NotEmpty
    String role = "USER";

    @NotEmpty
    String email = "";

    @Override
    public String toString() {
        return username + " : " + firstname + " : " + email + " : " + password + " : " + role;
    }
}
