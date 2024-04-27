package me.deamonet.nar.transmit;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistered {
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}
