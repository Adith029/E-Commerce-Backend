package com.ecommercebackend.onlineshoping_backend.api.Model;




import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegistrationBody {
    @NotNull
  @NotBlank
  @Size(min=3, max=255)
  private String userName;
  /** The email. */
  @Email
  @NotNull
  @NotBlank
  private String email;
  /** The password. */
  @NotNull
  @NotBlank
  @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
  @Size(min=6, max=32)
  private String password;
  /** The first name. */
  @NotNull
  @NotBlank
  private String firstName;
  /** The last name. */
  @NotNull
  @NotBlank
  private String lastName;

    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    
}
