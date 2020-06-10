package pl.edu.pjwstk.poj.dto;

import lombok.Data;
import pl.edu.pjwstk.poj.entity.Role;
import pl.edu.pjwstk.poj.validator.FieldMatch;
import pl.edu.pjwstk.poj.validator.ValidEmail;

import javax.validation.constraints.AssertTrue;
import java.util.Collection;


@Data
@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})
public class UserDto {

    private String firstName;

    private String lastName;

    @ValidEmail
    private String email;

    @ValidEmail
    private String confirmEmail;

    private String password;

    private String confirmPassword;

    @AssertTrue
    private Boolean terms;

    private Collection<Role> roles;


}
