package com.easyGo.easyGo.payload.response;

import com.easyGo.easyGo.entities.enums.Gender;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private Gender gender;
    private String pictureUrl;
}
