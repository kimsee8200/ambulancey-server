package org.example.global.auth.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    //private String email;
}
