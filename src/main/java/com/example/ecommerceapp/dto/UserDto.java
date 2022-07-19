package com.example.ecommerceapp.dto;

import com.example.ecommerceapp.model.Admin;
import com.example.ecommerceapp.model.Customer;
import com.example.ecommerceapp.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "The username cannot be blank.")
    @Size(min = 3, max = 30, message = "The username should have a length between 3 and " +
            "30")
    private String username;

    @NotBlank(message = "The password cannot be blank.")
    @Size(min = 3, max = 100, message = "The password should have a length between 3 " +
            "and 100.")
    private String password;

    @NotNull(message = "The user type cannot be null.")
    private UserType userType;

    public enum UserType {
        CUSTOMER {
            @Override
            public User buildUser(UserDto userDTO) {
                return new Customer(userDTO.getUsername(), userDTO.getPassword());
            }

        },
        ADMIN {
            @Override
            public User buildUser(UserDto userDTO) {
                return new Admin(userDTO.getUsername(), userDTO.getPassword());
            }
        };

        /**
         * @param userDTO is the userDTO from which the new user is built.
         * @return the user built on based on the given userDTO, with the corresponding subtype
         * of the User class.
         */
        public abstract User buildUser(UserDto userDTO);
    }
}
