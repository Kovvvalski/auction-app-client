package by.kovalski.auctionappclient.entity.dto;

import by.kovalski.auctionappclient.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String email;

    private String username;

    private String password;

    private Double money;

    private UserRole role;
}
